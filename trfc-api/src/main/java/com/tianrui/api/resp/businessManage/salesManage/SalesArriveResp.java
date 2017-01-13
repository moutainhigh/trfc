package com.tianrui.api.resp.businessManage.salesManage;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

public class SalesArriveResp extends BaseResp {
	
	private static final long serialVersionUID = 4625515863613381387L;

	private String id;

    private String code;

    private String auditstatus;

    private String source;

    private String status;

    private String vehicleid;

    private String driverid;

    private String billid;

    private String billcode;

    private String abnormalperson;
    
    private String abnormalpersonname;

    private Long abnormaltime;
    
    private String abnormaltimeStr;

    private String unit;

    private Double takeamount;

    private String spraycode;

    private String serialnumber;

    private String icardid;

    private String state;
    
    private String maindeduction;

    private String remarks;

    private String creator;
    
    private String creatorname;

    private Long createtime;
    
    private String createtimeStr;

    private String modifier;
    
    private String modifiername;

    private Long modifytime;
    
    private String modifytimeStr;
    
    private DriverManageResp driver;
    
    private VehicleManageResp vehicle;
    
    private SalesApplicationResp salesApplication;
    
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

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public String getAbnormalperson() {
		return abnormalperson;
	}

	public void setAbnormalperson(String abnormalperson) {
		this.abnormalperson = abnormalperson;
	}

	public String getAbnormalpersonname() {
		return abnormalpersonname;
	}

	public void setAbnormalpersonname(String abnormalpersonname) {
		this.abnormalpersonname = abnormalpersonname;
	}

	public Long getAbnormaltime() {
		return abnormaltime;
	}

	public void setAbnormaltime(Long abnormaltime) {
		this.abnormaltime = abnormaltime;
		this.abnormaltimeStr = DateUtil.parse(abnormaltime, "yyyy-MM-dd HH:mm:ss");
	}

	public String getAbnormaltimeStr() {
		return abnormaltimeStr;
	}

	public void setAbnormaltimeStr(String abnormaltimeStr) {
		this.abnormaltimeStr = abnormaltimeStr;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getTakeamount() {
		return takeamount;
	}

	public void setTakeamount(Double takeamount) {
		this.takeamount = takeamount;
	}

	public String getSpraycode() {
		return spraycode;
	}

	public void setSpraycode(String spraycode) {
		this.spraycode = spraycode;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getIcardid() {
		return icardid;
	}

	public void setIcardid(String icardid) {
		this.icardid = icardid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMaindeduction() {
		return maindeduction;
	}

	public void setMaindeduction(String maindeduction) {
		this.maindeduction = maindeduction;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		this.createtimeStr = DateUtil.parse(createtime, "yyyy-MM-dd HH:mm:ss");
	}

	public String getCreatetimeStr() {
		return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifiername() {
		return modifiername;
	}

	public void setModifiername(String modifiername) {
		this.modifiername = modifiername;
	}

	public Long getModifytime() {
		return modifytime;
	}

	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
		this.modifytimeStr = DateUtil.parse(modifytime, "yyyy-MM-dd HH:mm:ss");
	}

	public String getModifytimeStr() {
		return modifytimeStr;
	}

	public void setModifytimeStr(String modifytimeStr) {
		this.modifytimeStr = modifytimeStr;
	}

	public DriverManageResp getDriver() {
		return driver;
	}

	public void setDriver(DriverManageResp driver) {
		this.driver = driver;
	}

	public VehicleManageResp getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleManageResp vehicle) {
		this.vehicle = vehicle;
	}

	public SalesApplicationResp getSalesApplication() {
		return salesApplication;
	}

	public void setSalesApplication(SalesApplicationResp salesApplication) {
		this.salesApplication = salesApplication;
	}

}
