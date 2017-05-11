package com.tianrui.api.resp.businessManage.handset;

import com.tianrui.api.resp.BaseResp;
/**
 * 原发设置返回
 * @author Administrator
 *
 */
public class HandPrimarySettingResp extends BaseResp {

	private static final long serialVersionUID = -610722720762478057L;

	//主键id
	private String id;
	//供应商
	private String supplierName;
	private String supplierCode;
	private String supplierId;
	//物料
	private String materieName;
	private String materieId;
	private String materieCode;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getMaterieName() {
		return materieName;
	}
	public void setMaterieName(String materieName) {
		this.materieName = materieName;
	}
	public String getMaterieId() {
		return materieId;
	}
	public void setMaterieId(String materieId) {
		this.materieId = materieId;
	}
	public String getMaterieCode() {
		return materieCode;
	}
	public void setMaterieCode(String materieCode) {
		this.materieCode = materieCode;
	}
	
	
	
}