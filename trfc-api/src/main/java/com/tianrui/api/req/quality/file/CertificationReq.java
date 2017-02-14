package com.tianrui.api.req.quality.file;

import com.tianrui.api.req.BaseReq;

public class CertificationReq extends BaseReq {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8605493534324505112L;
	/**
	 * id
	 */
	private String id;
    /**
     * 物料id
     */
    private String materialid;
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
    /**
     * 用户id
     */
    private String user;
    /**
     * 分页查询开始位置
     */
    private int start;
    /**
     * 分页查询数据量
     */
    private int limit;
    
	public String getMaterialid() {
		return materialid;
	}
	public void setMaterialid(String materialid) {
		this.materialid = materialid;
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
