package com.coderman.dbmodel.check.enums;

/**
 * Created on 2018-7-19.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:
 */
public enum  YesNoEnum {

    YES("YES"),

    NO("NO")
    ;

    YesNoEnum(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
