package com.tianrui.api.resp.businessManage.handset;

import com.tianrui.api.resp.BaseResp;

public class HandSetReadICardResp extends BaseResp {
    private static final long serialVersionUID = 4581060747046967573L;
    //供应商或客户ID
    private String deptId;
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
    //榜单单号
    private String poundNoteCode;
    //通知单状态（车辆状态）
    private String noticeStatus;
    //是否原发设置(0：否，1：是)
    private String isPrimary;
    public String getDeptId() {
        return deptId;
    }
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
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
    public String getPoundNoteCode() {
        return poundNoteCode;
    }
    public void setPoundNoteCode(String poundNoteCode) {
        this.poundNoteCode = poundNoteCode;
    }
    public String getNoticeStatus() {
        return noticeStatus;
    }
    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getIsPrimary() {
        return isPrimary;
    }
    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }
    @Override
    public String toString() {
        return "HandSetReadICardResp [deptId=" + deptId + ", deptName=" + deptName + ", deptRemark=" + deptRemark
                + ", material=" + material + ", minemouth=" + minemouth + ", noticeCode=" + noticeCode
                + ", poundNoteCode=" + poundNoteCode + ", noticeStatus=" + noticeStatus + ", isPrimary=" + isPrimary
                + "]";
    }
    
}
