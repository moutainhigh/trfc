package com.tianrui.api.req.basicFile.measure;

import com.tianrui.api.req.BaseReq;

public class VehicleManageSave extends BaseReq {

	private static final long serialVersionUID = 2171660010873631914L;

	private String id;

    private String code;

    private String internalcode;

    private String rfid;

    private String vehicleno;

    private String transporttype;

    private String vehicletype;

    private String transportunit;

    private Double maxweight;

    private Double tareweight;

    private String ownername;

    private String telephone;

    private String address;

    private String orgid;

    private String orgname;

    private String isvalid;

    private String isblacklist;

    private String state;
    
    private String remarks;

    private String creator;

    private Long createtime;

    private String modifier;

    private Long modifytime;
    
    private String blackVno;
    
    private String blackRemarks;
    
    private String blackCreator;
    
    private Long blackCreatetime;
    
    
    private String currUId;


	public String getCurrUId() {
		return currUId;
	}

	public void setCurrUId(String currUId) {
		this.currUId = currUId;
	}

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

    public String getInternalcode() {
        return internalcode;
    }

    public void setInternalcode(String internalcode) {
        this.internalcode = internalcode == null ? null : internalcode.trim();
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid == null ? null : rfid.trim();
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
    }

    public String getTransporttype() {
        return transporttype;
    }

    public void setTransporttype(String transporttype) {
        this.transporttype = transporttype == null ? null : transporttype.trim();
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype == null ? null : vehicletype.trim();
    }

    public String getTransportunit() {
        return transportunit;
    }

    public void setTransportunit(String transportunit) {
        this.transportunit = transportunit == null ? null : transportunit.trim();
    }

    public Double getMaxweight() {
        return maxweight;
    }

    public void setMaxweight(Double maxweight) {
        this.maxweight = maxweight;
    }

    public Double getTareweight() {
        return tareweight;
    }

    public void setTareweight(Double tareweight) {
        this.tareweight = tareweight;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername == null ? null : ownername.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }

    public String getIsblacklist() {
        return isblacklist;
    }

    public void setIsblacklist(String isblacklist) {
        this.isblacklist = isblacklist == null ? null : isblacklist.trim();
    }

    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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