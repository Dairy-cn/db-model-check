package com.coderman.dbmodel.check.service.daos;

import com.coderman.dbmodel.check.bean.ColumnBean;
import com.coderman.dbmodel.check.bean.TableBean;
import com.coderman.dbmodel.check.mapper.SqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by  on 2017-10-18.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:
 */

@Service
public class QueryService {

    @Autowired
    public SqlMapper sqlMapper;


    /**
     * 获取数据库中字段描述元数据
     * @param dbName 数据库名称
     * @return
     */
    public List<ColumnBean> getColumnBeanList(String dbName){
        return sqlMapper.getColumnBeanList(dbName);
    }

    /**
     * 获取数据库表描述元数据
     * @param dbName 数据库名称
     * @return
     */
    public List<TableBean> getDBTableBeanList(String dbName)  {
        return sqlMapper.getDBTableBeanList(dbName);
    }

    /**
     * 获取事件数量
     * @param dbName 数据库名称
     * @return
     */
    public int getEventCount(String dbName)  {
        String eventSql = "SELECT   COUNT(*)  FROM information_schema.`EVENTS` WHERE EVENT_SCHEMA = '" + dbName + "'";
        return sqlMapper.getCount(eventSql);
    }

    /**
     * 获取触发器数量
     * @param dbName
     * @return
     */
    public int getTriggerCount(String dbName) {

        /**
         * 查询触发器的sql
         */
        String triggerSql = "SELECT  COUNT(*)  FROM information_schema.`TRIGGERS` WHERE TRIGGER_SCHEMA = '" + dbName + "'";
        return sqlMapper.getCount(triggerSql);
    }

    /**
     * 获取存储过程数量
     * @param dbName 数据库名称
     * @return
     */
    public int getProcedureCount(String dbName)  {
        /**
         * 查询存储过程的sql
         */
        String procedureSql = "SELECT COUNT(*) FROM mysql.proc WHERE db = '" + dbName + "' AND `type` = 'PROCEDURE'";
        return sqlMapper.getCount(procedureSql);
    }

    public int getViewCount(String dbName) {
        String viewSql = "SELECT COUNT(*)  FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + dbName + "' AND  TABLE_TYPE ='VIEW'";

        return sqlMapper.getCount(viewSql);
    }

}
