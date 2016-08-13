package com.tianrui.api.req.demo;

import com.tianrui.api.req.BaseReq;

public class DemoReq extends BaseReq {

	private String id;

	private String currId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurrId() {
		return currId;
	}

	public void setCurrId(String currId) {
		this.currId = currId;
	}
	
	
	
}
