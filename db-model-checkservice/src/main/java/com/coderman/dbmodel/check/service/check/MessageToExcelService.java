package com.coderman.dbmodel.check.service.check;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.coderman.dbmodel.check.bean.MessageBean;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by  on 2018-8-13.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:
 */
@Service
public class MessageToExcelService {

    /**
     * 写入Excel
     * @param messageBeanList
     * @param filePath
     * @return
     * @throws java.io.FileNotFoundException
     */
    public boolean writeToExcel(List<MessageBean> messageBeanList, String filePath) throws FileNotFoundException {

        OutputStream out = new FileOutputStream(filePath);
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet1 = new Sheet(1, 0,MessageBean.class);
            writer.write(messageBeanList, sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }




}
