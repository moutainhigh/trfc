package com.tianrui.api.req.android;

import com.tianrui.api.resp.BaseResp;

public class NoticeSave extends BaseResp {

	private static final long serialVersionUID = -1523880673015286422L;
	//当前用户ID
	private String userId;
	//当前登录用户身份（1：客户，2：供应商）
	private String IDType;
	//车辆ID
	private String vehicle;
	//司机ID
	private String driver;
	//提货量
	private Double number;
	//订单主表ID
	private String id;
	//订单子表ID
	private String detailId;
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
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public Double getNumber() {
		return number;
	}
	public void setNumber(Double number) {
		this.number = number;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "NoticeSave [userId=" + userId + ", IDType=" + IDType + ", vehicle=" + vehicle + ", driver=" + driver
				+ ", number=" + number + ", id=" + id + ", detailId=" + detailId + "]";
	}
}
