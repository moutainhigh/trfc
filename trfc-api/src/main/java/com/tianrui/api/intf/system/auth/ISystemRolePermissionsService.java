package com.tianrui.api.intf.system.auth;

import java.util.List;

import com.tianrui.api.req.system.auth.SystemRoleMenuSave;
import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.api.req.system.auth.SystemUserRoleSave;
import com.tianrui.api.resp.system.auth.SystemRoleMenuResp;
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
	/**
	 * @Description 给角色添加用户
	 * @author zhanggaohao
	 * @version 2017年5月4日 下午2:11:43
	 * @param save
	 * @return
	 */
	Result addUserToRole(SystemUserRoleSave save);
	/**
	 * @Description 删除角色当中的用户
	 * @author zhanggaohao
	 * @version 2017年5月4日 下午4:01:47
	 * @param save
	 * @return
	 */
	Result deleteUserToRole(SystemUserRoleSave save);
	/**
	 * @Description 根据角色查询菜单
	 * @author zhanggaohao
	 * @version 2017年5月5日 上午10:24:55
	 * @param req
	 * @return
	 */
	Result queryMenuByRole(SystemUserQueryReq req);
	
	Result queryIphoneByRole(SystemUserQueryReq req);
	/**
	 * 根据角色查看权限
	 * @Title: selectRole 
	 * @Description: TODO
	 * @param @param req
	 * @param @return   
	 * @return Result    
	 * @throws
	 */
	Result selectRole(String id);
	/**
	 * @Description 给角色授权菜单权限
	 * @author zhanggaohao
	 * @version 2017年5月6日 下午1:31:38
	 * @param save
	 * @return
	 */
	Result authorizeMenuToRole(SystemRoleMenuSave save);
	/**
	 * @Description 角色重置菜单
	 * @author zhanggaohao
	 * @version 2017年5月6日 下午2:28:21
	 * @param save
	 * @return
	 */
	Result resetMenuToRole(SystemRoleMenuSave save);
	/**
	 * 手持机权限查询
	 * @Title: iphonRole 
	 * @Description: TODO
	 * @param @param id
	 * @param @return   
	 * @return Result    
	 * @throws
	 */
	Result iphonRole(String id);
	
}
