package com.tianrui.api.req.quality.file;

import com.tianrui.api.req.BaseReq;

public class QualityItemReq extends BaseReq {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5049373992081280152L;
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
     * 英文表示
     */
    private String ename;
    /**
     * 单位
     */
    private String units;
    /**
     * 对应行号
     */
    private String line;
    /**
     * 类型(0:采购项目,1:销售项目)
     */
    private String type;
    /**
     * 公式
     */
    private String formula;
    /**
     * 值分组
     */
    private String vgroups;
    /**
     * 值天数(0:单天,1:3天,2:28天)
     */
    private String vdays;
    /**
     * 值类型(0:单值,1:平均,2:其他)
     */
    private String vtype;
    /**
     * 有效性(0:有效,1:无效)
     */
    private String invlid;
    /**
     * 备注
     */
    private String remark;
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
    /**
     * 编号(模糊查询)
     */
    private String codelike;
    /**
     * 名称查询(模糊查询)
     */
    private String namelike;
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
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getVgroups() {
		return vgroups;
	}
	public void setVgroups(String vgroups) {
		this.vgroups = vgroups;
	}
	public String getVdays() {
		return vdays;
	}
	public void setVdays(String vdays) {
		this.vdays = vdays;
	}
	public String getVtype() {
		return vtype;
	}
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	public String getInvlid() {
		return invlid;
	}
	public void setInvlid(String invlid) {
		this.invlid = invlid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getCodelike() {
		return codelike;
	}
	public void setCodelike(String codelike) {
		this.codelike = codelike;
	}
    
}
