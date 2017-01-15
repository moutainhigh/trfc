package com.tianrui.api.req.access;

import com.tianrui.api.req.BaseReq;

public class AccessRecordReq extends BaseReq{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 3989463151213846002L;
	/**
     * 门禁记录id
     */
	private String id;
	/**
	 * 通告单号
	 */
    private String salesarrivecode;
    /**
     * ic卡号
     */
    private String icardcode;
    /**
     * 记录时间
     */
    private String intotime;
    /**
     * RFID
     */
    private String rfid;
    /**
     * 门禁类型(1:入厂,2:出厂)
     */
    private String accesstype;
    /**
     * 业务类型(1:采购,2:销售)
     */
    private String businesstype;
    /**
     * 来源
     */
    private String source;
    /**
     * 状态(0:删除,1:显示)
     */
    private String state;
 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSalesarrivecode() {
        return salesarrivecode;
    }

    public void setSalesarrivecode(String salesarrivecode) {
        this.salesarrivecode = salesarrivecode == null ? null : salesarrivecode.trim();
    }

    public String getIcardcode() {
        return icardcode;
    }

    public void setIcardcode(String icardcode) {
        this.icardcode = icardcode == null ? null : icardcode.trim();
    }

    public String getIntotime() {
		return intotime;
	}

	public void setIntotime(String intotime) {
		this.intotime = intotime;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getAccesstype() {
        return accesstype;
    }

    public void setAccesstype(String accesstype) {
        this.accesstype = accesstype;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

  
}