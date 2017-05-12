package com.tianrui.api.req.system.auth;

import com.tianrui.api.req.BaseReq;
/**
 * 用户登录
 * @author lixp
 *
 */
public class AppUserReq extends BaseReq {

	private static final long serialVersionUID = 7754051527490768321L;
	//用户id
	private String id;
	//登录账户
	private String account;
	//登录密码
	private String pswd;
	//待修改密码
	private String newPswd;
	//手机号
	private String mobilePhone;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getNewPswd() {
		return newPswd;
	}
	public void setNewPswd(String newPswd) {
		this.newPswd = newPswd;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
}