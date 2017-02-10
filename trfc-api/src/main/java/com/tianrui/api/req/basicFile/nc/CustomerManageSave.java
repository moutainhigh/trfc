package com.tianrui.api.req.basicFile.nc;

import com.tianrui.api.req.BaseReq;

public class CustomerManageSave extends BaseReq {
	
	private static final long serialVersionUID = 6925178574314554995L;
	//主键id
	private String id;
	//客户编号
	private String code;
	//客户内码
	private String internalcode;
	//客户名称
	private String name;
	//客户简称
	private String abbrname;
	//拼音助记码
	private String pinyincode;
	//客户类型（0：其他客户，1：本地客户）
	private String customertype;
	//所属组织id
	private String orgid;
	//所属组织名称
	private String orgname;
	//渠道类型
	private String channeltype;
	//渠道内码
	private String channelcode;
	//业务员id
	private String salesmanid;
	//业务员名称
	private String salesmanname;
	//运输公司id
	private String transportcompanyid;
	//运输公司名称
	private String transportcompanyname;
	//部门id
	private String departmentid;
	//部门名称
	private String departmentname;
	//状态：（0：删除，1：正常）
	private String state;
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
	//档案比对时间戳
	private Long utc;
	
    private String currUId;

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

	public String getInternalcode() {
		return internalcode;
	}

	public void setInternalcode(String internalcode) {
		this.internalcode = internalcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbrname() {
		return abbrname;
	}

	public void setAbbrname(String abbrname) {
		this.abbrname = abbrname;
	}

	public String getPinyincode() {
		return pinyincode;
	}

	public void setPinyincode(String pinyincode) {
		this.pinyincode = pinyincode;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
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

	public String getChanneltype() {
		return channeltype;
	}

	public void setChanneltype(String channeltype) {
		this.channeltype = channeltype;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getCurrUId() {
		return currUId;
	}

	public void setCurrUId(String currUId) {
		this.currUId = currUId;
	}

}