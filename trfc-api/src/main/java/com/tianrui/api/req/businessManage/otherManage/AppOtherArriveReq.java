package com.tianrui.api.req.businessManage.otherManage;

import com.tianrui.api.req.BaseReq;

public class AppOtherArriveReq extends BaseReq {

    private static final long serialVersionUID = -3868174937854685122L;
    //车牌号
    private String vehicleno;
    //业务类型
    private String businesstype;
    //页码
    private int start;
    //条数
    private int limit;
    public String getVehicleno() {
        return vehicleno;
    }
    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }
    public String getBusinesstype() {
        return businesstype;
    }
    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
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
        return "AppOtherArriveReq [vehicleno=" + vehicleno + ", businesstype=" + businesstype + ", start=" + start
                + ", limit=" + limit + "]";
    }

}
