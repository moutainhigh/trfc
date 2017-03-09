package com.tianrui.api.resp.businessManage.financeManage;

import com.tianrui.api.resp.BaseResp;

public class CustomerBeginResp extends BaseResp {

	private static final long serialVersionUID = 8429444673097434768L;
	private String id;

    private String code;

    private String auditstatus;

    private String orgid;

    private String orgname;

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

    private Long makebilltime;
    
    private String auditid;

    private String auditname;

    private Long audittime;

    private String state;

    private String creator;

    private Long createtime;

    private String modifier;

    private Long modifytime;

    private String remark;

    private Long utc;

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

	public Long getUtc() {
		return utc;
	}

	public void setUtc(Long utc) {
		this.utc = utc;
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

	public Long getAudittime() {
		return audittime;
	}

	public void setAudittime(Long audittime) {
		this.audittime = audittime;
	}

	public String getMoneybig() {
		return moneybig;
	}

	public void setMoneybig(String moneybig) {
		this.moneybig = moneybig;
	}

}
