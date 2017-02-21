package com.tianrui.service.mapper.common;

import java.util.List;

import com.tianrui.service.bean.common.ReturnQueue;

public interface ReturnQueueMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReturnQueue record);

    int insertSelective(ReturnQueue record);

    ReturnQueue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReturnQueue record);

    int updateByPrimaryKey(ReturnQueue record);
    
    List<ReturnQueue> selectSelective(ReturnQueue record);
    
    int deteleByIds(List<String> list);
    
}