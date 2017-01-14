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
    private String salesarriveid;
    /**
     * ic卡号
     */
    private String icardid;
    /**
     * 门禁类型(0:入厂,1:出厂)
     */
    private Boolean accesstype;
    /**
     * 业务类型(0:采购,1:销售)
     */
    private Boolean businesstype;
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

    public String getSalesarriveid() {
        return salesarriveid;
    }

    public void setSalesarriveid(String salesarriveid) {
        this.salesarriveid = salesarriveid == null ? null : salesarriveid.trim();
    }

    public String getIcardid() {
        return icardid;
    }

    public void setIcardid(String icardid) {
        this.icardid = icardid == null ? null : icardid.trim();
    }

    public Boolean getAccesstype() {
        return accesstype;
    }

    public void setAccesstype(Boolean accesstype) {
        this.accesstype = accesstype;
    }

    public Boolean getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(Boolean businesstype) {
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