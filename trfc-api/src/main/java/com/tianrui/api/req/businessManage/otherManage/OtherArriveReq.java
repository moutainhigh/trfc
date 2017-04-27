package com.tianrui.api.req.businessManage.otherManage;

import com.tianrui.api.req.BaseReq;

public class OtherArriveReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8693577159505750320L;
	
    private String id;

    private String code;

    private String businesstype;

    private String supplierid;

    private String auditstatus;

    private String enteryard;

    private String leaveyard;

    private String reason;
    //编号key
    private String codekey;
    private String senddepartmentid;

    private String customerid;

    private Long starttime;

    private Long endtime;

    private String datasource;

    private String materielid;

    private String status;

    private String cargo;

    private String receivedepartmentid;

    private String vehicleid;

    private String warehouseid;

    private String driverid;
    
    private String icardno;
    private String icardid;

    private Double count;

    private String remark;

    /**
     * 用户
     */
    private String userid;

    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 分页查询开始位置
     */
	private int start;
	/**
	 * 查询量
	 */
	private int limit;
	/**
	 * 开始时间
	 */
	private Long startDate;
	/**
	 * 结束时间
	 */
	private Long endDate;
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
	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	public String getAuditstatus() {
		return auditstatus;
	}
	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	public String getEnteryard() {
		return enteryard;
	}
	public void setEnteryard(String enteryard) {
		this.enteryard = enteryard;
	}
	public String getLeaveyard() {
		return leaveyard;
	}
	public void setLeaveyard(String leaveyard) {
		this.leaveyard = leaveyard;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSenddepartmentid() {
		return senddepartmentid;
	}
	public void setSenddepartmentid(String senddepartmentid) {
		this.senddepartmentid = senddepartmentid;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	public String getMaterielid() {
		return materielid;
	}
	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getReceivedepartmentid() {
		return receivedepartmentid;
	}
	public void setReceivedepartmentid(String receivedepartmentid) {
		this.receivedepartmentid = receivedepartmentid;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getWarehouseid() {
		return warehouseid;
	}
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getIcardid() {
		return icardid;
	}
	public void setIcardid(String icardid) {
		this.icardid = icardid;
	}
	public Double getCount() {
		return count;
	}
	public void setCount(Double count) {
		this.count = count;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getIcardno() {
		return icardno;
	}
	public void setIcardno(String icardno) {
		this.icardno = icardno;
	}
	public String getCodekey() {
		return codekey;
	}
	public void setCodekey(String codekey) {
		this.codekey = codekey;
	}
	

}
