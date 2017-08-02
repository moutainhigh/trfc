package com.tianrui.quartz.job;

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
	
	@Autowired
	private IPurchaseStorageListService purchaseStorageListService;
	
	//@Scheduled(cron="0/10 * *  * * ? ") //每10秒执行一次
	@Scheduled(cron="0 0/10 *  * * ? ")
	public void sync(){
		try {
			purchaseStorageListService.returnPurchaseStorageList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
