package com.tianrui.api.req.system.merchants;

import com.tianrui.api.req.BaseReq;

/**
 * @Description 切换用户
 * @author zhanggaohao
 * @version 2017年5月10日 下午2:58:43
 */
public class AppUserGroupReq extends BaseReq {
	
	private static final long serialVersionUID = 5391895839927532175L;
	//待切换用户id
	private String userNcId;
    //当前登陆用户
    private String currId;
    //当前登录用户key
    private String key;
    
	public String getUserNcId() {
		return userNcId;
	}
	public String getCurrId() {
		return currId;
	}
	public String getKey() {
		return key;
	}
	public void setUserNcId(String userNcId) {
		this.userNcId = userNcId;
	}
	public void setCurrId(String currId) {
		this.currId = currId;
	}
	public void setKey(String key) {
		this.key = key;
	}
    
}