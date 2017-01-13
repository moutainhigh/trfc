package com.tianrui.service.bean.businessManage.salesManage;
/**
 * 销售提货通知单Bean
 * @author zhanggaohao
 * @createtime 2017年1月12日 上午10:53:56
 * @classname SalesArrive.java
 */
public class SalesArrive {
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

    private Long abnormaltime;

    private String unit;

    private Double takeamount;

    private String spraycode;

    private String serialnumber;

    private String icardid;

    private String state;

    private String maindeduction;
    
    private String remarks;

    private String creator;

    private Long createtime;

    private String modifier;

    private Long modifytime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(String auditstatus) {
        this.auditstatus = auditstatus == null ? null : auditstatus.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid == null ? null : billid.trim();
    }

    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode == null ? null : billcode.trim();
    }

    public String getAbnormalperson() {
        return abnormalperson;
    }

    public void setAbnormalperson(String abnormalperson) {
        this.abnormalperson = abnormalperson == null ? null : abnormalperson.trim();
    }

    public Long getAbnormaltime() {
        return abnormaltime;
    }

    public void setAbnormaltime(Long abnormaltime) {
        this.abnormaltime = abnormaltime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
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
        this.spraycode = spraycode == null ? null : spraycode.trim();
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
    }

    public String getIcardid() {
        return icardid;
    }

    public void setIcardid(String icardid) {
        this.icardid = icardid == null ? null : icardid.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Long getModifytime() {
        return modifytime;
    }

    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }
}