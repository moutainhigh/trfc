package com.tianrui.service.bean.quality.sales;
/**
 * 销售化验报告-检测值
 * @author lxy
 *
 */
public class AssayReportItem {
	/**
	 * 主键id
	 */
    private String id;
    /**
     * 质检项目id
     */
    private String itemid;
    /**
     * 化验报告id
     */
    private String assayid;
    /**
     * 检测值
     */
    private String testval;
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
     * 数据中心同步时间戳
     */
    private Long utc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid == null ? null : itemid.trim();
    }

    public String getAssayid() {
        return assayid;
    }

    public void setAssayid(String assayid) {
        this.assayid = assayid == null ? null : assayid.trim();
    }

    public String getTestval() {
        return testval;
    }

    public void setTestval(String testval) {
        this.testval = testval == null ? null : testval.trim();
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