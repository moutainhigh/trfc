package com.tianrui.smartfactory.common.common;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.Md5Utils;

public class ApiParamUtils {

	
	public static <T> ApiParam<T> getApiParam(T t){
		ApiParam<T> api =new ApiParam<T>();
		
		Head head =new Head();
		head.setCallSource("厂区回传");
		head.setCallType("厂区回传");
		head.setCallTime(DateUtil.getDateString(new Date()));
		head.setUserId("厂区回传");
		
		api.setBody(t);
		api.setHead(head);
		setkey(api);
		setMd5(api);
		return api;
	}
	
	static <T> void setkey(ApiParam<T> api){
		api.getHead().setKey(Md5Utils.MD5(Constant.apiAuthKey+api.getHead().getCallTime()));
	}
	
	static <T> void setMd5(ApiParam<T> api){
		api.setSign(Constant.apiAuthSign);
		api.setSign(Md5Utils.MD5(JSON.toJSONString(api)));
	}
}
