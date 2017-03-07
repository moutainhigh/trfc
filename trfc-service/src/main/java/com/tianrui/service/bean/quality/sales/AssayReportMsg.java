package com.tianrui.service.bean.quality.sales;

public class AssayReportMsg {
	/**
	 * 主键id
	 */
    private String id;
    /**
     * 化验报告id
     */
    private String reportid;
    /**
     * 审核步骤(1:第一步2:第二步,3:第一步(强制审核))
     */
    private String audit;
    /**
     * 意见
     */
    private String remark;
    /**
     * 状态(0:删除1:正常)
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
    /**
     * 数据同步时间戳
     */
    private Long utc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid == null ? null : reportid.trim();
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit == null ? null : audit.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Long getUtc() {
        return utc;
    }

    public void setUtc(Long utc) {
        this.utc = utc;
    }
}