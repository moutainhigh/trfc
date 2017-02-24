package com.tianrui.service.impl.businessManage.purchaseManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationDetailService;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationDetailResp;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;

@Service
public class PurchaseApplicationDetailService implements IPurchaseApplicationDetailService {

	@Autowired
	private PurchaseApplicationDetailMapper purchaseApplicationDetailMapper;
	
	@Override
	public List<PurchaseApplicationDetailResp> selectByPurchaseId(String purchaseId) throws Exception{
		if(StringUtils.isNotBlank(purchaseId)){
			List<PurchaseApplicationDetail> list = purchaseApplicationDetailMapper.selectByPurchaseId(purchaseId);
			return copyBeanList2RespList(list);
		}
		return null;
	}
	
	@Override
	public PurchaseApplicationDetailResp findOne(String id) throws Exception {
		PurchaseApplicationDetailResp resp = null;
		if(StringUtils.isNotBlank(id)){
			return copyBean2Resp(purchaseApplicationDetailMapper.selectByPrimaryKey(id));
		}
		return resp;
	}

	@Override
	public List<PurchaseApplicationDetailResp> selectByIds(List<String> ids) throws Exception {
		List<PurchaseApplicationDetailResp> list = null;
		if(CollectionUtils.isNotEmpty(ids)){
			list = copyBeanList2RespList(purchaseApplicationDetailMapper.selectByIds(ids));
		}
		return list;
	}
	
	private List<PurchaseApplicationDetailResp> copyBeanList2RespList(List<PurchaseApplicationDetail> list) throws Exception {
		List<PurchaseApplicationDetailResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<PurchaseApplicationDetailResp>();
			for(PurchaseApplicationDetail mater : list){
				listResp.add(copyBean2Resp(mater));
			}
		}
		return listResp;
	}
	
	private PurchaseApplicationDetailResp copyBean2Resp(PurchaseApplicationDetail bean) throws Exception {
		PurchaseApplicationDetailResp resp = null;
		if(bean != null){
			resp = new PurchaseApplicationDetailResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

}