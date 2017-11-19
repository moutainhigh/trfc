package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

/**
 * @annotation 客商APP我的磅单查询列表
 * @author zhanggaohao
 *
 */
public class MyPnListVo extends BaseResp {
	
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
	//一次过磅时间
	private String oneTime = "";
	//二次过磅时间
	private String twoTime = "";
	//榜单状态（1：一次过磅，2：二次过磅）
	private String status = "";
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
	public String getOneTime() {
		return oneTime;
	}
	public void setOneTime(String oneTime) {
		this.oneTime = oneTime;
	}
	public String getTwoTime() {
		return twoTime;
	}
	public void setTwoTime(String twoTime) {
		this.twoTime = twoTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "MyPnListVo [id=" + id + ", code=" + code + ", vehicle=" + vehicle + ", driver=" + driver + ", material="
				+ material + ", oneTime=" + oneTime + ", twoTime=" + twoTime + ", status=" + status + "]";
	}
}