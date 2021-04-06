package com.coderman.dbmodel.check.utils;

import com.coderman.dbmodel.check.bean.ColumnBean;
import com.coderman.dbmodel.check.bean.TableBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created on 2017-4-7.
 * @author
 * @version: V1.0
 * @Desc: JDBC简单工具类
 */
public class JdbcUtils {
    private  static  final Logger log = LoggerFactory.getLogger(JdbcUtils.class);

    private static Properties properties = new Properties();
    private static Connection connection = null;
    private static String dbName = "";


    private static void init(String configPath){
        //已经初始化过之后不用再初始化
        if(!properties.isEmpty() ){
            return;
        }
        InputStream is;
        try {
            is = new FileInputStream(new File(configPath));
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化db连接
     * @return
     * @throws Exception
     */
    public static Connection initDBConnection(String configPath)throws Exception {

        init(configPath);

        // 1. 准备获取连接的 4 个字符串: user, password, url, jdbcDriver
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url= properties.getProperty("url");
        String jdbcDriver= properties.getProperty("jdbcDriver");

        // 2. 加载驱动: Class.forName(driverClass)
        Class.forName(jdbcDriver);
        // 3.获取数据库连接
        connection = DriverManager.getConnection(url, user, password);
        return  connection;
    }


    /**
     * 执行单条语句
     * @param sql
     * @return
     * @throws Exception
     */
    public static boolean exeOneSql(String sql)throws Exception {
        if(connection == null){
            log.error("connection is null ,please init it....");
            return false;
        }
        //保存当前自动提交模式
        boolean autoCommit = connection.getAutoCommit();
        //关闭自动提交
        connection.setAutoCommit(false);
        try {
            connection.prepareStatement(sql).execute();
            connection.commit();
            connection.setAutoCommit(autoCommit);
            return true;

        }catch (Exception e){
            connection.rollback();
            return false;
        }

    }

    /**
     * 批量执行多条语句
     * @param sqlList
     * @return
     * @throws Exception
     */
    public static boolean exeBatchSql(List<String> sqlList) throws Exception {
        try {
            if(connection == null){
                log.error("connection is null ,please init it....");
                return false;
            }
            //保存当前自动提交模式
            boolean autoCommit=connection.getAutoCommit();
            //关闭自动提交
            connection.setAutoCommit(false);
            Statement preparedStatement = connection.createStatement();

            for (String sql : sqlList){
                preparedStatement.addBatch(sql);
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(autoCommit);
            return true;
        } catch (Exception e) {
            log.error("query error",e);
            connection.rollback();
            return false;
        }
    }

    /**
     * 查询数据库所有字段信息
     * @param querySql
     * @return
     * @throws Exception
     */
    public static List<ColumnBean> exeColumnSearchSql(String querySql) throws Exception {
        try {
            if(connection == null){
                log.error("connection is null ,please init it....");
                return null;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(querySql);
            ResultSet resultSet = preparedStatement.executeQuery();

            //得到结果集(rs)的结构信息，比如字段数、字段名等
            ResultSetMetaData md = resultSet.getMetaData();
            //返回此 ResultSet 对象中的列数

            List<ColumnBean> rowData = new ArrayList<>();

            while (resultSet.next()) {

                ColumnBean columnBean = new ColumnBean();

                String tableName = resultSet.getObject(1).toString();
                String columnName = resultSet.getObject(2).toString();
                String columnDefaultValue = resultSet.getObject(3).toString();
                String dataType = resultSet.getObject(4).toString();
                String extraInfo = resultSet.getObject(5).toString();
                String columnKey = resultSet.getObject(6).toString();

                String isNullable = resultSet.getObject(7).toString();
                String columnType = resultSet.getObject(8).toString();
                String columnComment = resultSet.getObject(9).toString();

                columnBean.setTableName(tableName);
                columnBean.setColumnName(columnName);
                columnBean.setColumnDefaultValue(columnDefaultValue);
                columnBean.setDataType(dataType);
                columnBean.setExtraInfo(extraInfo);
                columnBean.setColumnKey(columnKey);
                columnBean.setIsNullable(isNullable);
                columnBean.setColumnType(columnType);
                columnBean.setColumnComment(columnComment);

            }
            return rowData;
        } catch (Exception e) {
            log.error("query error",e);
            connection.rollback();
            return null;
        }
    }

    /**
     * 查询数据库表信息
     * @param querySql
     * @return
     * @throws Exception
     */
    public static List<TableBean> exeDBSearchSql(String querySql) throws Exception {
        try {
            if(connection == null){
                log.error("connection is null ,please init it....");
                return null;
            }
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(querySql);

            List<TableBean> rowData = new ArrayList<>();

            while (resultSet.next()) {
                TableBean tableBean = new TableBean();
                tableBean.setTableName(resultSet.getObject(1).toString());
                tableBean.setTableComment(resultSet.getObject(2).toString());
                rowData.add(tableBean);
            }
            return rowData;
        } catch (Exception e) {
            log.error("query error",e);
            return null;
        }
    }


    public static  int getCountBySql(String sql){
        Statement preparedStatement = null;
        try {
            if(connection == null){
                log.error("connection is null ,please init it....");
                return -1;
            }
            preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if(resultSet.next()) {
                int count = resultSet.getInt(1);
                return count;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }


    /**
     * 根据sql查询key--value形式的map
     * @param querySql
     * sql eg: select keyColumn,valueColumn from table
     * @return
     */
    public static Map<String,String>  exeQuerySql(String querySql){
        Map<String,String> map = new HashMap<String,String>();
        Statement preparedStatement = null;
        try {
            if(connection == null){
                log.error("connection is null ,please init it....");
                return null;
            }
            preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(querySql);
            while (resultSet.next()) {
                map.put(resultSet.getObject(1)+"",resultSet.getObject(2)+"");
            }

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getDbName(){

        return properties.getProperty("dbName");

    }

}
