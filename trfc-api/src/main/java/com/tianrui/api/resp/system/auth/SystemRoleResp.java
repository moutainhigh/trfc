package com.tianrui.api.resp.system.auth;

import java.io.Serializable;

public class SystemRoleResp implements Serializable{

	private static final long serialVersionUID = -5817774971738348903L;

	private String id;
	private String name;
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
	
	
	

}
