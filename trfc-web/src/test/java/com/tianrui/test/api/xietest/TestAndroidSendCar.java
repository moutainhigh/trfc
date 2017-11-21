package com.tianrui.test.api.xietest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.android.NoticeSave;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.Md5Utils;
/**
 * 测试派车
 * @author lenovo
 *
 */
@SuppressWarnings("unused")
public class TestAndroidSendCar {

	//private static String domin = "http://localhost/";
	private static String domin = "http://172.19.4.97:28080/";
	private static String uri_login = "api/android/static_new/sendCar";
	
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
	
	static ApiParam<NoticeSave> getParam0(){
		ApiParam<NoticeSave> api =new ApiParam<NoticeSave>();
		
		NoticeSave req =new NoticeSave();
		req.setId("CD201711140002");
		req.setDetailId("15bfd2800f7240a08691c2b5745396a5");
		req.setVehicle("56");
		req.setDriver("E2000016130B0267217038E4");
		req.setNumber(65.0);
		
		Head head =new Head();
		head.setCallSource("1");  
		head.setCallType("3");
		head.setCallTime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		//head.setUserId("043e657203f841fcbbeed0118b49a185");
		
		head.setIDType("2");
		head.setUserId("ef7a8c0e10064b9094c37de3048f8d9a");
		head.setNcId("1002P110000000ATUUTJ");

		
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
