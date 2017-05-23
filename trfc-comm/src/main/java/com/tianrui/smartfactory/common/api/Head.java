package com.tianrui.smartfactory.common.api;

public class Head {
	//用户id
	private String userId;
	//具体设备
	private String callSource;
	//子系统分类  1:门岗 2:卡务 3客商APP
	private String callType;
	//调用时间
	private String callTime;
	//用户加密标识
	private String key;
	//TOKEN
	private String token;
	
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
	
}
