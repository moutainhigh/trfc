package com.tianrui.service.bean.basicFile.finance;
/**
 * 随行就市价
 * @author lixp 2017年11月2日 14:19:51
 *
 */
public class PrmTariff {
    //  主键id
    private String id;

    //  nc价格表id
    private String ncid;

    //  客户ncid
    private String customerId;

    //  客户编码
    private String customerCode;

    //  渠道类型ncid
    private String channeltypeId;

    //  渠道类型编码
    private String channeltypeCode;

    //  销售组织ncid
    private String saleOrgId;

    //  发货组织ncid
    private String deliveryOrgId;

    //  物料ncid
    private String materialId;

    //  物料编码
    private String materialCode;

    //  价格
    private String nprice1;

    //  时间戳
    private String ts;

    //  创建时间
    private Long createTime;

    //  冗余字段1
    private String desc1;

    //  荣誉字段2
    private String desc2;

    //  荣誉字段3
    private String desc3;

    //  荣誉字段4
    private String desc4;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNcid() {
        return ncid;
    }

    public void setNcid(String ncid) {
        this.ncid = ncid == null ? null : ncid.trim();
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

    public String getChanneltypeId() {
        return channeltypeId;
    }

    public void setChanneltypeId(String channeltypeId) {
        this.channeltypeId = channeltypeId == null ? null : channeltypeId.trim();
    }

    public String getChanneltypeCode() {
        return channeltypeCode;
    }

    public void setChanneltypeCode(String channeltypeCode) {
        this.channeltypeCode = channeltypeCode == null ? null : channeltypeCode.trim();
    }

    public String getSaleOrgId() {
        return saleOrgId;
    }

    public void setSaleOrgId(String saleOrgId) {
        this.saleOrgId = saleOrgId == null ? null : saleOrgId.trim();
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

    public String getNprice1() {
        return nprice1;
    }

    public void setNprice1(String nprice1) {
        this.nprice1 = nprice1 == null ? null : nprice1.trim();
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