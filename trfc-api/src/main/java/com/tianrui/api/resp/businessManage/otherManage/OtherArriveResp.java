package com.tianrui.api.resp.businessManage.otherManage;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

public class OtherArriveResp extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2413491324558769860L;

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
	 * 供应商名称
	 */
	private String suppliername;

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
	 * 物料名称
	 */
	private String materielname;

	/**
	 * 状态(0：未入厂�?1：一次过磅，2：二次过磅，3：作废，4：发卡，5：出厂，6：入厂，7：装�?)
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
	 * 收货单位名称
	 */
	private String receivedepartmentname;

	/**
	 * 车辆
	 */
	private String vehicleid;
	/**
	 * 车号
	 */
	private String vehicleno;

	/**
	 * 车辆rfid
	 */
	private String rfid;

	/**
	 * 仓库
	 */
	private String warehouseid;
	/**
	 * 仓库名称
	 */
	private String warehousename;

	/**
	 * 司机
	 */
	private String driverid;
	/**
	 * 司机名称
	 */
	private String drivername;
	/**
	 * 身份证号
	 */
	private String driveridentityno;
	/**
	 * 数量
	 */
	private Double count;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建�?
	 */
	private String creator;
	/**
	 * 创建人名称
	 */
	private String creatorname;
	/**
	 * 创建时间
	 */
	private Long createtime;
	/**
	 * 磅单信息
	 */
	private PoundNoteResp poundDetail;

	private String businesstype;

	private String enteryard;

	private String enteryerdname;

	private String leaveyard;

	private String leaveyerdname;

	private String reason;

	private String senddepartmentid;

	private String senddepartmentname;

	private String customerid;

	private String customername;

	private Long starttime;
	
	private String starttimeStr;

	private Long endtime;
	
	private String endtimeStr;

	private String icardid;

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

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
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

	public String getMaterielname() {
		return materielname;
	}

	public void setMaterielname(String materielname) {
		this.materielname = materielname;
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

	public String getReceivedepartmentname() {
		return receivedepartmentname;
	}

	public void setReceivedepartmentname(String receivedepartmentname) {
		this.receivedepartmentname = receivedepartmentname;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDriveridentityno() {
		return driveridentityno;
	}

	public void setDriveridentityno(String driveridentityno) {
		this.driveridentityno = driveridentityno;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public PoundNoteResp getPoundDetail() {
		return poundDetail;
	}

	public void setPoundDetail(PoundNoteResp poundDetail) {
		this.poundDetail = poundDetail;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public String getEnteryard() {
		return enteryard;
	}

	public void setEnteryard(String enteryard) {
		this.enteryard = enteryard;
	}

	public String getEnteryerdname() {
		return enteryerdname;
	}

	public void setEnteryerdname(String enteryerdname) {
		this.enteryerdname = enteryerdname;
	}

	public String getLeaveyard() {
		return leaveyard;
	}

	public void setLeaveyard(String leaveyard) {
		this.leaveyard = leaveyard;
	}

	public String getLeaveyerdname() {
		return leaveyerdname;
	}

	public void setLeaveyerdname(String leaveyerdname) {
		this.leaveyerdname = leaveyerdname;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSenddepartmentid() {
		return senddepartmentid;
	}

	public void setSenddepartmentid(String senddepartmentid) {
		this.senddepartmentid = senddepartmentid;
	}

	public String getSenddepartmentname() {
		return senddepartmentname;
	}

	public void setSenddepartmentname(String senddepartmentname) {
		this.senddepartmentname = senddepartmentname;
	}




	public Long getStarttime() {
		return starttime;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
        this.starttimeStr = DateUtil.parse(starttime, DateUtil.Y_M_D_H_M_S);
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
        this.endtimeStr = DateUtil.parse(endtime, DateUtil.Y_M_D_H_M_S);
	}

	public String getIcardid() {
		return icardid;
	}

	public void setIcardid(String icardid) {
		this.icardid = icardid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

    public String getStarttimeStr() {
        return starttimeStr;
    }

    public void setStarttimeStr(String starttimeStr) {
        this.starttimeStr = starttimeStr;
    }

    public String getEndtimeStr() {
        return endtimeStr;
    }

    public void setEndtimeStr(String endtimeStr) {
        this.endtimeStr = endtimeStr;
    }

	

}
