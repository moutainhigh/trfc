package com.tianrui.api.resp.quality.sales;

import com.tianrui.api.resp.BaseResp;
/**
 * 销售化验报告resp
 * @author Administrator
 *
 */
public class AssayReportResp extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6437027645992637556L;
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
     * 批号
     */
    private String batchcode;
    /**
     * 物料方案id
     */
    private String mschemeid;
    /**
     * 物料品种
     */
    private String materialtype;
    /**
     * 水泥名称
     */
    private String materialname;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBatchnumid() {
		return batchnumid;
	}
	public void setBatchnumid(String batchnumid) {
		this.batchnumid = batchnumid;
	}
	public String getBatchcode() {
		return batchcode;
	}
	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}
	public String getMschemeid() {
		return mschemeid;
	}
	public void setMschemeid(String mschemeid) {
		this.mschemeid = mschemeid;
	}
	public String getMaterialtype() {
		return materialtype;
	}
	public void setMaterialtype(String materialtype) {
		this.materialtype = materialtype;
	}
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public String getQschemeid() {
		return qschemeid;
	}
	public void setQschemeid(String qschemeid) {
		this.qschemeid = qschemeid;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getReportorg() {
		return reportorg;
	}
	public void setReportorg(String reportorg) {
		this.reportorg = reportorg;
	}
	public String getReporttype() {
		return reporttype;
	}
	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
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
		this.remark = remark;
	}
	public String getPstate() {
		return pstate;
	}
	public void setPstate(String pstate) {
		this.pstate = pstate;
	}
	public String getAuditstate() {
		return auditstate;
	}
	public void setAuditstate(String auditstate) {
		this.auditstate = auditstate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
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
		this.auditer = auditer;
	}
	public Long getAudittime() {
		return audittime;
	}
	public void setAudittime(Long audittime) {
		this.audittime = audittime;
	}


    
}
