package com.tianrui.api.resp.quality.sales.batchnum;

import com.tianrui.api.resp.BaseResp;

public class SystemUserVO extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3346373655149238099L;
	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 用户名
	 */
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
