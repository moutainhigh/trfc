package com.tianrui.api.req.businessManage.poundNoteMaintain;

import com.tianrui.api.req.BaseReq;

public class ApiPoundNoteValidation extends BaseReq {
	private static final long serialVersionUID = -6639269016047238506L;
	//车牌ID
	private String vehicleId;
	//车牌号
	private String vehicleno;
	//RFID
	private String rfid;
	//一次或二次磅房(1: 一次过磅，2: 二次过磅)
	private String type;
	//当前登录用户
	private String currid;
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCurrid() {
		return currid;
	}
	public void setCurrid(String currid) {
		this.currid = currid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ApiPoundNoteValidation [vehicleId=" + vehicleId + ", vehicleno=" + vehicleno + ", rfid=" + rfid
				+ ", type=" + type + ", currid=" + currid + "]";
	}
}
