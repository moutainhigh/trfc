package com.tianrui.quartz.job;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.smartfactory.common.utils.DateUtil;

/**
 * 销售出库单数据回传定时服务
 * @author YangZhenFu
 *
 */
@Component
public class TestJobSchedule {

	
	@Scheduled(cron="0/10 * *  * * ? ") //每10秒执行一次
	public void test1(){
		System.out.println(DateUtil.getDateString(new Date())+"@@@test1执行");
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Scheduled(cron="0/10 * *  * * ? ") //每10秒执行一次
	public void test2(){
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(DateUtil.getDateString(new Date())+"@@@test2执行");
	}
	
	
	
	@Scheduled(cron="0/10 * *  * * ? ") //每10秒执行一次
	public void test3(){
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(DateUtil.getDateString(new Date())+"@@@test3执行");
		
	}
	@Scheduled(cron="0/10 * *  * * ? ") //每10秒执行一次
	public void test4(){
		System.out.println(DateUtil.getDateString(new Date())+"@@@test4执行");
		
	}
	@Scheduled(cron="0/10 * *  * * ? ") //每10秒执行一次
	public void test5(){
		System.out.println(DateUtil.getDateString(new Date())+"@@@test5执行");
		
	}
	@Scheduled(cron="0/10 * *  * * ? ") //每10秒执行一次
	public void test6(){
		System.out.println(DateUtil.getDateString(new Date())+"@@@test6执行");
		
	}
	@Scheduled(cron="0/10 * *  * * ? ") //每10秒执行一次
	public void test8(){
		System.out.println(DateUtil.getDateString(new Date())+"@@@test8执行");
		
	}
	@Scheduled(cron="0/10 * *  * * ? ") //每10秒执行一次
	public void test7(){
		System.out.println(DateUtil.getDateString(new Date())+"@@@test7执行");
		
	}
}
