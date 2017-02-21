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
import com.tianrui.api.intf.system.auth.ISmUserService;
import com.tianrui.api.req.system.auth.SmUserReq;
import com.tianrui.service.bean.system.auth.SmUser;
import com.tianrui.service.mapper.system.auth.SmUserMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * SmUserService
 * @author YangZhenFu
 * @createtime 2017年2月20日 下午13:54:40
 * @classname SmUserService.java
 */
@Service
public class SmUserService implements ISmUserService{
	
	@Autowired
	private SmUserMapper smUserMapper;
	
	@Override
	public Result findMaxUtc(SmUserReq query) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(query !=null  ){
			Date max =smUserMapper.findMaxUtc();
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
			List<SmUser>  toSave = new ArrayList<SmUser>();
			List<SmUser>  toUpdate = new ArrayList<SmUser>();
			for(JSONObject jsonItem :list ){
				if( idSet.contains(jsonItem.get("id"))){
					toUpdate.add(converJson2Bean(jsonItem));
				}else{
					toSave.add(converJson2Bean(jsonItem));
				}
			}
			//新增的调批量保存
			if(CollectionUtils.isNotEmpty(toSave)){
				smUserMapper.insertBatch(toSave);
			}
			//修改的就一个一个的调用修改
			if( CollectionUtils.isNotEmpty(toUpdate) ){
				for( SmUser item:toUpdate ){
					smUserMapper.updateByPrimaryKeySelective(item);
				}
			}
		}
		return rs;
	}
	private Set<String> getAllDb(){
		Set<String> set =new HashSet<>();
		List<SmUser> dbList =smUserMapper.selectSelective(null);
		
		if( CollectionUtils.isNotEmpty(dbList) ){
			for(SmUser manage:dbList){
				set.add(manage.getId());
			}
		}
		return set;
	}
	private SmUser converJson2Bean(JSONObject jsonItem) throws ParseException{
		SmUser item  =new SmUser();
		item.setId(jsonItem.getString("id"));
		item.setName(jsonItem.getString("name"));
		item.setCode(jsonItem.getString("code"));
		item.setPkBaseDoc(jsonItem.getString("pkBaseDoc"));
		item.setUserNote(jsonItem.getString("userNote"));
		item.setPkPsndoc(jsonItem.getString("pkPsndoc"));
		item.setPkCustomer(jsonItem.getString("pkCustomer"));
		item.setPkSupplier(jsonItem.getString("pkSupplier"));
		item.setBaseDocType(Integer.valueOf(jsonItem.getString("baseDocType")));
		item.setOrgId(jsonItem.getString("orgId"));
		item.setGroupId(jsonItem.getString("groupId"));
		item.setTs(jsonItem.getTimestamp("ts"));
		item.setCreateTime(System.currentTimeMillis());
		item.setStatus(Integer.valueOf(jsonItem.getString("status")));
		return item;
	}
}
