package com.tianrui.api.resp.basicFile.measure;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

public class VehicleManageResp extends BaseResp {

	private static final long serialVersionUID = 1173370021809641095L;

	private String id;
	//车辆编号
    private String code;
    //内码
    private String internalcode;
    //RFID
    private String rfid;
    //车牌号
    private String vehicleno;
    //运输类型（0：非倒运，1：倒运）
    private String transporttype;
    //车辆类型
    private String vehicletype;
    //运输单位
    private String transportunit;
    
    private String transportunitName;
    //运输单位
    private Double maxweight;
    //皮重
    private Double tareweight;
    //车主名称
    private String ownername;
    //电话
    private String telephone;
    //地址
    private String address;
    //组织id
    private String orgid;
    //组织名称
    private String orgname;
    //有效性（0：无效，1：有效）
    private String isvalid;
    //是否黑名单（0：否，1：是）
    private String isblacklist;
    //状态：（0：删除，1：正常）
    private String state;
    //备注
    private String remarks;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //创建时间
    private String createtimeStr;
    //最后修改人
    private String modifier;
    //最后修改时间
    private Long modifytime;
    //类别（0：临时，1固定）
    private Integer type;
    //IC卡ID
    private String icardId;
    //IC卡面编号
    private String icardCode;
    //IC卡序号
    private String icardNo;

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

    public String getTransportunitName() {
        return transportunitName;
    }

    public void setTransportunitName(String transportunitName) {
        this.transportunitName = transportunitName;
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
        this.createtimeStr = DateUtil.parse(createtime, DateUtil.Y_M_D_H_M_S);
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
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Long getModifytime() {
        return modifytime;
    }

    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcardId() {
		return icardId;
	}

	public void setIcardId(String icardId) {
		this.icardId = icardId == null ? null : icardId.trim();
	}

	public String getIcardCode() {
		return icardCode;
	}

	public void setIcardCode(String icardCode) {
		this.icardCode = icardCode;
	}

	public String getIcardNo() {
		return icardNo;
	}

	public void setIcardNo(String icardNo) {
		this.icardNo = icardNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}