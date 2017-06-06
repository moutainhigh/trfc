package com.tianrui.api.resp.businessManage.logisticsManage;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

public class PurchaseLogisticsResp extends BaseResp {

	private static final long serialVersionUID = 797031090293962312L;
	//订单号
	private String billCode;
	//派车通知单
	private String noticeCode;
	//单据类型
	private String type;
	//供应商
	private String supplierName;
	//物料
	private String materialName;
	//车号
	private String vehicleNo;
	//派车时间
	private String noticeTime;
	//入厂时间
	private String enterTime;
	//重车时间
	private String weightTime;
	//轻车时间
	private String lightTime;
	//收货时间
	private String receiptTime;
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
	public String getType() {
		String temp = "";
		switch (this.type) {
		case "0":
			temp = "到货";
			break;
		case "1":
			temp = "退货";
			break;
		default:
			break;
		}
		return temp;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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
	public String getWeightTime() {
		return weightTime;
	}
	public void setWeightTime(Long weightTime) {
		this.weightTime = DateUtil.parse(weightTime, DateUtil.Y_M_D_H_M_S);
	}
	public String getLightTime() {
		return lightTime;
	}
	public void setLightTime(Long lightTime) {
		this.lightTime = DateUtil.parse(lightTime, DateUtil.Y_M_D_H_M_S);
	}
	public String getReceiptTime() {
		return receiptTime;
	}
	public void setReceiptTime(Long receiptTime) {
		this.receiptTime = DateUtil.parse(receiptTime, DateUtil.Y_M_D_H_M_S);
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
