package com.tianrui.api.resp.businessManage.salesManage;

import com.tianrui.api.resp.BaseResp;

/**
 * api销售通知单详情
 * @author Administrator
 *
 */
public class ApiDoorQueueResp extends BaseResp {
	
	private static final long serialVersionUID = 4625515863613381387L;
	//小票号
	private String smallticket="";
	//排队数
	private String queuenumber="";
	public String getSmallticket() {
		return smallticket;
	}
	public void setSmallticket(String smallticket) {
		this.smallticket = smallticket;
	}
	public String getQueuenumber() {
		return queuenumber;
	}
	public void setQueuenumber(String queuenumber) {
		this.queuenumber = queuenumber;
	}
	
	
	
	
}
