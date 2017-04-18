package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.req.BaseReq;

public class AppDriverSaveReq extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;
	//用户id
	private String userId;
	//姓名
	private String name;
	//简称
	private String abbrname;
	//电话
	private String mobile;
	//地址
	private String addr;
	//身份证号
	private String idNo;
	//备注
	private String remark;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	

}
