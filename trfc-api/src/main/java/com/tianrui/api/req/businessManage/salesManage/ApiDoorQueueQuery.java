package com.tianrui.api.req.businessManage.salesManage;

import com.tianrui.api.req.BaseReq;
/**
 * 小票号
 * @author zhanggaohao
 * @createtime 2017年1月9日 下午4:24:40
 */
public class ApiDoorQueueQuery extends BaseReq {

	private static final long serialVersionUID = 2459911624693685605L;
	//物料
	private String materiel;
	
	private String currUid;

	public String getMateriel() {
		return materiel;
	}

	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}

	public String getCurrUid() {
		return currUid;
	}

	public void setCurrUid(String currUid) {
		this.currUid = currUid;
	}

	
	
	
	
}
