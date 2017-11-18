package com.tianrui.api.req.android;

public class BillSave extends AppBase {
	private static final long serialVersionUID = -4196553762448897051L;
	//物料
	private String material;
	//车辆ID
	private String vehicle;
	//司机ID
	private String driver;
	//提货量
	private Double number;
	//单位
	private String unit;
	//订单日期
	private Long billTime;
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
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
	public Double getNumber() {
		return number;
	}
	public void setNumber(Double number) {
		this.number = number;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Long getBillTime() {
		return billTime;
	}
	public void setBillTime(Long billTime) {
		this.billTime = billTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BillSave [material=" + material + ", vehicle=" + vehicle + ", driver=" + driver + ", number=" + number
				+ ", unit=" + unit + ", billTime=" + billTime + "]";
	}
}