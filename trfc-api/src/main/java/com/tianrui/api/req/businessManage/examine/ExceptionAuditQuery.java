package com.tianrui.api.req.businessManage.examine;

import com.tianrui.api.req.BaseReq;

public class ExceptionAuditQuery extends BaseReq {

    private static final long serialVersionUID = 1599253312437004267L;
    //榜单号
    private String pnCode;
    //客户ID
    private String customerId;
    //物料ID
    private String materialId;
    //车辆ID
    private String vehicleId;
    //开始时间
    private Long startTime;
    //结束时间
    private Long endTime;
    //业务类型：（1：空车出厂，2：补包，3：回包，4：无需补包）
    private Integer type;

    private int start;
    private int limit;
    public String getPnCode() {
        return pnCode;
    }
    public void setPnCode(String pnCode) {
        this.pnCode = pnCode;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getMaterialId() {
        return materialId;
    }
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    public Long getStartTime() {
        return startTime;
    }
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
    public Long getEndTime() {
        return endTime;
    }
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "ExceptionAuditReq [pnCode=" + pnCode + ", customerId=" + customerId + ", materialId=" + materialId
                + ", vehicleId=" + vehicleId + ", startTime=" + startTime + ", endTime=" + endTime + ", type=" + type
                + ", start=" + start + ", limit=" + limit + "]";
    }
}
