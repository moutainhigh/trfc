package com.tianrui.api.req.other.selfsystem;

import com.tianrui.api.req.BaseReq;

/**
 *  验证符合卡
 * @author lixp 2017年11月3日 11:23:38
 *
 */
public class ApiCheckRfidReq extends BaseReq {
	
	private static final long serialVersionUID = 4325189759681138634L;
	
	private String rfid;

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	
	

}