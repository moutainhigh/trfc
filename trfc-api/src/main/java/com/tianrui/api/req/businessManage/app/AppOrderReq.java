package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.req.BaseReq;

public class AppOrderReq extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;
	private String userId;
	//订单 通知单 过磅单id
	private String id;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
