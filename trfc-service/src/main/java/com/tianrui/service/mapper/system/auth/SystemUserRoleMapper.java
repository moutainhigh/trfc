package com.tianrui.service.mapper.system.auth;

import java.util.List;

import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.api.resp.system.auth.SystemUserRoleResp;
import com.tianrui.service.bean.system.auth.SystemRole;
import com.tianrui.service.bean.system.auth.SystemUserRole;

/**
 * @Description 用户与角色关系Mapper
 * @author zhanggaohao
 * @version 2017年4月27日 上午11:34:07
 */
public interface SystemUserRoleMapper {
	int deleteByPrimaryKey(String id);

	int insert(SystemUserRole record);

	int insertSelective(SystemUserRole record);

	SystemUserRole selectByPrimaryKey(String id);

	List<SystemUserRole> selectByUserRole(String id);
	
	List<SystemUserRole> selectByUserId(String id);

	int updateByPrimaryKeySelective(SystemUserRole record);

	int updateByPrimaryKey(SystemUserRole record);

	List<SystemUserRoleResp> queryUserByRole(SystemUserQueryReq req);

	List<SystemUserRoleResp> queryAllUserByRole(SystemUserQueryReq req);

	int insertBatch(List<SystemUserRole> list);

	int deleteUserToRole(List<String> list);

	int deleteByUserRole(String id);

	SystemUserRole iphoneRole(String id);
	
	List<SystemRole> subsystemRole(String id);
}