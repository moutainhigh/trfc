package com.tianrui.service.mapper.common;

import java.util.List;

import com.tianrui.service.bean.common.BillType;

public interface BillTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(BillType record);

    int insertSelective(BillType record);

    BillType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BillType record);

    int updateByPrimaryKey(BillType record);
    
    List<BillType> selectSelective(BillType record);
}