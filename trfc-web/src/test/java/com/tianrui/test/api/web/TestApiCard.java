package com.tianrui.test.api.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.businessManage.cardManage.CardApi;
import com.tianrui.api.req.businessManage.salesManage.ApiSalesArriveQuery;
import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.Md5Utils;

@SuppressWarnings("unused")
public class TestApiCard {

	private static String domin = "http://172.19.4.97:28080/";
	private static String card_validate = "api/card/validate";
	private static String rfidReg = "api/card/rfidReg";
	
	public static void main(String[] args) throws Exception {
		URL url = new URL(domin + rfidReg);
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
		System.out.println(url.getHost()+":"+url.getPort()+url.getPath());
		System.out.println(params);
		System.out.println(response);
		
		
	}
	static ApiParam<CardApi> getParam(){
		ApiParam<CardApi> api =new ApiParam<CardApi>();
		
		CardApi req =new CardApi();
		req.setCardno("3946352275");
		
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
	static ApiParam<RFIDReq> getParam1(){
	    ApiParam<RFIDReq> api =new ApiParam<RFIDReq>();
	    
	    RFIDReq req =new RFIDReq();
	    req.setRfid("E2000016060D0083270009D6");
	    req.setType("3");
	    
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
