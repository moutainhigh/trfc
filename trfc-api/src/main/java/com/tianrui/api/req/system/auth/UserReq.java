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
	private String newPswd;
	//子系统编码    01=门禁 02=磅房 03=排队  04=卡务 
	private String subSystemCode;
	
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
	public String getSubSystemCode() {
		return subSystemCode;
	}
	public void setSubSystemCode(String subSystemCode) {
		this.subSystemCode = subSystemCode;
	}
	
	
}