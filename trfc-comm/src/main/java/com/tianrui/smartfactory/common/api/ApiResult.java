package com.tianrui.smartfactory.common.api;

import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

public class ApiResult {

	private String code="";
	
	private String message="";
	
	private Object returnData;
	
	private String serviceTime=String.valueOf(System.currentTimeMillis());
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getReturnData() {
		return returnData;
	}
	public void setReturnData(Object returnData) {
		this.returnData = returnData;
	}

	
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	public ApiResult() {
		super();
	}
	public ApiResult(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public static ApiResult valueOf(Result rs){
		ApiResult appResult = null;
		if( rs !=null ){
			appResult=new ApiResult();
			appResult.setCode(rs.getCode());
			appResult.setReturnData(rs.getData());
			appResult.setMessage(rs.getError());
		}
		return appResult;
	}
	
	public static ApiResult valueOf(ErrorCode errorCode){
		ApiResult appResult = null;
		if( errorCode !=null ){
			appResult = new ApiResult();
			appResult.setCode(errorCode.getCode());
			appResult.setMessage(errorCode.getMsg());
		}
		return appResult;
	}
	
}
