package com.tianrui.api.intf.businessManage.report;

import java.util.List;

import com.tianrui.api.req.businessManage.report.ReportPurchaseQuery;
import com.tianrui.api.resp.businessManage.report.ReportPurchaseMaterResp;
import com.tianrui.api.resp.businessManage.report.ReportPurchaseResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

/**
 * 采购报表
 * 
 * @author Administrator
 *
 */
public interface IPurchaseReportService {

	// 逐车明细分页
	PaginationVO<ReportPurchaseResp> page(ReportPurchaseQuery query) throws Exception;
	List<ReportPurchaseResp> list(ReportPurchaseQuery query) throws Exception;
	//采购物料
	PaginationVO<ReportPurchaseMaterResp> page1(ReportPurchaseQuery query) throws Exception;
	List<ReportPurchaseMaterResp> list1(ReportPurchaseQuery query) throws Exception;
	//采购单位
	PaginationVO<ReportPurchaseMaterResp> page2(ReportPurchaseQuery query) throws Exception;
	List<ReportPurchaseMaterResp> list2(ReportPurchaseQuery query) throws Exception;
	//采购收料
	PaginationVO<ReportPurchaseResp> page3(ReportPurchaseQuery req) throws Exception;
	List<ReportPurchaseResp> list3(ReportPurchaseQuery query) throws Exception;
	//榜单补增
	PaginationVO<ReportPurchaseResp> page4(ReportPurchaseQuery req) throws Exception;
	List<ReportPurchaseResp> list4(ReportPurchaseQuery query) throws Exception;
	PaginationVO<ReportPurchaseMaterResp> minemouthnamePage(ReportPurchaseQuery query) throws Exception;
	List<ReportPurchaseMaterResp> minemouthnameList(ReportPurchaseQuery query) throws Exception;
}