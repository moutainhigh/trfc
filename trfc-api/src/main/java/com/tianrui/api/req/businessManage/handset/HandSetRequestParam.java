package com.tianrui.api.req.businessManage.handset;

import com.tianrui.api.req.BaseReq;

public class HandSetRequestParam extends BaseReq {

	private static final long serialVersionUID = -4266984974222837083L;
	//当前登录用户id
	private String userId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}