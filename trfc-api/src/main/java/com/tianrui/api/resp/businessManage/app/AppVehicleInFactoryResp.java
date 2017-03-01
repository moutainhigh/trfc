package com.tianrui.api.resp.businessManage.app;

import com.tianrui.api.req.BaseReq;
/**
 * 在场车辆
 * @author lixp
 *
 */
public class AppVehicleInFactoryResp extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;


    private String materName;
    //名称
    private String vehicleCount;
	public String getMaterName() {
		return materName;
	}
	public void setMaterName(String materName) {
		this.materName = materName;
	}
	public String getVehicleCount() {
		return vehicleCount;
	}
	public void setVehicleCount(String vehicleCount) {
		this.vehicleCount = vehicleCount;
	}

}
