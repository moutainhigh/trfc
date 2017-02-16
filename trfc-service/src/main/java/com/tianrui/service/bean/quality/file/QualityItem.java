package com.tianrui.service.bean.quality.file;

/**
 * 
 * @author lxy
 *
 */
public class QualityItem {
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

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units == null ? null : units.trim();
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line == null ? null : line.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula == null ? null : formula.trim();
    }

    public String getVgroups() {
        return vgroups;
    }

    public void setVgroups(String vgroups) {
        this.vgroups = vgroups == null ? null : vgroups.trim();
    }

    public String getVdays() {
        return vdays;
    }

    public void setVdays(String vdays) {
        this.vdays = vdays == null ? null : vdays.trim();
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype == null ? null : vtype.trim();
    }

    public String getInvlid() {
        return invlid;
    }

    public void setInvlid(String invlid) {
        this.invlid = invlid == null ? null : invlid.trim();
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
}