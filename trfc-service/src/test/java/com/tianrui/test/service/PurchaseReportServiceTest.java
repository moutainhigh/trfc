package com.tianrui.test.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.intf.businessManage.report.IPurchaseReportService;
import com.tianrui.api.req.businessManage.report.ReportPurchaseQuery;
import com.tianrui.api.resp.businessManage.report.ReportPurchaseMaterResp;
import com.tianrui.api.resp.businessManage.report.ReportPurchaseResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class PurchaseReportServiceTest {
	public static Logger logger =LoggerFactory.getLogger(PurchaseReportServiceTest.class);
	@Resource
	private IPurchaseReportService purchaseReportService;
	/*@Test
	public void test1() throws Exception{
		
		ReportPurchaseQuery query = new ReportPurchaseQuery();
	
		query.setMinemouthname("建北");
		
		PaginationVO<ReportPurchaseMaterResp> result = purchaseReportService.page1(query);
		System.out.println(result);
	}*/
	
	@Test
	public void test2() throws Exception{
		
		ReportPurchaseQuery query = new ReportPurchaseQuery();
	
		query.setMinemouthname("建北");
		
		PaginationVO<ReportPurchaseResp> result = purchaseReportService.page(query);
		System.out.println(result);
	}
}
