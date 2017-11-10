package com.tianrui.api.intf.system.auth;

import com.tianrui.api.req.system.auth.SystemMenuQueryReq;
import com.tianrui.api.req.system.auth.SystemMenuSaveReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface ISubsystemService {
	/**
	 * 菜单分页查询
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result page(SystemMenuQueryReq req) throws Exception;
	
	
	/**
	 * 菜单详情查询
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result detail(SystemMenuQueryReq req) throws Exception;
	
	
	/**
	 * 添加菜单
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result addMenu(SystemMenuSaveReq  req) throws Exception;
	
	/**
	 * 编辑菜单
	 * @param req
	 * @return
	 * @throws Exception
	 */	
	Result editMenu(SystemMenuSaveReq  req) throws Exception;
	/**
	 * 删除菜单
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result delMenu(SystemMenuQueryReq  req) throws Exception;
	/**
	 * 获取下拉树数据
	 */
	Result getTreeData() throws Exception;
	/**
	 * @Description 根据用户id查询拥有的角色的菜单权限
	 * @author zhanggaohao
	 * @version 2017年5月7日 下午2:12:20
	 * @param uId
	 * @return
	 * @throws Exception
	 */
	Result findMenuByUserId(String uId) throws Exception;
	
}
