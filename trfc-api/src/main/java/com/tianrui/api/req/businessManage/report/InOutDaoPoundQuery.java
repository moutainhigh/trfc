package com.tianrui.api.req.businessManage.report;

import com.tianrui.api.req.BaseReq;

public class InOutDaoPoundQuery extends BaseReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = -20378083136218927L;
	
	// 供应商
	private String suppliername;
	// 客户
	private String customername;
	// 物料
	private String cargo;
	// 司机姓名
	private String drivername;
	// 车牌号
	private String vehicleno;
	// 开始时间
	private String beginTime;
	// 结束时间
	private String endTime;
	// 备注
	private String remark;

	private String currUid;
	private String billtype;
	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCurrUid() {
		return currUid;
	}

	public void setCurrUid(String currUid) {
		this.currUid = currUid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBilltype() {
		return billtype;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}
	
	
}
