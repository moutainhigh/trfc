package com.tianrui.api.resp.access;

import com.tianrui.api.resp.BaseResp;

public class AccessRecordResp extends BaseResp {
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = -2691560029335419353L;
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
    /**
     * 创建者
     */
    private String creator;
    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 修改者
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Long modifytime;

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Long getModifytime() {
        return modifytime;
    }

    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }
}