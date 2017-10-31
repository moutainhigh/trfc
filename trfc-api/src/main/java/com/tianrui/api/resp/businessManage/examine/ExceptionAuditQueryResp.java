package com.tianrui.api.resp.businessManage.examine;

import com.tianrui.api.resp.BaseResp;

public class ExceptionAuditQueryResp extends BaseResp {

    private static final long serialVersionUID = 6642554287919750415L;
    //异常审批ID
    private String id;
    //榜单ID
    private String pnId;
    //榜单号
    private String pnCode;
    //推单状态（0：未推单，1：推单中，2：已推单）
    private String returnStatus;
    //通知单号
    private String noticeCode;
    //通知单状态：（0：未入厂，1：一次过磅，2：二次过磅，3：作废，4：发卡，5：出厂，6：入厂，7：装车）
    private String noticeStatus;
    //审批状态（0：未审批，1：已审批）
    private String auditStatus;
    //客户名称
    private String customerName;
    //物料名称
    private String materialName;
    //车牌号
    private String vehicleNo;
    //毛重
    private Double grossWeight;
    //皮重
    private Double tareWeight;
    //净重
    private Double netWeight;
    //轻车时间
    private Long lightTime;
    //重车时间
    private Long weightTime;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPnId() {
        return pnId;
    }
    public void setPnId(String pnId) {
        this.pnId = pnId;
    }
    public String getPnCode() {
        return pnCode;
    }
    public void setPnCode(String pnCode) {
        this.pnCode = pnCode;
    }
    public String getReturnStatus() {
        return returnStatus;
    }
    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }
    public String getNoticeCode() {
        return noticeCode;
    }
    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }
    public String getNoticeStatus() {
        return noticeStatus;
    }
    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }
    public String getAuditStatus() {
        return auditStatus;
    }
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getMaterialName() {
        return materialName;
    }
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
    public String getVehicleNo() {
        return vehicleNo;
    }
    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
    public Double getGrossWeight() {
        return grossWeight;
    }
    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }
    public Double getTareWeight() {
        return tareWeight;
    }
    public void setTareWeight(Double tareWeight) {
        this.tareWeight = tareWeight;
    }
    public Double getNetWeight() {
        return netWeight;
    }
    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }
    public Long getLightTime() {
        return lightTime;
    }
    public void setLightTime(Long lightTime) {
        this.lightTime = lightTime;
    }
    public Long getWeightTime() {
        return weightTime;
    }
    public void setWeightTime(Long weightTime) {
        this.weightTime = weightTime;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "ExceptionAuditResp [id=" + id + ", pnId=" + pnId + ", pnCode=" + pnCode + ", returnStatus="
                + returnStatus + ", noticeCode=" + noticeCode + ", noticeStatus=" + noticeStatus + ", auditStatus="
                + auditStatus + ", customerName=" + customerName + ", materialName=" + materialName + ", vehicleNo="
                + vehicleNo + ", grossWeight=" + grossWeight + ", tareWeight=" + tareWeight + ", netWeight=" + netWeight
                + ", lightTime=" + lightTime + ", weightTime=" + weightTime + "]";
    }
}
