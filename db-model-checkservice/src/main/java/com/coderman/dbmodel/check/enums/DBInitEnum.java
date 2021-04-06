package com.coderman.dbmodel.check.enums;

/**
 * Created by  on 2018-7-17.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:
 */
public enum DBInitEnum {

    JDBC(0,"JDBC"),
    DAO(1,"DAO"),

    ;

    private int code;
    private String name;
    DBInitEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
