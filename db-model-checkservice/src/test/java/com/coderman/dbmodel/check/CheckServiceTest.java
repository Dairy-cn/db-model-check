package com.coderman.dbmodel.check;

import com.alibaba.fastjson.JSON;
import com.coderman.dbmodel.check.bean.ColumnBean;
import com.coderman.dbmodel.check.bean.MessageBean;
import com.coderman.dbmodel.check.bean.TableBean;
import com.coderman.dbmodel.check.mapper.SqlMapper;
import com.coderman.dbmodel.check.service.check.CheckMysqlService;
import com.coderman.dbmodel.check.service.check.CheckTableService;
import com.coderman.dbmodel.check.service.check.MessageToExcelService;
import com.coderman.dbmodel.check.service.daos.QueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Author fanchunshuai
 * @Date 2019/11/25 18
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-dao.xml")
public class CheckServiceTest {

    @Autowired
    private SqlMapper sqlMapper;

    @Autowired
    private CheckMysqlService checkMysqlService;

    @Autowired
    private CheckTableService checkTableService;

    @Autowired
    private MessageToExcelService messageToExcelService;

    @Test
    public void testGetCount() {

    }

    @Test
    public void testGetList() {
        String dbName = "hebei_people_duties2_dev";
        List<TableBean> tableBeanList = sqlMapper.getDBTableBeanList(dbName);
        System.out.println(JSON.toJSONString(tableBeanList));
        List<ColumnBean> columnBeanList = sqlMapper.getColumnBeanList(dbName);
        System.out.println(JSON.toJSONString(columnBeanList));
    }

    @Test
    public void testCheckMysqlService(){
        String dbName = "hebei_people_duties2_dev";
    
        checkMysqlService.checkEvent(dbName);
        /**
         * 触发器表不存在，会报错
         */
        //checkMysqlService.checkProceduerCount("world");
        checkMysqlService.checkTrigger(dbName);
        checkMysqlService.checkViewCount(dbName);
    }

    @Test
    public void testCheckTableService(){
        String dbName = "hebei_people_duties2_dev";
    
        List<MessageBean> list = checkTableService.checkTableInfo(dbName);

        try {
            messageToExcelService.writeToExcel(list,"D:\\result.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(JSON.toJSONString(list));
    }
}
