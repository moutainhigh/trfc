package com.tianrui.service.bean.quality.file;
/**
 * 合格证维�?
 * @author lxy
 *
 */
public class Certification {


	/**
	 * 主键id
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
     * �?售电�?
     */
    private String salestel;
    /**
     * 有效�?(0:有效,1:无效)
     */
    private String invalid;
    /**
     * �?�?
     */
    private String intro;
    /**
     * 创建�?
     */
    private String creator;
    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 修改�?
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Long modifytime;
    /**
     * 数据同步时间�?
     */
    private Long utc;
    /**
     * 状�??(0:删除,1:正常)
     */
    private String state;

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

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark == null ? null : trademark.trim();
    }

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm == null ? null : norm.trim();
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }

    public String getFactorysite() {
        return factorysite;
    }

    public void setFactorysite(String factorysite) {
        this.factorysite = factorysite == null ? null : factorysite.trim();
    }

    public String getSalestel() {
        return salestel;
    }

    public void setSalestel(String salestel) {
        this.salestel = salestel == null ? null : salestel.trim();
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid == null ? null : invalid.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}