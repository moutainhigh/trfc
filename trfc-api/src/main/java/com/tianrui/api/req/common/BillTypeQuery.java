package com.tianrui.api.req.common;

import com.tianrui.api.req.BaseReq;

public class BillTypeQuery extends BaseReq {
	
	private static final long serialVersionUID = 4325189759681138634L;

	private String id;

    private String code;

    private String name;

    private String state;
    
    private Integer start;
    
    private Integer limit;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

}