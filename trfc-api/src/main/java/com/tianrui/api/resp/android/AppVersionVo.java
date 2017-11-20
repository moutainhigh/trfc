package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

public class AppVersionVo extends BaseResp {

	private static final long serialVersionUID = -7509157680323674223L;
	//是否需要更新
	private String flag;
	//更新app地址
	private String url;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AppVersionVo [flag=" + flag + ", url=" + url + "]";
	}
}
