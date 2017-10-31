package com.tianrui.service.bean.businessManage.salesManage;
/*
 * 销售出库单子表bean
 */
public class SalesOutboundOrderItem {
    private String id;
    //销售出库单主表主键
    private String saleOutboundOrderId;
    //物料
    private String cmaterialoid;
    //单位
    private String cunitid;
    //数量
    private String nnum;
    //时间戳
    private String ts;
    //创建时间
    private Long createTime;
    //NC订单子表ID
    private String ncId;
    //计量单号
    private String pnCode;
    //毛重
    private Double grossWeight;
    //皮重
    private Double tareWeight;
    //毛重时间
    private String grossTime;
    //皮重时间
    private String tareTime;
    //车牌号
    private String vehicleNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSaleOutboundOrderId() {
        return saleOutboundOrderId;
    }

    public void setSaleOutboundOrderId(String saleOutboundOrderId) {
        this.saleOutboundOrderId = saleOutboundOrderId == null ? null : saleOutboundOrderId.trim();
    }

    public String getCmaterialoid() {
        return cmaterialoid;
    }

    public void setCmaterialoid(String cmaterialoid) {
        this.cmaterialoid = cmaterialoid == null ? null : cmaterialoid.trim();
    }

    public String getCunitid() {
        return cunitid;
    }

    public void setCunitid(String cunitid) {
        this.cunitid = cunitid == null ? null : cunitid.trim();
    }

    public String getNnum() {
        return nnum;
    }

    public void setNnum(String nnum) {
        this.nnum = nnum == null ? null : nnum.trim();
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

    public String getNcId() {
        return ncId;
    }

    public void setNcId(String ncId) {
        this.ncId = ncId == null ? null : ncId.trim();
    }

    public String getPnCode() {
        return pnCode;
    }

    public void setPnCode(String pnCode) {
        this.pnCode = pnCode == null ? null : pnCode.trim();
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

    public String getGrossTime() {
        return grossTime;
    }

    public void setGrossTime(String grossTime) {
        this.grossTime = grossTime == null ? null : grossTime.trim();
    }

    public String getTareTime() {
        return tareTime;
    }

    public void setTareTime(String tareTime) {
        this.tareTime = tareTime == null ? null : tareTime.trim();
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
    }
}