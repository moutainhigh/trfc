package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

public class HomeNoticeVo extends BaseResp {

	private static final long serialVersionUID = -8334906301337503006L;
	//通知单ID
	private String id;
	//通知单号
	private String code;
	//车辆
	private String vehicle;
	//司机
	private String driver;
	//订单日期
	private String billTime;
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
	public String getBillTime() {
		return billTime;
	}
	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "HomeNoticeVo [id=" + id + ", code=" + code + ", vehicle=" + vehicle + ", driver=" + driver
				+ ", billTime=" + billTime + "]";
	}
}
