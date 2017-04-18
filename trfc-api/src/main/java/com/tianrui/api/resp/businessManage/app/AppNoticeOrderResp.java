package com.tianrui.api.resp.businessManage.app;

import com.tianrui.api.req.BaseReq;
/**
 * 订单
 * @author lixp
 *
 */
public class AppNoticeOrderResp extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;

	private String id;
	//通知单号
	private String code;
	//订单编码
	private String billCode;
	//订单日期
	private String billtime;
	//派单日期
	private String noticetime;
	//车号
	private String vehicleno;
	//物料名称
	private String materialName;
	//预提量
	private Double number;
	//来源
	private String source;
	//状态
	private String status;
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
	 * @return the billCode
	 */
	public String getBillCode() {
		return billCode;
	}
	/**
	 * @return the billtime
	 */
	public String getBilltime() {
		return billtime;
	}
	/**
	 * @return the noticetime
	 */
	public String getNoticetime() {
		return noticetime;
	}
	/**
	 * @return the vehicleno
	 */
	public String getVehicleno() {
		return vehicleno;
	}
	/**
	 * @return the materialName
	 */
	public String getMaterialName() {
		return materialName;
	}
	/**
	 * @return the number
	 */
	public Double getNumber() {
		return number;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
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
	 * @param billCode the billCode to set
	 */
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	/**
	 * @param billtime the billtime to set
	 */
	public void setBilltime(String billtime) {
		this.billtime = billtime;
	}
	/**
	 * @param noticetime the noticetime to set
	 */
	public void setNoticetime(String noticetime) {
		this.noticetime = noticetime;
	}
	/**
	 * @param vehicleno the vehicleno to set
	 */
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	/**
	 * @param materialName the materialName to set
	 */
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(Double number) {
		this.number = number;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
