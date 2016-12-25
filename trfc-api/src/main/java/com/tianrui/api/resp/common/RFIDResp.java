package com.tianrui.api.resp.common;

import com.tianrui.api.resp.BaseResp;

public class RFIDResp extends BaseResp{

	private static final long serialVersionUID = -5817774971738348903L;

	private String rfid;

    private String state;

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid == null ? null : rfid.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
