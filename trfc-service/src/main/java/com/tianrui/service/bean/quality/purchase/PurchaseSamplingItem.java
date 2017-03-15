package com.tianrui.service.bean.quality.purchase;

public class PurchaseSamplingItem {
    private String id;
    /**
     * 采样id
     */
    private String samplingid;
    /**
     * 采样车辆
     */
    private String samplingcar;
    /**
     * 采样编号
     */
    private String samplingcode;
    /**
     * 状态(0,删除,1,正常)
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
     * 数据同步时间戳
     */
    private Long utc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSamplingid() {
        return samplingid;
    }

    public void setSamplingid(String samplingid) {
        this.samplingid = samplingid == null ? null : samplingid.trim();
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