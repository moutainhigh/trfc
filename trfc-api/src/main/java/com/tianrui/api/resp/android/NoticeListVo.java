package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

public class NoticeListVo extends BaseResp {

	private static final long serialVersionUID = -2177834189585238320L;
	//通知单ID
	private String id = "";
	//订单号
	private String billCode = "";
	//通知单号
	private String code = "";
	//订单日期
	private String billTime = "";
	//车辆
	private String vehicle = "";
	//物料
	private String material = "";
	//司机
	private String driver = "";
	//来源
	private String source = "";
	//毛重
	private Double grossWeight = 0D;
	//皮重
	private Double tareWeight = 0D;
	//净重
	private Double netWeight = 0D;
	//轻车时间
	private String lightTime = "";
	//重车时间
	private String weightTime = "";
	//到货量/提货量
	private Double number = 0D;
	//余量
	private Double margin = 0D;
	//通知单状态
	private String status = "";
	//页签类别（0：待审核，1：未入厂，2：厂区，3：已出厂）
	private String type = "";
	//榜单编号
	private String pnCode;
	//单位
	private String unit;
	//收获时间
	private String signTime = "";
	//通知单类型（1：普通派车，2：多单合并派车）
	private String noticeType = "";
	//订单类型（0：一单一车，1：一单多车）
	private String billType = "";
	//供应商备注
	private String supRemark = "";
	//矿口
	private String minemouth = "";
	 //采矿点id
    private String miningpointid = "";
    //采矿点id
    private String miningpointname = "";
    
	public String getMiningpointid() {
		return miningpointid;
	}
	public void setMiningpointid(String miningpointid) {
		this.miningpointid = miningpointid;
	}
	public String getMiningpointname() {
		return miningpointname;
	}
	public void setMiningpointname(String miningpointname) {
		this.miningpointname = miningpointname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBillTime() {
		return billTime;
	}
	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public Double getTareWeight() {
		return tareWeight;
	}
	public void setTareWeight(Double tareWeight) {
		this.tareWeight = tareWeight;
	}
	public Double getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}
	public String getLightTime() {
		return lightTime;
	}
	public void setLightTime(String lightTime) {
		this.lightTime = lightTime;
	}
	public String getWeightTime() {
		return weightTime;
	}
	public void setWeightTime(String weightTime) {
		this.weightTime = weightTime;
	}
	public Double getNumber() {
		return number;
	}
	public void setNumber(Double number) {
		this.number = number;
	}
	public Double getMargin() {
		return margin;
	}
	public void setMargin(Double margin) {
		this.margin = margin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPnCode() {
		return pnCode;
	}
	public void setPnCode(String pnCode) {
		this.pnCode = pnCode;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSignTime() {
		return signTime;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
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
	@Override
	public String toString() {
		return "NoticeListVo [id=" + id + ", billCode=" + billCode + ", code=" + code + ", billTime=" + billTime
				+ ", vehicle=" + vehicle + ", material=" + material + ", driver=" + driver + ", source=" + source
				+ ", grossWeight=" + grossWeight + ", tareWeight=" + tareWeight + ", netWeight=" + netWeight
				+ ", lightTime=" + lightTime + ", weightTime=" + weightTime + ", number=" + number + ", margin="
				+ margin + ", status=" + status + ", type=" + type + ", pnCode=" + pnCode + ", unit=" + unit
				+ ", signTime=" + signTime + ", noticeType=" + noticeType + ", billType=" + billType + ", supRemark="
				+ supRemark + ", minemouth=" + minemouth + "]";
	}
}
