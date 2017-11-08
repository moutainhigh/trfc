package com.tianrui.api.req.android;

import com.tianrui.api.req.BaseReq;
/**
 * 用户登录
 * @author lixp
 *
 */
public class LoginUserParam extends BaseReq {

	private static final long serialVersionUID = 7754051527490768321L;
	//用户id
	private String id;
	//登录账户
	private String account;
	//登录密码
	private String pwd;
	//待修改密码
	private String newPwd;
	//手机号
	private String mobilePhone;
	//身份（1：客户，2：供应商）
	private String IDType;
	
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getIDType() {
		return IDType;
	}
	public void setIDType(String IDType) {
		this.IDType = IDType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}