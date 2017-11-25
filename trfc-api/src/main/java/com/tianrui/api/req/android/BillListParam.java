package com.tianrui.api.req.android;

public class BillListParam extends AppBase {

	private static final long serialVersionUID = 3365589892403864792L;
	//页签类别（0：待审核，1：可派车，2：已完成）
	private String type;
	//订单ID
	private String id;
	//子表ID
	private String detailId;
	//订单编号
	private String billCode;
	//物料
	private String material;
	//开始时间
	private Long startTime;
	//结束时间
	private Long endTime;
	//订单来源
	private String billSource;

	private int start;
	private int limit;
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
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
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
		return "BillListParam [type=" + type + ", id=" + id + ", detailId=" + detailId + ", billCode=" + billCode
				+ ", material=" + material + ", startTime=" + startTime + ", endTime=" + endTime + ", billSource="
				+ billSource + ", start=" + start + ", limit=" + limit + "]";
	}
}
