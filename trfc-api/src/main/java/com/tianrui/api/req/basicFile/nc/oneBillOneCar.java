package com.tianrui.api.req.basicFile.nc;

import com.tianrui.api.req.BaseReq;

public class oneBillOneCar extends BaseReq {

	private static final long serialVersionUID = 6271753428934507938L;
	//订单ID
	private String billId;
	//订单子表ID
	private String billDetailId;
	//类型  0是订单，1是通知单
	private String type;
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getBillDetailId() {
		return billDetailId;
	}
	public void setBillDetailId(String billDetailId) {
		this.billDetailId = billDetailId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "oneBillOneCar [billId=" + billId + ", billDetailId=" + billDetailId + ", type=" + type + "]";
	}
}
