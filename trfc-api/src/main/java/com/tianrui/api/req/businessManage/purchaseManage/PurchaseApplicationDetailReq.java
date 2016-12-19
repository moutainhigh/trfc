package com.tianrui.api.req.businessManage.purchaseManage;

import com.tianrui.api.req.BaseReq;

public class PurchaseApplicationDetailReq extends BaseReq {

	private static final long serialVersionUID = 8353898256317715845L;

	private String id;

    private String purchaseid;

    private String orgid;

    private String orgname;

    private String materielid;

    private String materielname;

    private String materielspec;

    private String materieltype;

    private String checkprogramid;

    private Double purchasesum;

    private Double receiptsum;

    private Double unreceiptsum;

    private Double advancesum;

    private Double remainsum;

    private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPurchaseid() {
		return purchaseid;
	}

	public void setPurchaseid(String purchaseid) {
		this.purchaseid = purchaseid;
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

	public String getMaterielid() {
		return materielid;
	}

	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}

	public String getMaterielname() {
		return materielname;
	}

	public void setMaterielname(String materielname) {
		this.materielname = materielname;
	}

	public String getMaterielspec() {
		return materielspec;
	}

	public void setMaterielspec(String materielspec) {
		this.materielspec = materielspec;
	}

	public String getMaterieltype() {
		return materieltype;
	}

	public void setMaterieltype(String materieltype) {
		this.materieltype = materieltype;
	}

	public String getCheckprogramid() {
		return checkprogramid;
	}

	public void setCheckprogramid(String checkprogramid) {
		this.checkprogramid = checkprogramid;
	}

	public Double getPurchasesum() {
		return purchasesum;
	}

	public void setPurchasesum(Double purchasesum) {
		this.purchasesum = purchasesum;
	}

	public Double getReceiptsum() {
		return receiptsum;
	}

	public void setReceiptsum(Double receiptsum) {
		this.receiptsum = receiptsum;
	}

	public Double getUnreceiptsum() {
		return unreceiptsum;
	}

	public void setUnreceiptsum(Double unreceiptsum) {
		this.unreceiptsum = unreceiptsum;
	}

	public Double getAdvancesum() {
		return advancesum;
	}

	public void setAdvancesum(Double advancesum) {
		this.advancesum = advancesum;
	}

	public Double getRemainsum() {
		return remainsum;
	}

	public void setRemainsum(Double remainsum) {
		this.remainsum = remainsum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}