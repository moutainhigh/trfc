package com.tianrui.api.resp.businessManage.app;

import com.tianrui.api.req.BaseReq;
/**
 * 订单
 * @author lixp
 *
 */
public class AppOrderResp extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;

	private String id;
	//订单子表id
	private String detailid;
	//订单编码
	private String code;
	//物料名称
	private String materialName;
	//客户/供应商
	private String customerName;
	//总量
	private Double billSum;
	//余量
	private Double margin;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the detailid
	 */
	public String getDetailid() {
		return detailid;
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
	 * @return the billSum
	 */
	public Double getBillSum() {
		return billSum;
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
	 * @param detailid the detailid to set
	 */
	public void setDetailid(String detailid) {
		this.detailid = detailid;
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
	 * @param billSum the billSum to set
	 */
	public void setBillSum(Double billSum) {
		this.billSum = billSum;
	}
	/**
	 * @param margin the margin to set
	 */
	public void setMargin(Double margin) {
		this.margin = margin;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
