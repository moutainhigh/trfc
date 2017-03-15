package com.tianrui.api.resp.businessManage.financeManage;

import com.tianrui.api.resp.BaseResp;

public class SalesLedgerResp extends BaseResp{

	private static final long serialVersionUID = -8014128210080335580L;
	
	private String id;
    //收款台账编号
    private String code;
    //审核状态 0-未审核，1-审核中，2-已审核，3-已退回
    private String auditstatus;
    //组织id
    private String orgid;
    //组织名称
    private String orgname;
    //客户id
    private String customerid;
    //客户名称
    private String customername;
    //收款类型(0-期初，1-收款)
    private String chargetype;
    //付款人
    private String payer;
    //单据日期
    private Long billdate;
    //金额
    private Double money;
    //金额大写
    private String moneybig;
    //收款单位
    private String chargeunit;
    //制单人id
    private String makeid;
    //制单人名称
    private String makebillname;
    //制单日期
    private Long makebilltime;
    //审核人id
    private String auditid;
    //审核名称
    private String auditname;
    //审核日期
    private Long audittime;
    //状态（0：删除，1：正常）
    private String state;
    //创建人id
    private String creator;
    //创建时间
    private Long createtime;
    //修改人id
    private String modifier;
    //修改时间
    private Long modifytime;
    //备注
    private String remark;
    //NC同步时间戳
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
	public String getChargetype() {
		return chargetype;
	}
	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public Long getBilldate() {
		return billdate;
	}
	public void setBilldate(Long billdate) {
		this.billdate = billdate;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getMoneybig() {
		return moneybig;
	}
	public void setMoneybig(String moneybig) {
		this.moneybig = moneybig;
	}
	public String getChargeunit() {
		return chargeunit;
	}
	public void setChargeunit(String chargeunit) {
		this.chargeunit = chargeunit;
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
    
}
