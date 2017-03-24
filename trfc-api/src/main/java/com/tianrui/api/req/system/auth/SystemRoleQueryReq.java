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
	private String id;
	
	private Integer start;
	private Integer limit;
	
	private String name;
	private String code;

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