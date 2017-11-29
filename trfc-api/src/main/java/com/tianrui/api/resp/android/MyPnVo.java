package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

/**
 * @annotation 客商APP我的磅单查询列表
 * @author zhanggaohao
 *
 */
public class MyPnVo extends BaseResp {
	
	private static final long serialVersionUID = -5333031938072107934L;
	private String id = "";
	//榜单编号
	private String code = "";
	//车辆
	private String vehicle = "";
	//司机
	private String driver = "";
	//物料
	private String material = "";
	//毛重
	private String grossWeight = "";
	//皮重
	private String tareWeight = "";
	//净重
	private String netWeight = "";
	//过磅时间
	private String time = "";
	//单位
	private String unit = "";
	//通知单号
	private String noticeCode = "";
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
	public String getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}
	public String getTareWeight() {
		return tareWeight;
	}
	public void setTareWeight(String tareWeight) {
		this.tareWeight = tareWeight;
	}
	public String getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getNoticeCode() {
		return noticeCode;
	}
	public void setNoticeCode(String noticeCode) {
		this.noticeCode = noticeCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "MyPnVo [id=" + id + ", code=" + code + ", vehicle=" + vehicle + ", driver=" + driver + ", material="
				+ material + ", grossWeight=" + grossWeight + ", tareWeight=" + tareWeight + ", netWeight=" + netWeight
				+ ", time=" + time + ", unit=" + unit + ", noticeCode=" + noticeCode + "]";
	}
}