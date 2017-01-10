package com.tianrui.api.req.basicFile.measure;

import com.tianrui.api.req.BaseReq;

public class VehicleManageQuery extends BaseReq {

	private static final long serialVersionUID = -8309730088499954718L;

	private String id;

    private String internalcode;

    private String rfid;

    private String vehicleno;

    private String orgid;

    private String orgname;

    private String transporttype;
    
    private String isblacklist;
    
    private String state;
    
    private Integer start;
    
    private Integer limit;
    
    private String blackVno;
    
    private String blackRemarks;
    
    private String blackCreator;
    
    private Long blackCreatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInternalcode() {
		return internalcode;
	}

	public void setInternalcode(String internalcode) {
		this.internalcode = internalcode;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getTransporttype() {
		return transporttype;
	}

	public void setTransporttype(String transporttype) {
		this.transporttype = transporttype;
	}

	public String getIsblacklist() {
		return isblacklist;
	}

	public void setIsblacklist(String isblacklist) {
		this.isblacklist = isblacklist;
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

	public String getBlackVno() {
		return blackVno;
	}

	public void setBlackVno(String blackVno) {
		this.blackVno = blackVno;
	}

	public String getBlackRemarks() {
		return blackRemarks;
	}

	public void setBlackRemarks(String blackRemarks) {
		this.blackRemarks = blackRemarks;
	}

	public String getBlackCreator() {
		return blackCreator;
	}

	public void setBlackCreator(String blackCreator) {
		this.blackCreator = blackCreator;
	}

	public Long getBlackCreatetime() {
		return blackCreatetime;
	}

	public void setBlackCreatetime(Long blackCreatetime) {
		this.blackCreatetime = blackCreatetime;
	}

}