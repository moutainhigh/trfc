package com.tianrui.api.req.android;

/**
 * @annotation 客商APP通知单列表查询条件
 * @author zhanggaohao
 *
 */
public class NoticeListParam extends AppBase {

	private static final long serialVersionUID = -4256379170667959044L;
	//页签类别（0：待审核，1：未入厂，2：厂区，3：已出厂）
	private String type;
	//通知单ID
	private String id;
	//通知单号
	private String code;
	//车辆
	private String vehicle;
	//司机
	private String driver;
	//物料
	private String material;
	//来源（0：业务平台, 2：客商APP）
	private String source;
	//开始时间
	private Long startTime;
	//结束时间
	private Long endTime;
	private int start;
	private int limit;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
		return "NoticeListParam [type=" + type + ", id=" + id + ", code=" + code + ", vehicle=" + vehicle + ", driver="
				+ driver + ", material=" + material + ", source=" + source + ", startTime=" + startTime + ", endTime="
				+ endTime + ", start=" + start + ", limit=" + limit + "]";
	}
}
