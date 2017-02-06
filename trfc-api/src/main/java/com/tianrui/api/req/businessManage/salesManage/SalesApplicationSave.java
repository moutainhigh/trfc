package com.tianrui.api.req.businessManage.salesManage;

import com.tianrui.api.req.BaseReq;

public class SalesApplicationSave extends BaseReq {
	
	private static final long serialVersionUID = -2559538852082320742L;
	//主键id
    private String id;
    //订单编号
    private String code;
    //订单状态
    private String status;
    //来源
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
    
    private String detailid;
    //物料id
    private String materielid;
    //物料名称
    private String materielname;
    //仓库id
    private String warehouseid;
    //仓库名称
    private String warehousename;
    //单位
    private String unit;
    //订单数量
    private Double salessum;
    //含税单价
    private Double taxprice;
    //不含税单价
    private Double untaxprice;
    //税率
    private Double taxrate;
    
    private String currid;
    
    private String currname;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getBilltypeid() {
		return billtypeid;
	}

	public void setBilltypeid(String billtypeid) {
		this.billtypeid = billtypeid;
	}

	public String getBilltypename() {
		return billtypename;
	}

	public void setBilltypename(String billtypename) {
		this.billtypename = billtypename;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getChannelcode() {
		return channelcode;
	}

	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}

	public String getSalesmanid() {
		return salesmanid;
	}

	public void setSalesmanid(String salesmanid) {
		this.salesmanid = salesmanid;
	}

	public String getSalesmanname() {
		return salesmanname;
	}

	public void setSalesmanname(String salesmanname) {
		this.salesmanname = salesmanname;
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
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getTransportcompanyid() {
		return transportcompanyid;
	}

	public void setTransportcompanyid(String transportcompanyid) {
		this.transportcompanyid = transportcompanyid;
	}

	public String getTransportcompanyname() {
		return transportcompanyname;
	}

	public void setTransportcompanyname(String transportcompanyname) {
		this.transportcompanyname = transportcompanyname;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getAuditid() {
		return auditid;
	}

	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}

	public String getAuditname() {
		return auditname;
	}

	public void setAuditname(String auditname) {
		this.auditname = auditname;
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
		this.state = state;
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
		this.makebillname = makebillname;
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
		this.remarks = remarks;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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
		this.modifier = modifier;
	}

	public Long getModifytime() {
		return modifytime;
	}

	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}

	public String getDetailid() {
		return detailid;
	}

	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}

	public String getMaterielid() {
		return materielid;
	}

	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}

	public String getMaterielname() {
		return materielname;
	}

	public void setMaterielname(String materielname) {
		this.materielname = materielname;
	}

	public String getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getSalessum() {
		return salessum;
	}

	public void setSalessum(Double salessum) {
		this.salessum = salessum;
	}

	public Double getTaxprice() {
		return taxprice;
	}

	public void setTaxprice(Double taxprice) {
		this.taxprice = taxprice;
	}

	public Double getUntaxprice() {
		return untaxprice;
	}

	public void setUntaxprice(Double untaxprice) {
		this.untaxprice = untaxprice;
	}

	public Double getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(Double taxrate) {
		this.taxrate = taxrate;
	}

	public String getCurrid() {
		return currid;
	}

	public void setCurrid(String currid) {
		this.currid = currid;
	}

	public String getCurrname() {
		return currname;
	}

	public void setCurrname(String currname) {
		this.currname = currname;
	}
    
}