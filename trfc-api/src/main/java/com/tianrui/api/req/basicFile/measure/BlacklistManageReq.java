package com.tianrui.api.req.basicFile.measure;

import com.tianrui.api.req.BaseReq;

public class BlacklistManageReq extends BaseReq {

	private static final long serialVersionUID = -2588539523024745920L;
	//主键ID
	private String id;
	//车辆ID
    private String vehicleid;
    //车牌号
    private String vehicleno;
    //说明
    private String remarks;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //修改人
    private String modifier;
    //修改时间
    private Long modifytime;
    //当前登陆人ID
    private String currId;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getVehicleid() {
        return vehicleid;
    }
    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }
    public String getVehicleno() {
        return vehicleno;
    }
    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public Long getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
    public String getModifier() {
        return modifier;
    }
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    public Long getModifytime() {
        return modifytime;
    }
    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }
    public String getCurrId() {
        return currId;
    }
    public void setCurrId(String currId) {
        this.currId = currId;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "BlacklistManageReq [id=" + id + ", vehicleid=" + vehicleid + ", vehicleno=" + vehicleno + ", remarks="
                + remarks + ", creator=" + creator + ", createtime=" + createtime + ", modifier=" + modifier
                + ", modifytime=" + modifytime + ", currId=" + currId + "]";
    }

}