package com.tianrui.api.resp.quality.purchase;

import com.tianrui.api.resp.BaseResp;

public class PurchaseSamplingItemResp extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5699167116581552588L;
	
    private String id;
    /**
     * 采样车辆id
     */
    private String samplingcar;
    /**
     * 采样编号
     */
    private String samplingcode;
    /**
     * 供应商
     */
    private String supplier;
    /**
     * 矿点
     */
    private String mine;
    /**
     * 物料
     */
    private String material;
    /**
     * 车辆
     */
    private String vehicle;
    /**
     * 备注
     */
    private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSamplingcar() {
		return samplingcar;
	}
	public void setSamplingcar(String samplingcar) {
		this.samplingcar = samplingcar;
	}
	public String getSamplingcode() {
		return samplingcode;
	}
	public void setSamplingcode(String samplingcode) {
		this.samplingcode = samplingcode;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getMine() {
		return mine;
	}
	public void setMine(String mine) {
		this.mine = mine;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
