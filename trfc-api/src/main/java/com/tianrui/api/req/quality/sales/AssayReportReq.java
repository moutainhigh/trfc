package com.tianrui.api.req.quality.sales;

import com.tianrui.api.req.BaseReq;
/**
 * 销售化验报告req
 * @author Administrator
 *
 */

public class AssayReportReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8428580883501787707L;
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
     * 用户
     */
    private String user;
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
     * 分页查询开始位置
     */
    private int start;
    /**
     * 分页查询数据量
     */
    private int limit;
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
	public String getMschemeid() {
		return mschemeid;
	}
	public void setMschemeid(String mschemeid) {
		this.mschemeid = mschemeid;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAuditstate() {
		return auditstate;
	}
	public void setAuditstate(String auditstate) {
		this.auditstate = auditstate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
	public String getBatchcode() {
		return batchcode;
	}
	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

}
