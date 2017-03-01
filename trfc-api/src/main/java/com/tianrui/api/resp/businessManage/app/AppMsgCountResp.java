package com.tianrui.api.resp.businessManage.app;

import com.tianrui.api.req.BaseReq;
/**
 * 订单详情
 * @author lixp
 *
 */
public class AppMsgCountResp extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;

    private String msgCount;

	public String getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(String msgCount) {
		this.msgCount = msgCount;
	}
    
    

}
