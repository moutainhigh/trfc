package com.tianrui.api.resp.quality.file;

import com.tianrui.api.resp.BaseResp;

public class CertificationResp extends BaseResp {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2866946244831792147L;
	/**
	 * 主键id
	 */
    private String id;
    /**
     * 物料id
     */
    private String materialid;
    /**
     * 物料名称
     */
    private String materialname;
    /**
     * 注册商标
     */
    private String trademark;
    /**
     * 执行标准
     */
    private String norm;
    /**
     * 生产许可证号
     */
    private String certificate;
    /**
     * 厂址
     */
    private String factorysite;
    /**
     * 销售电话
     */
    private String salestel;
    /**
     * 有效性
     */
    private String invalid;
    /**
     * 简介
     */
    private String intro;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaterialid() {
		return materialid;
	}
	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public String getTrademark() {
		return trademark;
	}
	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}
	public String getNorm() {
		return norm;
	}
	public void setNorm(String norm) {
		this.norm = norm;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getFactorysite() {
		return factorysite;
	}
	public void setFactorysite(String factorysite) {
		this.factorysite = factorysite;
	}
	public String getSalestel() {
		return salestel;
	}
	public void setSalestel(String salestel) {
		this.salestel = salestel;
	}
	public String getInvalid() {
		return invalid;
	}
	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
  
}
