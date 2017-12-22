package com.tianrui.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISalesOutboundOrderService salesOutboundOrderService;
	
	@Scheduled(cron="0/10 * *  * * ? ") //每10秒执行一次
//    @Scheduled(cron="0 0/10 *  * * ? ")
	public void sync(){
		try {
			long starTime = System.currentTimeMillis();
			log.info("销售出库单FC到DC推送定时服务启动...");
			salesOutboundOrderService.returnSalesOutboundOrder();
			log.info("销售出库单FC到DC推送定时服务结束..., 耗时：" + (System.currentTimeMillis() - starTime) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
