package com.tianrui.smartfactory.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @类描述：日期工具类
 * @创建人：tank
 * @创建时间：2016年1月17日下午5:02:41
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午5:02:41
 * @修改备注：
 *
 */
public class DateUtil {
	
	public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
	
	public static final String Y_M_D = "yyyy-MM-dd";
	/**
	 * 
	 * @描述:获取指定日期格式字符串
	 * @return
	 * @返回类型 String test：yyyy-MM-dd HH:mm:ss
	 * @创建人 tank
	 * @创建时间 2016年1月17日下午5:02:55
	 */
	public static String getNowDateString(String format){
		return new SimpleDateFormat(format).format(new Date());
	}
	/**
	 * 
	 * @描述:获取日期格式字符串yyyy-MM-dd HH:mm:ss
	 * @param d
	 * @return
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年2月21日下午4:26:28
	 */
	public static String getDateString(Date d){
		return new SimpleDateFormat(DateUtil.Y_M_D_H_M_S).format( d);
	}
	/**
	 * 
	 * @描述:获取日期格式字符串yyyy-MM-dd HH:mm:ss
	 * @param d
	 * @return
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年2月21日下午4:26:28
	 */
	public static String getDateString(Date d,String format){
		return new SimpleDateFormat(format).format( d);
	}
	/**
	 * 
	 * @描述:获取日期格式字符串yyyy-MM-dd HH:mm:ss
	 * @param d
	 * @return
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年2月21日下午4:26:28
	 */
	public static Long parse(String str,String format){
		Long time=null;
		try {
			time=new SimpleDateFormat(format).parse(str).getTime();
		} catch (ParseException e) {
		}
		return time;
	}
	
	/**
	 * 
	 * @param time 时间戳
	 * @param format 格式化模型 yyyy-MM-dd HH:mm:ss
	 * @return 日期字符串
	 */
	public static String parse(Long time, String format){
		if(time != null){
			return new SimpleDateFormat(format).format(new Date(time));
		}
		return null;
	}
	
	/**
	 * 
	 * @param time 时间戳
	 * @param format 格式化模型 yyyy-MM-dd HH:mm:ss
	 * @return 日期字符串
	 */
	public static String parse(Date date, String format){
	    if(date != null){
	        return new SimpleDateFormat(format).format(date);
	    }
	    return null;
	}
	
	/**
	 * @annotation 获取昨天日期
	 * @return date
	 */
	public static Date getYesterday(){
	    Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
	}
	
	/**
	 * @annotation 获取今天日期
	 * @return date
	 */
	public static Date getToday(){
	    Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
	}
	
}
