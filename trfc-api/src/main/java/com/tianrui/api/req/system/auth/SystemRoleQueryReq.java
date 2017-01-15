package com.tianrui.api.req.system.auth;

import com.tianrui.api.req.BaseReq;
/**
 * 用户登录
 * @author lixp
 *
 */
public class SystemRoleQueryReq extends BaseReq {

	private static final long serialVersionUID = 2174287526269962856L;
	private String currUId;

	public String getCurrUId() {
		return currUId;
	}

	public void setCurrUId(String currUId) {
		this.currUId = currUId;
	}
	
	
	
}