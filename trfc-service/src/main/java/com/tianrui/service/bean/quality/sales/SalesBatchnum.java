package com.tianrui.service.bean.quality.sales;
/**
 * 销售批号维护
 * @author lxy
 *
 */
public class SalesBatchnum {

	/**
	 * 主键ID
	 */
    private String id;
    /**
     * 物料
     */
    private String material;
    /**
     * 编号
     */
    private String code;
    /**
     * 出厂编号
     */
    private String factorycode;
    /**
     * 物料数量
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
     * 化验时间
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
     * 创建时间	
     */
    private String creator;
    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 修改人
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Long modifytime;
    /**
     * nc同步时间戳
     */
    private Long utc;
    /**
     * 审核人
     */
    private String auditer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getFactorycode() {
		return factorycode;
	}

	public void setFactorycode(String factorycode) {
		this.factorycode = factorycode;
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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAuditstate() {
        return auditstate;
    }

    public void setAuditstate(String auditstate) {
        this.auditstate = auditstate == null ? null : auditstate.trim();
    }

    public String getBillsstate() {
        return billsstate;
    }

    public void setBillsstate(String billsstate) {
        this.billsstate = billsstate == null ? null : billsstate.trim();
    }

    public String getTeststate() {
        return teststate;
    }

    public void setTeststate(String teststate) {
        this.teststate = teststate == null ? null : teststate.trim();
    }

    public Long getAudittime() {
        return audittime;
    }

    public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}

	public void setAudittime(Long audittime) {
        this.audittime = audittime;
    }

    public String getAssayer() {
        return assayer;
    }

    public void setAssayer(String assayer) {
        this.assayer = assayer == null ? null : assayer.trim();
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
        this.assayorg = assayorg == null ? null : assayorg.trim();
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

	@Override
	public String toString() {
		return "SalesBatchnum [id=" + id + ", material=" + material + ", code=" + code + ", factorycode=" + factorycode
				+ ", count=" + count + ", producedtime=" + producedtime + ", testtime=" + testtime + ", remark="
				+ remark + ", auditstate=" + auditstate + ", billsstate=" + billsstate + ", teststate=" + teststate
				+ ", audittime=" + audittime + ", assayer=" + assayer + ", assaytime=" + assaytime + ", assayorg="
				+ assayorg + ", starttime=" + starttime + ", endtime=" + endtime + ", creator=" + creator
				+ ", createtime=" + createtime + ", modifier=" + modifier + ", modifytime=" + modifytime + ", utc="
				+ utc + "]";
	}
    
}