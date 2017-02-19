package com.tianrui.api.resp.quality.file;

import com.tianrui.api.resp.BaseResp;

public class QualitySchemeItemResp extends BaseResp {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2501405088762032481L;
	/**
	 * 主键id
	 */
    private String id;
    /**
     * 质检方案id
     */
    private String schemeid;
    /**
     * 质检方案编码
     */
    private String schemecode;
    /**
     * 物料名称
     */
    private String materialname;
    /**
     * 质检项目id
     */
    private String itemid;
    /**
     * 质检项目编码
     */
    private String itemcode;
    /**
     * 质检项目名称
     */
    private String itemname;
    /**
     * 单位
     */
    private String units;
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
     * 有效性(0:有效,1,无效)
     */
    private String invalid;
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
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
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
	public String getInvalid() {
		return invalid;
	}
	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getSchemecode() {
		return schemecode;
	}
	public void setSchemecode(String schemecode) {
		this.schemecode = schemecode;
	}

	
}
