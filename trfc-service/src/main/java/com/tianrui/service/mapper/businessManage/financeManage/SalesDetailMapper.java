package com.tianrui.service.mapper.businessManage.financeManage;

import java.util.List;

import com.tianrui.api.req.businessManage.financeManage.SalesDetailQuery;
import com.tianrui.service.bean.basicFile.finance.ArRecbill;
import com.tianrui.service.bean.businessManage.financeManage.SalesDetail;

public interface SalesDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesDetail record);

    int insertSelective(SalesDetail record);
    
    int insertBatch(List<SalesDetail> list);
    
    SalesDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesDetail record);

    int updateByPrimaryKey(SalesDetail record);
    int findMaxUtc();
    List<String> selectIds();
    
    Long findSalesDetailPageCount(SalesDetailQuery query);
    
    List<SalesDetail> findSalesDetailPage(SalesDetailQuery query);
}