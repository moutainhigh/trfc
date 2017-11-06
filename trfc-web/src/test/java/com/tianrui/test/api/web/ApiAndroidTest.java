package com.tianrui.test.api.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.system.auth.AppUserReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.Md5Utils;

public class ApiAndroidTest {

	//private static String domin="http://172.19.4.73:8080/";
	private static String domin="http://127.0.0.1:8080/";
	private static String uri_login="api/android/static/login";

	static ApiParam<AppUserReq> getParam(){
		ApiParam<AppUserReq> api =new ApiParam<AppUserReq>();
		
		AppUserReq va =new AppUserReq();
		va.setAccount("liyang");
		va.setPswd(Md5Utils.MD5("666666"));
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime(DateUtil.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		
		api.setBody(va);
		api.setHead(head);
		return api;
	}
	
	static <T> void setkey(ApiParam<T> api){
		api.getHead().setKey(Md5Utils.MD5(Constant.apiAuthKey+api.getHead().getCallTime()));
	}
	
	static <T> void setMd5(ApiParam<T> api){
		api.setSign(Constant.apiAuthSign);
		api.setSign(Md5Utils.MD5(JSON.toJSONString(api)));
	}
	static String  httpPost(String url,String param){
		String sb = null;
	    try {
			URL realUrl = new URL(url);
	        // 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
	        // 设置通用的请求属性
			conn.setRequestMethod("POST");
			
		    // 表单参数与get形式一样
			conn.setDoOutput(true);// 是否输入参数
			
	        //连接
	        //conn.connect();
	        // 获取URLConnection对象对应的输出流
	        conn.getOutputStream().write(param.toString().getBytes());// 输入参数
	        // 定义BufferedReader输入流来读取URL的响应
    		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    		sb = in.readLine();
            // 断开连接
            conn.disconnect();
	    }catch(Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return sb.toString();
	}
	public static void main(String[] args) {
		ApiParam<AppUserReq> req =getParam();
		setkey(req);
		setMd5(req);
		String param =JSON.toJSONString(req);
		System.out.println(httpPost(domin+uri_login,"p="+param));
		

	}
	
}
