package com.tianrui.service.bean.basicFile.finance;
/**
 * 应收单
 * @author lixp 2017年11月2日 14:19:15
 *
 */
public class ArRecbill {
    //  主键id
    private String id;

    //  nc应收单子表id
    private String ncid2;

    //  nc应收单主表id
    private String ncid1;

    //  客户ncid
    private String customerId;

    //  客户编码
    private String customerCode;

    //  客户名称
    private String customerName;

    //  单据号
    private String billno;

    //  单据日期
    private String billdate;

    //  单据状态
    private String billstatus;

    //  组织本币金额
    private String localMoney;

    //  原币金额
    private String money;

    //  审批人
    private String approver;

    //  审批状态
    private String approvestatus;

    //  审批日期
    private String approvedated;

    //  收款组织ncid
    private String saleOrgId;

    //  收款组织名称
    private String saleOrgName;

    //  发货组织ncid
    private String deliveryOrgId;

    //  物料ncid
    private String materialId;

    //  物料编码
    private String materialCode;

    //  制单人
    private String billmaker;

    //  时间戳
    private String ts;

    //  创建时间
    private Long createTime;

    //  冗余字段1
    private String desc1;

    //  冗余字段2
    private String desc2;

    //  冗余字段3
    private String desc3;

    //  冗余字段4
    private String desc4;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNcid2() {
        return ncid2;
    }

    public void setNcid2(String ncid2) {
        this.ncid2 = ncid2 == null ? null : ncid2.trim();
    }

    public String getNcid1() {
        return ncid1;
    }

    public void setNcid1(String ncid1) {
        this.ncid1 = ncid1 == null ? null : ncid1.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno == null ? null : billno.trim();
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate == null ? null : billdate.trim();
    }

    public String getBillstatus() {
        return billstatus;
    }

    public void setBillstatus(String billstatus) {
        this.billstatus = billstatus == null ? null : billstatus.trim();
    }

    public String getLocalMoney() {
        return localMoney;
    }

    public void setLocalMoney(String localMoney) {
        this.localMoney = localMoney == null ? null : localMoney.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public String getApprovestatus() {
        return approvestatus;
    }

    public void setApprovestatus(String approvestatus) {
        this.approvestatus = approvestatus == null ? null : approvestatus.trim();
    }

    public String getApprovedated() {
        return approvedated;
    }

    public void setApprovedated(String approvedated) {
        this.approvedated = approvedated == null ? null : approvedated.trim();
    }

    public String getSaleOrgId() {
        return saleOrgId;
    }

    public void setSaleOrgId(String saleOrgId) {
        this.saleOrgId = saleOrgId == null ? null : saleOrgId.trim();
    }

    public String getSaleOrgName() {
        return saleOrgName;
    }

    public void setSaleOrgName(String saleOrgName) {
        this.saleOrgName = saleOrgName == null ? null : saleOrgName.trim();
    }

    public String getDeliveryOrgId() {
        return deliveryOrgId;
    }

    public void setDeliveryOrgId(String deliveryOrgId) {
        this.deliveryOrgId = deliveryOrgId == null ? null : deliveryOrgId.trim();
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode == null ? null : materialCode.trim();
    }

    public String getBillmaker() {
        return billmaker;
    }

    public void setBillmaker(String billmaker) {
        this.billmaker = billmaker == null ? null : billmaker.trim();
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts == null ? null : ts.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1 == null ? null : desc1.trim();
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2 == null ? null : desc2.trim();
    }

    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3 == null ? null : desc3.trim();
    }

    public String getDesc4() {
        return desc4;
    }

    public void setDesc4(String desc4) {
        this.desc4 = desc4 == null ? null : desc4.trim();
    }
}