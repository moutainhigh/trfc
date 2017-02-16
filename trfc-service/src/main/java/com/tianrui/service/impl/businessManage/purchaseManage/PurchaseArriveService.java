package com.tianrui.service.impl.businessManage.purchaseManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationDetailService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveQuery;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveUpdate;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class PurchaseArriveService implements IPurchaseArriveService {

	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private IPurchaseApplicationService purchaseApplicationService;
	@Autowired
	private IPurchaseApplicationDetailService purchaseApplicationDetailService;
	
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

	@Override
	public PurchaseArriveResp findOne(PurchaseArriveQuery query) throws Exception {
		PurchaseArriveResp resp = null;
		if(query != null && StringUtils.isNotBlank(query.getId())){
			resp = copyBean2Resp(purchaseArriveMapper.selectByPrimaryKey(query.getId()));
		}
		return resp;
	}
	
	@Override
	public Result updateOperation(PurchaseArriveUpdate update) throws Exception {
		Result result = Result.getParamErrorResult();
		if(update != null){
			PurchaseArrive pa = new PurchaseArrive();
			PropertyUtils.copyProperties(pa, update);
			if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) == 1){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}
		return result;
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
			if(StringUtils.isNotBlank(bean.getBillid())){
				resp.setPurchaseApplicationResp(purchaseApplicationService.findOne(bean.getBillid()));
			}
			if(StringUtils.isNotBlank(bean.getBilldetailid())){
				resp.setPurchaseApplicationDetailResp(purchaseApplicationDetailService.findOne(bean.getBilldetailid()));
			}
		}
		return resp;
	}
	
}
