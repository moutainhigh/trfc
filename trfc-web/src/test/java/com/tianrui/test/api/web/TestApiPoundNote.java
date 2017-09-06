package com.tianrui.test.api.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.businessManage.poundNoteMaintain.ApiPoundNoteQuery;
import com.tianrui.api.req.businessManage.poundNoteMaintain.ApiPoundNoteValidation;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.Md5Utils;

public class TestApiPoundNote {

	private static String domin="http://localhost/";
	private static String url_validation = "api/poundNote/validation";
	private static String url_up_weight = "api/poundNote/up/weight";
	private static String url_tare_weight = "api/poundNote/history/tareWeight";
	
	public static void main(String[] args) throws Exception {
		URL url = new URL(domin+url_up_weight);
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
	//查询皮重
	static ApiParam<ApiPoundNoteQuery> getParam2(){
		ApiParam<ApiPoundNoteQuery> api =new ApiParam<ApiPoundNoteQuery>();
		
		ApiPoundNoteQuery req =new ApiPoundNoteQuery();
		req.setRfid("E2000016060D014827000A5E");
		req.setVehicleno("豫S98765");
		req.setLimit(24);
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime("2017-01-07 11:01:00");
		head.setUserId("111111");
		
		api.setBody(req);
		api.setHead(head);
		
		setkey(api);
		setMd5(api);
		return api;
	}
	//磅房上传
	static ApiParam<ApiPoundNoteQuery> getParam1(){
		ApiParam<ApiPoundNoteQuery> api =new ApiParam<ApiPoundNoteQuery>();
		
		ApiPoundNoteQuery req =new ApiPoundNoteQuery();
		req.setRfid("E2000016130B015320904013");
		req.setVehicleno("豫A77777");
		req.setType("1");
		req.setServicetype("0");
		req.setNotionformcode("TH201708170001");
		req.setNumber("50");
		req.setTime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		
		//二次过磅所需参数
		req.setDeductionweight("0");
		req.setOriginalnetweight("0");
		req.setDeductionother("0");
		req.setNetweight("0");
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime("2017-01-07 11:01:00");
		head.setUserId("111111");
		
		api.setBody(req);
		api.setHead(head);
		
		setkey(api);
		setMd5(api);
		return api;
	}
	//磅房校验
	static ApiParam<ApiPoundNoteValidation> getParam0(){
		ApiParam<ApiPoundNoteValidation> api =new ApiParam<ApiPoundNoteValidation>();
		
		ApiPoundNoteValidation req =new ApiPoundNoteValidation();
		req.setRfid("08D4A547373531323120AB01");
		req.setVehicleno("豫G75121");
		req.setType("2");
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime("2017-01-07 11:01:00");
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
