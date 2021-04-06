package com.coderman.dbmodel.check.bean;

/**
 * Created on 2018-7-19.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc: 表字段描述
 */
public class ColumnBean {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 字段名
     */
    private String columnName;
    /**
     * 默认值
     */
    private String columnDefaultValue;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 字段特殊描述值
     */
    private String extraInfo;
    /**
     * 是否是主键（或者其他键）
     */
    private String columnKey;

    /**
     * 是否可以为空,NO,YES
     */
    private String isNullable;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 字段描述
     */
    private String columnComment;


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

    public String getColumnDefaultValue() {
        return columnDefaultValue;
    }

    public void setColumnDefaultValue(String columnDefaultValue) {
        this.columnDefaultValue = columnDefaultValue;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    @Override
    public String toString() {
        return "ColumnBean{" +
                "tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", columnDefaultValue='" + columnDefaultValue + '\'' +
                ", dataType='" + dataType + '\'' +
                ", extraInfo='" + extraInfo + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", isNullable='" + isNullable + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                '}';
    }
}
