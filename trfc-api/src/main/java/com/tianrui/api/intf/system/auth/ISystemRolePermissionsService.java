package com.tianrui.api.intf.system.auth;

import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * @Description 角色权限
 * @author zhanggaohao
 * @version 2017年5月3日 上午10:40:03
 */
public interface ISystemRolePermissionsService {
	/**
	 * @Description 根据角色查询用户信息
	 * @author zhanggaohao
	 * @version 2017年5月3日 上午10:39:59
	 * @param req
	 * @return
	 */
	Result queryUserByRole(SystemUserQueryReq req);
	/**
	 * @Description 根据角色查询所有用户信息
	 * @author zhanggaohao
	 * @version 2017年5月3日 下午4:47:44
	 * @param req
	 * @return
	 */
	Result queryAllUserByRole(SystemUserQueryReq req);

}
