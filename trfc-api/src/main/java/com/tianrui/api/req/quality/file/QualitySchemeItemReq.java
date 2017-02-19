package com.tianrui.api.req.quality.file;

import com.tianrui.api.req.BaseReq;

public class QualitySchemeItemReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2804854037734725274L;
	/**
	 * 主键id
	 */
    private String id;
    /**
     * 质检方案id
     */
    private String schemeid;
    /**
     * 质检项目id
     */
    private String itemid;
    /**
     * 运算符1
     */
    private String comparison1;
    /**
     * 运算符2
     */
    private String comparison2;
    /**
     * 运算符3
     */
    private String comparison3;
    /**
     * 标准值
     */
    private String standardval;
    /**
     * 上限
     */
    private String upperlimit;
    /**
     * 下限
     */
    private String lowlimit;
    /**
     * 基值
     */
    private String baseval;
    /**
     * 浮动值
     */
    private String floatval;
    /**
     * 扣价
     */
    private String charge;
    /**
     * 扣吨
     */
    private String deduct;
    /**
     * 状态(0:未初始化,1:已初始化)
     */
    private String status;
    /**
     * 有效性(0:有效,1,无效)
     */
    private String invalid;
    /**
     * 备注
     */
    private String remark;
    /**
     * 用户名
     */
	private String user;
	/**
	 * 数组数据
	 */
	private String arrStr;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSchemeid() {
		return schemeid;
	}
	public void setSchemeid(String schemeid) {
		this.schemeid = schemeid;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getComparison1() {
		return comparison1;
	}
	public void setComparison1(String comparison1) {
		this.comparison1 = comparison1;
	}
	public String getComparison2() {
		return comparison2;
	}
	public void setComparison2(String comparison2) {
		this.comparison2 = comparison2;
	}
	public String getComparison3() {
		return comparison3;
	}
	public void setComparison3(String comparison3) {
		this.comparison3 = comparison3;
	}
	public String getStandardval() {
		return standardval;
	}
	public void setStandardval(String standardval) {
		this.standardval = standardval;
	}
	public String getUpperlimit() {
		return upperlimit;
	}
	public void setUpperlimit(String upperlimit) {
		this.upperlimit = upperlimit;
	}
	public String getLowlimit() {
		return lowlimit;
	}
	public void setLowlimit(String lowlimit) {
		this.lowlimit = lowlimit;
	}
	public String getBaseval() {
		return baseval;
	}
	public void setBaseval(String baseval) {
		this.baseval = baseval;
	}
	public String getFloatval() {
		return floatval;
	}
	public void setFloatval(String floatval) {
		this.floatval = floatval;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getDeduct() {
		return deduct;
	}
	public void setDeduct(String deduct) {
		this.deduct = deduct;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getArrStr() {
		return arrStr;
	}
	public void setArrStr(String arrStr) {
		this.arrStr = arrStr;
	}

	
	
}
