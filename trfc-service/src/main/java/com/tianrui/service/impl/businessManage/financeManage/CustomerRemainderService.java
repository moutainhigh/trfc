package com.tianrui.service.impl.businessManage.financeManage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.basicFile.finance.ICustomerRemainderService;
import com.tianrui.service.bean.businessManage.financeManage.CustomerRemainder;
import com.tianrui.service.mapper.businessManage.financeManage.CustomerRemainderMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class CustomerRemainderService implements ICustomerRemainderService {

	@Autowired
	private CustomerRemainderMapper CustomerRemainderMapper;

	@Override
	public Result findMaxUtc(String orgId) {
		Result rs = Result.getErrorResult();
		Long maxUtc = CustomerRemainderMapper.findMaxUtc(orgId);
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		CustomerRemainder item = new CustomerRemainder();
		item.setId(jsonItem.getString("id"));
		item.setOrgName(jsonItem.getString("orgName"));
		item.setOrgId(jsonItem.getString("orgId"));
		item.setCustomerName(jsonItem.getString("customerName"));
		item.setCustomerId(jsonItem.getString("customerId"));
		item.setNlimitmny(Double.valueOf(jsonItem.getString("nlimitmny") == null || jsonItem.getString("nlimitmny").equals("") ? "0" : jsonItem.getString("nlimitmny")));
		item.setNengrossmny(Double.valueOf(jsonItem.getString("nengrossmny")==null||jsonItem.getString("nengrossmny")==""? "0":jsonItem.getString("nengrossmny")));
		item.setNbalancemny(Double.valueOf(jsonItem.getString("nbalancemny")==null||jsonItem.getString("nbalancemny")==""? "0":jsonItem.getString("nbalancemny")));
		item.setCorigcurrencyid(jsonItem.getString("corigcurrencyid"));
		item.setCreatetime(System.currentTimeMillis());
		item.setModifytime(System.currentTimeMillis());
		item.setRemark(jsonItem.getString("memo"));
		try {
			item.setUtc(format.parse(jsonItem.getString("ts")).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		item.setCorigcurrencyName(jsonItem.getString("corigcurrencyName"));
		item.setCustomerCode(jsonItem.getString("customerCode") == null ? "" : jsonItem.getString("customerCode"));
		item.setStatus(jsonItem.getString("status"));
		return item;
	}
	
}
