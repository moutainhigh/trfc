package com.tianrui.api.intf.system.auth;

import java.util.List;

import com.tianrui.api.req.businessManage.app.AppUserEditReq;
import com.tianrui.api.req.system.auth.AppUserReq;
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
	List<SystemUserResp> autoCompleteSearch(SystemUserQueryReq req) throws Exception;
	/**
	 * @Description 根据id获取用户
	 * @author zhanggaohao
	 * @version 2017年4月13日 上午11:48:31
	 * @param id
	 * @param isFlush 是否刷新缓存
	 * @return
	 * @throws Exception 
	 */
	SystemUserResp get(String id, boolean isFlush) throws Exception;
	/**
	 * @Description 根据id获取用户
	 * @author zhanggaohao
	 * @version 2017年4月13日 下午2:47:02
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SystemUserResp get(String id) throws Exception;
	/**
	 * @Description app登录接口
	 * @author zhanggaohao
	 * @version 2017年4月13日 下午3:11:07
	 * @param body
	 * @throws Exception 
	 */
	Result appLogin(AppUserReq req) throws Exception;
	/**
	 * @Description app修改密码接口
	 * @author zhanggaohao
	 * @version 2017年4月13日 下午3:49:39
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result appUpdatePswd(AppUserReq req) throws Exception;
	/**
	 * @Description app修改用户名称
	 * @author zhanggaohao
	 * @version 2017年4月18日 上午11:35:39
	 * @param req
	 * @return
	 */
	Result appUpdateUser(AppUserEditReq req);
	/**
	 * @Description 获取所有用户列表
	 * @author zhanggaohao
	 * @version 2017年4月27日 下午5:25:37
	 * @param orgid
	 * @return
	 */
	Result queryAllUser(String orgid);

	Result userCutover(String key, String ncid, String identityTypes) throws Exception;
	/**
	 * @Description 绑定手机号
	 * @author zhanggaohao
	 * @version 2017年5月11日 下午4:22:16
	 * @param req
	 * @return
	 */
	Result bindPhone(AppUserReq req);
	/**
	 * @Description 解绑手机号
	 * @author zhanggaohao
	 * @version 2017年5月11日 下午4:48:25
	 * @param req
	 * @return
	 */
	Result unBindPhone(AppUserReq req);
}
