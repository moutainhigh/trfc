package com.tianrui.service.impl.basicFile.finance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.basicFile.finance.IPrmTariffService;
import com.tianrui.api.req.BaseReq;
import com.tianrui.service.bean.basicFile.finance.PrmTariff;
import com.tianrui.service.mapper.basicFile.finance.PrmTariffMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class PrmTariffService implements IPrmTariffService {

	@Autowired
	PrmTariffMapper  prmTariffMapper;
	
	@Override
	public Result findMaxUtc(BaseReq query) throws Exception {
		Result rs =Result.getParamErrorResult();
		String max =prmTariffMapper.findMaxUtc();
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
			List<PrmTariff>  toSave = new ArrayList<PrmTariff>();
			List<PrmTariff>  toUpdate = new ArrayList<PrmTariff>();
			for(JSONObject jsonItem :list ){
				if( idSet.contains(jsonItem.get("id"))){
					toUpdate.add(converJson2Bean(jsonItem));
				}else{
					toSave.add(converJson2Bean(jsonItem));
				}
			}
			//新增的调批量保存
			if(CollectionUtils.isNotEmpty(toSave)){
				prmTariffMapper.insertBatch(toSave);
			}
			//修改的就一个一个的调用修改
			if( CollectionUtils.isNotEmpty(toUpdate) ){
				for( PrmTariff item:toUpdate ){
					prmTariffMapper.updateByPrimaryKeySelective(item);
				}
			}
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return rs;
	}
	
	private Set<String> getAllDb(){
		Set<String> set =new HashSet<String>();
		List<String> ids =prmTariffMapper.selectIds();
		if( CollectionUtils.isNotEmpty(ids) ){
			for(String id:ids){
				set.add(id);
			}
		}
		return set;
	}
	
	
	private PrmTariff converJson2Bean(JSONObject jsonItem){
		PrmTariff item  =new PrmTariff();
		
		item.setId(jsonItem.getString("ncid"));
		item.setNcid(jsonItem.getString("ncid"));
		item.setCustomerId(jsonItem.getString("customerId"));
		item.setCustomerCode(jsonItem.getString("customerCode"));
		item.setChanneltypeId(jsonItem.getString("channeltypeId"));
		
		item.setChanneltypeCode(jsonItem.getString("channeltypeCode"));
		item.setSaleOrgId(jsonItem.getString("saleOrgId"));
		item.setDeliveryOrgId(jsonItem.getString("deliveryOrgId"));
		item.setMaterialId(jsonItem.getString("materialId"));
		item.setMaterialCode(jsonItem.getString("materialCode"));
		item.setNprice1(jsonItem.getString("nprice1"));
		
		
		
		item.setTs(jsonItem.getString("ts"));
		item.setDesc1(jsonItem.getString("desc1"));
		item.setDesc2(jsonItem.getString("desc2"));
		item.setDesc3(jsonItem.getString("desc3"));
		item.setDesc4(jsonItem.getString("desc4"));
		
		item.setCreateTime(System.currentTimeMillis());

		return item;
	}
	

}