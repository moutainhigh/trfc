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