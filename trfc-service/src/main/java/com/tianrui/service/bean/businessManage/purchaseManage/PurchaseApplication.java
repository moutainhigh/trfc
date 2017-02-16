package com.tianrui.service.bean.businessManage.purchaseManage;
/**
 * @Description 采购申请单Bean
 * @author zhanggaohao
 * @version 2017年2月13日 上午9:00:48
 */
public class PurchaseApplication {
	//主键id
    private String id;
	//订单编号
    private String code;
	//审核状态 0-未审核，1-已审核
    private String auditstatus;
	//来源 0-联机，1-脱机
    private String source;
	//订单类型id
    private String billtypeid;
	//订单类型名称
    private String billtypename;
	//订单日期
    private Long billtime;
	//总数量
    private Double sumnum;
	//组织id
    private String orgid;
	//组织名称
    private String orgname;
	//供应商id
    private String supplierid;
	//供应商名称
    private String suppliername;
    //供应商备注
    private String supplierremark;
	//部门id
    private String departmentid;
	//部门
    private String departmentname;
	//采购员id
    private String buyerid;
	//采购员姓名
    private String buyername;
	//审核人id
    private String auditid;
	//审核人姓名
    private String auditname;
	//审核日期
    private Long audittime;
	//状态：（0：删除，1：正常）
    private String state;
	//制单人id
    private String makerid;
	//制单人名称
    private String makebillname;
	//制单时间
    private Long makebilltime;
	//制单人
    private String creator;
	//制单时间
    private Long createtime;
	//修改人
    private String modifier;
	//修改时间
    private Long modifytime;
	//备注
    private String remark;

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

    public String getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(String auditstatus) {
        this.auditstatus = auditstatus == null ? null : auditstatus.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getBilltypeid() {
        return billtypeid;
    }

    public void setBilltypeid(String billtypeid) {
        this.billtypeid = billtypeid == null ? null : billtypeid.trim();
    }

    public String getBilltypename() {
        return billtypename;
    }

    public void setBilltypename(String billtypename) {
        this.billtypename = billtypename == null ? null : billtypename.trim();
    }

    public Long getBilltime() {
        return billtime;
    }

    public void setBilltime(Long billtime) {
        this.billtime = billtime;
    }

    public Double getSumnum() {
        return sumnum;
    }

    public void setSumnum(Double sumnum) {
        this.sumnum = sumnum;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(String supplierid) {
        this.supplierid = supplierid == null ? null : supplierid.trim();
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername == null ? null : suppliername.trim();
    }

    public String getSupplierremark() {
		return supplierremark;
	}

	public void setSupplierremark(String supplierremark) {
		this.supplierremark = supplierremark == null ? null : supplierremark.trim();
	}

	public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname == null ? null : departmentname.trim();
    }

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid == null ? null : buyerid.trim();
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername == null ? null : buyername.trim();
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

    public String getAuditname() {
        return auditname;
    }

    public void setAuditname(String auditname) {
        this.auditname = auditname == null ? null : auditname.trim();
    }

    public Long getAudittime() {
        return audittime;
    }

    public void setAudittime(Long audittime) {
        this.audittime = audittime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getMakerid() {
        return makerid;
    }

    public void setMakerid(String makerid) {
        this.makerid = makerid == null ? null : makerid.trim();
    }

    public String getMakebillname() {
        return makebillname;
    }

    public void setMakebillname(String makebillname) {
        this.makebillname = makebillname == null ? null : makebillname.trim();
    }

    public Long getMakebilltime() {
        return makebilltime;
    }

    public void setMakebilltime(Long makebilltime) {
        this.makebilltime = makebilltime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}