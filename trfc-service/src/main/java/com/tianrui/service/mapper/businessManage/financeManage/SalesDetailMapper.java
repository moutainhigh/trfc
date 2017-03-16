package com.tianrui.service.mapper.businessManage.financeManage;

import java.util.List;

import com.tianrui.api.req.businessManage.financeManage.SalesDetailQuery;
import com.tianrui.service.bean.businessManage.financeManage.SalesDetail;

public interface SalesDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesDetail record);

    int insertSelective(SalesDetail record);

    SalesDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesDetail record);

    int updateByPrimaryKey(SalesDetail record);
    
    Long findSalesDetailPageCount(SalesDetailQuery query);
    
    List<SalesDetail> findSalesDetailPage(SalesDetailQuery query);
}