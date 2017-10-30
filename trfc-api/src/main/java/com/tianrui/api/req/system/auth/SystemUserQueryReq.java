package com.tianrui.api.req.system.auth;

import com.tianrui.api.req.BaseReq;
/**
 * 用户登录
 * @author lixp
 *
 */
public class SystemUserQueryReq extends BaseReq {

	private static final long serialVersionUID = 2174287526269962856L;
	private String id;
	
	private String codeLike;
	private String accountLike;
	private String nameLike;
	//身份类型：1客户，2供应商，3普通用户
	private String identityTypes;
	
	private String code;
	private String account;
	
	private Integer start;
	private Integer limit;
	
	private String currUId;
	//角色id
	private String roleid;
	//角色查詢用戶模糊匹配
	private String key;
	//app登录帐号
	private String appAccount;

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

	public String getIdentityTypes() {
        return identityTypes;
    }

    public void setIdentityTypes(String identityTypes) {
        this.identityTypes = identityTypes;
    }

    public String getCurrUId() {
		return currUId;
	}

	public void setCurrUId(String currUId) {
		this.currUId = currUId;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAppAccount() {
		return appAccount;
	}

	public void setAppAccount(String appAccount) {
		this.appAccount = appAccount;
	}
	
}