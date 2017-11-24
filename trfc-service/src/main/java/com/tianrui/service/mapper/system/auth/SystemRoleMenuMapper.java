package com.tianrui.service.mapper.system.auth;

import java.util.List;

import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.api.resp.system.auth.SystemRoleMenuResp;
import com.tianrui.service.bean.system.auth.SystemRoleMenu;
import com.tianrui.service.bean.system.auth.SystemUserRole;

public interface SystemRoleMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemRoleMenu record);

    int insertSelective(SystemRoleMenu record);

    SystemRoleMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemRoleMenu record);

    int updateByPrimaryKey(SystemRoleMenu record);

	List<SystemRoleMenuResp> queryMenuByRole(SystemUserQueryReq req);
    List<SystemRoleMenuResp> queryIphoneByRole(SystemUserQueryReq req);
    List<SystemRoleMenuResp> querySubsystemByRole(SystemUserQueryReq req);
    
	List<SystemRoleMenuResp> selectRole(SystemUserQueryReq req);
    List<SystemRoleMenuResp> selectIphoneRole(SystemUserQueryReq req);
	List<SystemRoleMenuResp> selectSubsystemRole(SystemUserQueryReq req);
	int deleteByRoleId(String roleId);

	int insertBatch(List<SystemRoleMenu> list);
	
	List<SystemRoleMenuResp> iphoneRole(List<SystemUserRole> lists);

	List<SystemRoleMenuResp> subsystemRole(String id);
}