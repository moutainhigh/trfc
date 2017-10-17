package com.tianrui.api.req.quality.purchase;

import com.tianrui.api.req.BaseReq;

public class PurchaseMixedReq extends BaseReq {

    private static final long serialVersionUID = -4422041798602304041L;

    private String date;
    
    private Long day;
    
    private String supplier;
    
    private String material;
    
    private String minemouth;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMinemouth() {
        return minemouth;
    }

    public void setMinemouth(String minemouth) {
        this.minemouth = minemouth;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "PurchaseMixedReq [date=" + date + ", day=" + day + ", supplier=" + supplier + ", material=" + material
                + ", minemouth=" + minemouth + "]";
    }

}
