package com.tianrui.test.api.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorSystemSave;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.Md5Utils;

public class TestApiDoorSystem {

	private static String domin = "http://localhost/";
	private static String uri_info = "api/doorSystem/enterFactoryCheck";
	private static String uri_leave = "api/doorSystem/leaveFactoryCheck";
	private static String uri_record = "api/doorSystem/record";
	private static String uri_queryWaiting = "api/doorSystem/queryWaiting";
	
	public static void main(String[] args) throws Exception {
		URL url = new URL(domin + uri_leave);
		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");
		StringBuffer params = new StringBuffer();
		
	    // 表单参数与get形式一样
		connection.setDoOutput(true);// 是否输入参数
        params.append("p").append("=").append(JSON.toJSONString(getParam0()));
        
        String aa =params.toString();
        System.out.println(aa);
        byte[] bypes = params.toString().getBytes();
        connection.getOutputStream().write(bypes);// 输入参数
		
		// 发送
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String response = in.readLine();
		System.out.println(response);
		
		
	}
	static ApiParam<ApiDoorQueueQuery> getParam2(){
		ApiParam<ApiDoorQueueQuery> api =new ApiParam<ApiDoorQueueQuery>();
		
		ApiDoorQueueQuery req =new ApiDoorQueueQuery();
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		head.setUserId("111111");
		
		api.setBody(req);
		api.setHead(head);
		
		setkey(api);
		setMd5(api);
		return api;
	}
	static ApiParam<ApiDoorSystemSave> getParam1(){
		ApiParam<ApiDoorSystemSave> api =new ApiParam<ApiDoorSystemSave>();
		
		ApiDoorSystemSave req =new ApiDoorSystemSave();
		req.setNotionformcode("DH201708280042");
		req.setIcardno("121298869");
		req.setServicetype("0");
		req.setType("1");
		req.setTime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		head.setUserId("111111");
		
		api.setBody(req);
		api.setHead(head);
		
		setkey(api);
		setMd5(api);
		return api;
	}
	static ApiParam<VehicleCheckApi> getParam0(){
		ApiParam<VehicleCheckApi> api =new ApiParam<VehicleCheckApi>();
		
		VehicleCheckApi req =new VehicleCheckApi();
		req.setRfid("E2000016130B015320904013");
		req.setVehicleNo("豫A77777");
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		head.setUserId("111111");
		
		api.setBody(req);
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
