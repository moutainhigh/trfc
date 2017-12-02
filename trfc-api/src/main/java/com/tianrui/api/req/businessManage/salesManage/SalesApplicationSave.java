package com.tianrui.api.req.businessManage.salesManage;

import com.tianrui.api.req.BaseReq;

public class SalesApplicationSave extends BaseReq {
	
	private static final long serialVersionUID = -2559538852082320742L;
	//订单主表ID
	private String id;
	//子表ID
	private String detailId;
	//订单类型
	private String billType;
	//订单日期
	private Long billTime;
	//客户ID
	private String customer;
	//销售组织
	private String salesOrg;
	//物料ID
	private String materiel;
	//仓库ID
	private String warehouse;
	//订单量
	private Double number;
	//车辆
	private String vehicle;
	//司机
	private String driver;
	//备注
	private String remark;
	//当前登录用户ID
	private String userId;
	//当前登录用户名称
	private String userName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public Long getBillTime() {
		return billTime;
	}
	public void setBillTime(Long billTime) {
		this.billTime = billTime;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getSalesOrg() {
		return salesOrg;
	}
	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}
	public String getMateriel() {
		return materiel;
	}
	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public Double getNumber() {
		return number;
	}
	public void setNumber(Double number) {
		this.number = number;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SalesApplicationSave [id=" + id + ", detailId=" + detailId + ", billType=" + billType + ", billTime="
				+ billTime + ", customer=" + customer + ", salesOrg=" + salesOrg + ", materiel=" + materiel
				+ ", warehouse=" + warehouse + ", number=" + number + ", vehicle=" + vehicle + ", driver=" + driver
				+ ", remark=" + remark + ", userId=" + userId + ", userName=" + userName + "]";
	}
}