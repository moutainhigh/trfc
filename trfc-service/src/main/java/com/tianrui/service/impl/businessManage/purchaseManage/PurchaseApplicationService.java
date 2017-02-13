package com.tianrui.service.impl.businessManage.purchaseManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationDetailService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationService;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseApplicationQuery;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationResp;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@Service
public class PurchaseApplicationService implements IPurchaseApplicationService {
	
	@Autowired
	private PurchaseApplicationMapper purchaseApplicationMapper;
	
	@Autowired
	private IPurchaseApplicationDetailService purchaseApplicationDetailService;
	
	@Override
	public PaginationVO<PurchaseApplicationResp> page(PurchaseApplicationQuery query) throws Exception{
		PaginationVO<PurchaseApplicationResp> page = null;
		if(query != null){
			page = new PaginationVO<PurchaseApplicationResp>();
			query.setState("1");
			long count = purchaseApplicationMapper.findPurchaseApplicationPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<PurchaseApplication> list = purchaseApplicationMapper.findPurchaseApplicationPage(query);
				page.setList(copyBeanList2RespList(list));
			}
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
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
			resp.setListdetail(purchaseApplicationDetailService.selectByPurchaseId(bean.getId()));
		}
		return resp;
	}
	
}