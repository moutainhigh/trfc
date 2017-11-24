package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

public class UserDriverVehicleVo extends BaseResp {

	private static final long serialVersionUID = 7903837388692051327L;
	//司机ID
	private String driverId = "";
	//司机名称
	private String driverName = "";
	//车辆ID
	private String vehicleId = "";
	//车牌号
	private String vehicleNo = "";
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "UserDriverVehicleVo [driverId=" + driverId + ", driverName=" + driverName + ", vehicleId=" + vehicleId
				+ ", vehicleNo=" + vehicleNo + "]";
	}
}