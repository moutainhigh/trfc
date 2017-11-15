package com.tianrui.smartfactory.common.vo;

import com.tianrui.smartfactory.common.constants.ErrorCode;

public class AppResult {

	private String code;
	
	private String message;
	
	private Object data;
	
	private Long serviceTime = System.currentTimeMillis();
	
	private static volatile AppResult appResult = null;
	
	public static AppResult getInstance() {
		if (appResult == null) {
			synchronized (AppResult.class) {
				if (appResult == null) {
					appResult = new AppResult();
				}
			}
		}
		return appResult;
	}

	public AppResult() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Long serviceTime) {
		this.serviceTime = serviceTime;
	}

	public void setErrorCode(ErrorCode errorCode ){
		this.code = errorCode.getCode();
		this.message = errorCode.getMsg();
		this.serviceTime = System.currentTimeMillis() - this.serviceTime;
	}
	
}
