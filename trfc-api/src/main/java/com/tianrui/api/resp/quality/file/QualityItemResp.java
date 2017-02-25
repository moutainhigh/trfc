package com.tianrui.api.resp.quality.file;

import com.tianrui.api.resp.BaseResp;

public class QualityItemResp extends BaseResp {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6618008033361487799L;
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
     * 状态(0:删除,1:正常)
     */
    private String state;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    
}
