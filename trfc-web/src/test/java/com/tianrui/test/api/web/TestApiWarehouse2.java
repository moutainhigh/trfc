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
        params.append("p").append("=").append(JSON.toJSONString(getParam1()));
        
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
	static ApiParam<List<LocalBdStordoc>> getParam1(){
		ApiParam<List<LocalBdStordoc>> api =new ApiParam<List<LocalBdStordoc>>();
		
		
		List<LocalBdStordoc> list =new ArrayList<LocalBdStordoc>();
		LocalBdStordoc bean1 =new LocalBdStordoc();
		bean1.setId("AAAAS");
		bean1.setTs(new Date());
		bean1.setCreateTime(System.currentTimeMillis());
		list.add(bean1);
		LocalBdStordoc bean2 =new LocalBdStordoc();
		bean2.setId("AAAAS2");
		bean2.setTs(new Date());
		bean2.setCreateTime(System.currentTimeMillis());
		list.add(bean1);
		list.add(bean2);
		api.setBody(list);
 		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime("2017-01-07 11:01:00");
		head.setUserId("111111");
		
		api.setHead(head);
		
		setkey(api);
		setMd5(api);
		return api;
	}
	static void setkey( ApiParam api){
		api.getHead().setKey(Md5Utils.MD5(Constant.apiAuthKey+api.getHead().getCallTime()));
	}
	
	static void setMd5( ApiParam api){
		api.setSign(Constant.apiAuthSign);
		api.setSign(Md5Utils.MD5(JSON.toJSONString(api)));
	}
	
}
