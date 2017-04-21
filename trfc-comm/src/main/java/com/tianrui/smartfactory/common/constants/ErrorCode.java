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
	 * FRID
	 */
	RFID_EXIST("E10007","FRID已存在，不能重复添加"),
	RFID_NOT_EXIST("E10008","RFID不存在，请先注册RFID！"),
	RFID_VEHICLE_EXIST("E10009","该车辆于RFID已绑定，不能重复绑定"),
	RFID_VEHICLE_NOT_EXIST("E10010","该车辆于RFID未绑定，请先绑定."),
	
	/**
	 * IC卡
	 */
	CARD_NOT_EXIST("E10031","IC卡不存在，请先注册."),
	CARD_REPEAT_REGISTER("E10032","此IC卡重复注册，请更换IC卡."),
	CARD_IN_USE("E10033","IC卡正在使用中，请更换IC卡."),
	
	/**
	 * 订单
	 */
	APPLICATION_NOT_EXIST("E11001", "该订单不存在."),
	
	/**
	 * 通知单
	 */
	NOTICE_NOT_EXIST("E10020","该通知单不存在."),
	NOTICE_NOT_AUDIT("E10021","该通知单未通过审核."),
	NOTICE_ON_INVALID("E10022","该通知单已作废."),
	NOTICE_NOT_LOAD("E10023","该通知单还未完成装货."),
	NOTICE_OUT_FACTORY("E10024","该通知单绑定的车辆已出厂."),
	/**
	 * 磅单
	 */
	POUNDNOTE_NOT_EXIST("E10100","该磅单不存在."),
	/**
	 * 车辆
	 */
	VEHICLE_EXIST("E10010","车辆已经存在."),
	VEHICLE_NOT_EXIST("E10011","车辆不存在."),
	VEHICLE_NOT_NOTICE("E10012","该车辆没有通知单."),
	VEHICLE_NOTICE_NOT_ONLY("E10013","该车辆有多个通知单."),
	VEHICLE_NOTICE_ALREADY_ENTER("E10014","该车辆绑定的通知单已经入场."),
	VEHICLE_NOTICE_NOT_ENTER("E10015","该车辆绑定的通知单未通过入厂门禁."),
	VEHICLE_NOTICE_NOT_ACCESSRECORD("E10016","该车辆绑定的通知单没有通过入厂门禁."),
	VEHICLE_NOTICE_NOT_ONE_WEIGHT("E10017","该车辆未过一次磅房."),
	VEHICLE_NOTICE_NOT_TWO_WEIGHT("E10018","该车辆未过二次磅房."),
	VEHICLE_NOTICE_NOT_LOAD("E10019","该车辆还未完成装货."),
	
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
	SYSTEM_USER_ERROR10("E200009","密码修改失败，请稍后重试."),
	
	SYSTEM_MENU_ERROR6("E200106","找不到相关菜单记录."),
	SYSTEM_MENU_ERROR7("E200107","该菜单名称已经存在."),
	SYSTEM_MENU_ERROR8("E200008","该菜单编码已经存在."),
	
	SYSTEM_ROLE_ERROR6("E200206","找不到相关角色记录."),
	SYSTEM_ROLE_ERROR7("E200207","该角色名称已经存在."),
	SYSTEM_ROLE_ERROR8("E200008","该角色编码已经存在."),
	
	
	SYSTEM_AUTH_API_ERROR6("E200101","系统访问接口与子系统类型不匹配."),
	
	
	//APP版本
	APP_VERSION_EXIST("E300000","未找到该系统适配的版本！");
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
