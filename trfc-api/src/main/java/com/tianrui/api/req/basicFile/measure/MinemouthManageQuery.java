package com.tianrui.api.req.basicFile.measure;

import com.tianrui.api.req.BaseReq;

public class MinemouthManageQuery extends BaseReq{

	private static final long serialVersionUID = 6005365042203233549L;
	
	private String id;
	
	private String code;
	
    private String name;
    
    private String pinyincode;
    
    private String isvalid;

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

	public String getPinyincode() {
		return pinyincode;
	}

	public void setPinyincode(String pinyincode) {
		this.pinyincode = pinyincode;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
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
