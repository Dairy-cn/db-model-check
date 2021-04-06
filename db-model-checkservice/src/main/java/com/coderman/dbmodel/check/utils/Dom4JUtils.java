package com.coderman.dbmodel.check.utils;


import com.coderman.dbmodel.check.Constants;
import com.coderman.dbmodel.check.config.BeanConfig;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by on 2017-9-15.
 * @author fanchunshuai
 * @version: V1.0
 * @Desc:
 */
public class Dom4JUtils {
    /**
     * 读取bean配置
     * @param configPath
     * @return
     * @throws Exception
     */
    public static BeanConfig readBeanConfig(String configPath) throws Exception {
        BeanConfig config  = new BeanConfig();

        Map<String,Object> map = getConfigMap(configPath);
        config.setDbConfigPath(map.get(Constants.DB_CONFIG_PATH).toString());
        config.setDbInitModel(map.get(Constants.DB_INIT_MODEL).toString());
        config.setDbCheckModel(map.get(Constants.DB_CHECK_MODEL).toString());
        config.setDevDBConfigPath(map.get("devDbConfig").toString());
        config.setTestDBConfigPath(map.get("testDbConfig").toString());

        return config;
    }

    private static Map<String,Object> getConfigMap(String configPath) throws DocumentException {
        Map<String,Object> map = new HashMap<String, Object>();

        InputStream is= null;
        try {
            is = new FileInputStream(new File(configPath+"\\db.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SAXReader reader = new SAXReader();
        Document document = reader.read(is);

        //获取根节点元素对象
        Element root = document.getRootElement();

        //配置db 初始化模式
        /**
         * v4:支持dao
         */
        Node dbInitNodel = root.selectSingleNode(Constants.DB_INIT_MODEL);
        map.put(Constants.DB_INIT_MODEL,dbInitNodel.getStringValue());


        /**
         * v5:支持dao
         */
        Node dbConfigPath = root.selectSingleNode(Constants.DB_CONFIG_PATH);
        map.put(Constants.DB_CONFIG_PATH,dbConfigPath.getStringValue());

        /**
         * v5:支持dao
         */
        Node dbCheckModel = root.selectSingleNode(Constants.DB_CHECK_MODEL);
        map.put(Constants.DB_CHECK_MODEL,dbCheckModel.getStringValue());



        Node importClazz = root.selectSingleNode("dbGroup");

        Node testDBConfigNode = importClazz.selectSingleNode("test").selectSingleNode("dbConfigPath");
        map.put("testDbConfig",testDBConfigNode.getStringValue());

        Node devDBConfigNode = importClazz.selectSingleNode("dev").selectSingleNode("dbConfigPath");
        map.put("devDbConfig",devDBConfigNode.getStringValue());


        return map;
    }

}
