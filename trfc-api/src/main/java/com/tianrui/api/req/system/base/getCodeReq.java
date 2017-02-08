package com.tianrui.api.req.system.base;

import com.tianrui.api.req.BaseReq;
/**
 * 获取编码时 所需参数 (用户id,编码代号,编码类型)
 * @author lxy
 *
 */
public class getCodeReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 76984776845631304L;
	/**
	 * 用户id
	 */
	private String Userid;
	/**
	 * 编码代号
	 */
	private String code;
	/**
	 * 编码类型(true:编码,false:内码)
	 */
	private Boolean codeType;
	
	public String getUserid() {
		return Userid;
	}
	public void setUserid(String userid) {
		Userid = userid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getCodeType() {
		return codeType;
	}
	public void setCodeType(Boolean codeType) {
		this.codeType = codeType;
	}

	
	
	
}
