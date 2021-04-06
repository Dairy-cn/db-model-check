package com.coderman.dbops.codermandbops.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: GeneratorDBController <br>
 * date: 2020/7/10 12:44 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@RestController
public class GeneratorDBController {

    @GetMapping(value = "/makedb")
    public String generatorDB(){
        try {
            String path = ResourceUtils.getURL("classpath:").getPath()+"sql";
            File file = new File(path);
            File dbAppFile = file.listFiles()[0];
            String appName = dbAppFile.getName().replace("-","_");
            String shardingFilePath = dbAppFile.getAbsolutePath()+"\\user\\"+"dbsharding.txt";
            System.out.println("shardingFilePath = "+shardingFilePath);
            List<String> dataList = FileUtils.readLines(new File(shardingFilePath),"UTF-8");
            System.out.println(JSON.toJSONString(dataList));
            String dbName = appName+"_"+"user";
            System.out.println(dbName);
            String v = "CREATE DATABASE IF NOT EXISTS test_db_char\n" +
                    "    -> DEFAULT CHARACTER SET utf8\n" +
                    "    -> DEFAULT COLLATE utf8_chinese_ci;";

            List<String>  dbNameList = new ArrayList<>();
            for (int i = 0; i < 4;i++){
                StringBuilder builder = new StringBuilder("CREATE DATABASE IF NOT EXISTS ");
                builder.append(dbName+"_"+i);
                dbNameList.add(dbName+"_"+i);
                builder.append(" DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;");
                System.out.println(builder.toString());
            }


            String shardingDBFilePath = dbAppFile.getAbsolutePath()+"\\user";
            File appRootFile = new File(shardingDBFilePath);
            File [] tableFileArr = appRootFile.listFiles();
            Map<String,List<String>> tableColumnMap = new HashMap<>();
            for (File file1 : tableFileArr){
                if(file1.getAbsolutePath().contains("sharding")){
                    continue;
                }
                List<String> fileContent = FileUtils.readLines(file1,"UTF-8");
                tableColumnMap.put(file1.getName(),fileContent);
            }
            tableColumnMap.forEach((k,vs)->{
                for (int m = 0;m<4;m++){
                    for (int i = 0;i < 16;i++){
                        StringBuilder builder = new StringBuilder("create table ");
                        String tableName = dbName + "_" + m + "." + k + "_" +i;
                        builder.append(tableName);
                        builder.append(StringUtils.join(vs,""));
                        System.out.println(builder.toString());
                    }
                }

            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }


}
