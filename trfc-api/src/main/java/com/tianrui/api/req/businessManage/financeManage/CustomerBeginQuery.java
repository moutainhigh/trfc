package com.tianrui.api.req.businessManage.financeManage;

import com.tianrui.api.req.BaseReq;

public class CustomerBeginQuery extends BaseReq {

	private static final long serialVersionUID = 800367509390000676L;
	
	private String id;
	//客户期初编号
    private String code;
    //审核状态（0：未审核，1：已审核）
    private String auditstatus;
    //组织id
    private String orgid;
    //组织名称
    private String orgname;
    //客户id
    private String customerid;
    //客户名称
    private String customername;
    //制单人id
    private String makeid;
    //制单人名称
    private String makebillname;
    //审核人id
    private String auditid;
    //审核人名称
    private String auditname;
    //用户id
    private String user;
	//状态（0：删除，1：正常）
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
	public String getMakeid() {
		return makeid;
	}
	public void setMakeid(String makeid) {
		this.makeid = makeid;
	}
	public String getMakebillname() {
		return makebillname;
	}
	public void setMakebillname(String makebillname) {
		this.makebillname = makebillname;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
    public String getUser() {
		return user;
    }
	public void setUser(String user) {
		this.user = user;
	}

}
