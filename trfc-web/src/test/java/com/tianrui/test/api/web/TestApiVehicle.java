package com.tianrui.test.api.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.basicFile.measure.VehicleManageApi;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;

public class TestApiVehicle {

	private static String domin="http://127.0.0.1/";
	private static String uri_login="api/system/login";
	private static String uri_rfid="api/card/rfidReg";
	private static String url_vehicle = "api/vehicle/vehicleCard";
	
	public static void main(String[] args) throws Exception {
		URL url = new URL(domin+url_vehicle);
		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");
		StringBuffer params = new StringBuffer();
		
	    // 表单参数与get形式一样
		connection.setDoOutput(true);// 是否输入参数
        params.append("p").append("=").append(JSON.toJSONString(getParam1()));
        
        String aa =params.toString();
        System.out.println(aa);
        byte[] bypes = params.toString().getBytes();
        connection.getOutputStream().write(bypes);// 输入参数
		
		
		// 发送
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String response = in.readLine();
		System.out.println(response);
		
		
	}
	static ApiParam<VehicleManageApi> getParam1(){
		ApiParam<VehicleManageApi> api =new ApiParam<VehicleManageApi>();
		
		VehicleManageApi req =new VehicleManageApi();
		req.setRfid("06D4A539303738202020AB01");
		req.setVehicleNo("豫GA2571");
		
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
