package com.tianrui.smartfactory.common.constants;

public enum ErrorCode {

	//系统异常
	SYSTEM_ERROR("E00000","服务器异常,请联系管理员."),
	//操作失败
	OPERATE_ERROR("E00001","服务器繁忙,请稍后重试."),
	//参数异常
	PARAM_ERROR("E10000","参数异常."),
	//参数为空异常
	PARAM_NULL_ERROR("E10002","参数异常,参数不能为空."),
	//TOKEN验证失败
	PARAM_TOKEN_ERROR("E10003","TOKEN验证失败."),
	//校验码异常
	PARAM_CHECK_CODE_ERROR("E10004","校验码异常."),
	//数据重复
	PARAM_REPEAT_ERROR("E10005","数据重复."),
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
