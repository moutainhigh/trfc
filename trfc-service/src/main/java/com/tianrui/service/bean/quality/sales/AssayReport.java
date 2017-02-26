package com.tianrui.service.bean.quality.sales;
/**
 * 销售化验报告Bean
 * @author lxy
 *
 */
public class AssayReport {
	/**
	 * 主键id
	 */
    private String id;
    /**
     * 编码
     */
    private String code;
    /**
     * 批号id
     */
    private String batchnumid;
    /**
     * 物料方案id
     */
    private String mschemeid;
    /**
     * 质检方案id
     */
    private String qschemeid;
    /**
     * 报告人
     */
    private String reporter;
    /**
     * 报告单位
     */
    private String reportorg;
    /**
     * 报告天数(0:3天报告,1:28天报告)
     */
    private String reporttype;
    /**
     * 地址
     */
    private String addr;
    /**
     * 销售日期
     */
    private Long selldate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 打印类型(0:否,1:是)
     */
    private String pstate;
    /**
     * 状态(0:删除1:正常)
     */
    private String state;
    /**
     * 审核状态(0:未审核,1:已审核)
     */
    private String auditstate;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 生产日期
     */
    private Long producedtime;
    /**
     * 化验日期
     */
    private Long testtime;
    /**
     * 审核人
     */
    private String auditer;
    /**
     * 审核日期
     */
    private Long audittime;
    /**
     * 最后修改人
     */
    private String modifier;
    /**
     * 最后修改时间
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBatchnumid() {
        return batchnumid;
    }

    public void setBatchnumid(String batchnumid) {
        this.batchnumid = batchnumid == null ? null : batchnumid.trim();
    }

    public String getMschemeid() {
        return mschemeid;
    }

    public void setMschemeid(String mschemeid) {
        this.mschemeid = mschemeid == null ? null : mschemeid.trim();
    }

    public String getQschemeid() {
        return qschemeid;
    }

    public void setQschemeid(String qschemeid) {
        this.qschemeid = qschemeid == null ? null : qschemeid.trim();
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter == null ? null : reporter.trim();
    }

    public String getReportorg() {
        return reportorg;
    }

    public void setReportorg(String reportorg) {
        this.reportorg = reportorg == null ? null : reportorg.trim();
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public Long getSelldate() {
        return selldate;
    }

    public void setSelldate(Long selldate) {
        this.selldate = selldate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPstate() {
        return pstate;
    }

    public void setPstate(String pstate) {
        this.pstate = pstate == null ? null : pstate.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getAuditstate() {
        return auditstate;
    }

    public void setAuditstate(String auditstate) {
        this.auditstate = auditstate == null ? null : auditstate.trim();
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

    public Long getProducedtime() {
        return producedtime;
    }

    public void setProducedtime(Long producedtime) {
        this.producedtime = producedtime;
    }

    public Long getTesttime() {
        return testtime;
    }

    public void setTesttime(Long testtime) {
        this.testtime = testtime;
    }

    public String getAuditer() {
        return auditer;
    }

    public void setAuditer(String auditer) {
        this.auditer = auditer == null ? null : auditer.trim();
    }

    public Long getAudittime() {
        return audittime;
    }

    public void setAudittime(Long audittime) {
        this.audittime = audittime;
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