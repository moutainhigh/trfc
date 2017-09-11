package com.tianrui.api.resp.businessManage.poundNoteMaintain;

import com.tianrui.api.resp.BaseResp;

/**
磅单号
物料
供应商
毛重--- 没有返回0
皮重--- 没有返回0
净重--- 没有返回0
原发净重--- 没有返回0
扣重--- 没有返回0
扣杂--- 没有返回0
装卸员
手持机号
装卸时间
仓库
堆场
 */
public class ApiSignDetailResp extends BaseResp {

	private static final long serialVersionUID = 2216553760770399508L;
	//榜单号
	private String poundNoteCode;
	//通知单code
    private String noticeCode;
    //物料
    private String material;
    //供应商
    private String supplier;
	//毛重
    private Double grossweight;
    //皮重
    private Double tareweight;
    //净重
    private Double netweight;
    //原发净重
    private Double originalnetweight;
	//扣重
    private Double deductionweight;
	//扣杂
    private Double deductionother;
    //装卸员
    private String signPersonName;
    //手持机号
    private String signID;
    //装卸时间
    private String signTime;
    //仓库
    private String warehouse;
    //堆场
    private String yard;
    public String getPoundNoteCode() {
        return poundNoteCode;
    }
    public void setPoundNoteCode(String poundNoteCode) {
        this.poundNoteCode = poundNoteCode;
    }
    public String getNoticeCode() {
        return noticeCode;
    }
    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public Double getGrossweight() {
        return grossweight;
    }
    public void setGrossweight(Double grossweight) {
        this.grossweight = grossweight;
    }
    public Double getTareweight() {
        return tareweight;
    }
    public void setTareweight(Double tareweight) {
        this.tareweight = tareweight;
    }
    public Double getNetweight() {
        return netweight;
    }
    public void setNetweight(Double netweight) {
        this.netweight = netweight;
    }
    public Double getOriginalnetweight() {
        return originalnetweight;
    }
    public void setOriginalnetweight(Double originalnetweight) {
        this.originalnetweight = originalnetweight;
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
    public String getSignPersonName() {
        return signPersonName;
    }
    public void setSignPersonName(String signPersonName) {
        this.signPersonName = signPersonName;
    }
    public String getSignID() {
        return signID;
    }
    public void setSignID(String signID) {
        this.signID = signID;
    }
    public String getSignTime() {
        return signTime;
    }
    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }
    public String getWarehouse() {
        return warehouse;
    }
    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
    public String getYard() {
        return yard;
    }
    public void setYard(String yard) {
        this.yard = yard;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "ApiSignDetailResp [poundNoteCode=" + poundNoteCode + ", noticeCode=" + noticeCode + ", material="
                + material + ", supplier=" + supplier + ", grossweight=" + grossweight + ", tareweight=" + tareweight
                + ", netweight=" + netweight + ", originalnetweight=" + originalnetweight + ", deductionweight="
                + deductionweight + ", deductionother=" + deductionother + ", signPersonName=" + signPersonName
                + ", signID=" + signID + ", signTime=" + signTime + ", warehouse=" + warehouse + ", yard=" + yard + "]";
    }
    
}
