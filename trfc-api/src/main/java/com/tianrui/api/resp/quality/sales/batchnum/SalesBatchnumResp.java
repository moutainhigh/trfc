package com.tianrui.api.resp.quality.sales.batchnum;

import com.tianrui.api.resp.BaseResp;

/**
 * 出参
 */
public class SalesBatchnumResp extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5796395565616681593L;

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
     * 创建人	
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
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Long getModifytime() {
		return modifytime;
	}
	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}
    
}
