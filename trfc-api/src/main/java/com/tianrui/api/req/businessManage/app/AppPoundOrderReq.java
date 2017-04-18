package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.req.BaseReq;

public class AppPoundOrderReq extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;
	//当前登录用户id
	private String userId;
	//当前登录用户身份类型
	private String identityTypes;
	//磅单id
	private String id;
	//磅单编号 
	private String code;
	//订单编号
	private String billcode;
	//通知单号
	private String noticecode;
	//物料
	private String materielid;
	//车号
	private String vehicleno;
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
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @return the identityTypes
	 */
	public String getIdentityTypes() {
		return identityTypes;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return the billcode
	 */
	public String getBillcode() {
		return billcode;
	}
	/**
	 * @return the noticecode
	 */
	public String getNoticecode() {
		return noticecode;
	}
	/**
	 * @return the materielid
	 */
	public String getMaterielid() {
		return materielid;
	}
	/**
	 * @return the vehicleno
	 */
	public String getVehicleno() {
		return vehicleno;
	}
	/**
	 * @return the starttime
	 */
	public Long getStarttime() {
		return starttime;
	}
	/**
	 * @return the endtime
	 */
	public Long getEndtime() {
		return endtime;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @return the orderColumn
	 */
	public String getOrderColumn() {
		return orderColumn;
	}
	/**
	 * @return the orderAsc
	 */
	public String getOrderAsc() {
		return orderAsc;
	}
	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}
	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @param identityTypes the identityTypes to set
	 */
	public void setIdentityTypes(String identityTypes) {
		this.identityTypes = identityTypes;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @param billcode the billcode to set
	 */
	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}
	/**
	 * @param noticecode the noticecode to set
	 */
	public void setNoticecode(String noticecode) {
		this.noticecode = noticecode;
	}
	/**
	 * @param materielid the materielid to set
	 */
	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}
	/**
	 * @param vehicleno the vehicleno to set
	 */
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @param orderColumn the orderColumn to set
	 */
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	/**
	 * @param orderAsc the orderAsc to set
	 */
	public void setOrderAsc(String orderAsc) {
		this.orderAsc = orderAsc;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}
