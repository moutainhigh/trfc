package com.tianrui.api.req.businessManage.salesManage;

import com.tianrui.api.req.BaseReq;

public class SalesApplicationDetailQuery extends BaseReq {

	private static final long serialVersionUID = -1843808397413423610L;
	
	private String id;
	
	private String salesid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSalesid() {
		return salesid;
	}

	public void setSalesid(String salesid) {
		this.salesid = salesid;
	}
	
	
	
}
