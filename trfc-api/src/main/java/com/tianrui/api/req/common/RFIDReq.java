package com.tianrui.api.req.common;

import com.tianrui.api.req.BaseReq;
/**
 * @annotation RFID卡注册
 * @author zhanggaohao
 * @date 2017年7月17日 上午10:16:15
 */
public class RFIDReq extends BaseReq{

	private static final long serialVersionUID = -7424498211061351171L;
	//RFID
	private String rfid;
	//类型：（1：复合卡，2：临时标签，3：正式标签）
	private String type;
    
    private String currUid;

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid == null ? null : rfid.trim();
    }

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrUid() {
		return currUid;
	}

	public void setCurrUid(String currUid) {
		this.currUid = currUid;
	}
    
}
