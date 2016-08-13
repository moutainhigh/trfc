package com.tianrui.smartfactory.common.constants;

public enum ErrorCode {

	//系统异常
	SYSTEM_ERROR("E000001","服务器繁忙."),
	
	//参数异常
	PARAM_ERROR("E100001","参数异常."),
	PARAM_NULL_ERROR("E100002","参数异常,参数不能为空."),
	PARAM_TOKEN_ERROR("E100003","参数异常."),
	PARAM_PARAM_ERROR("E100004","校验码异常."),
	
	

	;
	
	private String code;
	private String msg;
	
	private ErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
