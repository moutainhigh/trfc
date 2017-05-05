package com.tianrui.api.req.system.auth;

import com.tianrui.api.req.BaseReq;

public class SystemUserRoleSave extends BaseReq {

	private static final long serialVersionUID = 6166483481082077556L;
	//用户角色关系表ids
	private String userRoleIdJson;
	//用户ids  数组 -- json格式
	private String userIdJson;
	//角色id
	private String roleId;
	//当前登录用户id
	private String currId;

	public String getUserRoleIdJson() {
		return userRoleIdJson;
	}

	public String getUserIdJson() {
		return userIdJson;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getCurrId() {
		return currId;
	}

	public void setUserRoleIdJson(String userRoleIdJson) {
		this.userRoleIdJson = userRoleIdJson;
	}

	public void setUserIdJson(String userIdJson) {
		this.userIdJson = userIdJson;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setCurrId(String currId) {
		this.currId = currId;
	}
	
}