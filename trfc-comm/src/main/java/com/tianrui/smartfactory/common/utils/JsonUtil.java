package com.tianrui.smartfactory.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.tianrui.smartfactory.common.api.ApiParam;

public class JsonUtil {
	
	static Logger logger =LoggerFactory.getLogger(JsonUtil.class);

	public static  <T> ApiParam<T> getApiParam(String param, @SuppressWarnings("rawtypes") Class clazz){
		ApiParam<T> appParam =JSON.parseObject(param, TypeReferenceUtil.getTypeReference(clazz));
		return appParam;
	}
	
	public static boolean validateSign(String json, String signValue, String authSign) {
		boolean result = false;
		try {
			// 将签名字符串替换为签名KEY
			String sourceStr = json.replaceAll(signValue, authSign);
			// MD5加密
			String md5Str = Md5Utils.MD5(sourceStr);
			// 比较MD5字符串
			result = (md5Str.equalsIgnoreCase(signValue));
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);
		}
		return result;
	}
	public static boolean validateKey(String keyValue, String time, String authKey) {
		boolean result = false;
		try {
			result = (keyValue.equalsIgnoreCase( Md5Utils.MD5(time+authKey)));
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);
		}
		return result;
	}
	

	
}
