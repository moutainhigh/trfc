package com.tianrui.api.resp.businessManage.app;

import com.tianrui.api.req.BaseReq;
/**
 * 订单详情
 * @author lixp
 *
 */
public class AppDriverResp extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;

    private String id;
    //名称
    private String name;
    //身份证号
    private String idNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	

    
    
  

}
