package com.tianrui.api.req.quality.file;

import com.tianrui.api.req.BaseReq;


public class QualitySchemeReq extends BaseReq {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5140166564725840933L;
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
     * 模糊查询  名字
     */
    private String namelike;
    /**
     * 物料
     */
    private String materialid;
    /**
     * 单据
     */
    private String bills;
    /**
     * 有效性(0:有效,1:无效)
     */
    private String invalid;
    /**
     * 默认的(0:默认的,1:非默认的)
     */
    private String def;
    /**
     * 类型(0:采购项目,1:销售项目)
     */
    private String type;
    /**
     * 质量标准(0:标准,1:非标准)
     */
    private String standard;
    /**
     * 描述
     */
    private String describe;
    /**
     * 用户名
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaterialid() {
		return materialid;
	}
	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}
	public String getBills() {
		return bills;
	}
	public void setBills(String bills) {
		this.bills = bills;
	}
	public String getInvalid() {
		return invalid;
	}
	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getDef() {
		return def;
	}
	public void setDef(String def) {
		this.def = def;
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
	public String getNamelike() {
		return namelike;
	}
	public void setNamelike(String namelike) {
		this.namelike = namelike;
	}
    
}
