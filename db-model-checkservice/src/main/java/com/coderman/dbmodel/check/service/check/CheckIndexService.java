package com.coderman.dbmodel.check.service.check;

import com.coderman.dbmodel.check.Constants;
import com.coderman.dbmodel.check.bean.ColumnBean;
import com.coderman.dbmodel.check.bean.MessageBean;
import com.coderman.dbmodel.check.enums.CheckLevelEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by on 2018-8-17.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:数据库索引检查
 */
@Service
public class CheckIndexService {


    /**
     * 智能索引检查
     * @return
     */
    public List<MessageBean> checkIndex(Map<String,List<ColumnBean>> listMap){
        List<MessageBean> list = new ArrayList<>();

        /**
         * 初始化id集合
         * key:表名
         * value:默认id
         */
        Map<String,String>  initIDMap = new HashMap<>(listMap.size());

        /**
         * 表之间的关系
         * key:关联表表名+关联id
         * value:主表表名
         */
        Map<String,String>  resultIDMap = new HashMap<>(listMap.size());

        listMap.forEach((k,v)->{
            initIDMap.put(k, Constants.DEFAULT_KEY_COLUMN);
        });


        listMap.forEach((k,v)->{
            v.stream().forEach(columnBean->{
                String columnName = columnBean.getColumnName();
                if(columnName.endsWith("_id")){
                    String referTableName = columnName.substring(0,columnName.length() - 3);
                    String id = initIDMap.get(referTableName);
                    if(StringUtils.isNotBlank(id)){
                        resultIDMap.put(columnBean.getTableName()+":"+columnName,referTableName);
                    }
                }
            });
        });

        resultIDMap.forEach((k,v)->{
            MessageBean messageBean = new MessageBean();
            String tableName = k.split(":")[0];
            String columnName = k.split(":")[1];
            messageBean.setTableName(tableName);
            messageBean.setColumnName(columnName);
            messageBean.setLevelName(CheckLevelEnum.ADVICE.getLevelName());
            String msg = "表["+tableName+"]与表["+v+"]存在关联关系,关联字段为"+columnName+",请确定该表是否已加索引,保证大数据量下sql层无性能问题!";
            messageBean.setErrorMsg(msg);
            list.add(messageBean);
        });
        return list;
    }

}
