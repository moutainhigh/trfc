package com.tianrui.api.req.businessManage.app;

import com.tianrui.api.req.BaseReq;

/**
 * 版本信息查询
 * @author Administrator
 *
 */
public class AppUserEditReq extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;
	private String 	userId;    //当前用户id
	private String 	nickName;   //名称
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
