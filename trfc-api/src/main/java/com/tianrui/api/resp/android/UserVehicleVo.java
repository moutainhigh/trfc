package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

public class UserVehicleVo extends BaseResp {

	private static final long serialVersionUID = 7903837388692051327L;
	//用户ID
	private String userId = "";
	//车辆ID
	private String vehicleId = "";
	//车辆牌号
	private String vehicleNo = "";
	//使用次数
	private Integer number = 0;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
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
		return "UserVehicleVo [userId=" + userId + ", vehicleId=" + vehicleId + ", vehicleNo=" + vehicleNo + ", number="
				+ number + "]";
	}
}