package com.tianrui.api.req.quality.sales.batchnum;

import com.tianrui.api.req.BaseReq;

/**
 * 入参
 */
public class SalesBatchnumReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5014253875529032054L;

	/**
	 * 主键ID
	 */
    private String id;
    /**
     * 物料
     */
    private String material;
    /**
     * 出厂编号
     */
    private String code;
    /**
     * 数量
     */
    private Long count;
    /**
     * 生产日期
     */
    private Long producedtime;
    /**
     * 试验日期
     */
    private Long testtime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 审核状态
     */
    private String auditstate;
    /**
     * 单据状态
     */
    private String billsstate;
    /**
     * 化验状态
     */
    private String teststate;
    /**
     * 审核时间
     */
    private Long audittime;
    /**
     * 化验人
     */
    private String assayer;
    /**
     * 化验时间
     */
    private Long assaytime;
    /**
     * 化验单位
     */
    private String assayorg;
    /**
     * 开始时间
     */
    private Long starttime;
    /**
     * 结束时间
     */
    private Long endtime;
    /**
     * 用户
     */
    private String user;
    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 当前页
     */
	private int pageNo;
	/**
	 * 每页显示数据总数
	 */
	private int pageSize;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAuditstate() {
		return auditstate;
	}
	public void setAuditstate(String auditstate) {
		this.auditstate = auditstate;
	}
	public String getBillsstate() {
		return billsstate;
	}
	public void setBillsstate(String billsstate) {
		this.billsstate = billsstate;
	}
	public String getTeststate() {
		return teststate;
	}
	public void setTeststate(String teststate) {
		this.teststate = teststate;
	}
	public Long getAudittime() {
		return audittime;
	}
	public void setAudittime(Long audittime) {
		this.audittime = audittime;
	}
	public String getAssayer() {
		return assayer;
	}
	public void setAssayer(String assayer) {
		this.assayer = assayer;
	}
	public Long getAssaytime() {
		return assaytime;
	}
	public void setAssaytime(Long assaytime) {
		this.assaytime = assaytime;
	}
	public String getAssayorg() {
		return assayorg;
	}
	public void setAssayorg(String assayorg) {
		this.assayorg = assayorg;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
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
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
