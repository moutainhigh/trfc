package com.tianrui.service.impl.system.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.system.auth.ISystemMenuService;
import com.tianrui.api.req.system.auth.SystemMenuQueryReq;
import com.tianrui.api.req.system.auth.SystemMenuSaveReq;
import com.tianrui.service.mapper.system.auth.SystemMenuMapper;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SystemMenuService implements ISystemMenuService {

	@Autowired
	SystemMenuMapper systemMenuMapper;
	@Override
	public Result page(SystemMenuQueryReq req) throws Exception {

		return null;
	}

	@Override
	public Result detail(SystemMenuQueryReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result addRole(SystemMenuSaveReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result editRole(SystemMenuSaveReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delRole(SystemMenuQueryReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}