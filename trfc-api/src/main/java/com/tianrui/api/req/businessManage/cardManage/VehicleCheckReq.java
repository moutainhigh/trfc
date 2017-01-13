package com.tianrui.api.req.businessManage.cardManage;

import com.tianrui.api.req.BaseReq;

/**
 * 车牌号与rfid绑定验证
 * @author Administrator
 *
 */
public class VehicleCheckReq extends BaseReq{
	
	private static final long serialVersionUID = 9073851476669970786L;
	private String vehicleno;
	private String rfid;
	
	private String currUid;
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
	public String getCurrUid() {
		return currUid;
	}
	public void setCurrUid(String currUid) {
		this.currUid = currUid;
	}
	
	

}
