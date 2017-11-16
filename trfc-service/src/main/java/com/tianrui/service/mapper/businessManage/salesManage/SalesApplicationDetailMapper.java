package com.tianrui.service.mapper.businessManage.salesManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;

public interface SalesApplicationDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesApplicationDetail record);
    int insertBatch(@Param("list")List<SalesApplicationDetail> list);

    int insertSelective(SalesApplicationDetail record);

    SalesApplicationDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesApplicationDetail record);

    int updateByPrimaryKey(SalesApplicationDetail record);

    List<SalesApplicationDetail> selectBySalesId(String salesId);
    
    List<SalesApplicationDetail> selectBySalesIds(List<String> list);
    
    List<SalesApplicationDetail> selectByIds(List<String> list);
    
    double getSumMarginBySalesId(List<String> list);
    
}