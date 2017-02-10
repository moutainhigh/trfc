package com.tianrui.api.req.basicFile.nc;

import com.tianrui.api.req.BaseReq;

public class CustomerManageQuery extends BaseReq {

	private static final long serialVersionUID = 3974553146232519193L;
	//主键id
	private String id;
	//客户内码
    private String internalcode;
	//客户名称
    private String name;
	//所属组织id
    private String orgid;
	//所属组织名称
    private String orgname;
    //拼音助记码
    private String pinyincode;
    //状态：（0：删除，1：正常）
    private String state;
    //起始页数
    private Integer start;
    //条数
    private Integer limit;
    //档案比对时间戳
    private Long utc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPinyincode() {
		return pinyincode;
	}

	public void setPinyincode(String pinyincode) {
		this.pinyincode = pinyincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Long getUtc() {
		return utc;
	}

	public void setUtc(Long utc) {
		this.utc = utc;
	}

}