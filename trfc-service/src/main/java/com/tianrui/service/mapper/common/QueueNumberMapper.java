package com.tianrui.service.mapper.common;

import java.util.List;

import com.tianrui.api.resp.common.QueueNumberResp;
import com.tianrui.service.bean.common.QueueNumber;

public interface QueueNumberMapper {
    int deleteByPrimaryKey(String noticecode);

    int insert(QueueNumber record);

    int insertSelective(QueueNumber record);

    QueueNumber selectByPrimaryKey(String noticecode);

    int updateByPrimaryKeySelective(QueueNumber record);

    int updateByPrimaryKey(QueueNumber record);
    
    List<QueueNumberResp> queryWaiting();
    
}