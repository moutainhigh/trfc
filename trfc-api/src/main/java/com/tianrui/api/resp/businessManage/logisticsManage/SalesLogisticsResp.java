package com.tianrui.api.resp.businessManage.logisticsManage;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

public class SalesLogisticsResp extends BaseResp {

	private static final long serialVersionUID = 797031090293962312L;
	//订单号
	private String billCode;
	//派车通知单
	private String noticeCode;
	//客户
	private String customerName;
	//物料
	private String materialName;
	//车号
	private String vehicleNo;
	//派车时间
	private String noticeTime;
	//入厂时间
	private String enterTime;
	//轻车时间
	private String lightTime;
	//重车时间
	private String weightTime;
	//开始装车
	private String startLoadingTime;
	//结束装车
	private String endLoadingTime;
	//铅封时间
	private String sealTime;
	//出厂时间
	private String outTime;
	
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getNoticeCode() {
		return noticeCode;
	}
	public void setNoticeCode(String noticeCode) {
		this.noticeCode = noticeCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(Long noticeTime) {
		this.noticeTime = DateUtil.parse(noticeTime, DateUtil.Y_M_D_H_M_S);
	}
	public String getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Long enterTime) {
		this.enterTime = DateUtil.parse(enterTime, DateUtil.Y_M_D_H_M_S);
	}
	public String getLightTime() {
		return lightTime;
	}
	public void setLightTime(Long lightTime) {
		this.lightTime = DateUtil.parse(lightTime, DateUtil.Y_M_D_H_M_S);
	}
	public String getWeightTime() {
		return weightTime;
	}
	public void setWeightTime(Long weightTime) {
		this.weightTime = DateUtil.parse(weightTime, DateUtil.Y_M_D_H_M_S);
	}
	public String getStartLoadingTime() {
		return startLoadingTime;
	}
	public void setStartLoadingTime(Long startLoadingTime) {
		this.startLoadingTime = DateUtil.parse(startLoadingTime, DateUtil.Y_M_D_H_M_S);
	}
	public String getEndLoadingTime() {
		return endLoadingTime;
	}
	public void setEndLoadingTime(Long endLoadingTime) {
		this.endLoadingTime = DateUtil.parse(endLoadingTime, DateUtil.Y_M_D_H_M_S);
	}
	public String getSealTime() {
		return sealTime;
	}
	public void setSealTime(Long sealTime) {
		this.sealTime = DateUtil.parse(sealTime, DateUtil.Y_M_D_H_M_S);
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(Long outTime) {
		this.outTime = DateUtil.parse(outTime, DateUtil.Y_M_D_H_M_S);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
