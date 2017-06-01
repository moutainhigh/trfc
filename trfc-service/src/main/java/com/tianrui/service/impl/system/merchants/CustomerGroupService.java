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
import com.tianrui.api.intf.system.merchants.ICustomerGroupService;
import com.tianrui.api.req.system.merchants.AppUserGroupReq;
import com.tianrui.api.req.system.merchants.CustomerGroupQuery;
import com.tianrui.api.req.system.merchants.CustomerGroupSave;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.api.resp.system.merchants.AppCutoverGroup;
import com.tianrui.api.resp.system.merchants.CustomerGroupResp;
import com.tianrui.service.bean.system.merchants.CustomerGroup;
import com.tianrui.service.mapper.system.merchants.CustomerGroupMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class CustomerGroupService implements ICustomerGroupService {
    
	@Autowired
	private CustomerGroupMapper customerGroupMapper;
	@Autowired
	private ISystemUserService systemUserService;
	
	@Override
	public PaginationVO<CustomerGroupResp> page(CustomerGroupQuery query){
		PaginationVO<CustomerGroupResp> page = null;
		if(query != null){
			page = new PaginationVO<CustomerGroupResp>();
			long count = customerGroupMapper.selectCustomerGroupPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1) * query.getPageSize());
				query.setLimit(query.getPageSize());
				List<CustomerGroupResp> list = customerGroupMapper.selectCustomerGroupPage(query);
				page.setList(list);
			}
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
			page.setTotal(count);
		}
		return page;
	}

	@Override
	public Result addCustomerGroup(CustomerGroupSave save) {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getCustomerid())){
			if(validateCustomer(save.getCustomerid(), result)){
				CustomerGroup bean = new CustomerGroup();
				bean.setId(UUIDUtil.getId());
				bean.setCustomerid(save.getCustomerid());
				bean.setGroupid(save.getCustomerid());
				bean.setState("1");
				bean.setCreator(save.getCurrId());
				bean.setCreatetime(System.currentTimeMillis());
				bean.setRemark(save.getRemark());
				if(customerGroupMapper.insertSelective(bean) == 1){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}
	
	public boolean validateCustomer(String customerid, Result result){
		if(customerGroupMapper.validateCustomer(customerid) != null){
			result.setErrorCode(ErrorCode.SUPPLIER_GROUP_ERROR0);
			return false;
		}
		return true;
	}

	@Override
	public Result addCustomerToGroup(String groupid, String childrenList, String userid) {
		Result result = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(groupid) 
				&& StringUtils.isNotBlank(childrenList) 
				&& StringUtils.isNotBlank(userid)){
			List<JSONObject> jsonArray = JSONArray.parseArray(childrenList, JSONObject.class);
			if(CollectionUtils.isNotEmpty(jsonArray)){
				List<CustomerGroup> list = new ArrayList<CustomerGroup>();
				for(JSONObject jsonObject : jsonArray){
					if(validateCustomer(jsonObject.getString("customerid"), result)){
						CustomerGroup bean = new CustomerGroup();
						bean.setId(UUIDUtil.getId());
						bean.setCustomerid(jsonObject.getString("customerid"));
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
				if(customerGroupMapper.insertBatch(list) == jsonArray.size()){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}

	@Override
	public Result customerGroupCutover(String customerid) {
		Result result = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(customerid)){
			CustomerGroup customerGroup = customerGroupMapper.validateCustomer(customerid);
			if(customerGroup != null){
				List<AppCutoverGroup> list = customerGroupMapper.selectCustomerByGroupId(customerGroup.getGroupid());
				result.setData(list);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.SUPPLIER_GROUP_ERROR1);
			}
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
	public Result customerGroupDetail(CustomerGroupQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getGroupid())){
			List<CustomerGroupResp> list = customerGroupMapper.customerGroupDetail(query);
			result.setData(list);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}
	
}