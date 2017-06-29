package com.tianrui.api.resp.common;

import com.tianrui.smartfactory.common.utils.DateUtil;

public class QueueNumberResp {

    //小票号
    private String queuenumber;
    //车牌号
    private String vehicleno;
	//物料名称
	private String materialname;
	//
	private String materialpk;
	//客户名称
	private String customername;
    //入厂时间
    private String entertime;
    
	public String getQueuenumber() {
		return queuenumber;
	}
	public void setQueuenumber(String queuenumber) {
		this.queuenumber = queuenumber;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public String getMaterialpk() {
		return materialpk;
	}
	public void setMaterialpk(String materialpk) {
		this.materialpk = materialpk;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getEntertime() {
		return entertime;
	}
	public void setEntertime(Long entertime) {
		this.entertime = DateUtil.parse(entertime, DateUtil.Y_M_D_H_M_S);
	}
    
}
