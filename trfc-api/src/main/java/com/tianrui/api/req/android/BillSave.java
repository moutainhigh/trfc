package com.tianrui.api.req.android;

import com.tianrui.api.req.BaseReq;

public class BillSave extends BaseReq {
	private static final long serialVersionUID = -4196553762448897051L;
	//当前用户ID
	private String userId;
	//当前登录用户身份（1：客户，2：供应商）
	private String IDType;
	//销售组织ID
	private String salesOrg;
	//物料
	private String material;
	//车辆ID
	private String vehicle;
	//司机ID
	private String driver;
	//提货量
	private Double number;
	//单位
	private String unit;
	//订单日期
	private Long billTime;
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
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Long getBillTime() {
		return billTime;
	}
	public void setBillTime(Long billTime) {
		this.billTime = billTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BillSave [userId=" + userId + ", IDType=" + IDType + ", salesOrg=" + salesOrg + ", material=" + material
				+ ", vehicle=" + vehicle + ", driver=" + driver + ", number=" + number + ", unit=" + unit
				+ ", billTime=" + billTime + "]";
	}
}