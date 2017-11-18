package com.tianrui.api.req.android;

public class SearchKeyParam extends AppBase {
	private static final long serialVersionUID = -4397535659687349695L;
	//模糊搜索条件
	private String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SearchKeyParam [key=" + key + "]";
	}
}