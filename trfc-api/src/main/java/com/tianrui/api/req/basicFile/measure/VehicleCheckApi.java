package com.tianrui.api.req.basicFile.measure;

import com.tianrui.api.req.BaseReq;

public class VehicleCheckApi extends BaseReq {

	private static final long serialVersionUID = 2171660010873631914L;

	//车牌号
	private String vehicleNo;
	//rfid标识
	private String rfid;
	
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

	public String getCurrUid() {
		return currUid;
	}

	public void setCurrUid(String currUid) {
		this.currUid = currUid;
	}
	
	
}