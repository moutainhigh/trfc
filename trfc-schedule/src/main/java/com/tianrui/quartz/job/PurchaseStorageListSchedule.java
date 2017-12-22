package com.tianrui.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * 采购入库单数据回传定时服务
 * @author YangZhenFu
 *
 */

import com.tianrui.quartz.service.IPurchaseStorageListService;
@Component
public class PurchaseStorageListSchedule {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPurchaseStorageListService purchaseStorageListService;
	
	@Scheduled(cron="0/10 * *  * * ? ") //每10秒执行一次
//	@Scheduled(cron="0 0/10 *  * * ? ")
	public void sync(){
		try {
			long starTime = System.currentTimeMillis();
			log.info("采购入库单FC到DC推送定时服务启动...");
			purchaseStorageListService.returnPurchaseStorageList();
			log.info("采购入库单FC到DC推送定时服务结束..., 耗时：" + (System.currentTimeMillis() - starTime) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
