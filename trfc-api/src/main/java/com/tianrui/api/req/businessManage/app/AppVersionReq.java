package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.req.BaseReq;

/**
 * 版本信息查询
 * @author Administrator
 *
 */
public class AppVersionReq extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;
	private String currVersion;
	public String getCurrVersion() {
		return currVersion;
	}
	public void setCurrVersion(String currVersion) {
		this.currVersion = currVersion;
	}
	
	
	

}
