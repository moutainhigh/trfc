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
	
	private String id;
	private String name;
	private String code;
	
	private Integer start;
	private Integer limit;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}