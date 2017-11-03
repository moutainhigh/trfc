package com.tianrui.service.impl.basicFile.finance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.basicFile.finance.IArRecbillService;
import com.tianrui.api.req.BaseReq;
import com.tianrui.service.bean.basicFile.finance.ArRecbill;
import com.tianrui.service.mapper.basicFile.finance.ArRecbillMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class ArRecbillService implements IArRecbillService {

	@Autowired
	ArRecbillMapper  arRecbillMapper;
	
	@Override
	public Result findMaxUtc(BaseReq query) throws Exception {
		Result rs =Result.getParamErrorResult();
		String max =arRecbillMapper.findMaxUtc();
		rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		rs.setData(max);
		return rs;
	}

	@Override
	public Result updateDataWithDC(List<JSONObject> list) {
		Result rs =Result.getParamErrorResult();
		if(CollectionUtils.isNotEmpty(list) ){
			Set<String> idSet = getAllDb();
			// 区分新增还是修改
			List<ArRecbill>  toSave = new ArrayList<ArRecbill>();
			List<ArRecbill>  toUpdate = new ArrayList<ArRecbill>();
			for(JSONObject jsonItem :list ){
				if( idSet.contains(jsonItem.get("id"))){
					toUpdate.add(converJson2Bean(jsonItem));
				}else{
					toSave.add(converJson2Bean(jsonItem));
				}
			}
			//新增的调批量保存
			if(CollectionUtils.isNotEmpty(toSave)){
				arRecbillMapper.insertBatch(toSave);
			}
			//修改的就一个一个的调用修改
			if( CollectionUtils.isNotEmpty(toUpdate) ){
				for( ArRecbill item:toUpdate ){
					arRecbillMapper.updateByPrimaryKeySelective(item);
				}
			}
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return rs;
	}
	private Set<String> getAllDb(){
		Set<String> set =new HashSet<String>();
		List<String> ids =arRecbillMapper.selectIds();
		if( CollectionUtils.isNotEmpty(ids) ){
			for(String id:ids){
				set.add(id);
			}
		}
		return set;
	}
	
	
	private ArRecbill converJson2Bean(JSONObject jsonItem){
		ArRecbill item  =new ArRecbill();
		
		item.setId(jsonItem.getString("ncid2"));
		item.setNcid2(jsonItem.getString("ncid2"));
		item.setNcid1(jsonItem.getString("ncid1"));
		item.setCustomerId(jsonItem.getString("customerId"));
		item.setCustomerCode(jsonItem.getString("customerCode"));
		
		item.setCustomerName(jsonItem.getString("customerName"));
		item.setBillno(jsonItem.getString("billno"));
		item.setBilldate(jsonItem.getString("billdate"));
		item.setBillstatus(jsonItem.getString("billstatus"));
		item.setLocalMoney(jsonItem.getString("localMoney"));
		
		
		item.setMoney(jsonItem.getString("money"));
		item.setApprover(jsonItem.getString("approver"));
		item.setApprovestatus(jsonItem.getString("approvestatus"));
		item.setApprovedated(jsonItem.getString("approvedated"));
		item.setSaleOrgId(jsonItem.getString("saleOrgId"));
		
		
		item.setSaleOrgName(jsonItem.getString("saleOrgName"));
		item.setDeliveryOrgId(jsonItem.getString("deliveryOrgId"));
		item.setMaterialId(jsonItem.getString("materialId"));
		item.setMaterialCode(jsonItem.getString("materialCode"));
		item.setBillmaker(jsonItem.getString("billmaker"));
		
		item.setTs(jsonItem.getString("ts"));
		item.setDesc1(jsonItem.getString("desc1"));
		item.setDesc2(jsonItem.getString("desc2"));
		item.setDesc3(jsonItem.getString("desc3"));
		item.setDesc4(jsonItem.getString("desc4"));
		
		item.setCreateTime(System.currentTimeMillis());

		return item;
	}


}