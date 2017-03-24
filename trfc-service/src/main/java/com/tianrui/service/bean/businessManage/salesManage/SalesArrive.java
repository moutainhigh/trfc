package com.tianrui.service.bean.businessManage.salesManage;
/**
 * @Description 销售提货通知单Bean
 * @author zhanggaohao
 * @version 2017年2月4日 上午9:22:01
 */
public class SalesArrive {
	//主键id
	private String id;
	//提货单号
    private String code;
    //审核状态
    private String auditstatus;
    //来源
    private String source;
    //状态：（0：未入厂，1：一次过磅，2：二次过磅，3：作废，4：发卡，5：出厂，6：入厂，7：装车）
    private String status;
    //车辆id
    private String vehicleid;
    //车牌号
    private String vehicleno;
    //RFID
    private String vehiclerfid;
    //司机id
    private String driverid;
    //司机名称
    private String drivername;
    //司机身份证号
    private String driveridentityno;
    //销售订单id
    private String billid;
    //销售订单编号
    private String billcode;
    //销售订单详情id
    private String billdetailid;
    //作废/强制出厂人
    private String abnormalperson;
    //作废/强制出厂人名称
    private String abnormalpersonname;
    //作废/强制出厂时间
    private Long abnormaltime;
	//单位
    private String unit;
    //提货量
    private Double takeamount;
    //实际提货量
    private Double actualtakeamount;
    //喷码
    private String spraycode;
    //出厂编号
    private String serialnumber;
    //IC卡id
    private String icardid;
    //卡序号
    private String icardno;
    //数据状态：（0：删除，1：正常）
    private String state;
    //主单扣量（0：否，1：是）
    private String maindeduction;
    //制单人id
    private String makerid;
    //制单人名称
    private String makebillname;
    //制单时间
    private Long makebilltime;
    //备注
    private String remarks;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //最后修改人
    private String modifier;
    //最后修改时间
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

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
    }

    public String getVehiclerfid() {
		return vehiclerfid;
	}

	public void setVehiclerfid(String vehiclerfid) {
		this.vehiclerfid = vehiclerfid;
	}

	public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername == null ? null : drivername.trim();
    }

    public String getDriveridentityno() {
        return driveridentityno;
    }

    public void setDriveridentityno(String driveridentityno) {
        this.driveridentityno = driveridentityno == null ? null : driveridentityno.trim();
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

    public String getBilldetailid() {
		return billdetailid;
	}

	public void setBilldetailid(String billdetailid) {
        this.billdetailid = billdetailid == null ? null : billdetailid.trim();
	}

	public String getAbnormalperson() {
        return abnormalperson;
    }

    public void setAbnormalperson(String abnormalperson) {
        this.abnormalperson = abnormalperson == null ? null : abnormalperson.trim();
    }

    public String getAbnormalpersonname() {
        return abnormalpersonname;
    }

    public void setAbnormalpersonname(String abnormalpersonname) {
        this.abnormalpersonname = abnormalpersonname == null ? null : abnormalpersonname.trim();
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

    /**
	 * @return the actualtakeamount
	 */
	public Double getActualtakeamount() {
		return actualtakeamount;
	}

	/**
	 * @param actualtakeamount the actualtakeamount to set
	 */
	public void setActualtakeamount(Double actualtakeamount) {
		this.actualtakeamount = actualtakeamount;
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

    public String getIcardno() {
    	return icardno;
    }
    
    public void setIcardno(String icardno) {
    	this.icardno = icardno == null ? null : icardno.trim();
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
        this.maindeduction = maindeduction == null ? null : maindeduction.trim();
    }

    public String getMakerid() {
        return makerid;
    }

    public void setMakerid(String makerid) {
        this.makerid = makerid == null ? null : makerid.trim();
    }
    
    public String getMakebillname() {
    	return makebillname;
    }
    
    public void setMakebillname(String makebillname) {
    	this.makebillname = makebillname == null ? null : makebillname.trim();
    }

    public Long getMakebilltime() {
        return makebilltime;
    }

    public void setMakebilltime(Long makebilltime) {
        this.makebilltime = makebilltime;
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