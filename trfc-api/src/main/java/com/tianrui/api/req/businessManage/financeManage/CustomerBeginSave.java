package com.tianrui.api.req.businessManage.financeManage;

import com.tianrui.api.req.BaseReq;

public class CustomerBeginSave extends BaseReq{
	
	private static final long serialVersionUID = -738072654872172625L;
	private String id;

    private String code;

    private String auditstatus;

    private String customerid;

    private String customername;

    private String paymentmethod;

    private String payer;

    private String billdate;

    private Double money;
    
    private String moneybig;

    private String collectionunit;

    private String makeid;

    private String makebillname;
    
    private String auditid;

    private String auditname;

    private String remark;

    private String user;
    
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


	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getBilldate() {
		return billdate;
	}

	public void setBilldate(String billdate) {
		this.billdate = billdate;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getCollectionunit() {
		return collectionunit;
	}

	public void setCollectionunit(String collectionunit) {
		this.collectionunit = collectionunit;
	}

	public String getMakeid() {
		return makeid;
	}

	public void setMakeid(String makeid) {
		this.makeid = makeid;
	}

	public String getMakebillname() {
		return makebillname;
	}

	public void setMakebillname(String makebillname) {
		this.makebillname = makebillname;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAuditid() {
		return auditid;
	}

	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}

	public String getAuditname() {
		return auditname;
	}

	public void setAuditname(String auditname) {
		this.auditname = auditname;
	}

	public String getMoneybig() {
		return moneybig;
	}

	public void setMoneybig(String moneybig) {
		this.moneybig = moneybig;
	}

}
