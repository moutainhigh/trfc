package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

public class SearchWarehouseListVo extends BaseResp {

	private static final long serialVersionUID = -5333031938072107934L;
	//仓库id
	private String id = "";
	//仓库名称
	private String name = "";
	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SearchWarehouseListVo [id=" + id + ", name=" + name + "]";
	}
		
}
