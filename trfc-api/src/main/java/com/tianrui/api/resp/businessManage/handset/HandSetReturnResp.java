package com.tianrui.api.resp.businessManage.handset;

import com.tianrui.api.resp.BaseResp;

public class HandSetReturnResp extends BaseResp {

	private static final long serialVersionUID = -610722720762478057L;

	//主键id
	private String id;
	//名称
	private String name;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}