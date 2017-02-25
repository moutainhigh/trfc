package com.tianrui.service.bean.quality.file;

public class QualityScheme {

	/**
 	* 主键id
    */
	private String id;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 物料id
	 */
	private String materialid;
	/**
	 * 单据类型
	 */
	private String bills;
	/**
	 * 有效�?(0:有效,1:无效)
	 */
	private String invalid;
	/**
	 * 默认�?(0:默认�?,1:非默认的)
	 */
	private String def;
	/**
	 * 类型(0:采购项目,1:�?售项�?)
	 */
	private String type;
	/**
	 * 质量标准
	 */
	private String standard;
	/**
	 * 描述
	 */
	private String describe;
	private String creator;
	private Long createtime;
	private String modifier;
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

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid == null ? null : materialid.trim();
    }

    public String getBills() {
        return bills;
    }

    public void setBills(String bills) {
        this.bills = bills == null ? null : bills.trim();
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid == null ? null : invalid.trim();
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def == null ? null : def.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
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