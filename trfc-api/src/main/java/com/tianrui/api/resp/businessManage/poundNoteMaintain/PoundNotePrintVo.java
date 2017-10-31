package com.tianrui.api.resp.businessManage.poundNoteMaintain;

import com.tianrui.api.resp.BaseResp;

public class PoundNotePrintVo extends BaseResp {

    private static final long serialVersionUID = -2078400709809395975L;
    //发货单号
    private String billCode;
    //提货单号
    private String noticeCode;
    //计量单号
    private String poundNoteCode;
    //计量单位
    private String unit;
    //客户名称
    private String customer;
    //承运单位
    private String carrierUnit;
    //发货单位
    private String sendDept;
    //车牌号码
    private String vehicle;
    //产品名称
    private String material;
    //业务类型
    private String type;
    //皮重时间
    private String tareTime;
    //皮重
    private String tareWeight;
    //毛重时间
    private String grossTime;
    //毛重
    private String grossWeight;
    //净重
    private String netWeight;
    //净重大写
    private String netWeightBig;
    public String getBillCode() {
        return billCode;
    }
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }
    public String getNoticeCode() {
        return noticeCode;
    }
    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }
    public String getPoundNoteCode() {
        return poundNoteCode;
    }
    public void setPoundNoteCode(String poundNoteCode) {
        this.poundNoteCode = poundNoteCode;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getCarrierUnit() {
        return carrierUnit;
    }
    public void setCarrierUnit(String carrierUnit) {
        this.carrierUnit = carrierUnit;
    }
    public String getSendDept() {
        return sendDept;
    }
    public void setSendDept(String sendDept) {
        this.sendDept = sendDept;
    }
    public String getVehicle() {
        return vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTareTime() {
        return tareTime;
    }
    public void setTareTime(String tareTime) {
        this.tareTime = tareTime;
    }
    public String getTareWeight() {
        return tareWeight;
    }
    public void setTareWeight(String tareWeight) {
        this.tareWeight = tareWeight;
    }
    public String getGrossTime() {
        return grossTime;
    }
    public void setGrossTime(String grossTime) {
        this.grossTime = grossTime;
    }
    public String getGrossWeight() {
        return grossWeight;
    }
    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }
    public String getNetWeight() {
        return netWeight;
    }
    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }
    public String getNetWeightBig() {
        return netWeightBig;
    }
    public void setNetWeightBig(String netWeightBig) {
        this.netWeightBig = netWeightBig;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "PoundNotePrintVo [billCode=" + billCode + ", noticeCode=" + noticeCode + ", poundNoteCode="
                + poundNoteCode + ", unit=" + unit + ", customer=" + customer + ", carrierUnit=" + carrierUnit
                + ", sendDept=" + sendDept + ", vehicle=" + vehicle + ", material=" + material + ", type=" + type
                + ", tareTime=" + tareTime + ", tareWeight=" + tareWeight + ", grossTime=" + grossTime
                + ", grossWeight=" + grossWeight + ", netWeight=" + netWeight + ", netWeightBig=" + netWeightBig + "]";
    }
}
