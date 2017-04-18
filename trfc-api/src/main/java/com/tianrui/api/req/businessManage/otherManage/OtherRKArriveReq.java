package com.tianrui.api.req.businessManage.otherManage;

import com.tianrui.api.req.BaseReq;

/**
 * 其他入库通知单 (入参)
 * @author lxy
 *
 */
public class OtherRKArriveReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8489827741263023073L;
	
	/**
     * 主键id
     */
    private String id;

    /**
     * 通知单号
     */
    private String code;

    /**
     * 供应�?
     */
    private String supplierid;

    /**
     * 审核状�??
     */
    private String auditstatus;

    /**
     * 单位(货物来源)
     */
    private String datasource;

    /**
     * 物料
     */
    private String materielid;

    /**
     * 状�??(0：未入厂�?1：一次过磅，2：二次过磅，3：作废，4：发卡，5：出厂，6：入厂，7：装�?)
     */
    private String status;

    /**
     * 货物
     */
    private String cargo;

    /**
     * 收货单位
     */
    private String receivedepartmentid;

    /**
     * 车辆
     */
    private String vehicleid;

    /**
     * 矿口
     */
    private String warehouseid;

    /**
     * 司机
     */
    private String driverid;

    /**
     * 数量
     */
    private Long count;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户
     */
    private String userid;

    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 分页查询开始位置
     */
	private int start;
	/**
	 * 查询量
	 */
	private int limit;
	/**
	 * 开始时间
	 */
	private Long starttime;
	/**
	 * 结束时间
	 */
	private Long endtime;
	
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
	public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	public String getAuditstatus() {
		return auditstatus;
	}
	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	public String getMaterielid() {
		return materielid;
	}
	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getReceivedepartmentid() {
		return receivedepartmentid;
	}
	public void setReceivedepartmentid(String receivedepartmentid) {
		this.receivedepartmentid = receivedepartmentid;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getWarehouseid() {
		return warehouseid;
	}
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
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
	
}
