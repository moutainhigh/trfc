package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.req.BaseReq;

public class AppOrderReq extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;
	private String userId;
	//订单 通知单 过磅单id
	private String id;
	//是否显示 0：删除， 1：显示   默认显示
	private String state = "1";
	//排序方式
	private String orderColumn = "ID";
	//正序或倒序
	private String orderAsc = "ASC";
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderAsc() {
		return orderAsc;
	}
	public void setOrderAsc(String orderAsc) {
		this.orderAsc = orderAsc;
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
