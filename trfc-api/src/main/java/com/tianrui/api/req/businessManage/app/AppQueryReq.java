package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.req.BaseReq;

public class AppQueryReq extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;
	private String userId;
	private String key;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
	

}
