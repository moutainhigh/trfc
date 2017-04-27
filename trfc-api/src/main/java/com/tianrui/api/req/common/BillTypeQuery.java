package com.tianrui.api.req.common;

import com.tianrui.api.req.BaseReq;

public class BillTypeQuery extends BaseReq {
	
	private static final long serialVersionUID = 4325189759681138634L;
	//主键id
    private String id;
    //订单类型code
    private String code;
    //订单类型名称
    private String name;
    //类型（0：采购，1：销售）
    private String type;
    //是否默认显示（0：否，1：是）
    private String defaultshow;
    //状态：（0：删除，1：显示）
    private String state;
    
    private Integer start;
    
    private Integer limit;

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getDefaultshow() {
		return defaultshow;
	}

	public String getState() {
		return state;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDefaultshow(String defaultshow) {
		this.defaultshow = defaultshow;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}