package com.tianrui.service.impl.businessManage.purchaseManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationService;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseApplicationReq;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationDetailResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationResp;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@Service
public class PurchaseApplicationService implements IPurchaseApplicationService {
	
	@Autowired
	private PurchaseApplicationMapper purchaseApplicationMapper;
	
	@Autowired
	private PurchaseApplicationDetailMapper purchaseApplicationDetailMapper;
	
	@Override
	public PaginationVO<PurchaseApplicationResp> page(PurchaseApplicationReq req) throws Exception{
		PaginationVO<PurchaseApplicationResp> page = null;
		if(req != null){
			page = new PaginationVO<PurchaseApplicationResp>();
			long count = purchaseApplicationMapper.findPurchaseApplicationPageCount(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<PurchaseApplication> list = purchaseApplicationMapper.findPurchaseApplicationPage(req);
				page.setList(copyBeanList2RespList(list));
			}
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
			page.setTotal(count);
		}
		return page;
	}
	
	private List<PurchaseApplicationResp> copyBeanList2RespList(List<PurchaseApplication> list) throws Exception {
		List<PurchaseApplicationResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<PurchaseApplicationResp>();
			for(PurchaseApplication mater : list){
				listResp.add(copyBean2Resp(mater));
			}
		}
		return listResp;
	}
	
	private PurchaseApplicationResp copyBean2Resp(PurchaseApplication bean) throws Exception {
		PurchaseApplicationResp resp = null;
		if(bean != null){
			resp = new PurchaseApplicationResp();
			PropertyUtils.copyProperties(resp, bean);
			if(StringUtils.isNotBlank(resp.getId())){
				PurchaseApplicationDetail detail = purchaseApplicationDetailMapper.findPurchaseApplicationDetail(resp.getId());
				resp.setDetailResp(copyBean2DetailResp(detail));
			}
		}
		return resp;
	}
	

	private PurchaseApplicationDetailResp copyBean2DetailResp(PurchaseApplicationDetail bean) throws Exception {
		PurchaseApplicationDetailResp resp = null;
		if(bean != null){
			resp = new PurchaseApplicationDetailResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
	
}