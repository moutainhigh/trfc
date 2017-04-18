package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.resp.BaseResp;

public class AppPoundOrderResp extends BaseResp{

	private static final long serialVersionUID = 4409597097855350344L;
    //登录用户id
	private String userId;
	//磅单id
	private String id;
	//磅单编号 
	private String code;
	//订单编号
	private String billcode;
	//通知单号
	private String noticecode;
	//物料
	private String materielname;
	//车号
	private String vehicleno;
	//轻车时间
	private Long lighttime;
	//重车时间
	private Long weighttime;
	//预提量
	private Double pickupquantity;
	//毛重
	private Double grossweight;
	//皮重
	private Double tareweight;
	//净重
	private Double netweight;
	//出厂编号
	private String serialnumber;
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return the billcode
	 */
	public String getBillcode() {
		return billcode;
	}
	/**
	 * @return the noticecode
	 */
	public String getNoticecode() {
		return noticecode;
	}
	/**
	 * @return the materielname
	 */
	public String getMaterielname() {
		return materielname;
	}
	/**
	 * @return the vehicleno
	 */
	public String getVehicleno() {
		return vehicleno;
	}
	/**
	 * @return the lighttime
	 */
	public Long getLighttime() {
		return lighttime;
	}
	/**
	 * @return the weighttime
	 */
	public Long getWeighttime() {
		return weighttime;
	}
	/**
	 * @return the pickupquantity
	 */
	public Double getPickupquantity() {
		return pickupquantity;
	}
	/**
	 * @return the grossweight
	 */
	public Double getGrossweight() {
		return grossweight;
	}
	/**
	 * @return the tareweight
	 */
	public Double getTareweight() {
		return tareweight;
	}
	/**
	 * @return the netweight
	 */
	public Double getNetweight() {
		return netweight;
	}
	/**
	 * @return the serialnumber
	 */
	public String getSerialnumber() {
		return serialnumber;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @param billcode the billcode to set
	 */
	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}
	/**
	 * @param noticecode the noticecode to set
	 */
	public void setNoticecode(String noticecode) {
		this.noticecode = noticecode;
	}
	/**
	 * @param materielname the materielname to set
	 */
	public void setMaterielname(String materielname) {
		this.materielname = materielname;
	}
	/**
	 * @param vehicleno the vehicleno to set
	 */
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	/**
	 * @param lighttime the lighttime to set
	 */
	public void setLighttime(Long lighttime) {
		this.lighttime = lighttime;
	}
	/**
	 * @param weighttime the weighttime to set
	 */
	public void setWeighttime(Long weighttime) {
		this.weighttime = weighttime;
	}
	/**
	 * @param pickupquantity the pickupquantity to set
	 */
	public void setPickupquantity(Double pickupquantity) {
		this.pickupquantity = pickupquantity;
	}
	/**
	 * @param grossweight the grossweight to set
	 */
	public void setGrossweight(Double grossweight) {
		this.grossweight = grossweight;
	}
	/**
	 * @param tareweight the tareweight to set
	 */
	public void setTareweight(Double tareweight) {
		this.tareweight = tareweight;
	}
	/**
	 * @param netweight the netweight to set
	 */
	public void setNetweight(Double netweight) {
		this.netweight = netweight;
	}
	/**
	 * @param serialnumber the serialnumber to set
	 */
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	
}
