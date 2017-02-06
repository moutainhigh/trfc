package com.tianrui.api.req.basicFile.measure;

import com.tianrui.api.req.BaseReq;

public class TransportunitManageQuery extends BaseReq{

	private static final long serialVersionUID = 1032784613760075829L;
	
	private String id;

    private String internalcode;

    private String name;
    
    private String isvalid;

    private String orgid;

    private String orgname;

    private String state;
    
    private Integer start;
    
    private Integer limit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInternalcode() {
		return internalcode;
	}

	public void setInternalcode(String internalcode) {
		this.internalcode = internalcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
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
