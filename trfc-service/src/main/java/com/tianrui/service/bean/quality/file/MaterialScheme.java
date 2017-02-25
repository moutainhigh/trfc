package com.tianrui.service.bean.quality.file;

public class MaterialScheme {

	


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
     * 混合料品�??
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
     * 石膏掺加�??
     */
    private String gypsumadd;
    /**
     * 助磨剂品�??
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
     * 有效�??(0:有效,1:无效)
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
    /**
     * 创建�??
     */
    private String creator;
    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 修改�??
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Long modifytime;
    /**
     * 数据同步时间�??
     */
    private Long utc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid == null ? null : materialid.trim();
    }

    public String getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(String materialtype) {
        this.materialtype = materialtype == null ? null : materialtype.trim();
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength == null ? null : strength.trim();
    }

    public String getAdmixture() {
        return admixture;
    }

    public void setAdmixture(String admixture) {
        this.admixture = admixture == null ? null : admixture.trim();
    }

    public String getAdmixtureadd() {
        return admixtureadd;
    }

    public void setAdmixtureadd(String admixtureadd) {
        this.admixtureadd = admixtureadd == null ? null : admixtureadd.trim();
    }

    public String getGypsum() {
        return gypsum;
    }

    public void setGypsum(String gypsum) {
        this.gypsum = gypsum == null ? null : gypsum.trim();
    }

    public String getGypsumadd() {
        return gypsumadd;
    }

    public void setGypsumadd(String gypsumadd) {
        this.gypsumadd = gypsumadd == null ? null : gypsumadd.trim();
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    public String getAidadd() {
        return aidadd;
    }

    public void setAidadd(String aidadd) {
        this.aidadd = aidadd == null ? null : aidadd.trim();
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid == null ? null : invalid.trim();
    }
	public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict == null ? null : verdict.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Long getModifytime() {
        return modifytime;
    }

    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

    public Long getUtc() {
        return utc;
    }

    public void setUtc(Long utc) {
        this.utc = utc;
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