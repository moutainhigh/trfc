package com.tianrui.api.resp.businessManage.app;

import com.tianrui.api.req.BaseReq;
/**
 * 通知单详情
 * @author lixp
 *
 */
public class AppNoticeOrderDetailResp extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;

	private String id;
	//订单编码
	private String code;
	//通知单编码
	private String noticeCode;
	//货物名称
	private String cargoName;
	//日期
	private String data;
	//客户名称
	private String customName;
	//余量
	private String allowance;
	//预提量
	private String withholdingAmount;
	//状态
	private String status;
	//司机名称
	private String driverName;
	//司机电话
	private String driverMobile;
	//车牌号
	private String vehicleNo;
	//派车日期
	private String noticeData;
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
	public String getAllowance() {
		return allowance;
	}
	public void setAllowance(String allowance) {
		this.allowance = allowance;
	}
	public String getWithholdingAmount() {
		return withholdingAmount;
	}
	public void setWithholdingAmount(String withholdingAmount) {
		this.withholdingAmount = withholdingAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getNoticeData() {
		return noticeData;
	}
	public void setNoticeData(String noticeData) {
		this.noticeData = noticeData;
	}
	
	
	

}
