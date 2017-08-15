package com.tianrui.api.resp.businessManage.handset;

import com.tianrui.api.resp.BaseResp;

public class HandSetReadICardResp extends BaseResp {
    private static final long serialVersionUID = 4581060747046967573L;
    //供应商或客户
    private String deptName;
    //供应商或客户备注
    private String deptRemark;
    //物料名称
    private String material;
    //矿口
    private String minemouth;
    //通知单号
    private String noticeCode;
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getDeptRemark() {
        return deptRemark;
    }
    public void setDeptRemark(String deptRemark) {
        this.deptRemark = deptRemark;
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
    public String getNoticeCode() {
        return noticeCode;
    }
    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "HandSetReadICardResp [deptName=" + deptName + ", deptRemark=" + deptRemark + ", material=" + material
                + ", minemouth=" + minemouth + ", noticeCode=" + noticeCode + "]";
    }
    
}
