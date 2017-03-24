package com.tianrui.service.bean.businessManage.purchaseManage;
/**
 * @Description 采购到货通知单Bean
 * @author zhanggaohao
 * @version 2017年2月14日 上午9:49:52
 */
public class PurchaseArrive {
	//主键id
    private String id;
	//到货单号
    private String code;
	//审核状态（0：未审核，1：已审核）
    private String auditstatus;
	//来源（0：业务平台，1：客商平台，2：客商APP）
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
	//采购订单id
    private String billid;
	//采购订单编号
    private String billcode;
    //采购订单详情id
    private String billdetailid;
	//作废/强制出厂人
    private String abnormalperson;
	//作废/强制出厂人名称
    private String abnormalpersonname;
	//作废/强制出厂时间
    private Long abnormaltime;
	//到货量
    private Double arrivalamount;
    //实际签收量
    private Double signamount;
	//单位 default='吨'
    private String unit;
	//制单人id
    private String makerid;
	//制单人名称
    private String makebillname;
	//制单时间
    private Long makebilltime;
    //IC卡id
    private String icardid;
    //状态：（0：删除，1：正常）
    private String state;
	//创建人
    private String creator;
	//创建时间
    private Long createtime;
	//最后修改人
    private String modifier;
	//最后修改时间
    private Long modifytime;
	//备注
    private String remark;

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
        this.vehiclerfid = vehiclerfid == null ? null : vehiclerfid.trim();
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

    public Double getArrivalamount() {
        return arrivalamount;
    }

    public void setArrivalamount(Double arrivalamount) {
        this.arrivalamount = arrivalamount;
    }

    /**
	 * @return the signamount
	 */
	public Double getSignamount() {
		return signamount;
	}

	/**
	 * @param signamount the signamount to set
	 */
	public void setSignamount(Double signamount) {
		this.signamount = signamount;
	}

	public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}