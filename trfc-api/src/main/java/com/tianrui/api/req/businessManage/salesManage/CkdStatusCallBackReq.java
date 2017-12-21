package com.tianrui.api.req.businessManage.salesManage;

import com.tianrui.api.req.BaseReq;

public class CkdStatusCallBackReq extends BaseReq {

	private static final long serialVersionUID = 2444840232982098020L;
	//出库单ID
	private String id;
	//出库单NC ID
	private String NcId;
	//状态码（-1：失败,000000：成功）
	private String status;
	//推送信息
	private String message;
	//ts
	private String ts;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNcId() {
		return NcId;
	}
	public void setNcId(String ncId) {
		NcId = ncId;
	}
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
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CkdStatusCallBackReq [id=" + id + ", NcId=" + NcId + ", status=" + status + ", message=" + message
				+ ", ts=" + ts + "]";
	}
}
