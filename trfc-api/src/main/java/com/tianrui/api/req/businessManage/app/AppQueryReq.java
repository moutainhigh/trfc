package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.req.BaseReq;

public class AppQueryReq extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;
	//当前登录用户id
	private String userId;
	//查询条件
	private String key;
	//是否显示 0：删除， 1：显示   默认显示
	private String state = "1";
	//起始页
    private Integer start;
    //条数
    private Integer limit;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
