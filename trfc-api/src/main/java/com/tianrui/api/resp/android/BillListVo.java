package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

public class BillListVo extends BaseResp {

	private static final long serialVersionUID = 5599908659929080197L;
	//订单主表ID
	private String id = "";
	//订单子表ID
	private String detailId = "";
	//订单号
	private String code = "";
	//订单类型
	private String billType = "";
	//订单时间
	private String billTime = "";
	//订单来源
	private String billSource = "";
	//物料id
	private String materielid = "";
	//物料
	private String material = "";
	//余量
	private Double margin = 0D;
	//单位
	private String unit = "";
	//总量
	private Double sumNum = 0D;
	//出库占用量
	private Double outInNum = 0D;
	//未出库占用量
	private Double unOutInNum = 0D;
	//预提占用量
	private Double planNum = 0D;
	//单价
	private String price = "";
	//状态（0：待审核，1：可派车，2：已完成）
	private String type = "";
	//供应商备注
	private String supRemark = "";
	//矿口
	private String minemouth = "";
	//矿口
	private String vehicle = "";
	
	
	public String getMaterielid() {
		return materielid;
	}
	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillTime() {
		return billTime;
	}
	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}
	public String getBillSource() {
		return billSource;
	}
	public void setBillSource(String billSource) {
		this.billSource = billSource;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public Double getMargin() {
		return margin;
	}
	public void setMargin(Double margin) {
		this.margin = margin;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getSumNum() {
		return sumNum;
	}
	public void setSumNum(Double sumNum) {
		this.sumNum = sumNum;
	}
	public Double getOutInNum() {
		return outInNum;
	}
	public void setOutInNum(Double outInNum) {
		this.outInNum = outInNum;
	}
	public Double getUnOutInNum() {
		return unOutInNum;
	}
	public void setUnOutInNum(Double unOutInNum) {
		this.unOutInNum = unOutInNum;
	}
	public Double getPlanNum() {
		return planNum;
	}
	public void setPlanNum(Double planNum) {
		this.planNum = planNum;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSupRemark() {
		return supRemark;
	}
	public void setSupRemark(String supRemark) {
		this.supRemark = supRemark;
	}
	public String getMinemouth() {
		return minemouth;
	}
	public void setMinemouth(String minemouth) {
		this.minemouth = minemouth;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BillListVo [id=" + id + ", detailId=" + detailId + ", code=" + code + ", billType=" + billType
				+ ", billTime=" + billTime + ", billSource=" + billSource + ", material=" + material + ", margin="
				+ margin + ", unit=" + unit + ", sumNum=" + sumNum + ", outInNum=" + outInNum + ", unOutInNum="
				+ unOutInNum + ", planNum=" + planNum + ", price=" + price + ", type=" + type + ", supRemark="
				+ supRemark + ", minemouth=" + minemouth + ", vehicle=" + vehicle + "]";
	}
}
