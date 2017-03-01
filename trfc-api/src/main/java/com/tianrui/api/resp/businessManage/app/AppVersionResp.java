package com.tianrui.api.resp.businessManage.app;

import com.tianrui.api.req.BaseReq;
/**
 * 订单详情
 * @author lixp
 *
 */
public class AppVersionResp extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;

    private String updateFlag;
    private String updateUrl;
	public String getUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	public String getUpdateUrl() {
		return updateUrl;
	}
	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}
	
    
  

}
