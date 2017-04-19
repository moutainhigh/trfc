package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.req.BaseReq;

/**
 * 版本信息查询
 * @author Administrator
 *
 */
public class AppVersionReq extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;
	//手机系统类型
	private String phoneType;
	//版本编号
	private String currVersion;
	/**
	 * @return the phoneType
	 */
	public String getPhoneType() {
		return phoneType;
	}
	/**
	 * @return the currVersion
	 */
	public String getCurrVersion() {
		return currVersion;
	}
	/**
	 * @param phoneType the phoneType to set
	 */
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	/**
	 * @param currVersion the currVersion to set
	 */
	public void setCurrVersion(String currVersion) {
		this.currVersion = currVersion;
	}

}
