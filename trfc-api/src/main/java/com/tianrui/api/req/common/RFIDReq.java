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
	//车牌号
	private String vehicleNo;
	//通知单号
	private String noticeNo;
	//通知单类型
	private String noticeType;
    
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

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
    
}
