package com.tianrui.api.req.quality.sales;

import com.tianrui.api.req.BaseReq;

public class AssayReportItemReq extends BaseReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2106343938653316762L;
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
     * 用户
     */
	private String user;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getAssayid() {
		return assayid;
	}
	public void setAssayid(String assayid) {
		this.assayid = assayid;
	}
	public String getTestval() {
		return testval;
	}
	public void setTestval(String testval) {
		this.testval = testval;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
}
