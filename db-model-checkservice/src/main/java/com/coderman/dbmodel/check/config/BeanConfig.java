package com.coderman.dbmodel.check.config;



/**
 * Created by ${fanchunshuai} on 2017-9-15.
 *
 * @version: V1.0
 * @Desc:
 */
public class BeanConfig {

    /**
     * db 初始化模式
     */
    private String dbInitModel;

    /**
     * db 配置路径
     */
    private String dbConfigPath;

    /**
     * 数据库检查模式
     */
    private String dbCheckModel;


    /**
     * 开发库连接配置地址
     */
    private String  devDBConfigPath;

    /**
     * 测试库连接配置地址
     */
    private String testDBConfigPath;

    public String getDevDBConfigPath() {
        return devDBConfigPath;
    }

    public void setDevDBConfigPath(String devDBConfigPath) {
        this.devDBConfigPath = devDBConfigPath;
    }

    public String getTestDBConfigPath() {
        return testDBConfigPath;
    }

    public void setTestDBConfigPath(String testDBConfigPath) {
        this.testDBConfigPath = testDBConfigPath;
    }

    public String getDbCheckModel() {
        return dbCheckModel;
    }

    public void setDbCheckModel(String dbCheckModel) {
        this.dbCheckModel = dbCheckModel;
    }

    public String getDbInitModel() {
        return dbInitModel;
    }

    public void setDbInitModel(String dbInitModel) {
        this.dbInitModel = dbInitModel;
    }

    public String getDbConfigPath() {
        return dbConfigPath;
    }

    public void setDbConfigPath(String dbConfigPath) {
        this.dbConfigPath = dbConfigPath;
    }
}
