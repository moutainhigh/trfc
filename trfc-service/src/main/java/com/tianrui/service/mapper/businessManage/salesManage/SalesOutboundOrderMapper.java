package com.tianrui.service.mapper.businessManage.salesManage;

import java.util.List;

import com.tianrui.service.bean.businessManage.salesManage.SalesOutboundOrder;

public interface SalesOutboundOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesOutboundOrder record);

    int insertSelective(SalesOutboundOrder record);

    SalesOutboundOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesOutboundOrder record);

    int updateByPrimaryKey(SalesOutboundOrder record);

	List<SalesOutboundOrder> selectIncrementalData(SalesOutboundOrder query);
	
	int insertBatch(List<SalesOutboundOrder> list);
}