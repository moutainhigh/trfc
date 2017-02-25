package com.tianrui.service.bean.quality.file;

public class QualitySchemeItem {

	/**
	 * 主键id
	 */
    private String id;
    /**
     * 质检方案id
     */
    private String schemeid;
    /**
     * 质检项目id
     */
    private String itemid;
    /**
     * 运算�?1
     */
    private String comparison1;
    /**
     * 运算�?2
     */
    private String comparison2;
    /**
     * 运算�?3
     */
    private String comparison3;
    /**
     * 标准�?
     */
    private String standardval;
    /**
     * 上限
     */
    private String upperlimit;
    /**
     * 下限
     */
    private String lowlimit;
    /**
     * 基�??
     */
    private String baseval;
    /**
     * 浮动�?
     */
    private String floatval;
    /**
     * 扣价
     */
    private String charge;
    /**
     * 扣吨
     */
    private String deduct;
    /**
     * 状�??(0:未初始化,1:已初始化)
     */
    private String status;
    /**
     * 有效�?(0:有效,1,无效)
     */
    private String invalid;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建�?
     */
    private String creator;
    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 修改�?
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Long modifytime;
    /**
     * 数据同步时间�?
     */
    private Long utc;
    /**
     * 状�??(0:删除,1:正常)
     */
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid == null ? null : schemeid.trim();
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid == null ? null : itemid.trim();
    }

    public String getComparison1() {
        return comparison1;
    }

    public void setComparison1(String comparison1) {
        this.comparison1 = comparison1 == null ? null : comparison1.trim();
    }

    public String getComparison2() {
        return comparison2;
    }

    public void setComparison2(String comparison2) {
        this.comparison2 = comparison2 == null ? null : comparison2.trim();
    }

    public String getComparison3() {
        return comparison3;
    }

    public void setComparison3(String comparison3) {
        this.comparison3 = comparison3 == null ? null : comparison3.trim();
    }

    public String getStandardval() {
        return standardval;
    }

    public void setStandardval(String standardval) {
        this.standardval = standardval == null ? null : standardval.trim();
    }

    public String getUpperlimit() {
        return upperlimit;
    }

    public void setUpperlimit(String upperlimit) {
        this.upperlimit = upperlimit == null ? null : upperlimit.trim();
    }

    public String getLowlimit() {
        return lowlimit;
    }

    public void setLowlimit(String lowlimit) {
        this.lowlimit = lowlimit == null ? null : lowlimit.trim();
    }

    public String getBaseval() {
        return baseval;
    }

    public void setBaseval(String baseval) {
        this.baseval = baseval == null ? null : baseval.trim();
    }

    public String getFloatval() {
        return floatval;
    }

    public void setFloatval(String floatval) {
        this.floatval = floatval == null ? null : floatval.trim();
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge == null ? null : charge.trim();
    }

    public String getDeduct() {
        return deduct;
    }

    public void setDeduct(String deduct) {
        this.deduct = deduct == null ? null : deduct.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid == null ? null : invalid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Long getUtc() {
        return utc;
    }

    public void setUtc(Long utc) {
        this.utc = utc;
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}