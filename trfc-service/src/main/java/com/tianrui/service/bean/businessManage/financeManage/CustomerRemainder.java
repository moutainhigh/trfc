package com.tianrui.service.bean.businessManage.financeManage;

public class CustomerRemainder {
  
    private String id;

    //   组织id
    private String orgName;

    //   组织名称
    private String orgId;

    //  客户名称
    private String customerName;

    //  客户id
    private String customerId;

    //  信用额度
    private Double nlimitmny;

    //  信用占用
    private Double nengrossmny;

    //  信用余额
    private Double nbalancemny;

    //  币种
    private String corigcurrencyid;

    //  创建人
    private String creator;

    //  创建日期
    private Long createtime;

    //  修改人
    private String modifier;

    //   修改时间
    private Long modifytime;

    //  备注
    private String remark;

    //  NC同步时间
    private Long utc;

    //  币种名称
    private String corigcurrencyName;

    //  客户编码
    private String customerCode;
    
    //  状态
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public Double getNlimitmny() {
        return nlimitmny;
    }

    public void setNlimitmny(Double nlimitmny) {
        this.nlimitmny = nlimitmny;
    }

    public Double getNengrossmny() {
        return nengrossmny;
    }

    public void setNengrossmny(Double nengrossmny) {
        this.nengrossmny = nengrossmny;
    }

    public Double getNbalancemny() {
        return nbalancemny;
    }

    public void setNbalancemny(Double nbalancemny) {
        this.nbalancemny = nbalancemny;
    }

    public String getCorigcurrencyid() {
        return corigcurrencyid;
    }

    public void setCorigcurrencyid(String corigcurrencyid) {
        this.corigcurrencyid = corigcurrencyid == null ? null : corigcurrencyid.trim();
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
        this.remark = remark == null ? null:remark.trim() ;
    }

    public Long getUtc() {
        return utc;
    }

    public void setUtc(Long utc) {
        this.utc = utc;
    }

    public String getCorigcurrencyName() {
        return corigcurrencyName;
    }

    public void setCorigcurrencyName(String corigcurrencyName) {
        this.corigcurrencyName = corigcurrencyName == null ? null : corigcurrencyName.trim();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
       
}