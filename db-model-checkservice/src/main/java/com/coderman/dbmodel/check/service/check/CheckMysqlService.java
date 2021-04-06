package com.coderman.dbmodel.check.service.check;

import com.coderman.dbmodel.check.bean.MessageBean;
import com.coderman.dbmodel.check.enums.CheckLevelEnum;
import com.coderman.dbmodel.check.service.daos.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 2018-8-10.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:mysql 事件，触发器，存储过程检查
 */
@Service
public class CheckMysqlService {

    @Autowired
    public QueryService queryService;



    /**
     * 事件检查
     * @return
     * @throws Exception
     */
    public List<MessageBean>  checkEvent(String dbName){
        List<MessageBean> list = new ArrayList<>();
        int count = queryService.getEventCount(dbName);
        if(count > 0){
            MessageBean messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),null,null,"该数据库实例中存在[事件],请检查");
            list.add(messageBean);
        }
        return list;
    }

    /**
     * 触发器检查
     * @return
     * @throws Exception
     */
    public List<MessageBean>  checkTrigger(String dbName) {
        List<MessageBean> list = new ArrayList<>();
        int count = queryService.getTriggerCount(dbName);
        if(count > 0){
            MessageBean messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),null,null,"该数据库实例中存在[触发器],请检查");
            list.add(messageBean);
        }
        return list;
    }


    /**
     * 存储过程检查
     * @return
     * @throws Exception
     */
    public List<MessageBean>  checkProceduerCount(String dbName) {
        List<MessageBean> list = new ArrayList<>();
        int count = queryService.getProcedureCount(dbName);
        if(count > 0){
            MessageBean messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),null,null,"该数据库实例中存在[存储过程],请检查");
            list.add(messageBean);
        }
        return list;
    }


    /**
     * 视图检查
     * @return
     * @throws Exception
     */
    public List<MessageBean>  checkViewCount(String dbName) {
        List<MessageBean> list = new ArrayList<>();
        int count = queryService.getViewCount(dbName);
        if(count > 0){
            MessageBean messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),null,null,"该数据库实例中存在[视图],请检查");
            list.add(messageBean);
        }
        return list;
    }




    /**
     * 构建扫描消息
     * @param levelName
     * @param tableName
     * @param columnName
     * @param message
     * @return
     */
    private MessageBean buildMessageBean(String levelName,String tableName,String columnName,String message){
        MessageBean messageBean = new MessageBean();
        messageBean.setLevelName(levelName);
        messageBean.setErrorMsg(message);
        messageBean.setTableName(tableName);
        messageBean.setColumnName(columnName);
        return messageBean;
    }

}
