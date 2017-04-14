package com.tianrui.api.resp.businessManage.app;

import com.tianrui.api.req.BaseReq;
/**
 * 订单详情
 * @author lixp
 *
 */
public class AppOrderDetailResp extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;

	private String id;
	//订单编码
	private String code;
	//物料
	private String materialName;
	//销售组织
	private String orgName;
	//客户/供应商
	private String customerName;
	//订单日期
	private String billDateStr;
	//余量
	private Double margin;
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
	 * @return the materialName
	 */
	public String getMaterialName() {
		return materialName;
	}
	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @return the billDateStr
	 */
	public String getBillDateStr() {
		return billDateStr;
	}
	/**
	 * @return the margin
	 */
	public Double getMargin() {
		return margin;
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
	 * @param materialName the materialName to set
	 */
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @param billDateStr the billDateStr to set
	 */
	public void setBillDateStr(String billDateStr) {
		this.billDateStr = billDateStr;
	}
	/**
	 * @param margin the margin to set
	 */
	public void setMargin(Double margin) {
		this.margin = margin;
	}
	
}
