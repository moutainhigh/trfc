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
    //车辆ID
    private String vehicleId;
    //车牌号
    private String vehicleNo;
    //RFID
    private String rfid;
    //司机ID
    private String driverId;
    //司机名称
    private String driverName;
    //订单来源（0：NC，1：业务平台，2：客商APP）
    private Integer billSource;
    //NC主键
    private String ncId;
    //作废状态：（0：未作废，1：作废中，2：已作废，3：作废失败）
    private String validStatus;
    //作废失败原因
    private String validError;
    //nc审核状态（1=自由,2=审批通过,3=冻结,4=关闭,5=失败,7=审批中,8=审批不通过,9=删除）
    private String ncStatus;
    //0：未推送，1推送中，2：已推送
    private String pushStatus;
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
	public Long getUtc() {
		return utc;
	}
	public void setUtc(Long utc) {
		this.utc = utc;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public Integer getBillSource() {
		return billSource;
	}
	public void setBillSource(Integer billSource) {
		this.billSource = billSource;
	}
	public String getNcId() {
		return ncId;
	}
	public void setNcId(String ncId) {
		this.ncId = ncId;
	}
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	public String getValidError() {
		return validError;
	}
	public void setValidError(String validError) {
		this.validError = validError;
	}
	public String getNcStatus() {
		return ncStatus;
	}
	public void setNcStatus(String ncStatus) {
		this.ncStatus = ncStatus;
	}
	public String getPushStatus() {
		return pushStatus;
	}
	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}
	
}