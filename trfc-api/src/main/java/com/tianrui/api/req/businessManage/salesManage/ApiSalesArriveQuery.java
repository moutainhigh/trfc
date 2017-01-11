package com.tianrui.api.req.businessManage.salesManage;

import com.tianrui.api.req.BaseReq;
/**
 * 销售通知单查询
 * @author zhanggaohao
 * @createtime 2017年1月9日 下午4:24:40
 */
public class ApiSalesArriveQuery extends BaseReq {

	private static final long serialVersionUID = 2459911624693685605L;
	private String vehicleno;
	private String rfid;
	
	private String currUid;

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getCurrUid() {
		return currUid;
	}

	public void setCurrUid(String currUid) {
		this.currUid = currUid;
	}
	
	
	
}
