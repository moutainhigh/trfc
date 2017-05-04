package com.tianrui.api.intf.system.auth;

import com.tianrui.api.req.system.auth.SystemRoleQueryReq;
import com.tianrui.api.req.system.auth.SystemRoleSaveReq;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 角色管理
 * @author Administrator
 *
 */
public interface ISystemRoleService {
	/**
	 * 角色分页查询
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result page(SystemRoleQueryReq req) throws Exception;
	/**
	 * 角色详情查询
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result detail(SystemRoleQueryReq req) throws Exception;
	/**
	 * 添加角色
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result addRole(SystemRoleSaveReq  req) throws Exception;
	/**
	 * 编辑角色
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result editRole(SystemRoleSaveReq  req) throws Exception;
	/**
	 * 删除角色
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result delRole(SystemRoleQueryReq  req) throws Exception;
	/**
	 * @Description 查询所有角色
	 * @author zhanggaohao
	 * @version 2017年5月2日 下午2:19:07
	 * @return
	 * @throws Exception 
	 */
	Result queryAllRole() throws Exception;
	
}
