package com.tianrui.service.bean.quality.file;

public class SupplierScheme {


	/**
    * 主键id
    */
	private String id;
	/**
	 * 编码
	 */
    private String code;
	/**
	 * 方案名称
	 */
    private String name;
	/**
	 * 供应商id
	 */
    private String supplierid;
	/**
	 * 供应商备�?
	 */
    private String supremark;
	/**
	 * 物料id
	 */
    private String materialid;
	/**
	 * 质检方案id
	 */
    private String schemeid;
	/**
	 * �?始时�?
	 */
    private Long starttime;
	/**
	 * 结束时间
	 */
    private Long endtime;
	/**
	 * 平均方式(0:月加权平均�??,1:日平均�??)
	 */
    private String mean;
	/**
	 * 扣款方式(0:按金�?,1:按吨)
	 */
    private String deduct;
	/**
	 * 默认(0:默认�?,1:不默�?)
	 */
    private String ref;
	/**
	 * 有效�?(0:有效,1:无效)
	 */
    private String invalid;
	/**
	 * 备注
	 */
    private String remark;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(String supplierid) {
        this.supplierid = supplierid == null ? null : supplierid.trim();
    }

    public String getSupremark() {
        return supremark;
    }

    public void setSupremark(String supremark) {
        this.supremark = supremark == null ? null : supremark.trim();
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid == null ? null : materialid.trim();
    }

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid == null ? null : schemeid.trim();
    }

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean == null ? null : mean.trim();
    }

    public String getDeduct() {
        return deduct;
    }

    public void setDeduct(String deduct) {
        this.deduct = deduct == null ? null : deduct.trim();
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref == null ? null : ref.trim();
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid == null ? null : invalid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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