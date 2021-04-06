package com.coderman.dbmodel.check.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by on 2018-7-23.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:
 */
public enum  DBCharEnum {

    TEXT_TYPE("text"),
    BLOB_TYPE("blob"),
    MEDIUMTEXT_TYPE("mediumtext"),
    LONGTEXT_TYPE("longtext")
    ;
    private String name;



    DBCharEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Set<String> getCharTypeSet(){
        Set<String> set = new HashSet<>();
        for (DBCharEnum dbCharEnum:DBCharEnum.values()){
            set.add(dbCharEnum.getName());
        }
        return set;
    }
}
