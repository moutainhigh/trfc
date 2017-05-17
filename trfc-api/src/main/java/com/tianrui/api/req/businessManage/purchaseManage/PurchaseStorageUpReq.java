package com.tianrui.api.req.businessManage.purchaseManage;

import com.tianrui.api.req.BaseReq;

public class PurchaseStorageUpReq extends BaseReq{

	private static final long serialVersionUID = -1229244043806991975L;
	private String id;
	//0失败 1成功
	private String status;
	private String msg;
	
	private String ncId;
	private String ts; 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getNcId() {
		return ncId;
	}
	public void setNcId(String ncId) {
		this.ncId = ncId;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	
	
}
