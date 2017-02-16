package com.tianrui.service.bean.businessManage.salesManage;
/**
 * @Description 销售申请单Bean
 * @author zhanggaohao
 * @version 2017年2月4日 下午4:25:40
 */
public class SalesApplication {
	//主键id
    private String id;
    //订单编号
    private String code;
    //审核状态（0：未审核，1：已审核）
    private String status;
    //来源（0：联机，1：脱机）
    private String source;
    //订单类型id
    private String billtypeid;
    //订单类型名称
    private String billtypename;
    //客户id
    private String customerid;
    //客户名称
    private String customername;
    //区域码
    private String channelcode;
    //业务员id
    private String salesmanid;
    //业务员名称
    private String salesmanname;
    //订单日期
    private Long billtime;
    //组织id
    private String orgid;
    //组织名称
    private String orgname;
    //运输公司id
    private String transportcompanyid;
    //运输公司名称
    private String transportcompanyname;
    //部门id
    private String departmentid;
    //部门名称
    private String departmentname;
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
    //备注
    private String remarks;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //最后修改人
    private String modifier;
    //最后修改时间
    private Long modifytime;
    //NC同步时间戳
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode == null ? null : channelcode.trim();
    }

    public String getSalesmanid() {
        return salesmanid;
    }

    public void setSalesmanid(String salesmanid) {
        this.salesmanid = salesmanid == null ? null : salesmanid.trim();
    }

    public String getSalesmanname() {
        return salesmanname;
    }

    public void setSalesmanname(String salesmanname) {
        this.salesmanname = salesmanname == null ? null : salesmanname.trim();
    }

    public Long getBilltime() {
        return billtime;
    }

    public void setBilltime(Long billtime) {
        this.billtime = billtime;
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

    public String getTransportcompanyid() {
        return transportcompanyid;
    }

    public void setTransportcompanyid(String transportcompanyid) {
        this.transportcompanyid = transportcompanyid == null ? null : transportcompanyid.trim();
    }

    public String getTransportcompanyname() {
        return transportcompanyname;
    }

    public void setTransportcompanyname(String transportcompanyname) {
        this.transportcompanyname = transportcompanyname == null ? null : transportcompanyname.trim();
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
		this.makerid = makerid;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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