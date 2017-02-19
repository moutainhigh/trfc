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
    
    
    //redis 缓存的前缀
    //public final static String PRE_REDIS ="wl2_";
    public final static String PRE_REDIS ="wl_";
    
    //日志文件 访问日志 记录访问来源
    public final static String ACCESS ="access";
    //日志文件  性能日志 记录返回时间
    public final static String PREF ="pref";
    
    public final static String ORG_ID ="0001PP1000000000BSF6";
    public final static String ORG_NAME ="天瑞集团汝州水泥有限公司";
    
    //接口url
    public final static  String URL_DOMAIN = "http://localhost";
    public final static String URL_RETURN_SALESAPPLICATION = URL_DOMAIN + "/api/boOrder/orderReturn";
    
    
}
