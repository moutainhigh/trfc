package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.req.BaseReq;

public class AppOrderReq extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;
	private String userId;
	//订单id
	private String id;
	//订单子表id
	private String detailid;
	//订单号
	private String code;
	//物料
	private String materielid;
	//开始时间
	private Long starttime;
	//结束时间
	private Long endtime;
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
	public String getDetailid() {
		return detailid;
	}
	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMaterielid() {
		return materielid;
	}
	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
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
