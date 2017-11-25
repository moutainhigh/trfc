package com.tianrui.api.intf.businessManage.report;

import com.tianrui.api.req.businessManage.report.ReportPurchaseQuery;
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
}