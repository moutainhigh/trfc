package com.tianrui.service.impl.system.auth;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.system.auth.ISystemRolePermissionsService;
import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.api.resp.system.auth.SystemUserRoleResp;
import com.tianrui.service.mapper.system.auth.SystemUserRoleMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SystemRolePermissionsService implements ISystemRolePermissionsService {

	@Autowired
	private SystemUserRoleMapper systemUserRoleMapper;
	
	@Override
	public Result queryUserByRole(SystemUserQueryReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getRoleid())){
			List<SystemUserRoleResp> list = systemUserRoleMapper.queryUserByRole(req);
			result.setData(list);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	@Override
	public Result queryAllUserByRole(SystemUserQueryReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getRoleid())){
			List<SystemUserRoleResp> list = systemUserRoleMapper.queryAllUserByRole(req);
			result.setData(list);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}
	
}