package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

/**
 * @annotation 模糊查询结果集
 * @author zhanggaohao
 *
 */
public class SearchListVo extends BaseResp {
	
	private static final long serialVersionUID = -5333031938072107934L;
	private String id = "";
	private String name = "";
	private String telephone = "";
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SearchListVo [id=" + id + ", name=" + name + ", telephone=" + telephone + "]";
	}
}