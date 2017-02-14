package com.tianrui.api.resp.businessManage.purchaseManage;

import com.tianrui.api.resp.BaseResp;
/**
 * @Description 采购到货通知单Resp
 * @author zhanggaohao
 * @version 2017年2月14日 上午9:44:45
 */
public class PurchaseArriveResp extends BaseResp {
	/** serialVersionUID */
	private static final long serialVersionUID = 5250449009196691062L;
	//主键id
    private String id;
	//到货单号
    private String code;
	//审核状态（0：未审核，1：已审核）
    private String auditstatus;
	//来源（0：业务平台，1：客商平台，2：客商APP）
    private String source;
	//状态：（0：未入厂，1：空车，2：重车，3：作废，4：发卡，5：出厂，6：入厂，7：装车）
    private String status;
	//供应商id
    private String supplierid;
	//供应商名称
    private String suppliername;
	//供应商备注
    private String supplierremark;
	//矿口id
    private String minemouthid;
	//矿口名称
    private String minemouthname;
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
	//作废/强制出厂人
    private String abnormalperson;
	//作废/强制出厂人名称
    private String abnormalpersonname;
	//作废/强制出厂时间
    private Long abnormaltime;
    //作废/强制出厂时间字符串
    private String abnormaltimeStr;
	//提货量
    private Double arrivalamount;
	//单位 default='吨'
    private String unit;
	//制单人id
    private String makerid;
	//制单人名称
    private String makebillname;
	//制单时间
    private Long makebilltime;
    //制单时间字符串
    private String makebilltimeStr;
    //状态：（0：删除，1：正常）
    private String state;
	//创建人
    private String creator;
	//创建时间
    private Long createtime;
    //创建时间字符串
    private String createtimeStr;
	//最后修改人
    private String modifier;
	//最后修改时间
    private Long modifytime;
    //最后修改时间字符串
    private String modifytimeStr;
	//备注
    private String remark;
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
	public String getSupplierremark() {
		return supplierremark;
	}
	public void setSupplierremark(String supplierremark) {
		this.supplierremark = supplierremark;
	}
	public String getMinemouthid() {
		return minemouthid;
	}
	public void setMinemouthid(String minemouthid) {
		this.minemouthid = minemouthid;
	}
	public String getMinemouthname() {
		return minemouthname;
	}
	public void setMinemouthname(String minemouthname) {
		this.minemouthname = minemouthname;
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
	public String getAbnormaltimeStr() {
		return abnormaltimeStr;
	}
	public void setAbnormaltimeStr(String abnormaltimeStr) {
		this.abnormaltimeStr = abnormaltimeStr;
	}
	public Double getArrivalamount() {
		return arrivalamount;
	}
	public void setArrivalamount(Double arrivalamount) {
		this.arrivalamount = arrivalamount;
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
	public String getMakebilltimeStr() {
		return makebilltimeStr;
	}
	public void setMakebilltimeStr(String makebilltimeStr) {
		this.makebilltimeStr = makebilltimeStr;
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
	public Long getModifytime() {
		return modifytime;
	}
	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}
	public String getModifytimeStr() {
		return modifytimeStr;
	}
	public void setModifytimeStr(String modifytimeStr) {
		this.modifytimeStr = modifytimeStr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
