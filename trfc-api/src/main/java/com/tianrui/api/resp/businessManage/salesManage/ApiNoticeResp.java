package com.tianrui.api.resp.businessManage.salesManage;

import com.tianrui.api.resp.BaseResp;

/**
 * api销售通知单详情
 * @author Administrator
 *
 */
public class ApiNoticeResp extends BaseResp {
	
	private static final long serialVersionUID = 4625515863613381387L;
	//车牌号
	private String vehicleno="";
	//供应商/客户id
	private String customerid="";
	//供应商/客户
	private String customer="";
	//物料id
	private String materielid="";
	//物料
	private String materiel="";
	//水泥类型 1袋装2散装
	private String cementtype="";
	//业务类型 
	//0 采购到货   
	//1 采购退货
	//2 销售提货
	//3 销售退货
	//4 厂内倒运
	//5 其它入库
	//6 其它入库退货
	//7 其它出库
	//8 其它出库退货
	//9 工程车辆
	private String servicetype="";
	//
	private String notionformcode="";
	//
	private String batchnumber="";
	//是否原发(0：否，1：是)
	private String primary="";
	//
	private String vehicleid="";
	//矿口  采购用，销售为空
	private String minemouth="";
	//预提量 单位吨
	private String number="";
	//通知单状态
	private String status="";
	//通知单审核状态（0：未审核，1：已审核）
    private String auditstatus="";
    //通知单ID
    private String noticeId="";
    //签收状态（0：退货，1：签收）
    private Integer signStatus=0;
    //强制出厂表示（0：否，1：是）
    private Integer forceOutFactory=0;
    //备注
    private String remark="";
    //订单编码
    private String billNo="";
    //是否有空车出厂审批（销售）0：无，1：有
    private String isEmptyOut="0";
    //空车出厂审批状态 0：未审批，1：已审批
    private String emptyOutStatus="0";
    //是否有无需补包审批（销售）0：无，1：有
    private String isDontFill="0";
    //无需补包审批状态 0：未审批，1：已审批
    private String dontFillStatus="0";
    //供应商备注
    private String supRemark="";
	
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getMateriel() {
		return materiel;
	}
	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}
	public String getCementtype() {
		return cementtype;
	}
	public void setCementtype(String cementtype) {
		this.cementtype = cementtype;
	}
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public String getNotionformcode() {
		return notionformcode;
	}
	public void setNotionformcode(String notionformcode) {
		this.notionformcode = notionformcode;
	}
	public String getBatchnumber() {
		return batchnumber;
	}
	public void setBatchnumber(String batchnumber) {
		this.batchnumber = batchnumber;
	}
	public String getPrimary() {
		return primary;
	}
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getMinemouth() {
		return minemouth;
	}
	public void setMinemouth(String minemouth) {
		this.minemouth = minemouth;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getMaterielid() {
		return materielid;
	}
	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuditstatus() {
		return auditstatus;
	}
	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
    public String getNoticeId() {
        return noticeId;
    }
    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }
    public Integer getSignStatus() {
        return signStatus;
    }
    public void setSignStatus(Integer integer) {
        this.signStatus = integer;
    }
    public Integer getForceOutFactory() {
        return forceOutFactory;
    }
    public void setForceOutFactory(Integer forceOutFactory) {
        this.forceOutFactory = forceOutFactory;
    }
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getIsEmptyOut() {
		return isEmptyOut;
	}
	public void setIsEmptyOut(String isEmptyOut) {
		this.isEmptyOut = isEmptyOut;
	}
	public String getEmptyOutStatus() {
		return emptyOutStatus;
	}
	public void setEmptyOutStatus(String emptyOutStatus) {
		this.emptyOutStatus = emptyOutStatus;
	}
	public String getIsDontFill() {
		return isDontFill;
	}
	public void setIsDontFill(String isDontFill) {
		this.isDontFill = isDontFill;
	}
	public String getDontFillStatus() {
		return dontFillStatus;
	}
	public void setDontFillStatus(String dontFillStatus) {
		this.dontFillStatus = dontFillStatus;
	}
	public String getSupRemark() {
		return supRemark;
	}
	public void setSupRemark(String supRemark) {
		this.supRemark = supRemark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
