package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

public class UserDriverVo extends BaseResp {

	private static final long serialVersionUID = 7903837388692051327L;
	//用户ID
	private String userId = "";
	//司机ID
	private String driverId = "";
	//司机名称
	private String name = "";
	//司机手机号
	private String telephone = "";
	//使用次数
	private Integer number = 0;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "UserVehicleVo [userId=" + userId + ", driverId=" + driverId + ", name=" + name + ", telephone="
				+ telephone + ", number=" + number + "]";
	}
}