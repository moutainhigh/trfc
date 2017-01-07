package com.tianrui.api.req.common;

import com.tianrui.api.req.BaseReq;

public class RFIDReq extends BaseReq{

	private static final long serialVersionUID = -7424498211061351171L;
	
	private String rfid;
    
    private String currUid;

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid == null ? null : rfid.trim();
    }

	public String getCurrUid() {
		return currUid;
	}

	public void setCurrUid(String currUid) {
		this.currUid = currUid;
	}
    
}
