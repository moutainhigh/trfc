package com.tianrui.api.req.system.auth;

import com.tianrui.api.req.BaseReq;
/**
 * 角色管理req
 * @author lixp
 *
 */
public class SystemRoleSaveReq extends BaseReq {

	private static final long serialVersionUID = 2174287526269962856L;
	
	private String id;
    //角色编码
    private String code;
    //角色名称
    private String name;
    //角色列表名称
    private String rolename;
    //角色分类
    private String roleType;
    //有效性  默认无效 0无效 1有效
    private Byte isvalid;
    //允许编辑 默认无效 0无效 1有效
    private Byte allowEdit;
    //允许编辑 默认无效 0无效 1有效
    private Byte allowDel;
    //说明
    private String info;
    //用户类型
    private String userType;
	//当前用户
	private String currUId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrUId() {
		return currUId;
	}
	public void setCurrUId(String currUId) {
		this.currUId = currUId;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public Byte getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Byte isvalid) {
		this.isvalid = isvalid;
	}
	public Byte getAllowEdit() {
		return allowEdit;
	}
	public void setAllowEdit(Byte allowEdit) {
		this.allowEdit = allowEdit;
	}
	public Byte getAllowDel() {
		return allowDel;
	}
	public void setAllowDel(Byte allowDel) {
		this.allowDel = allowDel;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}