package com.tianrui.test.api.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.Md5Utils;

public class ApiTest {

	//private static String domin="http://172.19.4.73:8080/";
	private static String domin="http://127.0.0.1/";
	private static String uri_login="api/system/login";
	private static String uri_rfid="api/card/rfidReg";
	
	
	static ApiParam<UserReq> getParam(){
		ApiParam<UserReq> api =new ApiParam<UserReq>();
		
		UserReq userReq =new UserReq();
		userReq.setAccount("test");
		userReq.setPswd(Md5Utils.MD5("123456"));
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime("2017-01-07 11:01:00");
		head.setUserId("111111");
		
		api.setBody(userReq);
		api.setHead(head);
		return api;
	}
	static ApiParam<RFIDReq> getParam1(){
		ApiParam<RFIDReq> api =new ApiParam<RFIDReq>();
		
		RFIDReq req =new RFIDReq();
		req.setRfid("1234533");
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime("2017-01-07 11:01:00");
		head.setUserId("111111");
		
		api.setBody(req);
		api.setHead(head);
		return api;
	}
	
	
	static void setkey( ApiParam api){
		api.getHead().setKey(Md5Utils.MD5(Constant.apiAuthKey+api.getHead().getCallTime()));
	}
	
	static void setMd5( ApiParam api){
		api.setSign(Constant.apiAuthSign);
		api.setSign(Md5Utils.MD5(JSON.toJSONString(api)));
	}
	static String  httpPost(String url,String param){
		StringBuffer sb = new StringBuffer("");
	    try {
			URL realUrl = new URL(url);
	        // 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
	        // 设置通用的请求属性
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			//conn.setRequestProperty("Content-Type","aplication/json");
			//conn.setRequestProperty("Content-Type","aplication/x-www-form-urlencoded");
	        // 发送POST请求必须设置如下两行
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        //连接
	        conn.connect();
	        // 获取URLConnection对象对应的输出流
	        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	        out.writeBytes(param);
	        // flush输出流的缓冲
	        out.flush();
            out.close();
	        // 定义BufferedReader输入流来读取URL的响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines="";
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            // 断开连接
            conn.disconnect();
	    }catch(Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return sb.toString();
	}
	public static void main(String[] args) {
//		ApiParam<UserReq> req =getParam();
//		setkey(req);
//		setMd5(req);
//		String param =JSON.toJSONString(req);
//		System.out.println(httpPost(domin+uri,"p="+param));
		
		ApiParam<RFIDReq> req =getParam1();
		setkey(req);
		setMd5(req);
		String param =JSON.toJSONString(req);
		System.out.println(httpPost(domin+uri_rfid,"p="+param));
		
//		System.out.println(Md5Utils.MD5("1"));
	}
	
	
}
