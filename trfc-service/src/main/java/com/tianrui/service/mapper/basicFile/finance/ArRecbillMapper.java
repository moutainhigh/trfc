package com.tianrui.service.mapper.basicFile.finance;

import java.util.List;

import com.tianrui.service.bean.basicFile.finance.ArRecbill;

public interface ArRecbillMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArRecbill record);

    int insertSelective(ArRecbill record);
    
    int insertBatch(List<ArRecbill> list);

    ArRecbill selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArRecbill record);

    int updateByPrimaryKey(ArRecbill record);
    
    List<String> selectIds();
    
    String findMaxUtc();
}