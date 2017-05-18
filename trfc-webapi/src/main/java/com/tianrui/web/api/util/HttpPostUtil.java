package com.tianrui.web.api.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostUtil {
	
	private HttpPostUtil(){
		
	}
	private static HttpPostUtil httpPostUtil ;
	public static HttpPostUtil getInstance(){
		if( httpPostUtil ==null ){
			httpPostUtil =new HttpPostUtil();
		}
		return httpPostUtil;
	}
	
	

	public String post(String url,String param){
		String sb = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestMethod("POST");

			// 表单参数与get形式一样
			conn.setDoOutput(true);// 是否输入参数

			// 连接
			// conn.connect();
			// 获取URLConnection对象对应的输出流
			conn.getOutputStream().write(param.toString().getBytes());// 输入参数
			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			sb = in.readLine();
			// 断开连接
			conn.disconnect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
}
