package com.tianrui.api.req.other.selfsystem;

import com.tianrui.api.req.BaseReq;

/**
 * 自助终端查询公用接口
 * 采购发卡,销售发卡,其他如发卡,其他出发卡.
 * @author lixp 2017年11月3日 11:23:38
 *
 */
public class ApiSendCardQueryReq extends BaseReq {
	
	private static final long serialVersionUID = 4325189759681138634L;
	//身份证号
	private String icno;
	public String getIcno() {
		return icno;
	}
	public void setIcno(String icno) {
		this.icno = icno;
	}
	
	

}