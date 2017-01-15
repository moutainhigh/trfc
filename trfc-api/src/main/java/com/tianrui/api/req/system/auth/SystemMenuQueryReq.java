package com.tianrui.api.req.system.auth;

import com.tianrui.api.req.BaseReq;
/**
 * 用户登录
 * @author lixp
 *
 */
public class SystemMenuQueryReq extends BaseReq {

	private static final long serialVersionUID = 2174287526269962856L;
	private String codeLike;
	private String accountLike;
	private String nameLike;
	
	private String currUId;

	public String getCodeLike() {
		return codeLike;
	}

	public void setCodeLike(String codeLike) {
		this.codeLike = codeLike;
	}

	public String getAccountLike() {
		return accountLike;
	}

	public void setAccountLike(String accountLike) {
		this.accountLike = accountLike;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public String getCurrUId() {
		return currUId;
	}

	public void setCurrUId(String currUId) {
		this.currUId = currUId;
	}
	
	
	
}