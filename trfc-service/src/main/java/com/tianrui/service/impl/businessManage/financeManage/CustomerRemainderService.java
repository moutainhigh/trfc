package com.tianrui.service.impl.businessManage.financeManage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.basicFile.finance.ICustomerRemainderService;
import com.tianrui.api.req.BaseReq;
import com.tianrui.service.bean.businessManage.financeManage.CustomerRemainder;
import com.tianrui.service.mapper.businessManage.financeManage.CustomerRemainderMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class CustomerRemainderService implements ICustomerRemainderService {

	@Autowired
	private CustomerRemainderMapper CustomerRemainderMapper;

	@Override
	public Result findMaxUtc(BaseReq baseReq) {
		Result rs = Result.getErrorResult();
		Long maxUtc = CustomerRemainderMapper.findMaxUtc();
		rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		rs.setData(maxUtc);
		return rs;
	}

	@Override
	public Result updateDataWithDC(List<JSONObject> list) {
		Result result = Result.getParamErrorResult();
		if(CollectionUtils.isNotEmpty(list)){
			Set<String> idSet = getAllDb();
			List<CustomerRemainder> toSave = new ArrayList<CustomerRemainder>();
			List<CustomerRemainder> toUpdate = new ArrayList<CustomerRemainder>();
			for(JSONObject jsonItem : list){
				if(idSet.contains(jsonItem.getString("id"))){
					toUpdate.add(converJson2Bean(jsonItem));				
				}else{
					toSave.add(converJson2Bean(jsonItem));
				}
			}
			//新增的数据就批量保存
			if(CollectionUtils.isNotEmpty(toSave)){
				CustomerRemainderMapper.insertBatch(toSave);
			}
			//修改的数据就循环修改
			if(CollectionUtils.isNotEmpty(toUpdate)){
				for(CustomerRemainder customerRemainder : toUpdate){
					CustomerRemainderMapper.updateByPrimaryKey(customerRemainder);
				}
			}
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	private Set<String> getAllDb() {
		Set<String> set = new HashSet<>();
		List<String> ids = CustomerRemainderMapper.queryIds();
		if(CollectionUtils.isNotEmpty(ids)){
			for(String id : ids){
				set.add(id);
			}				
		}
		return set;
	}

	private CustomerRemainder converJson2Bean(JSONObject jsonItem){
		CustomerRemainder item = new CustomerRemainder();
		item.setId(jsonItem.getString("id"));
		item.setOrgName(jsonItem.getString("orgName"));
		item.setOrgId(jsonItem.getString("orgId"));
		item.setCustomerName(jsonItem.getString("customerName"));
		item.setCustomerId(jsonItem.getString("customerId"));
		item.setNlimitmny(Double.valueOf(jsonItem.getString("nlimitmny")));
		item.setNengrossmny(Double.valueOf(jsonItem.getString("nengrossmny")));
		item.setNbalancemny(Double.valueOf(jsonItem.getString("nbalancemny")));
		item.setCorigcurrencyid(jsonItem.getString("corigcurrencyid"));
		item.setCreator(jsonItem.getString("creator"));
		item.setCreatetime(Long.parseLong(jsonItem.getString("createtime")));
		item.setModifier(jsonItem.getString("modifier"));
		item.setModifytime(System.currentTimeMillis());
		item.setRemark(jsonItem.getString("remark"));
		item.setUtc(Long.parseLong(jsonItem.getString("utc")));
		item.setCorigcurrencyName(jsonItem.getString("corigcurrencyName"));
		item.setCustomerCode(jsonItem.getString("customerCode"));
		return item;
	}
	
}
