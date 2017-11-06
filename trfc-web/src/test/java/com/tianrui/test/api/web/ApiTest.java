package com.tianrui.test.api.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.basicFile.measure.VehicleManageApi;
import com.tianrui.api.req.businessManage.poundNoteMaintain.ApiPoundNoteQuery;
import com.tianrui.api.req.businessManage.poundNoteMaintain.ApiPoundNoteValidation;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorSystemSave;
import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.Head;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.Md5Utils;

@SuppressWarnings("unused")
public class ApiTest {

	//private static String domin="http://172.19.4.73:8080/";
	private static String domin="http://127.0.0.1/";
	private static String uri_login="api/system/login";
	private static String uri_rfid="api/card/rfidReg";
	private static String url_enterFactoryCheck = "api/doorSystem/enterFactoryCheck";
	private static String url_leaveFactoryCheck = "api/doorSystem/leaveFactoryCheck";
	private static String url_record = "api/doorSystem/record";
	private static String url_pound = "api/poundNote/validation";
	private static String url_up_weight = "api/poundNote/up/weight";
	private static String uri_rfidvehicle = "api/vehicle/vehicleCard";
	
	//磅单回传
	static ApiParam<ApiPoundNoteQuery> getParam(){
		ApiParam<ApiPoundNoteQuery> api =new ApiParam<ApiPoundNoteQuery>();
		
		ApiPoundNoteQuery va =new ApiPoundNoteQuery();
		va.setRfid("E2005024861501660240F5D9");
		va.setType("1");
		va.setVehicleno("豫05744");
		va.setServicetype("5");
		va.setNotionformcode("QRN2017050900001");
		va.setNumber("4");
		va.setTime("2017-04-06 14:12:00");
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime("2017-03-28 15:10:00");
		head.setUserId("0001P110000000010J0E");
		
		
		api.setBody(va);
		api.setHead(head);
		return api;
	}
	//磅房验证
	static ApiParam<ApiPoundNoteValidation> getParam3(){
		ApiParam<ApiPoundNoteValidation> api =new ApiParam<ApiPoundNoteValidation>();
		
		ApiPoundNoteValidation va =new ApiPoundNoteValidation();
		va.setRfid("E2005024861501660240F5D9");
		va.setType("2");
		va.setVehicleno("豫05744");
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime("2017-03-29 11:01:00");
		head.setUserId("0001P110000000010J0E");
		
		
		api.setBody(va);
		api.setHead(head);
		return api;
	}
	//入厂验证
	static ApiParam<VehicleCheckApi> getParam5(){
		ApiParam<VehicleCheckApi> api =new ApiParam<VehicleCheckApi>();
		
		VehicleCheckApi va =new VehicleCheckApi();
		va.setRfid("E2005024861501660240F5D9");
		va.setCurrUid("0001P110000000010J0E");
		va.setVehicleNo("豫05744");
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime("2017-03-29 11:01:00");
		head.setUserId("0001P110000000010J0E");
		
		
		api.setBody(va);
		api.setHead(head);
		return api;
	}
	//过磅验证
	static ApiParam<ApiPoundNoteValidation> getParam4(){
		ApiParam<ApiPoundNoteValidation> api =new ApiParam<ApiPoundNoteValidation>();
		
		ApiPoundNoteValidation va =new ApiPoundNoteValidation();
		va.setRfid("E2005024861501660240F5D9");
		va.setType("1");
		va.setVehicleno("豫05744");
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime("2017-03-29 11:01:00");
		head.setUserId("0001P110000000010J0E");
		
		
		api.setBody(va);
		api.setHead(head);
		return api;
	}
	//车辆与rfid绑定
	static ApiParam<VehicleManageApi> getParam7(){
		ApiParam<VehicleManageApi> api =new ApiParam<VehicleManageApi>();
		
		VehicleManageApi va =new VehicleManageApi();
		va.setRfid("E2005024861501660240F5D9");
		va.setVehicleNo("豫05744");
		
		Head head =new Head();
		head.setCallSource("1");
		head.setCallType("2");
		head.setCallTime(DateUtil.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		head.setUserId("0001P110000000010J0E");
		
		
		api.setBody(va);
		api.setHead(head);
		return api;
	}
	
	static <T> void setkey(ApiParam<T> api){
		api.getHead().setKey(Md5Utils.MD5(Constant.apiAuthKey+api.getHead().getCallTime()));
	}
	
	static <T> void setMd5(ApiParam<T> api){
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
		//车辆与rfid绑定
//		ApiParam<VehicleManageApi> req =getParam7();
//		setkey(req);
//		setMd5(req);
//		String param =JSON.toJSONString(req);
//		System.out.println(httpPost(domin+uri_rfidvehicle,"p="+param));
		
		
		//一次过磅
//		ApiParam<ApiPoundNoteValidation> req =getParam3();
//		setkey(req);
//		setMd5(req);
//		String param =JSON.toJSONString(req);
//		System.out.println(httpPost(domin+url_pound,"p="+param));
		
		//上传磅单
//		ApiParam<ApiPoundNoteQuery> req =getParam();
//		setkey(req);
//		setMd5(req);
//		String param =JSON.toJSONString(req);
//		System.out.println(httpPost(domin+url_up_weight,"p="+param));
		
		//入厂验证
//		ApiParam<VehicleCheckApi> req =getParam5();
//		setkey(req);
//		setMd5(req);
//		String param =JSON.toJSONString(req);
//		System.out.println(httpPost(domin+url_enterFactoryCheck,"p="+param));
		//离场
//		ApiParam<VehicleCheckApi> req =getParam5();
//		setkey(req);
//		setMd5(req);
//		String param =JSON.toJSONString(req);
//		System.out.println(httpPost(domin+url_leaveFactoryCheck,"p="+param));
		
		//生成门禁
		ApiParam<ApiDoorSystemSave> req =getParam1();
		setkey(req);
		setMd5(req);
		String param =JSON.toJSONString(req);
		System.out.println(httpPost(domin+url_record,"p="+param));
//		
//		ApiParam<ApiPoundNoteQuery> req =getParam();
//		setkey(req);
//		setMd5(req);
//		String param =JSON.toJSONString(req);
//		System.out.println(httpPost(domin+url_up_weight,"p="+param));
		
//		System.out.println(Md5Utils.MD5("1"));
	}
	static ApiParam<ApiDoorSystemSave> getParam1(){
		ApiParam<ApiDoorSystemSave> api =new ApiParam<ApiDoorSystemSave>();
		
		ApiDoorSystemSave req =new ApiDoorSystemSave();
//		req.setNotionformcode("TH259378");
//		req.setIccode("AB0D8F93BA080400015C61242F23081D");
//		req.setType("1");
//		req.setTime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		req.setIcardno("249930359");
		req.setNotionformcode("QRN2017050900001");
		req.setServicetype("5");
		//出入厂
		req.setType("2");
		req.setTime("2017-04-06 13:41:00");
		req.setCurrUid("0001P110000000029VXC");
		
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
