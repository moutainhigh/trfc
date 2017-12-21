package com.tianrui.api.req.businessManage.salesManage;

import com.tianrui.api.req.BaseReq;

public class SalesApplicationReturnErrorReq extends BaseReq {

	private static final long serialVersionUID = -2914039044038390957L;
	//状态码（-1：失败,000000：成功）
	private String status;
	//失败信息
	private String message;
	//订单ID
	private String billId;
	//订单子表ID
	private String billDetailId;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SalesApplicationReturnErrorReq [status=" + status + ", message=" + message + ", billId=" + billId
				+ ", billDetailId=" + billDetailId + "]";
	}
}
