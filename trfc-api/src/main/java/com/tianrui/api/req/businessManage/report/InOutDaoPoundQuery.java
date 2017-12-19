package com.tianrui.api.req.businessManage.report;

import com.tianrui.api.req.BaseReq;

public class InOutDaoPoundQuery extends BaseReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = -20378083136218927L;
	 //  供应商id
    private String supplierid;
	// 供应商
	private String suppliername;
	// 客户
	private String customername;
	//物料id
	private String materielid;
	// 物料
	private String cargo;
	// 司机id
	private String driverid;
	// 司机姓名
	private String drivername;
	//  车辆id
    private String vehicleid;
	// 车牌号
	private String vehicleno;
	// 开始时间
	private String beginTime;
	// 结束时间
	private String endTime;
	// 备注
	private String remark;
	//订单号
	private String billcode;
	//磅单号
	private String code;

	private String currUid;
	private String billtype;
	//堆场id
	private String yardid;
	//通知单code
    private String noticecode;
    //调入堆场
    private String enteryardname;
    //调离堆场名称
    private String leaveyardname;
	//单据状态
	private String status;
	
	
	
	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public String getMaterielid() {
		return materielid;
	}

	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getYardid() {
		return yardid;
	}

	public void setYardid(String yardid) {
		this.yardid = yardid;
	}

	public String getNoticecode() {
		return noticecode;
	}

	public void setNoticecode(String noticecode) {
		this.noticecode = noticecode;
	}

	public String getEnteryardname() {
		return enteryardname;
	}

	public void setEnteryardname(String enteryardname) {
		this.enteryardname = enteryardname;
	}

	public String getLeaveyardname() {
		return leaveyardname;
	}

	public void setLeaveyardname(String leaveyardname) {
		this.leaveyardname = leaveyardname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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
