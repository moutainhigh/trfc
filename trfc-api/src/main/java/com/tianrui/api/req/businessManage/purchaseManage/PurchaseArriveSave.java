package com.tianrui.api.req.businessManage.purchaseManage;

import com.tianrui.api.req.BaseReq;
/**
 * @Description 采购通知单Update
 * @author zhanggaohao
 * @version 2017年2月15日 上午10:36:01
 */
public class PurchaseArriveSave extends BaseReq {
	/** serialVersionUID */
	private static final long serialVersionUID = 8233358448528743510L;
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
    //单据类型（0：到货通知单，1：退货通知单）
    private String type;
	//车辆id
    private String vehicleid;
	//车牌号
    private String vehicleno;
	//RFID
    private String vehiclerfid;
    //ic卡id
    private String icardid;
    //ic卡号
    private String icardno;
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
    //磅单id
    private String poundnoteid;
    //磅单单号
    private String poundnotecode;
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
	//当前用户
    private String currId;
    //强制出厂标识（0：否，1：是）
    private Integer forceOutFactory;
    //强制出厂操作人
    private String forceOutFactoryPerson;
    //强制出厂操作时间
    private Long forceOutFactoryTime;
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
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	public String getBilldetailid() {
		return billdetailid;
	}
	public void setBilldetailid(String billdetailid) {
		this.billdetailid = billdetailid;
	}
	/**
	 * @return the poundnoteid
	 */
	public String getPoundnoteid() {
		return poundnoteid;
	}
	/**
	 * @return the poundnotecode
	 */
	public String getPoundnotecode() {
		return poundnotecode;
	}
	/**
	 * @param poundnoteid the poundnoteid to set
	 */
	public void setPoundnoteid(String poundnoteid) {
		this.poundnoteid = poundnoteid;
	}
	/**
	 * @param poundnotecode the poundnotecode to set
	 */
	public void setPoundnotecode(String poundnotecode) {
		this.poundnotecode = poundnotecode;
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
		this.unit = unit;
	}
	public String getMakerid() {
		return makerid;
	}
	public void setMakerid(String makerid) {
		this.makerid = makerid;
	}
	public String getMakebillname() {
		return makebillname;
	}
	public void setMakebillname(String makebillname) {
		this.makebillname = makebillname;
	}
	public Long getMakebilltime() {
		return makebilltime;
	}
	public void setMakebilltime(Long makebilltime) {
		this.makebilltime = makebilltime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
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
		this.modifier = modifier;
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
		this.remark = remark;
	}
	public String getCurrId() {
		return currId;
	}
	public void setCurrId(String currId) {
		this.currId = currId;
	}
	public String getIcardid() {
		return icardid;
	}
	public void setIcardid(String icardid) {
		this.icardid = icardid;
	}
	public String getIcardno() {
		return icardno;
	}
	public void setIcardno(String icardno) {
		this.icardno = icardno;
	}
    public Integer getForceOutFactory() {
        return forceOutFactory;
    }
    public void setForceOutFactory(Integer forceOutFactory) {
        this.forceOutFactory = forceOutFactory;
    }
    public String getForceOutFactoryPerson() {
        return forceOutFactoryPerson;
    }
    public void setForceOutFactoryPerson(String forceOutFactoryPerson) {
        this.forceOutFactoryPerson = forceOutFactoryPerson;
    }
    public Long getForceOutFactoryTime() {
        return forceOutFactoryTime;
    }
    public void setForceOutFactoryTime(Long forceOutFactoryTime) {
        this.forceOutFactoryTime = forceOutFactoryTime;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
