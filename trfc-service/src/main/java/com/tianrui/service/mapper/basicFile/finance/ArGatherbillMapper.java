package com.tianrui.service.mapper.basicFile.finance;

import java.util.List;

import com.tianrui.service.bean.basicFile.finance.ArGatherbill;

public interface ArGatherbillMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArGatherbill record);
    
    int insertBatch(List<ArGatherbill> list);

    int insertSelective(ArGatherbill record);

    ArGatherbill selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArGatherbill record);

    int updateByPrimaryKey(ArGatherbill record);
    
    List<String> selectIds();
    
    String findMaxUtc();
}