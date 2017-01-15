package com.tianrui.web.util;

import javax.servlet.http.HttpServletRequest;

public class CurrUserUtils {

	public static String getCurrid(HttpServletRequest request){
		return "root";
	} 
	public static String getCurrOrgName(HttpServletRequest request){
		return "汝州天瑞水泥";
	} 
	public static String getCurrOrgid(HttpServletRequest request){
		return "000101";
	} 
}
