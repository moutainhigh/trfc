package com.tianrui.service.mapper.businessManage.financeManage;

import java.util.List;

import com.tianrui.api.req.businessManage.financeManage.SalesChargeQuery;
import com.tianrui.service.bean.businessManage.financeManage.SalesCharge;

public interface SalesChargeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesCharge record);

    int insertSelective(SalesCharge record);

    SalesCharge selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesCharge record);

    int updateByPrimaryKey(SalesCharge record);
    
    long findSalesChargePageCount(SalesChargeQuery query);
    
    List<SalesCharge> findSalesChargePage(SalesChargeQuery query);

   	List<SalesCharge> selectSelective(SalesCharge record);
}