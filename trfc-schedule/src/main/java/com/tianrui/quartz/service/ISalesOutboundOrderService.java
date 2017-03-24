package com.tianrui.quartz.service;

import java.util.Date;
import java.util.List;

import com.tianrui.service.bean.businessManage.salesManage.SalesOutboundOrder;

public interface ISalesOutboundOrderService {
	public List<SalesOutboundOrder> getSalesOutboundOrderList(String orgId,Date minDate);
	
	public void returnSalesOutboundOrder() throws Exception;
}
