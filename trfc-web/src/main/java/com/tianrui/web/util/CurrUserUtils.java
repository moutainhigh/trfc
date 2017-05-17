package com.tianrui.web.util;

import javax.servlet.http.HttpSession;

import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;

public class CurrUserUtils {

	public static String getCurrid(HttpSession session){
		SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
		return user.getId();
	} 
	public static String getCurrOrgName(HttpSession session){
		return Constant.ORG_NAME;
	} 
	public static String getCurrOrgid(HttpSession session){
		return Constant.ORG_ID;
	} 
}
