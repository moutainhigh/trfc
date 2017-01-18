package com.tianrui.test.api.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.basicFile.measure.VehicleManageApi;
import com.tianrui.api.req.basicFile.nc.WarehouseManageQuery;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.Md5Utils;
import com.tianrui.smartfactory.common.vo.Result;

public class TestApiWarehouse2 {

	private static String domin="http://127.0.0.1/";
	
	private static String uri="api/dc/warehouse/updateData";
	public static void main(String[] args) throws Exception {
		URL url = new URL(domin+uri);
		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");
		StringBuffer params = new StringBuffer();
		
	    // 表单参数与get形式一样
		connection.setDoOutput(true);// 是否输入参数
        //params.append("p").append("=").append(JSON.toJSONString(getParam1()));
        
        String aa =params.toString();
        System.out.println(aa);
        byte[] bypes = params.toString().getBytes();
        connection.getOutputStream().write(bypes);// 输入参数
		
		
		// 发送
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String response = in.readLine();
		System.out.println(response);
		
		Result rs =JSON.parseObject(response, Result.class);
		System.out.println("111111$$"+rs.getData());
		
		
	}
	
	static void setkey( ApiParam api){
		api.getHead().setKey(Md5Utils.MD5(Constant.apiAuthKey+api.getHead().getCallTime()));
	}
	
	static void setMd5( ApiParam api){
		api.setSign(Constant.apiAuthSign);
		api.setSign(Md5Utils.MD5(JSON.toJSONString(api)));
	}
	
}
