package com.tianrui.api.resp.other.selfsystem;

import com.tianrui.api.resp.BaseResp;

/**
 * 榜单查询返回
 * @author lixp 2017年11月3日 11:23:38
 *
 */
public class ApiPoundQueryResp extends BaseResp {
	
	private static final long serialVersionUID = 4325189759681138634L;
	//榜单id
	private String poundId;
	//车号
	private String poundNo;
	//车号
	private String vehicleNo;
	//毛重
	private String grossweight;
	//皮重
	private String tareweight;
	//净重
	private String netweight;
	//皮重时间
	private String lighttime;
	//毛重时间
	private String weighttime;
	//供应商名称/客户名称
	private String customer;
	//通知单id
	private String noticeid;
	//订单id
	private String billid;
	public String getPoundId() {
		return poundId;
	}
	public void setPoundId(String poundId) {
		this.poundId = poundId;
	}
	public String getPoundNo() {
		return poundNo;
	}
	public void setPoundNo(String poundNo) {
		this.poundNo = poundNo;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getGrossweight() {
		return grossweight;
	}
	public void setGrossweight(String grossweight) {
		this.grossweight = grossweight;
	}
	public String getTareweight() {
		return tareweight;
	}
	public void setTareweight(String tareweight) {
		this.tareweight = tareweight;
	}
	public String getNetweight() {
		return netweight;
	}
	public void setNetweight(String netweight) {
		this.netweight = netweight;
	}
	public String getLighttime() {
		return lighttime;
	}
	public void setLighttime(String lighttime) {
		this.lighttime = lighttime;
	}
	public String getWeighttime() {
		return weighttime;
	}
	public void setWeighttime(String weighttime) {
		this.weighttime = weighttime;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	
		
}