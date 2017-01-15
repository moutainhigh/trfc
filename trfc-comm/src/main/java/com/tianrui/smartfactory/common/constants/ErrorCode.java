package com.tianrui.smartfactory.common.constants;

public enum ErrorCode {

	//操作成功
	SYSTEM_SUCCESS("000000","操作成功"),
	//系统异常
	SYSTEM_ERROR("E00000","服务器异常,请联系管理员."),
	//操作失败
	OPERATE_ERROR("E00001","服务器繁忙,请稍后重试."),
	
	/**
	 * 参数相关
	 */
	PARAM_ERROR("E10000","参数异常."),
	PARAM_NULL_ERROR("E10002","参数异常,参数不能为空."),
	PARAM_TOKEN_ERROR("E10003","KEY验证失败."),
	PARAM_CHECK_CODE_ERROR("E10004","校验码异常."),
	PARAM_REPEAT_ERROR("E10005","数据重复."),
	PARAM_NULL_USER_ERROR("E10006","参数异常,参数用户信息不能为空."),
	
	
	/**
	 * 用户相关
	 */
	SYSTEM_USER_ERROR1("E200001","账户不存在."),
	SYSTEM_USER_ERROR2("E200002","密码错误."),
	SYSTEM_USER_ERROR3("E200003","用户被锁定."),
	SYSTEM_USER_ERROR4("E200004","用户被禁用."),
	SYSTEM_USER_ERROR5("E200005","用户无效."),
	SYSTEM_USER_ERROR6("E200006","找不到相关用户记录."),
	SYSTEM_USER_ERROR7("E200007","该登录账户已经存在."),
	SYSTEM_USER_ERROR8("E200008","该用户编码已经存在."),
	SYSTEM_USER_ERROR9("E200009","原密码错误."),
	
	SYSTEM_AUTH_API_ERROR6("E200101","系统访问接口与子系统类型不匹配."),
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
