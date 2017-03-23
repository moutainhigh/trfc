package com.tianrui.service.mapper.businessManage.salesManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.service.bean.businessManage.salesManage.SalesOutboundOrderItem;

public interface SalesOutboundOrderItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesOutboundOrderItem record);

    int insertSelective(SalesOutboundOrderItem record);

    SalesOutboundOrderItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesOutboundOrderItem record);

    int updateByPrimaryKey(SalesOutboundOrderItem record);

	List<SalesOutboundOrderItem> selectByOrderIds(@Param("ids") List<String> ids);
	
	int insertBatch(List<SalesOutboundOrderItem> list);
}