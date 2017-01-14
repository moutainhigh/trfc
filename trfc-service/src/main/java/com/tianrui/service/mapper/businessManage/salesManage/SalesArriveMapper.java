package com.tianrui.service.mapper.businessManage.salesManage;

import java.util.List;

import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;

public interface SalesArriveMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesArrive record);

    int insertSelective(SalesArrive record);

    SalesArrive selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesArrive record);

    int updateByPrimaryKey(SalesArrive record);
    
    List<SalesArrive> selectSelective(SalesArrive record);

	long findSalesArrivePageCount(SalesArriveQuery query);

	List<SalesArrive> findSalesArrivePage(SalesArriveQuery query);
	
	int checkICUse(SalesArriveQuery query);
}