package com.tianrui.quartz.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.quartz.service.TaskJobService;

/**
  * cron配置
	"0 0 12 * * ?"    每天中午十二点触发 
	"0 15 10 ? * *"    每天早上10：15触发 
	"0 15 10 * * ?"    每天早上10：15触发 
	"0 15 10 * * ? *"    每天早上10：15触发 
	"0 15 10 * * ? 2005"    2005年的每天早上10：15触发 
	"0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发 
	"0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发 
	"0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 
	"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发 
	"0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发 
	"0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发 
 *
 */
@Component  
public class TaskJob {
	
	@Autowired
	private TaskJobService taskJobService;
	
	//@Scheduled(cron="0/10 * *  * * ? ")   //每10秒执行一次 
    @Scheduled(cron="0 0/10 *  * * ? ") 
	public void returnDataCenter(){
		 try {
			taskJobService.returnDataCenter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	//@Scheduled(cron="0 0 12 * * ?")   //每10秒执行一次  
    @Scheduled(cron="0 0/10 *  * * ? ")
	public void customer_supplier_user(){
		 try {
			taskJobService.customer_supplier_user();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
