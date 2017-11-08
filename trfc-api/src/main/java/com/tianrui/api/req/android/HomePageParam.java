package com.tianrui.api.req.android;

import com.tianrui.api.req.BaseReq;

public class HomePageParam extends BaseReq {

	private static final long serialVersionUID = 3365589892403864792L;
	//当前用户ID
	private String userId;
	//当前登录用户身份（1：客户，2：供应商）
	private String IDType;
	//销售组织ID
	private String salesOrg;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIDType() {
		return IDType;
	}
	public void setIDType(String iDType) {
		IDType = iDType;
	}
	public String getSalesOrg() {
		return salesOrg;
	}
	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
