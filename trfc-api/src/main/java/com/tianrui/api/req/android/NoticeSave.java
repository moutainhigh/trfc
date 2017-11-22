package com.tianrui.api.req.android;

public class NoticeSave extends AppBase {

	private static final long serialVersionUID = -1523880673015286422L;
	//车辆ID
	private String vehicle;
	//司机ID
	private String driver;
	//提货量
	private Double number;
	//订单主表ID
	private String id;
	//订单子表ID
	private String detailId;
	//多单一车订单集合
	private String ids;
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "NoticeSave [vehicle=" + vehicle + ", driver=" + driver + ", number=" + number + ", id=" + id
				+ ", detailId=" + detailId + ", ids=" + ids + ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", getVehicle()=" + getVehicle() + ", getDriver()=" + getDriver() + ", getNumber()=" + getNumber()
				+ ", getId()=" + getId() + ", getDetailId()=" + getDetailId() + ", getIds()=" + getIds()
				+ ", getUserId()=" + getUserId() + ", getIDType()=" + getIDType() + ", getSalesOrg()=" + getSalesOrg()
				+ ", getNcId()=" + getNcId() + ", getKey()=" + getKey() + ", toString()=" + super.toString()
				+ ", getCallSource()=" + getCallSource() + ", getCallTime()=" + getCallTime() + ", getCallId()="
				+ getCallId() + ", getPageNo()=" + getPageNo() + ", getPageSize()=" + getPageSize() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
