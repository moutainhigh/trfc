package com.tianrui.api.req.basicFile.measure;

import com.tianrui.api.req.BaseReq;

public class VehicleManageApi extends BaseReq {

	private static final long serialVersionUID = 2171660010873631914L;
	//车牌号
	private String vehicleNo;
	//RFID
	private String rfid;
	//IC卡序号
	private String icardNo;
	//当前用户ID
	private String currUid;
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getIcardNo() {
		return icardNo;
	}
	public void setIcardNo(String icardNo) {
		this.icardNo = icardNo;
	}
	public String getCurrUid() {
		return currUid;
	}
	public void setCurrUid(String currUid) {
		this.currUid = currUid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}