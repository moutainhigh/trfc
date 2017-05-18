package com.tianrui.web.util;

import javax.servlet.http.HttpServletRequest;

import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;

public class CurrUserUtils {

	public static String getCurrid(HttpServletRequest request){
		SystemUserResp user = SessionManager.getSessionUser(request);
		return user.getId();
	} 
	public static String getCurrOrgName(){
		return Constant.ORG_NAME;
	} 
	public static String getCurrOrgid(){
		return Constant.ORG_ID;
	} 
}
