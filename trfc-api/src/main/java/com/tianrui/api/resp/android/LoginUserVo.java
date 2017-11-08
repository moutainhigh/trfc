package com.tianrui.api.resp.android;

import java.io.Serializable;

public class LoginUserVo implements Serializable{

	private static final long serialVersionUID = -6047532361711189172L;

	private String id;
	private String ncid;
	private String token;
	private String userName;
	private String mobile;
	private String orgid;
    private String orgName;
    //身份类型：1客户，2供应商，3普通用户
	private String IDType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getIDType() {
		return IDType;
	}
	public void setIDType(String IDType) {
		this.IDType = IDType;
	}
	public String getNcid() {
		return ncid;
	}
	public void setNcid(String ncid) {
		this.ncid = ncid;
	}
	
}
