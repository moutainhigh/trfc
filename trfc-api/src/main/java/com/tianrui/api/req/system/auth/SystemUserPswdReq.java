package com.tianrui.api.req.system.auth;

import com.tianrui.api.req.BaseReq;
/**
 * 用户登录
 * @author lixp
 *
 */
public class SystemUserPswdReq extends BaseReq {

	private static final long serialVersionUID = 2174287526269962856L;
	private String id;
	
	private String oldPswd;
	private String newPswd;
	//当前用户
	private String currUId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOldPswd() {
		return oldPswd;
	}
	public void setOldPswd(String oldPswd) {
		this.oldPswd = oldPswd;
	}
	public String getNewPswd() {
		return newPswd;
	}
	public void setNewPswd(String newPswd) {
		this.newPswd = newPswd;
	}
	public String getCurrUId() {
		return currUId;
	}
	public void setCurrUId(String currUId) {
		this.currUId = currUId;
	}
	
	
}