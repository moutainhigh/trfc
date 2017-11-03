package com.tianrui.api.req.other.selfsystem;

import com.tianrui.api.req.BaseReq;

/**
 * 榜单查询
 * @author lixp 2017年11月3日 11:23:38
 *
 */
public class ApiPoundQueryReq extends BaseReq {
	
	private static final long serialVersionUID = 4325189759681138634L;
	//榜单号
	private String poundno;
	public String getPoundno() {
		return poundno;
	}
	public void setPoundno(String poundno) {
		this.poundno = poundno;
	}
	
	

}