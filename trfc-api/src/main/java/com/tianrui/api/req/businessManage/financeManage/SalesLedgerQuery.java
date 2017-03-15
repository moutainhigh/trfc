package com.tianrui.api.req.businessManage.financeManage;

import com.tianrui.api.req.BaseReq;

public class SalesLedgerQuery extends BaseReq{
	
	private static final long serialVersionUID = -4033623750522530271L;
	private String id;
    //收款台账编号
    private String code;
    //审核状态 0-未审核，1-审核中，2-已审核，3-已退回
    private String auditstatus;
    //客户名称
    private String customername;
    //收款类型(0-期初，1-收款)
    private String chargetype;
    //收款单位
    private String chargeunit;
    //制单人名称
    private String makebillname;
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
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getChargetype() {
		return chargetype;
	}
	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}
	public String getChargeunit() {
		return chargeunit;
	}
	public void setChargeunit(String chargeunit) {
		this.chargeunit = chargeunit;
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
    
}
