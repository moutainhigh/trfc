package com.tianrui.smartfactory.common.constants;

/**
 * 
 * @类描述：一些常量类工具
 * @创建人：tank
 * @创建时间：2016年1月17日下午4:51:26
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午4:51:26 @修改备注：
 *
 */
public class Constant {

	public final static String INFO = "[INFO]";

	public final static int MAX_PAGESIZE = 200;

	// 初始密码
	public final static String PASSWORD_INIT = "123456";

	public final static String FILE_URL_PRE = "http://172.19.4.73:81/uploadimgs/";

	// 用户验证key
	public final static String apiAuthKey = "@#!tr2017!#$";
	// md5篡改
	public final static String apiAuthSign = "trapi20170107";
	// 成功
	public final static String SUCCESS = "000000";

	// redis 缓存的前缀
	public final static String PRE_REDIS = "trfc_";

	// 日志文件 访问日志 记录访问来源
	public final static String ACCESS = "access";
	// 日志文件 性能日志 记录返回时间
	public final static String PREF = "pref";

	public final static String ORG_ID = "0001PP1000000000BSF6";
	public final static String ORG_NAME = "天瑞集团汝州水泥有限公司";

	// public final static String ORG_ID ="0001PP1000000000BSIU";
	// public final static String ORG_NAME ="天瑞集团水泥有限公司（裕泰公司）";

	// 接口URL
	public final static String URL_DOMAIN = "http://172.19.4.48:8091";
	// public final static String URL_DOMAIN = "http://172.20.10.18:8089";
	public final static String URL_RETURN_SALESAPPLICATION = URL_DOMAIN + "/api/boOrder/orderReturn";
	public final static String URL_RETURN_SALESOUTBOUNDCATION = URL_DOMAIN + "/api/saleoutboundorder/orderReturn";
	public final static String URL_RETURN_PURCHASESTORAGEATION = URL_DOMAIN + "/api/purchasestorage/orderReturn";
	public final static String URL_PULL_FROM_DC = URL_DOMAIN + "/api/fileSync/pull";

	// 用户身份类型
	// 客户
	public final static String USER_CUSTOMER = "1";
	// 供应商
	public final static String USER_SUPPLIER = "2";
	// APP版本
	// 安卓
	public final static String APP_ANDROID = "android";
	// 苹果
	public final static String APP_IOS = "ios";

	// 数据有效性 0：无效 1：有效
	public final static String DATA_VAILD = "1";
	public final static String DATA_INVALID = "0";

	// 推单历史记录表状态 1已推送,0未推送,2推单中
	public final static String PUSH_STATUS_ING = "2";
	public final static String PUSH_STATUS_END = "1";
	public final static String PUSH_STATUS_NULL = "0";

	// 榜单推单状态 0：未推单，1：推单中，2：已推单
	public final static String POUND_PUSH_STATUS_ING = "1";
	public final static String POUND_PUSH_STATUS_END = "2";
	public final static String POUND_PUSH_STATUS_NULL = "0";

	/**
	 * 业务类型
	 * 
	 * 0 采购到货 1 采购退货 2 销售提货 3 销售退货 4 厂内倒运 5 其它入库 6 其它入库退货 7 其它出库 8 其它出库退货 9 工程车辆
	 */
	public final static String NOTICE_OF_CGDH = "0";
	public final static String NOTICE_OF_CGTH = "1";
	public final static String NOTICE_OF_XSDH = "2";
	public final static String NOTICE_OF_XSTH = "3";
	public final static String NOTICE_OF_CNDY = "4";
	public final static String NOTICE_OF_QTRK = "5";
	public final static String NOTICE_OF_QTRKTH = "6";
	public final static String NOTICE_OF_QTCK = "7";
	public final static String NOTICE_OF_QTCKTH = "8";
	public final static String NOTICE_OF_GCCL = "9";

	// 榜单净重小于0.8 不推单
	public final static Double NOT_RETURN_NUM = 0.8;

	// 数字常量工具
	public final static String ZERO_STRING = "0";
	public final static String ONE_STRING = "1";
	public final static String TWO_STRING = "2";
	public final static String THREE_STRING = "3";
	public final static String FOUR_STRING = "4";
	public final static String FIVE_STRING = "5";
	public final static String SIX_STRING = "6";
	public final static String SEVEN_STRING = "7";
	public final static String EIGHT_STRING = "8";
	public final static String NINE_STRING = "9";

	public final static Integer ZERO_NUMBER = 0;
	public final static Integer ONE_NUMBER = 1;
	public final static Integer TWO_NUMBER = 2;
	public final static Integer THREE_NUMBER = 3;
	public final static Integer FOUR_NUMBER = 4;
	public final static Integer FIVE_NUMBER = 5;
	public final static Integer SIX_NUMBER = 6;
	public final static Integer SEVEN_NUMBER = 7;
	public final static Integer EIGHT_NUMBER = 8;
	public final static Integer NINE_NUMBER = 9;

}
