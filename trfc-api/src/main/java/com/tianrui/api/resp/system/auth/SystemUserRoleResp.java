package com.tianrui.api.resp.system.auth;

import com.tianrui.api.resp.BaseResp;

public class SystemUserRoleResp extends BaseResp {

	private static final long serialVersionUID = -8430470030272964889L;
	//关系表主键id
	private String id;
	//用户id
	private String userid;
	//用户名称
	private String username;
	//用户编码
	private String usercode;
	//用户备注
	private String userremark;
	
	public String getId() {
		return id;
	}
	public String getUserid() {
		return userid;
	}
	public String getUsername() {
		return username;
	}
	public String getUsercode() {
		return usercode;
	}
	public String getUserremark() {
		return userremark;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public void setUserremark(String userremark) {
		this.userremark = userremark;
	}
	
}
