package com.tianrui.service.impl.system.merchants;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.merchants.ISupplierGroupService;
import com.tianrui.api.req.system.merchants.AppUserGroupReq;
import com.tianrui.api.req.system.merchants.SupplierGroupQuery;
import com.tianrui.api.req.system.merchants.SupplierGroupSave;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.api.resp.system.merchants.AppSupplierGroup;
import com.tianrui.api.resp.system.merchants.SupplierGroupResp;
import com.tianrui.service.bean.system.merchants.SupplierGroup;
import com.tianrui.service.mapper.system.merchants.SupplierGroupMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SupplierGroupService implements ISupplierGroupService {
    
	@Autowired
	private SupplierGroupMapper supplierGroupMapper;
	@Autowired
	private ISystemUserService systemUserService;
	
	@Override
	public PaginationVO<SupplierGroupResp> page(SupplierGroupQuery query){
		PaginationVO<SupplierGroupResp> page = null;
		if(query != null){
			page = new PaginationVO<SupplierGroupResp>();
			long count = supplierGroupMapper.selectSupplierGroupPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1) * query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SupplierGroupResp> list = supplierGroupMapper.selectSupplierGroupPage(query);
				page.setList(list);
			}
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
			page.setTotal(count);
		}
		return page;
	}

	@Override
	public Result addSupplierGroup(SupplierGroupSave save) {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getSupplierid())){
			if(validateSupplier(save.getSupplierid(), result)){
				SupplierGroup bean = new SupplierGroup();
				bean.setId(UUIDUtil.getId());
				bean.setSupplierid(save.getSupplierid());
				bean.setGroupid(save.getSupplierid());
				bean.setState("1");
				bean.setCreator(save.getCurrId());
				bean.setCreatetime(System.currentTimeMillis());
				bean.setRemark(save.getRemark());
				if(supplierGroupMapper.insertSelective(bean) == 1){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}
	
	public boolean validateSupplier(String supplierid, Result result){
		if(supplierGroupMapper.validateSupplier(supplierid) != null){
			result.setErrorCode(ErrorCode.SUPPLIER_GROUP_ERROR);
			return false;
		}
		return true;
	}

	@Override
	public Result addSupplierToGroup(String groupid, String childrenList, String userid) {
		Result result = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(groupid) 
				&& StringUtils.isNotBlank(childrenList) 
				&& StringUtils.isNotBlank(userid)){
			List<JSONObject> jsonArray = JSONArray.parseArray(childrenList, JSONObject.class);
			if(CollectionUtils.isNotEmpty(jsonArray)){
				List<SupplierGroup> list = new ArrayList<SupplierGroup>();
				for(JSONObject jsonObject : jsonArray){
					if(validateSupplier(jsonObject.getString("supplierid"), result)){
						SupplierGroup bean = new SupplierGroup();
						bean.setId(UUIDUtil.getId());
						bean.setSupplierid(jsonObject.getString("supplierid"));
						bean.setGroupid(groupid);
						bean.setState("1");
						bean.setCreator(userid);
						bean.setCreatetime(System.currentTimeMillis());
						bean.setRemark(jsonObject.getString("remark"));
						list.add(bean);
					}else{
						return result;
					}
				}
				if(supplierGroupMapper.insertBatch(list) == jsonArray.size()){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}

	@Override
	public Result supplierGroupCutover(String groupId) {
		Result result = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(groupId)){
			List<AppSupplierGroup> list = supplierGroupMapper.selectSupplierByGroupId(groupId);
			result.setData(list);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	@Override
	public Result userCutover(AppUserGroupReq req) throws Exception {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getUserNcId())
				&& StringUtils.isNotBlank(req.getCurrId())
				&& StringUtils.isNotBlank(req.getKey())){
			SystemUserResp user = systemUserService.getUser(req.getCurrId());
			if(user != null){
				result = systemUserService.userCutover(req.getKey(), req.getUserNcId(), user.getIdentityTypes());
			}else{
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		}
		return result;
	}

	@Override
	public Result supplierGroupDetail(SupplierGroupQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getGroupid())){
			List<SupplierGroupResp> list = supplierGroupMapper.supplierGroupDetail(query);
			result.setData(list);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}
	
}