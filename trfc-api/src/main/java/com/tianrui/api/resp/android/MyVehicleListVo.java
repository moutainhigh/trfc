package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

public class MyVehicleListVo extends BaseResp {

	private static final long serialVersionUID = -7520772893903734639L;
	//车辆
	private String vehicle = "";
	//司机
	private String driver = "";
	//电话
	private String telephone = "";
	//状态
	private String status = "";
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "MyVehicleListVo [vehicle=" + vehicle + ", driver=" + driver + ", telephone=" + telephone + ", status="
				+ status + "]";
	}
}
