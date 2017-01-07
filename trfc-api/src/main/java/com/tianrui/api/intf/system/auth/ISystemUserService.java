package com.tianrui.api.intf.system.auth;

import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface ISystemUserService {
	
	/**
	 * 子系统登录验证
	 * @return
	 * @throws Exception
	 */
	Result apiLogin(UserReq req) throws Exception;

}
