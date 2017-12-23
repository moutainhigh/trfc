package com.tianrui.test.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.intf.businessManage.purchaseManage.IPushSingleService;
import com.tianrui.api.intf.businessManage.report.IPurchaseReportService;
import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.api.req.businessManage.report.ReportPurchaseQuery;
import com.tianrui.api.resp.businessManage.report.ReportPurchaseMaterResp;
import com.tianrui.api.resp.businessManage.report.ReportPurchaseResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class PushSingleServiceTest {
	public static Logger logger =LoggerFactory.getLogger(PushSingleServiceTest.class);
	@Resource
	private IPushSingleService pushSingleService;
	
	@Test
	public void test2() throws Exception{
		PushSingleReq req = new PushSingleReq();
		req.setRequisitionNum("DH201712231114");//申请单号
		//req.setNoticeNum("DH201712231114");//通知单号
		req.setRequisitionType("1");//申请单类型
		req.setDesc2("0");//业务类型
		
		
		pushSingleService.savePushSingle(req);
		
	}
}
