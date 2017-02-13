package com.tianrui.api.req.quality.file;

import com.tianrui.api.req.BaseReq;

public class MaterialSchemeReq extends BaseReq {

	/**
	 * 序号
	 */
	private static final long serialVersionUID = 5362610726143833751L;
	/**
	 * id
	 */
    private String id;
    /**
     * 物料id
     */
    private String materialid;
    /**
     * 物料品种
     */
    private String materialtype;
    /**
     * 强度等级
     */
    private String strength;
    /**
     * 混合料品种
     */
    private String admixture;
    /**
     * 混合料掺加量
     */
    private String admixtureadd;
    /**
     * 石膏品种
     */
    private String gypsum;
    /**
     * 石膏掺加量
     */
    private String gypsumadd;
    /**
     * 助磨剂品种
     */
    private String aid;
    /**
     * 助磨剂掺加量
     */
    private String aidadd;
    /**
     * 有效性(0:有效,1:无效)
     */
    private String invalid;
    /**
     * 显示(0:全部显示,1:部分显示,2:不显示)
     */
    private String shows;
    /**
     * 结论
     */
    private String verdict;
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
	public String getMaterialtype() {
		return materialtype;
	}
	public void setMaterialtype(String materialtype) {
		this.materialtype = materialtype;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getAdmixture() {
		return admixture;
	}
	public void setAdmixture(String admixture) {
		this.admixture = admixture;
	}
	public String getAdmixtureadd() {
		return admixtureadd;
	}
	public void setAdmixtureadd(String admixtureadd) {
		this.admixtureadd = admixtureadd;
	}
	public String getGypsum() {
		return gypsum;
	}
	public void setGypsum(String gypsum) {
		this.gypsum = gypsum;
	}
	public String getGypsumadd() {
		return gypsumadd;
	}
	public void setGypsumadd(String gypsumadd) {
		this.gypsumadd = gypsumadd;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAidadd() {
		return aidadd;
	}
	public void setAidadd(String aidadd) {
		this.aidadd = aidadd;
	}
	public String getInvalid() {
		return invalid;
	}
	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}
	public String getVerdict() {
		return verdict;
	}
	public void setVerdict(String verdict) {
		this.verdict = verdict;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
	public String getShows() {
		return shows;
	}
	public void setShows(String shows) {
		this.shows = shows;
	}

    
    
}
