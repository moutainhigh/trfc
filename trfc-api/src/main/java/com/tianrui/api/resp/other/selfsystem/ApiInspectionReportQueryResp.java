package com.tianrui.api.resp.other.selfsystem;

import com.tianrui.api.resp.BaseResp;

/**
 * 榜单查询返回
 * @author lixp 2017年11月3日 11:23:38
 *
 */
public class ApiInspectionReportQueryResp extends BaseResp {
	
	private static final long serialVersionUID = 4325189759681138634L;
	//榜单id
	private String poundno;
	//次数
	private int count=0;
	public String getPoundno() {
		return poundno;
	}
	public void setPoundno(String poundno) {
		this.poundno = poundno;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
		
}