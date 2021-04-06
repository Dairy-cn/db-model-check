package com.coderman.dbmodel.check.mapper;

import com.coderman.dbmodel.check.bean.ColumnBean;
import com.coderman.dbmodel.check.bean.TableBean;
import org.apache.ibatis.annotations.Flush;

import java.util.List;

/**
 * @Author fanchunshuai
 * @Date 2019/11/22 17
 * @Description:mysql 元数据查询接口
 */
public interface SqlMapper {
    @Flush
    Integer getCount(String countSql);

    @Flush
    List<TableBean> getDBTableBeanList(String dbName);

    @Flush
    List<ColumnBean> getColumnBeanList(String dbName);
}
