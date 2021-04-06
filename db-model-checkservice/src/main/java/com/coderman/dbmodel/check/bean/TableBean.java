package com.coderman.dbmodel.check.bean;

/**
 * Created  on 2018-7-19.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:表信息描述
 */
public class TableBean {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableComment;


    /**
     * 使用的存储引擎
     */
    private String engineName;

    /**
     * 字符集
     */
    private String tableCollation;

    /**
     * 表记录数
     */
    private Long tableRows;

    public Long getTableRows() {
        return tableRows;
    }

    public void setTableRows(Long tableRows) {
        this.tableRows = tableRows;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public String getTableCollation() {
        return tableCollation;
    }

    public void setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
    }
}
