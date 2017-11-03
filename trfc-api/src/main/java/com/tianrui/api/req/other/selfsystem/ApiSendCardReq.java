package com.tianrui.api.req.other.selfsystem;

import com.tianrui.api.req.BaseReq;

/**
 * 自助终端发卡
 * 采购发卡,销售发卡,其他如发卡,其他出发卡.
 * @author lixp 2017年11月3日 11:23:38
 *
 */
public class ApiSendCardReq extends BaseReq {
	
	private static final long serialVersionUID = 4325189759681138634L;
	//通知单号
	private String no;
	//rfid号
	private String rfid;
	//id卡号
	private String icno;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getIcno() {
		return icno;
	}
	public void setIcno(String icno) {
		this.icno = icno;
	}
	
	
	

}