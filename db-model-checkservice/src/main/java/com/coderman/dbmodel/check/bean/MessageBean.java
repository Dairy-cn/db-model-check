package com.coderman.dbmodel.check.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * Created on 2018-8-9.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:扫描消息实体
 */
public class MessageBean extends BaseRowModel {

    /**
     * 警告级别
     */
    @ExcelProperty(value = "消息级别" ,index = 0)
    private String levelName;
    /**
     * 表名
     */
    @ExcelProperty(value = "表名称" ,index = 1)
    private String tableName;
    /**
     * 字段名
     */
    @ExcelProperty(value = "字段名称" ,index = 2)
    private String columnName;
    /**
     * 错误消息
     */
    @ExcelProperty(value = "警告内容" ,index = 3)
    private String errorMsg;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "levelName='" + levelName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
