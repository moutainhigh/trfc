package com.tianrui.api.req.businessManage.poundNoteMaintain;

import com.tianrui.api.req.BaseReq;

public class ApiPoundNoteValidation extends BaseReq {
	private static final long serialVersionUID = -6639269016047238506L;
	//车牌号
	private String vehicleno;
	//RFID
	private String rfid;
	//一次或二次磅房(1: 一次过磅，2: 二次过磅)
	private String type;
	//当前登录用户
	private String currid;
	/**
	 * @return the vehicleno
	 */
	public String getVehicleno() {
		return vehicleno;
	}
	/**
	 * @return the rfid
	 */
	public String getRfid() {
		return rfid;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the currid
	 */
	public String getCurrid() {
		return currid;
	}
	/**
	 * @param vehicleno the vehicleno to set
	 */
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	/**
	 * @param rfid the rfid to set
	 */
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @param currid the currid to set
	 */
	public void setCurrid(String currid) {
		this.currid = currid;
	}
	
}
