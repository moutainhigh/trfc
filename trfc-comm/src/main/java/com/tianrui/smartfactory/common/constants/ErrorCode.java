package com.tianrui.smartfactory.common.constants;

public enum ErrorCode {

	//操作成功
	SYSTEM_SUCCESS("000000","操作成功"),
	//系统异常
	SYSTEM_ERROR("E00000","服务器异常,请联系管理员."),
	//操作失败
	OPERATE_ERROR("E00001","服务器繁忙,请稍后重试."),
	//数据不存在
	DATA_ERROR("E00002","数据不存在，请重新确认."),
	//连接超时
	CONNECTION_TIMEOUT_ERROR("000000","数据中心连接超时，该单据未推送成功."),
	//推单失败
	RETURN_ERROR("000000","上传榜单成功，推单NC失败."),
	DATE_NOT_UPDATE("E00003","未做修改无需保存."),
	APP_BILL_RETURN_ERROR("000004","FC-DC推单失败，请稍候重试."),
	//日志修改失败
	lOGE_UPDATE_ERROR("E00004","推送管理日志修改失败！"),
	//日志修改失败
	lOGE_SAVE_ERROR("E00005","推送管理日志保存失败！"),
	//日志修改失败
	lOGE_SELECT_ERROR("E00006","未查到异常信息！"),
	//暂不支持该业务
	THE_BUSINESS_IS_NOT_SUPPORTED("E00007","暂不支持该业务！"),
	
	/**
	 * 参数相关
	 */
	PARAM_ERROR("E10000","参数异常."),
	PARAM_NULL_ERROR("E10002","参数异常,参数不能为空."),
	PARAM_TOKEN_ERROR("E10003","TOKEN验证失败."),
	PARAM_KEY_ERROR("E10003","KEY验证失败."),
	PARAM_CHECK_CODE_ERROR("E10004","校验码异常."),
	PARAM_REPEAT_ERROR("E10005","数据重复."),
	PARAM_NULL_USER_ERROR("E10006","参数异常,参数用户信息不能为空."),
	PARAM_NAME_ERROR("E10007","项目名称已存在,不能重复添加."),
	/**
	 * FRID
	 */
	RFID_EXIST("E10007","FRID已存在，不能重复添加"),
	RFID_NOT_EXIST("E10008","RFID不存在，请先注册RFID！"),
	RFID_VEHICLE_NOT_EXIST("E10010","该车辆于RFID未绑定，请先绑定."),
	RFID_VEHICLE_EXIST1("E10009","这个RFID与该车辆已经绑定，无需重复绑定！"),
    RFID_VEHICLE_EXIST2("E10011","这个RFID已经绑定过其他车辆了！"),
    RFID_ERROR1("E10012","RFID类型不是临时卡"),
    RFID_ERROR2("E10013","RFID状态失效"),
    RFID_ERROR3("E10014","RFID与车辆绑定关系不一直"),
    RFID_ERROR4("E10015","RFID未绑定车辆"),
	
	/**
	 * IC卡
	 */
	CARD_NOT_EXIST("E10031","IC卡不存在，请先注册."),
	CARD_REPEAT_REGISTER("E10032","此IC卡重复注册，请更换IC卡."),
	CARD_IN_USE("E10033","IC卡正在使用中，请更换IC卡."),
	CARD_IS_VALID("E10034","IC卡已失效."),
	CARD_ALREADY_BIND_CAR("E10035","IC卡已经绑定过车辆."),
	
	/**
	 * 订单
	 */
	APPLICATION_NOT_EXIST("E11001", "该订单不存在."),
	APPLICATION_NOT_RETURN("E11002", "自制的订单未推送至NC."),
	APPLICATION_NOT_DELETE1("E11003", "已审核的订单不能作废."),
	APPLICATION_NOT_DELETE2("E11004", "联机的订单不能作废."),
	APPLICATION_MARGIN_ERROR("E11005", "订单余量已不足."),
	APPLICATION_IS_VALID_ERROR("E11006", "已作废或作废中的订单不能派单."),
	APPLICATION_DONT_SEND_CAR("E11007", "一单一车的订单不允许派车."),
	APPLICATION_DONT_MORE_SEND_VALID("E11008", "一单多车的订单不允许APP不允许作废."),
	APPLICATION_NOT_DELETE3("E11009", "一单多车的订单不允许APP不允许作废."),
	APPLICATION_NOT_UPDATE1("E11010", "已审核的订单不能修改"),
	APPLICATION_NOT_UPDATE2("E11011", "已作废的订单不能修改"),
	APPLICATION_NOT_UPDATE3("E11012", "联机的订单不能修改"),
	APPLICATION_NOT_DELETE4("E11013", "已审核和厂区审核的订单不能删除."),
	APPLICATION_NOT_DELETE5("E11014", "联机的订单不能删除."),
	APPLICATION_NOT_DELETE6("E11015", "推单中和已退单的订单不能删除."),
	APPLICATION_DONT_NEED_UNAUDIT("E11016", "未审核的订单无需反审."),
	APPLICATION_DONT_NEED_AUDIT("E11017", "已审核的订单无需再审."),
	APPLICATION_DONT_UNAUDIT("E11018", "联机的订单不能反审."),
	APPLICATION_DONT_AUDIT("E11019", "联机的订单不能审核."),
	APPLICATION_DONT_HAVE_PERMISSIONS("E11020", "您没有对该订单操作的权限."),
	APPLICATION_MATER_NOT_PRICE("E11021", "该物料没有单价，不能生成申请单."),
	
	/**
	 * 通知单
	 */
	NOTICE_NOT_EXIST("E10020","该通知单不存在."),
	NOTICE_NOT_AUDIT("E10021","该通知单未通过审核."),
	NOTICE_ON_INVALID("E10022","该通知单已作废."),
	NOTICE_NOT_LOAD("E10023","该通知单还未完成装货."),
	NOTICE_OUT_FACTORY("E10024","该通知单已出厂."),
	NOTICE_NOT_ONE_POUNDNOTE("E10025","该通知单未过一次磅房."),
	NOTICE_NOT_SIGN("E10025","该通知单未签收确认."), 
	NOTICE_ON_SIGN("E10026","该通知单已确认,请勿重复确认."), 
	NOTICE_NOT_VALID_TIME("E10027","该通知单不在有效时间内."), 
	NOTICE_NOT_ENTER("E10028","该通知单已入厂."), 
	NOTICE_OUT_FACTORY2("E10029","该通知单无相关入厂记录."), 
	NOTICE_NUMBER_ERROR("E10030","到货量/提货量必须大于零且小于等于订单余量."), 
	NOTICE_YES_AUDIT("E10031","该通知单已经通过审核无法作废."),
	NOTICE_NOT_EXIST2("E10032","该通知单不是其他入库类型."),
	NOTICE_STATUS_ERROR("E10033","通知单状态不合法."),
	NOTICE_SEND_CAR_ERROR("E10034","订单余量过剩."),
	NOTICE_DONT_VALID_ERROR("E10035","未入厂的通知单才可以作废."), 
	NOTICE_DONT_UPDATE_ERROR("E10036","该通知单已经通过审核无法修改."),
	NOTICE_YES_POUNDNOTE("E10037","该通知单已过一次磅房."),
	NOTICE_NOT_VALID("E10038","该通知单已失效."),
	NOTICE_DONT_REPEAT_AUDIT("E10039","该通知单已审核，无需重复审核."),
	NOTICE_DONT_AUDIT("E10040","未入厂的通知单才可以审核."), 
	NOTICE_DONT_REPEAT_UNAUDIT("E10041","该通知单已反审，无需重复反审."),
	NOTICE_DONT_UNAUDIT("E10042","未入厂的通知单才可以反审."),
	NOTICE_FORCE_OUT_FACTORY("E10043","该通知单已强制出厂."),
	NOTICE_NOT_TWO_POUNDNOTE("E10044","该通知单未过二次磅房."),
	NOTICE_ONE_BILL_ONE_CAR_DONT_AUDIT("E10045", "一单一车的通知单不能审核."),
	NOTICE_ONE_BILL_ONE_CAR_DONT_UNAUDIT("E10046", "一单一车的通知单不能反审."),
	NOTICE_ONE_BILL_ONE_CAR_DONT_VALID("E10047", "一单一车的通知单不能作废."),
	NOTICE_ISVALID("E10048","该采矿点是无效的."), 
	NOTICE_MININGPOINT("E10049","该采矿点是不存在."), 
	/**
	 * 磅单
	 */
	POUNDNOTE_NOT_EXIST("E10100","该磅单不存在."),
	POUNDNOTE_RETURN_ERROR1("E10101","退货净重大于入库净重."),
	POUNDNOTE_RETURN_ERROR2("E10102","该订单的余量小于预提量."),
	POUNDNOTE_RETURN_ERROR3("E10103","过磅单据未推送到NC."),
	POUNDNOTE_ERROR0("E10104","过磅单据已作废."),
	POUNDNOTE_ERROR1("E10105","已红冲过的单据才允许参照."),
	POUNDNOTE_ERROR2("E10106","已推单过的单据才允许红冲."),
	POUNDNOTE_ERROR3("E10107","已红冲过的单据不允许重复红冲."),
	POUNDNOTE_ERROR4("E10108","退货单据不允许红冲."),
	POUNDNOTE_ERROR5("E10109","作废和退货单据不允许红冲."),
	POUNDNOTE_ERROR6("E10110","该收料员不存在."),
	POUNDNOTE_ERROR7("E10111","该榜单已经补打过，请勿重复补打."),
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
	VEHICLE_NOTICE_NOT_OUT_FACTORY("E10020","该车辆还未出厂."),
	VEHICLE_NOTICE_TWO_WEIGHT("E10021","该车辆已过二次磅房."),
	VEHICLE_IS_WX("E10022","车辆已无效."),
	VEHICLE_IS_BLACK("E10023","车辆被列入黑名单."),
	VEHICLE_NOTICE_NOT_LOAD2("E10024","该车辆还未在平台确认收货."),
	VEHICLE_DONT_BIND_RFID("E10025","该车辆已绑定过RFID."),
	VEHICLE_ALREADY_BIND_CARD("E10025","该车辆已绑定过IC卡."),
	/**
	 * 司机
	 */
	DRIVER_NOT_EXIST("E11001","司机不存在."),
	DRIVER_IS_WX("E11002","司机已无效."),
	/**
	 * 物料
	 */
	MATERIAL_NOT_EXIST("E12000","物料不存在."),
	MATERIAL_IS_WX("E12001","物料已无效."),
	/**
	 * 仓库
	 */
	WAREHOUSE_NOT_EXIST("E13000","仓库不存在."),
	/**
	 * 堆场
	 */
	YARD_NOT_EXIST("E14000","堆场不存在."),
	YARD_NOT_VALID("E14001","堆场已无效."),
	/**
	 * 采矿点
	 */
	MININGPOINT_NOT_EXIST("E15000","采矿点不存在."),
	MININGPOINT_NOT_VALID("E15001","采矿点已无效."),
	MININGPOINT_DONTS_SUPPLIER("E15002","采矿点与供应商不匹配."),
	
	/**
	 * 用户相关
	 */
	SYSTEM_USER_ERROR0("E200000","账号不正确."),
	SYSTEM_USER_ERROR1("E200001","账户不存在."),
	SYSTEM_USER_ERROR2("E200002","密码错误."),
	SYSTEM_USER_ERROR3("E200003","用户被锁定."),
	SYSTEM_USER_ERROR4("E200004","用户被禁用."),
	SYSTEM_USER_ERROR5("E200005","用户无效."),
	SYSTEM_USER_ERROR6("E200006","找不到相关用户记录."),
	SYSTEM_USER_ERROR7("E200007","该登录账户已经存在."),
	SYSTEM_USER_ERROR8("E200008","该用户编码已经存在."),
	SYSTEM_USER_ERROR9("E200009","原密码错误."),
	SYSTEM_USER_ERROR10("E200010","密码修改失败，请稍后重试."),
	SYSTEM_USER_ERROR11("E200011","该用户没有登录网页平台的权限."),
	SYSTEM_USER_ERROR12("E200012","该用户没有登录客商平台的权限."),
	SYSTEM_USER_ERROR13("E200013","该手机号已经被绑定."),
	SYSTEM_USER_ERROR14("E200014","无法识别用户身份."),
	SYSTEM_USER_ERROR15("E200015","用户无子系统角色."),
	SYSTEM_USER_ERROR16("E200016","用户无对应子系统角色."),
	SYSTEM_USER_ERROR17("E200017","当前用户不存在."),
	SYSTEM_USER_ERROR18("E200018","当前登录用户不存在."),
	
	SYSTEM_MENU_ERROR6("E200106","找不到相关菜单记录."),
	SYSTEM_MENU_ERROR7("E200107","该菜单名称已经存在."),
	SYSTEM_MENU_ERROR8("E200008","该菜单编码已经存在."),
	
	SYSTEM_ROLE_ERROR6("E200206","找不到相关角色记录."),
	SYSTEM_ROLE_ERROR7("E200207","该角色名称已经存在."),
	SYSTEM_ROLE_ERROR8("E200008","该角色编码已经存在."),
	
	
	SYSTEM_AUTH_API_ERROR6("E200101","系统访问接口与子系统类型不匹配."),
	
	
	//APP版本
	APP_VERSION_EXIST("E300000","未找到该系统适配的版本！"),
	//供应商数据权限
	SUPPLIER_GROUP_ERROR0("E400000", "该供应商已被添加！"),
	SUPPLIER_GROUP_ERROR1("E400000", "该供应商没有可切换的用户！"),
	//采购原发设置
    PRIMARY_SETTING_ERROR("E500000", "供应商已存在该物料启用数据！"),
    //采矿点原发设置
    MININGPOINT_SETTING_ERROR("E500001", "物料与之对应的采矿点已存在！"),
    //黑名单
    //重复添加
    BLACKLIST_REPEAT_ERROR("E600000", "该车辆已被添加到黑名单！"),
    BLACKLIST_REMOVE_ERROR("E600001", "该车辆已被移除黑名单！"),
    
    //质检
    PURCHASE_MIXED_ERROR1("E700001","无法混样（只能同一个供应商同一种物料同一个矿口的采样单可以混样）."),
    PURCHASE_MIXED_ERROR2("E700002","已经生成过化验单了，无法重复生成."),
    //异常审批
    EXCEPTION_AUDIT_ERROR("E800000","未找到异常审批单据."),
    EXCEPTION_AUDIT_ERROR1("E800001","该单据已被审批，请勿重复审批."),
    EXCEPTION_AUDIT_ERROR2("E800002","该车辆已经空车出厂确认."),
    EXCEPTION_AUDIT_ERROR4("E800004","该通知单已经存在未审核的申请."),
    EXCEPTION_AUDIT_ERROR3("E800003","该车辆已经无需补包确认."),
	
	//出库单不存在
	SALES_OUT_BOUND_NOT_EXIST("E90000","该出库单不存在.");
    
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
