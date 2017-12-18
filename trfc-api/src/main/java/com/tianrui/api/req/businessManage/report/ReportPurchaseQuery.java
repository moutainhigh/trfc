package com.tianrui.api.req.businessManage.report;

import com.tianrui.api.req.BaseReq;

/**
 * 采购报表查询条件
 * 
 * @author Administrator
 *
 */
public class ReportPurchaseQuery extends BaseReq {

	private static final long serialVersionUID = 1L;
	 //  供应商id
    private String supplierid;
	// 供应商
	private String suppliername;
	//矿口id
	private String minemouthid;
	// 矿口
	private String minemouthname;
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
	//  推单状态（0：未推单，1：推单中，2：已推单）
    private String returnstatus;
    //  堆场id
    private String yardid;

   
	//  堆场名称
    private String yardname;
	// 开始时间
	private String beginTime;
	// 结束时间
	private String endTime;
	// 备注
	private String remark;

	private String currUid;
	
	
	//到货通知单号
	private String billcode;
	//过磅单号
	private String poundcode;
	
	
	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public String getPoundcode() {
		return poundcode;
	}

	public void setPoundcode(String poundcode) {
		this.poundcode = poundcode;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public String getMinemouthid() {
		return minemouthid;
	}

	public void setMinemouthid(String minemouthid) {
		this.minemouthid = minemouthid;
	}

	public String getMaterielid() {
		return materielid;
	}

	public void setMaterielid(String materielid) {
		this.materielid = materielid;
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


	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getMinemouthname() {
		return minemouthname;
	}

	public void setMinemouthname(String minemouthname) {
		this.minemouthname = minemouthname;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
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

	public String getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}

	public String getYardname() {
		return yardname;
	}

	public void setYardname(String yardname) {
		this.yardname = yardname;
	}
	
	

}