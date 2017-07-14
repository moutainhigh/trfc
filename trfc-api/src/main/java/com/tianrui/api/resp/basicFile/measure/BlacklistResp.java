package com.tianrui.api.resp.basicFile.measure;

import com.tianrui.api.resp.BaseResp;

public class BlacklistResp extends BaseResp {

	private static final long serialVersionUID = 7226887348956432032L;
	//主键
	private String id;
	//车牌号
    private String vehicleNo;
    //创建人名称
    private String createName;
    //创建时间
    private Long createTime;
    private String createTimeStr;
    //说明
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "BlacklistResp [id=" + id + ", vehicleNo=" + vehicleNo + ", createName=" + createName + ", createTime="
                + createTime + ", createTimeStr=" + createTimeStr + ", remarks=" + remarks + "]";
    }

}