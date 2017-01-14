package com.tianrui.api.resp.businessManage.salesManage;

import com.tianrui.api.resp.BaseResp;

/**
 * api销售通知单详情
 * @author Administrator
 *
 */
public class ApiSalesArriveResp extends BaseResp {
	
	private static final long serialVersionUID = 4625515863613381387L;
	//车牌号
	private String vehicleno="";
	//供应商/客户
	private String customerid="";
	//物料
	private String materiel="";
	//水泥类型 1袋装2散装
	private String cementtype="";
	//业务类型 
	//  1采购    
	//	2销售
	//	3其它入库
	//	4其它出库
	//	5厂内倒运
	//	6工程车辆，
	//	11采购退货
	//	21销售退货
	//	31其它入库退货
	//	41其它出库退货
	private String servicetype="";
	//
	private String notionformcode="";
	//
	private String batchnumber="";
	//
	private String primary="";
	//
	private String vehicleid="";
	//矿口  采购用，销售为空
	private String minemouth="";
	//预提量 单位吨
	private String number="";
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getMateriel() {
		return materiel;
	}
	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}
	public String getCementtype() {
		return cementtype;
	}
	public void setCementtype(String cementtype) {
		this.cementtype = cementtype;
	}
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public String getNotionformcode() {
		return notionformcode;
	}
	public void setNotionformcode(String notionformcode) {
		this.notionformcode = notionformcode;
	}
	public String getBatchnumber() {
		return batchnumber;
	}
	public void setBatchnumber(String batchnumber) {
		this.batchnumber = batchnumber;
	}
	public String getPrimary() {
		return primary;
	}
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getMinemouth() {
		return minemouth;
	}
	public void setMinemouth(String minemouth) {
		this.minemouth = minemouth;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

}
