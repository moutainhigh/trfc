package com.tianrui.api.resp.subSystem;

import com.tianrui.api.resp.BaseResp;

public class VehicleAndCardResp extends BaseResp {
	
	private static final long serialVersionUID = 2192472046233179508L;
	//车牌号
	private String vehicle;
	//类别（0：临时，1：固定）
	private String type;
	//卡序号
	private String cardno;
	//卡面编号
	private String cardcode;
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
