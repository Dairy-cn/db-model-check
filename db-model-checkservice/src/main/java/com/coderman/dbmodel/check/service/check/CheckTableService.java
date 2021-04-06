package com.coderman.dbmodel.check.service.check;

import com.coderman.dbmodel.check.Constants;
import com.coderman.dbmodel.check.bean.ColumnBean;
import com.coderman.dbmodel.check.bean.MessageBean;
import com.coderman.dbmodel.check.bean.TableBean;
import com.coderman.dbmodel.check.enums.CheckLevelEnum;
import com.coderman.dbmodel.check.enums.DBCharEnum;
import com.coderman.dbmodel.check.enums.EngineEnum;
import com.coderman.dbmodel.check.enums.YesNoEnum;
import com.coderman.dbmodel.check.service.daos.QueryService;
import com.coderman.dbmodel.check.utils.StringUtilX;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 58-daojia on 2018-7-19.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:
 */
@Service
public class CheckTableService {
    @Autowired
    private QueryService queryService;

    @Autowired
    private CheckIndexService checkIndexService ;

    /**
     * 检查表和字段的元数据信息
     * @param dbName 数据库名称
     * @return
     */
    public List<MessageBean> checkTableInfo(String dbName){
        List<MessageBean> resultListx = new ArrayList<>();
        try {
            System.out.println(">>>>:start check table..........");
            MessageBean messageBean;
            List<TableBean> list = queryService.getDBTableBeanList(dbName);
            if(list.size() > 2000){
                messageBean = buildMessageBean(CheckLevelEnum.WARNING.getLevelName(),null,null,"该数据库实例中表数量超过2000,请确保是否有合适的策略!");
                resultListx.add(messageBean);
            }


            for (TableBean tableBean : list){
                //表名长度检查
                if(tableBean.getTableName().length() > 32){
                    messageBean = buildMessageBean(CheckLevelEnum.WARNING.getLevelName(),tableBean.getTableName(),"表名超过32个字符，建议重命名!");
                    resultListx.add(messageBean);
                }
                //表注释检查
                if(StringUtils.isBlank(tableBean.getTableComment())){
                    messageBean = buildMessageBean(CheckLevelEnum.WARNING.getLevelName(),tableBean.getTableName(),"该表需要加comment注释,表示该表的意义!");
                    resultListx.add(messageBean);
                }
                //表名规范检查
                boolean b = StringUtilX.isNumericHead(tableBean.getTableName());
                if(b==true){
                    messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),tableBean.getTableName(),"表名称不能以数字开头,需要改正!");
                    resultListx.add(messageBean);
                }
                //表名规范检查
                b = StringUtilX.isUpCaseChar(tableBean.getTableName());
                if(b==false){
                    messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),tableBean.getTableName(),"表名称存在大写字母,需要改正!");
                    resultListx.add(messageBean);
                }

//                //存储引擎检查
//                if(tableBean.getEngineName() == null || !tableBean.getEngineName().equalsIgnoreCase(EngineEnum.INNODB.getName())){
//                    messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),tableBean.getTableName(),"表的存储引擎不是"+EngineEnum.INNODB.getName()+",请检查!");
//                    resultListx.add(messageBean);
//                }
//
//                //字符集检查（默认utf字符集）
//                if(tableBean.getTableCollation() == null || !tableBean.getTableCollation().startsWith("utf")){
//                    messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),tableBean.getTableName(),"表的字符集不是utf-8相关的,请检查!");
//                    resultListx.add(messageBean);
//                }


                //表名是否是复数
                if(tableBean.getTableName().endsWith("s")){
                    messageBean = buildMessageBean(CheckLevelEnum.WARNING.getLevelName(),tableBean.getTableName(),"表名称可能是复数，请检查!");
                    resultListx.add(messageBean);
                }

                //表记录数检查
                if(tableBean.getTableRows() != null && tableBean.getTableRows() > 1000000){
                    messageBean = buildMessageBean(CheckLevelEnum.WARNING.getLevelName(),tableBean.getTableName(),"该表记录数已超过100W,请确定该表未来数据增量,适当采取分库分表策略");
                    resultListx.add(messageBean);
                }
            }


            System.out.println(">>>>:end check table..........,msgSize= "+resultListx.size());

            List<ColumnBean> colList = queryService.getColumnBeanList(dbName);

            Map<String,List<ColumnBean>> colListMap = colList.stream().collect(Collectors.groupingBy(ColumnBean::getTableName));

            List<MessageBean> indexCheckList = checkIndexService.checkIndex(colListMap);

            resultListx.addAll(indexCheckList);

            colListMap.forEach((k,v)->{
                List<MessageBean> list1 = checkColumnInfo(v);
                resultListx.addAll(list1);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultListx;
    }

    /**
     * 字段检查
     * @param list 字段元数据
     * @return
     */
    public List<MessageBean> checkColumnInfo(List<ColumnBean> list){

        if(CollectionUtils.isEmpty(list)){
            return null;
        }

        List<MessageBean> resultList = new ArrayList<>();
        boolean hasPrKey = false;
        String tableName = list.get(0).getTableName();
        boolean hasId = false;
        MessageBean messageBean;

        if(list.size() > 50){
            messageBean = buildMessageBean(CheckLevelEnum.WARNING.getLevelName(),list.get(0).getTableName(),null,"该表中字段数量超过50,请确认该表是否具有分拆字段新建表的可能!");
            resultList.add(messageBean);
        }

        for (ColumnBean columnBean : list){

            //字段名长度检查
            if(columnBean.getColumnName().length() > 32){
                messageBean = buildMessageBean(CheckLevelEnum.WARNING.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"该字段名过长,超过32个字符,建议重命名!");
                resultList.add(messageBean);
            }
            //字段是否有注释
            if(StringUtils.isBlank(columnBean.getColumnComment())){
                messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"需要加comment注释,表示该column的意义!");
                resultList.add(messageBean);
            }
            //字段是否有默认值(非主键检查)
//            if(columnBean.getColumnDefaultValue() == null && !columnBean.getColumnName().equals(Constants.DEFAULT_KEY_COLUMN)){
//                messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"需要加默认值,表示该column的默认值!");
//                resultList.add(messageBean);
//            }
            //字段是为可为空
//            if(columnBean.getIsNullable().equals(YesNoEnum.YES.getName())){
//                messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"需要设置非空,表示该column不允许存在空值!");
//                resultList.add(messageBean);
//
//            }
            //字段名规范检查
            boolean b = StringUtilX.isNumericHead(columnBean.getColumnName());
            if(b==true){
                messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"字段名称不能以数字开头,需要改正!");
                resultList.add(messageBean);
            }
            //字段名规范检查
            b = StringUtilX.isUpCaseChar(columnBean.getColumnName());
            if(b==false){
                messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"字段名称存在大写字母,需要改正!");
                resultList.add(messageBean);
            }

            //主键检查
            if(columnBean.getColumnKey().equals(Constants.PRI_KEY)){
                hasPrKey = true;
                tableName = columnBean.getTableName();
            }

            //字段类型检测
            if(columnBean.getDataType().startsWith("enum")){
                messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"字段禁止使用枚举enum类型,推荐使用tinyint!");
                resultList.add(messageBean);
            }


            //字段类型检测
            boolean b1 = (columnBean.getColumnName().endsWith("date") || columnBean.getColumnName().endsWith("time")) &&  columnBean.getDataType().equals("varchar");
            if(b1){
                messageBean = buildMessageBean(CheckLevelEnum.WARNING.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"字段可能是时间/日期相关的,建议使用时间/日期类型,当前为varchar!");
                resultList.add(messageBean);
            }


            //大文本字段检查
            if(DBCharEnum.getCharTypeSet().contains(columnBean.getDataType()) && list.size() > 3){
                messageBean = buildMessageBean(CheckLevelEnum.WARNING.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"字段是大文本字段,建议新建表存储!");
                resultList.add(messageBean);
            }


            //关联键类型检查
            if(columnBean.getColumnName().endsWith("_id") && columnBean.getDataType().equals("int")){
                messageBean = buildMessageBean(CheckLevelEnum.WARNING.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"字段可能是某表的关联id,但是类型是int,建议调整为bigint!");
                resultList.add(messageBean);
            }

            //关联键类型检查
            if(columnBean.getColumnName().endsWith("_id") && columnBean.getDataType().equals("varchar")){
                messageBean = buildMessageBean(CheckLevelEnum.ADVICE.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"字段可能是某表的关联id,但是类型是varchar,建议调整!");
                resultList.add(messageBean);
            }


            String columnType = columnBean.getColumnType();

            //varchar 字段长度检查
            if(columnType.startsWith("varchar") && columnType.contains("(")){
                String x = columnType;
                x = x.substring(x.indexOf("(")+1,x.lastIndexOf(")"));
                if(Integer.parseInt(x) >=5000){
                    messageBean = buildMessageBean(CheckLevelEnum.ADVICE.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"字段长度设置超长(>=5000),建议新建表存储!");
                    resultList.add(messageBean);
                }
            }


            if(StringUtils.isNotBlank(columnBean.getColumnDefaultValue()) && StringUtils.isNotBlank( columnBean.getDataType())){
                //字段类型和字段默认值检查
                if(columnBean.getColumnDefaultValue().equals("0") && columnBean.getDataType().equals("varchar")){
                    messageBean = buildMessageBean(CheckLevelEnum.ADVICE.getLevelName(),columnBean.getTableName(),columnBean.getColumnName(),"字段默认值是0,但是类型是varchar,建议调整!");
                    resultList.add(messageBean);
                }
            }

            if(columnBean.getColumnName().equals(Constants.DEFAULT_KEY_COLUMN)){
                hasId = true;
            }

        }

        if(hasPrKey == false){
            messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),tableName,"没有主键,需要设置至少一个主键!");
            resultList.add(messageBean);
        }

//        if(hasId == false){
//            messageBean = buildMessageBean(CheckLevelEnum.ERROR.getLevelName(),tableName,"没有id,需要设置增加一列默认为id,且为主键!");
//            resultList.add(messageBean);
//        }

        return resultList;
    }

    /**
     * 构建扫描消息
     * @param levelName
     * @param tableName
     * @param message
     * @return
     */
    private MessageBean buildMessageBean(String levelName,String tableName,String message){
        MessageBean messageBean = new MessageBean();
        messageBean.setLevelName(levelName);
        messageBean.setErrorMsg(message);
        messageBean.setTableName(tableName);
        return messageBean;
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
