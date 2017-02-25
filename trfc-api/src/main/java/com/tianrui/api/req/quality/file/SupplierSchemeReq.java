package com.tianrui.api.req.quality.file;

import com.tianrui.api.req.BaseReq;

public class SupplierSchemeReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3940670901853433814L;

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
		 * 供应商备注
		 */
	    private String supremark;
		/**
		 * 物料id
		 */
	    private String materialid;
		/**
		 * 质检方案id
		 */
	    private String schemeid;
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
	     * 项目详细
	     */
	    private String detail;
	    /**
	     * 用户id
	     */
	    private String user;
	    /**
	     * 创建时间
	     */
	    private Long createtime;
	    /**
	     * 分页查询开始位置
	     */
	    private int start;
	    /**
	     * 状态(0:删除,1:正常)
	     */
	    private String state;
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
		public String getSchemeid() {
			return schemeid;
		}
		public void setSchemeid(String schemeid) {
			this.schemeid = schemeid;
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
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
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
		public Long getCreatetime() {
			return createtime;
		}
		public void setCreatetime(Long createtime) {
			this.createtime = createtime;
		}
		public String getDetail() {
			return detail;
		}
		public void setDetail(String detail) {
			this.detail = detail;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
	    
	    
}
