package com.tianrui.api.resp.businessManage.app;

import com.tianrui.api.req.BaseReq;
/**
 * 订单详情
 * @author lixp
 *
 */
public class AppVehicleResp extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;


    private String id;
    //名称
    private String vehicle;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

    
    
  

}
