package com.coderman.dbmodel.check.enums;

/**
 * Created by  on 2018-7-19.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:
 */
public enum  CheckLevelEnum {
    //错误，需要修改
    ERROR("ERROR"),
    //警告，需要修改
    WARNING("WARNING"),
    //违规，需要修改
    VIOLATION("VIOLATION"),
    //建议，不需要修改
    ADVICE("ADVICE")
    ;

    CheckLevelEnum(String levelName){
        this.levelName = levelName;
    }

    private String levelName;

    public String getLevelName() {
        return levelName;
    }

}
