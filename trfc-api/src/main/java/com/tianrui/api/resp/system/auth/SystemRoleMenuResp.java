package com.tianrui.api.resp.system.auth;

import com.tianrui.api.resp.BaseResp;

public class SystemRoleMenuResp extends BaseResp {

	private static final long serialVersionUID = 6745655432244373179L;
	//关系表主键id
	private String id;
	//菜单id
	private String menuId;
	//菜单名称
	private String menuName;
	//菜单编号
	private String menuCode;
	//父菜单id
	private String menuPid;
	//菜单排序
	private String orderBy;
	//菜单说明
	private String info;
	//是否授权此菜单
	private String roleHasMenu;
	//角色id
	private String roleId;
	
	public String getId() {
		return id;
	}
	public String getMenuId() {
		return menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public String getMenuPid() {
		return menuPid;
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
	public String getRoleId() {
		return roleId;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
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
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
