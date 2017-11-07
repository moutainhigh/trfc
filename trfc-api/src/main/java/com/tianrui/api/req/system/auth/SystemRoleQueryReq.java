package com.tianrui.api.req.system.auth;

import com.tianrui.api.req.BaseReq;
/**
 * 角色管理req
 * @author lixp
 *
 */
public class SystemRoleQueryReq extends BaseReq {

	private static final long serialVersionUID = 2174287526269962856L;
	//当前用户id
	private String currUId;
	private String id;
	private String userid;
	private Integer start;
	private Integer limit;
	
	private String name;
	private String code;
	//角色列表名称
	private String rolename;
	
	private String roleType;
	
	public String getCurrUId() {
		return currUId;
	}

	public void setCurrUId(String currUId) {
		this.currUId = currUId;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	
}