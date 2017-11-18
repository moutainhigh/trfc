package com.tianrui.api.req.android;

/**
 * @annotation 客商APP我的磅单查询条件
 * @author zhanggaohao
 *
 */
public class MyPnListParam extends AppBase {
	
	private static final long serialVersionUID = 3574816464825541063L;
	//榜单ID
	private String id;
	//通知单号
	private String noticeCode;
	//车辆ID
	private String vehicle;
	//司机ID
	private String driver;
	//物料
	private String material;
	//订单日期开始时间
	private Long startTime;
	//订单日期结束时间
	private Long endTime;
	private int start;
	private int limit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNoticeCode() {
		return noticeCode;
	}
	public void setNoticeCode(String noticeCode) {
		this.noticeCode = noticeCode;
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
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "MyPnListParam [id=" + id + ", noticeCode=" + noticeCode + ", vehicle=" + vehicle + ", driver=" + driver
				+ ", material=" + material + ", startTime=" + startTime + ", endTime=" + endTime + ", start=" + start
				+ ", limit=" + limit + "]";
	}
}