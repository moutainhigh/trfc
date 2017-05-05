package com.tianrui.api.resp.system.auth;

import com.tianrui.api.resp.BaseResp;

public class SystemRoleMenuResp extends BaseResp {

	private static final long serialVersionUID = 6745655432244373179L;
	//关系表主键id
	private String id;
	//菜单id
	private String menuid;
	//菜单名称
	private String menuname;
	//菜单编号
	private String menucode;
	//菜单排序
	private String orderBy;
	//菜单说明
	private String info;
	//是否授权此菜单
	private String roleHasMenu;
	//角色id
	private String roleid;
	
	public String getId() {
		return id;
	}
	public String getMenuid() {
		return menuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public String getMenucode() {
		return menucode;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public String getInfo() {
		return info;
	}
	public String getRoleHasMenu() {
		return roleHasMenu;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public void setRoleHasMenu(String roleHasMenu) {
		this.roleHasMenu = roleHasMenu;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
}
