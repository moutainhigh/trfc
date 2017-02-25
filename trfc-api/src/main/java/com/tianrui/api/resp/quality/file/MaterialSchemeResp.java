package com.tianrui.api.resp.quality.file;

import com.tianrui.api.resp.BaseResp;

public class MaterialSchemeResp extends BaseResp{

	/**
	 * 序号
	 */
	private static final long serialVersionUID = 7191442742470457280L;
	/**
	 * id
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
     * 显示
     */
    private String shows;
    /**
     * 有效性(0:有效,1:无效)
     */
    private String invalid;
    /**
     * 结论
     */
    private String verdict;
    /**
     * 状态(0:删除,1:正常)
     */
    private String state;
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
	public String getShows() {
		return shows;
	}
	public void setShows(String shows) {
		this.shows = shows;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


}
