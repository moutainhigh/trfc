package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.req.BaseReq;

public class AppOrderSaveReq extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;
	//用户id
	private String userId;
	//订单id
	private String billId;
	//订单子表id
	private String billDetailId;
	//车辆id
	private String vehicleId;
	//司机id
	private String driverId;
	//预提量
	private String number;
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @return the billId
	 */
	public String getBillId() {
		return billId;
	}
	/**
	 * @return the billDetailId
	 */
	public String getBillDetailId() {
		return billDetailId;
	}
	/**
	 * @return the vehicleId
	 */
	public String getVehicleId() {
		return vehicleId;
	}
	/**
	 * @return the driverId
	 */
	public String getDriverId() {
		return driverId;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @param billId the billId to set
	 */
	public void setBillId(String billId) {
		this.billId = billId;
	}
	/**
	 * @param billDetailId the billDetailId to set
	 */
	public void setBillDetailId(String billDetailId) {
		this.billDetailId = billDetailId;
	}
	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	/**
	 * @param driverId the driverId to set
	 */
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

}
