package com.tianrui.smartfactory.common.constants;

/**
 * 
 * @类描述：一些常量类工具
 * @创建人：tank
 * @创建时间：2016年1月17日下午4:51:26
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午4:51:26
 * @修改备注：
 *
 */
public class Constant {
    
    public final static String INFO = "[INFO]";
    
    public final static int MAX_PAGESIZE = 200;
    
    //初始密码
    public final static String PASSWORD_INIT="123456";
    
    public final static String FILE_URL_PRE="http://172.19.4.73:81/uploadimgs/";
    
    
    // 用户验证key
    public final static String apiAuthKey ="@#!tr2017!#$";
    // md5篡改
    public final static String apiAuthSign ="trapi20170107";
    // 成功
    public final static String SUCCESS ="000000";
    
    //redis 缓存的前缀
    public final static String PRE_REDIS ="trfc_";
    
    //日志文件 访问日志 记录访问来源
    public final static String ACCESS ="access";
    //日志文件  性能日志 记录返回时间
    public final static String PREF ="pref";
    
    public final static String ORG_ID ="0001PP1000000000BSF6";
    public final static String ORG_NAME ="天瑞集团汝州水泥有限公司";
    
//    public final static String ORG_ID ="0001PP1000000000BSIU";
//    public final static String ORG_NAME ="天瑞集团水泥有限公司（裕泰公司）";
    
    //接口URL
    public final static  String URL_DOMAIN = "http://172.19.4.48:8091";
//    public final static  String URL_DOMAIN = "http://172.20.10.18:8089";
    public final static String URL_RETURN_SALESAPPLICATION = URL_DOMAIN + "/api/boOrder/orderReturn";
    public final static String URL_RETURN_SALESOUTBOUNDCATION=URL_DOMAIN+"/api/saleoutboundorder/orderReturn";
    public final static String URL_RETURN_PURCHASESTORAGEATION=URL_DOMAIN+"/api/purchasestorage/orderReturn";
    public final static String URL_PULL_FROM_DC = URL_DOMAIN+"/api/fileSync/pull";
    
    
    
    
    //用户身份类型
    //客户
    public final static String USER_CUSTOMER = "1";
    //供应商
    public final static String USER_SUPPLIER = "2";
    //APP版本
    //安卓
    public final static String APP_ANDROID = "android";
    //苹果
    public final static String APP_IOS = "ios";
    
    
    //推单历史记录表状态 1已推送,0未推送,2推单中
    public final static String PUSH_STATUS_ING ="2"; 
    public final static String PUSH_STATUS_END ="1"; 
    public final static String PUSH_STATUS_NULL ="0"; 
    
    //榜单推单状态 0：未推单，1：推单中，2：已推单
    public final static String POUND_PUSH_STATUS_ING ="1"; 
    public final static String POUND_PUSH_STATUS_END ="2"; 
    public final static String POUND_PUSH_STATUS_NULL ="0"; 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
