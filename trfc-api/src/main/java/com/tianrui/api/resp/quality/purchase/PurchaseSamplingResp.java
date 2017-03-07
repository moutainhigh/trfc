package com.tianrui.api.resp.quality.purchase;

import com.tianrui.api.resp.BaseResp;

public class PurchaseSamplingResp extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6357888669248652377L;
	/**
	 * 主键id
	 */
    private String id;
    /**
     * 编号
     */
    private String code;
    /**
     * 采样日期
     */
    private Long samplingtime;
    /**
     * 采样车辆
     */
    private String samplingcar;
    /**
     * 采样编号
     */
    private String samplingcode;
    /**
     * 化验类型
     */
    
    private String assaytype;
    private String assayname;
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 创建者
     */
    private String creator;
    /**
     * 创建时间
     */
    private Long createtime;
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
	public Long getSamplingtime() {
		return samplingtime;
	}
	public void setSamplingtime(Long samplingtime) {
		this.samplingtime = samplingtime;
	}
	public String getSamplingcar() {
		return samplingcar;
	}
	public void setSamplingcar(String samplingcar) {
		this.samplingcar = samplingcar;
	}
	public String getSamplingcode() {
		return samplingcode;
	}
	public void setSamplingcode(String samplingcode) {
		this.samplingcode = samplingcode;
	}
	public String getAssaytype() {
		return assaytype;
	}
	public void setAssaytype(String assaytype) {
		this.assaytype = assaytype;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getAssayname() {
		return assayname;
	}
	public void setAssayname(String assayname) {
		this.assayname = assayname;
	}
    
    
    
}
