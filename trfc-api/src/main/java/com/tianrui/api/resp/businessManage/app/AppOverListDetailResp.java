package com.tianrui.api.resp.businessManage.app;

import com.tianrui.api.req.BaseReq;
/**
 * 郭榜单详情
 * @author lixp
 *
 */
public class AppOverListDetailResp extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;

	private String id;
	//订单编码
	private String code;
	//通知单编码
	private String noticeCode;
	//过榜单编码
	private String overListCode;
	//货物名称
	private String cargoName;
	//日期
	private String data;
	//客户名称
	private String customName;
	//预提量
	private String withholdingAmount;
	//司机名称
	private String driverName;
	//司机电话
	private String driverMobile;
	//车牌号
	private String vehicleNo;
	//毛重
	private String grossWeight;
	//皮重
	private String tare;
	//结算重量
	private String settlementWeight;
	//出场编码
	private String doorCode;
	//轻车时间
	private String tareData;
	//重车时间
	private String grossWeightData;
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
	public String getNoticeCode() {
		return noticeCode;
	}
	public void setNoticeCode(String noticeCode) {
		this.noticeCode = noticeCode;
	}
	public String getOverListCode() {
		return overListCode;
	}
	public void setOverListCode(String overListCode) {
		this.overListCode = overListCode;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getWithholdingAmount() {
		return withholdingAmount;
	}
	public void setWithholdingAmount(String withholdingAmount) {
		this.withholdingAmount = withholdingAmount;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverMobile() {
		return driverMobile;
	}
	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}
	public String getTare() {
		return tare;
	}
	public void setTare(String tare) {
		this.tare = tare;
	}
	public String getSettlementWeight() {
		return settlementWeight;
	}
	public void setSettlementWeight(String settlementWeight) {
		this.settlementWeight = settlementWeight;
	}
	public String getDoorCode() {
		return doorCode;
	}
	public void setDoorCode(String doorCode) {
		this.doorCode = doorCode;
	}
	public String getTareData() {
		return tareData;
	}
	public void setTareData(String tareData) {
		this.tareData = tareData;
	}
	public String getGrossWeightData() {
		return grossWeightData;
	}
	public void setGrossWeightData(String grossWeightData) {
		this.grossWeightData = grossWeightData;
	}
	
	
	

	

}
