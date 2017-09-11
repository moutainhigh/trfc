package com.tianrui.api.req.businessManage.handset;

import com.tianrui.api.req.BaseReq;

public class HandSetRequestParam extends BaseReq {

	private static final long serialVersionUID = -4266984974222837083L;
	//当前登录用户id
	private String userId;
	//车牌号
	private String vehicleNo;
	//通知单号
	private String noticeCode;
	//收货人ID
	private String receivePersonId;
	//仓库ID
	private String warehouseId;
	//堆场ID
	private String yardId;
	//扣重
	private Double deductionweight;
	//扣杂
	private Double deductionother;
	//原发净重
	private Double originalnetweight;
	//签收设备
	private String signID;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getVehicleNo() {
        return vehicleNo;
    }
    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
    public String getNoticeCode() {
        return noticeCode;
    }
    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }
    public String getReceivePersonId() {
        return receivePersonId;
    }
    public void setReceivePersonId(String receivePersonId) {
        this.receivePersonId = receivePersonId;
    }
    public String getWarehouseId() {
        return warehouseId;
    }
    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }
    public String getYardId() {
        return yardId;
    }
    public void setYardId(String yardId) {
        this.yardId = yardId;
    }
    public Double getDeductionweight() {
        return deductionweight;
    }
    public void setDeductionweight(Double deductionweight) {
        this.deductionweight = deductionweight;
    }
    public Double getDeductionother() {
        return deductionother;
    }
    public void setDeductionother(Double deductionother) {
        this.deductionother = deductionother;
    }
    public Double getOriginalnetweight() {
        return originalnetweight;
    }
    public void setOriginalnetweight(Double originalnetweight) {
        this.originalnetweight = originalnetweight;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getSignID() {
        return signID;
    }
    public void setSignID(String signID) {
        this.signID = signID;
    }
    @Override
    public String toString() {
        return "HandSetRequestParam [userId=" + userId + ", vehicleNo=" + vehicleNo + ", noticeCode=" + noticeCode
                + ", receivePersonId=" + receivePersonId + ", warehouseId=" + warehouseId + ", yardId=" + yardId
                + ", deductionweight=" + deductionweight + ", deductionother=" + deductionother + ", originalnetweight="
                + originalnetweight + ", signID=" + signID + "]";
    }
	
}