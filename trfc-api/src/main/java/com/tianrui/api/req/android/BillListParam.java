package com.tianrui.api.req.android;

import com.tianrui.api.req.BaseReq;

public class BillListParam extends BaseReq {

	private static final long serialVersionUID = 3365589892403864792L;
	//当前用户ID
	private String userId;
	//当前登录用户身份（1：客户，2：供应商）
	private String IDType;
	//销售组织ID
	private String salesOrg;
	//页签类别
	private String type;
	//订单ID
	private String id;
	//子表ID
	private String detailId;
	//订单编号
	private String billCode;
	//开始时间
	private Long startTime;
	//结束时间
	private Long endTime;
	//订单来源
	private String billSource;

	private int start;
	private int limit;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIDType() {
		return IDType;
	}
	public void setIDType(String iDType) {
		IDType = iDType;
	}
	public String getSalesOrg() {
		return salesOrg;
	}
	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getBillSource() {
		return billSource;
	}
	public void setBillSource(String billSource) {
		this.billSource = billSource;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BillListParam [userId=" + userId + ", IDType=" + IDType + ", salesOrg=" + salesOrg + ", type=" + type
				+ ", id=" + id + ", detailId=" + detailId + ", billCode=" + billCode + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", billSource=" + billSource + ", start=" + start + ", limit=" + limit + "]";
	}
}
