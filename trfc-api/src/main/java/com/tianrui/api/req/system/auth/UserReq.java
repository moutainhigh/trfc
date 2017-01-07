package com.tianrui.api.req.system.auth;

import com.tianrui.api.req.BaseReq;
/**
 * 用户登录相关
 * @author lixp 2017年1月7日 09:32:41
 *
 */
public class UserReq extends BaseReq {

	
	private static final long serialVersionUID = 7754051527490768321L;
	private String account;
	private String pswd;
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	
	
}