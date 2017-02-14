package com.tianrui.service.impl.businessManage.purchaseManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveQuery;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@Service
public class PurchaseArriveService implements IPurchaseArriveService {

	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	
	@Override
	public PaginationVO<PurchaseArriveResp> page(PurchaseArriveQuery query) throws Exception{
		PaginationVO<PurchaseArriveResp> page = null;
		if(query != null){
			page = new PaginationVO<PurchaseArriveResp>();
			query.setState("1");
			long count = purchaseArriveMapper.findPurchaseArrivePageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<PurchaseArrive> list = purchaseArriveMapper.findPurchaseArrivePage(query);
				page.setList(copyBeanList2RespList(list));
			}
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
			page.setTotal(count);
		}
		return page;
	}

	private List<PurchaseArriveResp> copyBeanList2RespList(List<PurchaseArrive> list) throws Exception {
		List<PurchaseArriveResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<PurchaseArriveResp>();
			for(PurchaseArrive mater : list){
				listResp.add(copyBean2Resp(mater));
			}
		}
		return listResp;
	}
	
	private PurchaseArriveResp copyBean2Resp(PurchaseArrive bean) throws Exception {
		PurchaseArriveResp resp = null;
		if(bean != null){
			resp = new PurchaseArriveResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
	
}
