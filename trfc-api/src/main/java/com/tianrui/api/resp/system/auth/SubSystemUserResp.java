package com.tianrui.api.resp.system.auth;

import java.io.Serializable;

public class SubSystemUserResp implements Serializable{

	private static final long serialVersionUID = -6047532361711189172L;

	private String id;
	private String name;
	private String userType;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}
