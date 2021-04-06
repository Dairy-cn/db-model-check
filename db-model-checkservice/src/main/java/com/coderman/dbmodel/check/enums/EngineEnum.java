package com.coderman.dbmodel.check.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by  on 2018-7-23.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:
 */
public enum  EngineEnum {
    INNODB("innodb"),
    ARCHIVE("archive"),
    CSV("csv"),
    MEMORY("memory"),
    MYISAM("myisam"),
    BLACKHOLE("blackhole"),
    ;
    private String name;
    EngineEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Set<String> getEngineSet(){
        Set<String> set = new HashSet<>();
        for (EngineEnum engineEnum:EngineEnum.values()){
            set.add(engineEnum.getName());
        }
        return set;
    }

}
