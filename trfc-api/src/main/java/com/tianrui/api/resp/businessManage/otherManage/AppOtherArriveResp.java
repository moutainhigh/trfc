package com.tianrui.api.resp.businessManage.otherManage;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

public class AppOtherArriveResp extends BaseResp {

    private static final long serialVersionUID = 4896304202404299613L;
    //主键
    private String id;
    //单据编号
    private String code;
    //车牌号码
    private String vehicleno;
    //组织名称
    private String orgname;
    //调入堆场
    private String enteryerdname;
    //调出堆场
    private String leaveyerdname;
    //开始时间
    private Long starttime;
    private String starttimeStr;
    //结束时间
    private Long endtime;
    private String endtimeStr;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getVehicleno() {
        return vehicleno;
    }
    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }
    public String getOrgname() {
        return orgname;
    }
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }
    public String getEnteryerdname() {
        return enteryerdname;
    }
    public void setEnteryerdname(String enteryerdname) {
        this.enteryerdname = enteryerdname;
    }
    public String getLeaveyerdname() {
        return leaveyerdname;
    }
    public void setLeaveyerdname(String leaveyerdname) {
        this.leaveyerdname = leaveyerdname;
    }
    public Long getStarttime() {
        return starttime;
    }
    public void setStarttime(Long starttime) {
        this.starttime = starttime;
        this.starttimeStr = DateUtil.parse(starttime, DateUtil.Y_M_D_H_M_S);
    }
    public String getStarttimeStr() {
        return starttimeStr;
    }
    public void setStarttimeStr(String starttimeStr) {
        this.starttimeStr = starttimeStr;
    }
    public Long getEndtime() {
        return endtime;
    }
    public void setEndtime(Long endtime) {
        this.endtime = endtime;
        this.endtimeStr = DateUtil.parse(endtime, DateUtil.Y_M_D_H_M_S);
    }
    public String getEndtimeStr() {
        return endtimeStr;
    }
    public void setEndtimeStr(String endtimeStr) {
        this.endtimeStr = endtimeStr;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "AppOtherArriveResp [id=" + id + ", code=" + code + ", vehicleno=" + vehicleno + ", orgname=" + orgname
                + ", enteryerdname=" + enteryerdname + ", leaveyerdname=" + leaveyerdname + ", starttime=" + starttime
                + ", starttimeStr=" + starttimeStr + ", endtime=" + endtime + ", endtimeStr=" + endtimeStr + "]";
    }

}
