package com.tianrui.test.api.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.basicFile.measure.VehicleManageApi;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorSystemSave;
import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.Md5Utils;

public class ApiTest {

	//private static String domin="http://172.19.4.73:8080/";
	private static String domin="http://127.0.0.1/";
	private static String uri_login="api/system/login";
	private static String uri_rfid="api/card/rfidReg";
	private static String url = "api/doorSystem/leaveFactoryCheck";
	
	
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
	
	static void setkey( ApiParam api){
		api.getHead().setKey(Md5Utils.MD5(Constant.apiAuthKey+api.getHead().getCallTime()));
	}
	
	static void setMd5( ApiParam api){
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
//		ApiParam<UserReq> req =getParam();
//		setkey(req);
//		setMd5(req);
//		String param =JSON.toJSONString(req);
//		System.out.println(httpPost(domin+uri_login,"p="+param));
		
		ApiParam<VehicleCheckApi> req =getParam1();
		setkey(req);
		setMd5(req);
		String param =JSON.toJSONString(req);
		System.out.println(httpPost(domin+url,"p="+param));
		
//		System.out.println(Md5Utils.MD5("1"));
	}
	static ApiParam<VehicleCheckApi> getParam1(){
		ApiParam<VehicleCheckApi> api =new ApiParam<VehicleCheckApi>();
		
		VehicleCheckApi req =new VehicleCheckApi();
		req.setVehicleNo("豫Q98765");
		req.setRfid("E2004145291401971830563B");
//		req.setNotionformcode("TH259378");
//		req.setIccode("AB0D8F93BA080400015C61242F23081D");
//		req.setType("1");
//		req.setTime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime("2017-01-07 11:01:00");
		head.setUserId("111111");
		
		api.setBody(req);
		api.setHead(head);
		return api;
	}
	
}
