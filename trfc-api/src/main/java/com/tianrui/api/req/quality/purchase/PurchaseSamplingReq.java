package com.tianrui.api.req.quality.purchase;

import com.tianrui.api.req.BaseReq;

public class PurchaseSamplingReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3792359131551469140L;
	
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
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态：（0：删除，1：显示）
     */
    private String state;
    /**
     * 用户
     */
    private String user;
    /**
     * 创建时间
     */
    private Long createtime;
    private int start;
    private int limit;
    private long starttime;
    private long endtime;
    private String arrstr;
    
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}
	public String getArrstr() {
		return arrstr;
	}
	public void setArrstr(String arrstr) {
		this.arrstr = arrstr;
	}
	
}
