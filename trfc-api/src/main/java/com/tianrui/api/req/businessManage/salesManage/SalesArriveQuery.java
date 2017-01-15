package com.tianrui.api.req.businessManage.salesManage;

import com.tianrui.api.req.BaseReq;

/**
 * 销售提货通知单查询专用对象
 * @author zhanggaohao
 * @createtime 2017年1月9日 下午4:24:40
 * @classname SalesArriveQuery.java
 */
public class SalesArriveQuery extends BaseReq {

	private static final long serialVersionUID = 2459911624693685605L;
	//到货通知单id
	private String id;
	//到货通知单提货单号
    private String code;
    //审核状态
    private String auditstatus;
    //来源
    private String source;
    //状态
    private String status;
    //车辆id
    private String vehicleid;
    //司机id
    private String driverid;
    //客户id
    private String customerid;
    //物料id
    private String materielid;
    //物料包装类型
    private String packagetype;
    //销售申请单编号
    private String billcode;
    //数据状态
    private String state;
    //开始时间
    private Long starttime;
    //结束事件
    private Long endtime;
    //结束事件
    private String icardid;

    private int start;
    
    private int limit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getMaterielid() {
		return materielid;
	}

	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}

	public String getPackagetype() {
		return packagetype;
	}

	public void setPackagetype(String packagetype) {
		this.packagetype = packagetype;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getIcardid() {
		return icardid;
	}

	public void setIcardid(String icardid) {
		this.icardid = icardid;
	}
	
}
