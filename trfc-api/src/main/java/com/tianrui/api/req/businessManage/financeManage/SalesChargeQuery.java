package com.tianrui.api.req.businessManage.financeManage;

import com.tianrui.api.req.BaseReq;

public class SalesChargeQuery extends BaseReq{

	private static final long serialVersionUID = 473461343210026343L;
	
	private String id;

    private String code;

    private String auditstatus;

    private String orgid;

    private String orgname;

    private String customerid;

    private String customername;

    private String chargeunit;

    private String auditid;

    private String auditname;

    private Long audittime;
    
    private String state;
    
    //开始时间
    private Long starttime;
    //结束时间
    private Long endtime;
    //起始页
    private Integer start;
    //条数
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

	public String getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
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

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getChargeunit() {
		return chargeunit;
	}

	public void setChargeunit(String chargeunit) {
		this.chargeunit = chargeunit;
	}

	public String getAuditid() {
		return auditid;
	}

	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}

	public String getAuditname() {
		return auditname;
	}

	public void setAuditname(String auditname) {
		this.auditname = auditname;
	}

	public Long getAudittime() {
		return audittime;
	}

	public void setAudittime(Long audittime) {
		this.audittime = audittime;
	}

	public Long getStarttime() {
		return starttime;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


}
