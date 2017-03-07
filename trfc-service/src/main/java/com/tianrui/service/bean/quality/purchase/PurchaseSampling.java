package com.tianrui.service.bean.quality.purchase;

public class PurchaseSampling {
	/**
	 * 主键id
	 */
    private String id;
    /**
     * 编号
     */
    private String code;
    /**
     * 采样日期
     */
    private Long samplingtime;
    /**
     * 采样车辆
     */
    private String samplingcar;
    /**
     * 采样编号
     */
    private String samplingcode;
    /**
     * 化验类型
     */
    private String assaytype;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态：（0：删除，1：显示）
     */
    private String state;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 修改者
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Long modifytime;
    /**
     * 时间戳
     */
    private Long utc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getSamplingtime() {
        return samplingtime;
    }

    public void setSamplingtime(Long samplingtime) {
        this.samplingtime = samplingtime;
    }

    public String getSamplingcar() {
        return samplingcar;
    }

    public void setSamplingcar(String samplingcar) {
        this.samplingcar = samplingcar == null ? null : samplingcar.trim();
    }

    public String getSamplingcode() {
        return samplingcode;
    }

    public void setSamplingcode(String samplingcode) {
        this.samplingcode = samplingcode == null ? null : samplingcode.trim();
    }

    public String getAssaytype() {
        return assaytype;
    }

    public void setAssaytype(String assaytype) {
        this.assaytype = assaytype == null ? null : assaytype.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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
}