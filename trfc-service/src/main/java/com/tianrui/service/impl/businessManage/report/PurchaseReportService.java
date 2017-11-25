package com.tianrui.service.impl.businessManage.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationDetailService;
import com.tianrui.api.intf.businessManage.report.IPurchaseReportService;
import com.tianrui.api.req.businessManage.report.ReportPurchaseQuery;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationDetailResp;
import com.tianrui.api.resp.businessManage.report.ReportPurchaseResp;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@Service
public class PurchaseReportService implements IPurchaseReportService {

	@Autowired
	private IPurchaseReportService purchaseReportService;

	@Override
	public PaginationVO<ReportPurchaseResp> page(ReportPurchaseQuery query) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}