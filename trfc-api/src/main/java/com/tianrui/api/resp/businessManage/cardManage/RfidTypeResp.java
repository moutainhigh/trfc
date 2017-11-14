package com.tianrui.api.resp.businessManage.cardManage;

import com.tianrui.api.resp.BaseResp;

public class RfidTypeResp extends BaseResp {

	private static final long serialVersionUID = 2083493515864518621L;

	private String rfid;
	private String vehicleNo;
	//rfid类型  1：复合卡，2：临时标签，3：正式标签
	private String rfidType;
	
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getRfidType() {
		return rfidType;
	}
	public void setRfidType(String rfidType) {
		this.rfidType = rfidType;
	}
	
	
	
}