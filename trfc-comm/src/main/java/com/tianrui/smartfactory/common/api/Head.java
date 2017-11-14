package com.tianrui.smartfactory.common.api;

public class Head {
	//用户id
	private String userId;
	//具体设备
	private String callSource;
	//子系统分类  1:门岗 2:卡务 3:客商APP 4:手持机
	private String callType;
	//调用时间
	private String callTime;
	//用户加密标识
	private String key;
	//TOKEN
	private String token;
	//用户身份（1：客户，2：供应商）
	private String IDType;
	//销售组织
	private String salesOrg;
	//用户NCID
	private String ncId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCallSource() {
		return callSource;
	}
	public void setCallSource(String callSource) {
		this.callSource = callSource;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
}
