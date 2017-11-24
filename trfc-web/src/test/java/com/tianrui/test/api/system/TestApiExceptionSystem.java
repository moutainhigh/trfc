package com.tianrui.test.api.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditReq;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditSaveReq;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.Md5Utils;
/**
 * 红外被挡审批
 * @author Administrator
 *
 */
@SuppressWarnings("unused")
public class TestApiExceptionSystem {

	//private static String domin = "http://localhost/";
	private static String domin = "http://172.19.4.97:28080/";
	private static String uri_apply = "api/exceptionAudit/apply";
	private static String uri_query = "api/exceptionAudit/query";
	
	public static void main(String[] args) throws Exception {
		URL url = new URL(domin + uri_apply);
		//URL url = new URL(domin + uri_query);
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
	
	
	static ApiParam<ExceptionAuditSaveReq> getParam0(){
		ApiParam<ExceptionAuditSaveReq> api =new ApiParam<ExceptionAuditSaveReq>();
		
		ExceptionAuditSaveReq req =new ExceptionAuditSaveReq();
		req.setNoticeNo("DH20171120043");
		req.setSeqNo("2017/11/20 16:33:15");
		req.setNoticeType("0");
		
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		head.setUserId("043e657203f841fcbbeed0118b49a185");
		
		api.setBody(req);
		api.setHead(head);
		
		setkey(api);
		setMd5(api);
		return api;
	}
	
	//c77e8caaa2db4052b5d6c9801e901468
	static ApiParam<ExceptionAuditReq> getParam1(){
		ApiParam<ExceptionAuditReq> api =new ApiParam<ExceptionAuditReq>();
		
		ExceptionAuditReq req =new ExceptionAuditReq();
		
		req.setId("c77e8caaa2db4052b5d6c9801e901468");		
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		head.setUserId("043e657203f841fcbbeed0118b49a185");
		
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
