package com.tianrui.api.resp.quality.file;

import java.util.List;

import com.tianrui.api.resp.BaseResp;

public class SupplierSchemeResp extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1002982482985768367L;
	
	 /**
	    * 主键id
	    */
		private String id;
		/**
		 * 编码
		 */
	    private String code;
		/**
		 * 方案名称
		 */
	    private String name;
		/**
		 * 供应商id
		 */
	    private String supplierid;
	    /**
	     * 供应商名称
	     */
	    private String suppliername;
		/**
		 * 供应商备注
		 */
	    private String supremark;
		/**
		 * 物料id
		 */
	    private String materialid;
	    /**
	     * 物料名称
	     */
	    private String materialname;
		/**
		 * 质检方案id
		 */
	    private String schemeid;
	    /**
	     * 质检方案名称
	     */
	    private String schemename;
		/**
		 * 开始时间
		 */
	    private Long starttime;
		/**
		 * 结束时间
		 */
	    private Long endtime;
		/**
		 * 平均方式(0:月加权平均值,1:日平均值)
		 */
	    private String mean;
		/**
		 * 扣款方式(0:按金额,1:按吨)
		 */
	    private String deduct;
		/**
		 * 默认(0:默认的,1:不默认)
		 */
	    private String ref;
		/**
		 * 有效的(0:有效,1:无效)
		 */
	    private String invalid;
		/**
		 * 备注
		 */
	    private String remark;
	    /**
	     * 制单人
	     */
	    private String creator;
	    /**
	     * 质检方案质量标准详细
	     */
	    private List<QualitySchemeItemResp> data;
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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSupplierid() {
			return supplierid;
		}
		public void setSupplierid(String supplierid) {
			this.supplierid = supplierid;
		}
		public String getSuppliername() {
			return suppliername;
		}
		public void setSuppliername(String suppliername) {
			this.suppliername = suppliername;
		}
		public String getSupremark() {
			return supremark;
		}
		public void setSupremark(String supremark) {
			this.supremark = supremark;
		}
		public String getMaterialid() {
			return materialid;
		}
		public void setMaterialid(String materialid) {
			this.materialid = materialid;
		}
		public String getMaterialname() {
			return materialname;
		}
		public void setMaterialname(String materialname) {
			this.materialname = materialname;
		}
		public String getSchemeid() {
			return schemeid;
		}
		public void setSchemeid(String schemeid) {
			this.schemeid = schemeid;
		}
		public String getSchemename() {
			return schemename;
		}
		public void setSchemename(String schemename) {
			this.schemename = schemename;
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
		public String getMean() {
			return mean;
		}
		public void setMean(String mean) {
			this.mean = mean;
		}
		public String getDeduct() {
			return deduct;
		}
		public void setDeduct(String deduct) {
			this.deduct = deduct;
		}
		public String getRef() {
			return ref;
		}
		public void setRef(String ref) {
			this.ref = ref;
		}
		public String getInvalid() {
			return invalid;
		}
		public void setInvalid(String invalid) {
			this.invalid = invalid;
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
		public List<QualitySchemeItemResp> getData() {
			return data;
		}
		public void setData(List<QualitySchemeItemResp> data) {
			this.data = data;
		}
	    
}
