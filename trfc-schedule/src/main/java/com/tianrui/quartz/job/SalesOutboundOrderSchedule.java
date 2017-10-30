package com.tianrui.quartz.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.quartz.service.ISalesOutboundOrderService;

/**
 * 销售出库单数据回传定时服务
 * @author YangZhenFu
 *
 */
@Component
public class SalesOutboundOrderSchedule {

	@Autowired
	private ISalesOutboundOrderService salesOutboundOrderService;
	
	//@Scheduled(cron="0/10 * *  * * ? ") //每10秒执行一次
    @Scheduled(cron="0 0/10 *  * * ? ")
	public void sync(){
		try {
			salesOutboundOrderService.returnSalesOutboundOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
