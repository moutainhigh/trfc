package com.tianrui.api.intf.system.auth;

import com.tianrui.api.req.system.auth.SystemUserPswdReq;
import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.api.req.system.auth.SystemUserSaveReq;
import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.vo.Result;

public interface ISystemUserService {
	
	/**
	 * 子系统登录验证
	 * @return
	 * @throws Exception
	 */
	Result apiLogin(UserReq req) throws Exception;
	
	/**
	 * 用户分页查询
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result page(SystemUserQueryReq req) throws Exception;
	
	
	/**
	 * 用户详情查询
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result detail(SystemUserQueryReq req) throws Exception;
	
	
	/**
	 * 添加用户
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result addUser(SystemUserSaveReq  req) throws Exception;
	
	/**
	 * 编辑用户
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result editUser(SystemUserSaveReq  req) throws Exception;
	/**
	 * 删除用户
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result delUser(SystemUserQueryReq  req) throws Exception;
	
	/**
	 * 重置密码
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result resetUser(SystemUserQueryReq  req) throws Exception;
	/**
	 * 修改密码
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result editPswd(SystemUserPswdReq  req) throws Exception;
	
	/**
	 * 登录接口
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result login(UserReq  req) throws Exception;
	/**
	 * 查询用户
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	SystemUserResp getUser(String id) throws Exception;
	/**
	 * 自动搜索下拉框
	 * @param req
	 * @return List<SystemUserResp>
	 * @throws Exception
	 */
	Result autoCompleteSearch(SystemUserQueryReq req) throws Exception;
}
