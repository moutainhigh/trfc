package com.tianrui.api.req.android;

import com.tianrui.api.req.BaseReq;

public class AppBase extends BaseReq  {
	private static final long serialVersionUID = -9181263323911889976L;
	//当前用户ID
	private String userId;
	//当前登录用户身份（1：客户，2：供应商）
	private String IDType;
	//销售组织ID
	private String salesOrg;
	//用户NCID
	private String ncId;
	//密钥
	private String key;
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
	public String getNcId() {
		return ncId;
	}
	public void setNcId(String ncId) {
		this.ncId = ncId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AppBase [userId=" + userId + ", IDType=" + IDType + ", salesOrg=" + salesOrg + ", ncId=" + ncId
				+ ", key=" + key + "]";
	}
}
