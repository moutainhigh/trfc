package com.tianrui.service.mapper.basicFile.finance;

import java.util.List;

import com.tianrui.service.bean.basicFile.finance.PrmTariff;

public interface PrmTariffMapper {
    int deleteByPrimaryKey(String id);

    int insert(PrmTariff record);

    int insertSelective(PrmTariff record);
    
    int insertBatch(List<PrmTariff> list);

    PrmTariff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PrmTariff record);

    int updateByPrimaryKey(PrmTariff record);
    
    List<String> selectIds();
    
    List<PrmTariff> selectByCondition(PrmTariff record);
    
    String findMaxUtc();
}