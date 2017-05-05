package com.tianrui.api.req.system.auth;

import com.tianrui.api.req.BaseReq;

public class SystemRoleMenuSave extends BaseReq {

	private static final long serialVersionUID = 6166483481082077556L;
	//角色菜单关系表ids
	private String roleMenuIdJson;
	//菜单ids  数组 -- json格式
	private String menuIdJson;
	//角色id
	private String roleId;
	//当前登录用户id
	private String currId;
	
	public String getRoleMenuIdJson() {
		return roleMenuIdJson;
	}
	public String getMenuIdJson() {
		return menuIdJson;
	}
	public String getRoleId() {
		return roleId;
	}
	public String getCurrId() {
		return currId;
	}
	public void setRoleMenuIdJson(String roleMenuIdJson) {
		this.roleMenuIdJson = roleMenuIdJson;
	}
	public void setMenuIdJson(String menuIdJson) {
		this.menuIdJson = menuIdJson;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public void setCurrId(String currId) {
		this.currId = currId;
	}

}