package com.coderman.dbmodel.check.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by on 2018-7-23.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:
 */
public class StringUtilX {
    /**
     * 判断是否是数字开头
     * @param str
     * @return  true:是  false:否
     *
     */
    public static boolean isNumericHead(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str.charAt(0)+"");
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否全是小写字母
     * @param str
     * @return  true:全是小写  false:不是小写
     */
    public static boolean isUpCaseChar(String str){
        String newstr = str.toLowerCase();
        if (str.equals(newstr)) {
            return true;
        }else {
            return false;
        }
    }


}
