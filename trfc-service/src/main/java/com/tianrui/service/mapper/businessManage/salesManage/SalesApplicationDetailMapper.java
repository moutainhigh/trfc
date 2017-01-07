package com.tianrui.service.mapper.businessManage.salesManage;

import java.util.List;

import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;

public interface SalesApplicationDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesApplicationDetail record);

    int insertSelective(SalesApplicationDetail record);

    SalesApplicationDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesApplicationDetail record);

    int updateByPrimaryKey(SalesApplicationDetail record);

    List<SalesApplicationDetail> selectBySalesId(String salesId);
    
}