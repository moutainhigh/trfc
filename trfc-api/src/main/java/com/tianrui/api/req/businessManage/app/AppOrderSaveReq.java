package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.req.BaseReq;

public class AppOrderSaveReq extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;
	//用户id
	private String userId;
	//订单id
	private String orderId;
	//派单时间
	private String orderDate;
	//车辆id
	private String vehicleId;
	//司机id
	private String driverId;
	//预提量
	private String number;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	
	

}
