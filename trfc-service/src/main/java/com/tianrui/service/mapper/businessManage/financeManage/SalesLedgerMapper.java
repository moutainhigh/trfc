package com.tianrui.service.mapper.businessManage.financeManage;

import java.util.List;

import com.tianrui.api.req.businessManage.financeManage.SalesLedgerQuery;
import com.tianrui.service.bean.businessManage.financeManage.SalesLedger;

public interface SalesLedgerMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesLedger record);

    int insertSelective(SalesLedger record);

    SalesLedger selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesLedger record);

    int updateByPrimaryKey(SalesLedger record);
    
    Long findSalesLedgerPageCount(SalesLedgerQuery query);
    
    List<SalesLedger> findSalesLedgerPage(SalesLedgerQuery query);

   	List<SalesLedger> selectSelective(SalesLedger record);
}