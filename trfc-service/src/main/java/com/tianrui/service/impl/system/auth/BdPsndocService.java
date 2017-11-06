package com.tianrui.service.impl.system.auth;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.system.auth.IBdPsndocService;
import com.tianrui.api.req.system.auth.BdPsndocReq;
import com.tianrui.service.bean.system.auth.BdPsndoc;
import com.tianrui.service.mapper.system.auth.BdPsndocMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * BdPsndocService
 * @author YangZhenFu
 * @createtime 2017年2月21日 下午14:47:40
 * @classname BdPsndocService.java
 */
@Service
public class BdPsndocService implements IBdPsndocService{

	@Autowired
	private BdPsndocMapper bdPsndocMapper;
	
	@Override
	public Result findMaxUtc(BdPsndocReq query) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(query !=null  ){
			Date max =bdPsndocMapper.findMaxUtc();
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			rs.setData(max);
		}
		return rs;
	}

	@Override
	public Result updateDataWithDC(List<JSONObject> list) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(CollectionUtils.isNotEmpty(list) ){
			Set<String> idSet = getAllDb();
			// 区分新增还是修改
			List<BdPsndoc>  toSave = new ArrayList<BdPsndoc>();
			List<BdPsndoc>  toUpdate = new ArrayList<BdPsndoc>();
			for(JSONObject jsonItem :list ){
				if( idSet.contains(jsonItem.get("id"))){
					toUpdate.add(converJson2Bean(jsonItem));
				}else{
					toSave.add(converJson2Bean(jsonItem));
				}
			}
			//新增的调批量保存
			if(CollectionUtils.isNotEmpty(toSave)){
				bdPsndocMapper.insertBatch(toSave);
			}
			//修改的就一个一个的调用修改
			if( CollectionUtils.isNotEmpty(toUpdate) ){
				for( BdPsndoc item:toUpdate ){
					bdPsndocMapper.updateByPrimaryKeySelective(item);
				}
			}
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return rs;
	}
	private Set<String> getAllDb(){
		Set<String> set =new HashSet<>();
		List<BdPsndoc> dbList =bdPsndocMapper.selectSelective(null);
		
		if( CollectionUtils.isNotEmpty(dbList) ){
			for(BdPsndoc manage:dbList){
				set.add(manage.getId());
			}
		}
		return set;
	}
	private BdPsndoc converJson2Bean(JSONObject jsonItem) throws ParseException{
		BdPsndoc item  =new BdPsndoc();
		item.setId(jsonItem.getString("id"));
		item.setName(jsonItem.getString("name"));
		item.setAccount(jsonItem.getString("account"));
		item.setSex(jsonItem.getString("sex"));
		item.setBirthDate(jsonItem.getString("birthDate"));
		item.setIdCard(jsonItem.getString("idCard"));
		item.setIdCardType(jsonItem.getString("idCardType"));
		item.setAddr(jsonItem.getString("addr"));
		item.setMobile(jsonItem.getString("mobile"));
		item.setOrgId(jsonItem.getString("orgId"));
		item.setTs(jsonItem.getTimestamp("ts"));
		item.setCreateTime(System.currentTimeMillis());
		item.setRemark(jsonItem.getString("remark"));
		item.setStatus(Integer.valueOf(jsonItem.getString("status")));
		return item;
	}
}
