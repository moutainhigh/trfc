package com.tianrui.test.api.datacenter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.android.LoginUserParam;
import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.basicFile.nc.WarehouseManageQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.api.req.system.auth.AppUserReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.Md5Utils;

@SuppressWarnings("unused")
public class TestWarehouse {

	//private static String domin = "http://localhost/";
	private static String domin = "http://172.16.2.99:28080/";
	private static String uri_login = "api/dc/warehouse/getLastUTC";
	
	public static void main(String[] args) throws Exception {
		URL url = new URL(domin + uri_login);
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
        System.out.println(url.getHost()+":"+url.getPort()+url.getPath());
        System.out.println(params);
		System.out.println(response);
		
		
	}
	
	
	static ApiParam<WarehouseManageQuery> getParam0(){
		ApiParam<WarehouseManageQuery> api =new ApiParam<WarehouseManageQuery>();
		
		WarehouseManageQuery req =new WarehouseManageQuery();
//		req.setAccount("GY0141044516");
//		req.setPswd("f379eaf3c831b04de153469d1bec345e");
		req.setName("中文乱码");
		
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("3");
		head.setCallTime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		//head.setUserId("043e657203f841fcbbeed0118b49a185");
		
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
