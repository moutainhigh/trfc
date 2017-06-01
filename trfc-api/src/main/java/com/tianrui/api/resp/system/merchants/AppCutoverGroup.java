package com.tianrui.api.resp.system.merchants;

import com.tianrui.api.resp.BaseResp;

/**
 * @Description APP切换用户返回结果
 * @author zhanggaohao
 * @version 2017年5月9日 上午9:17:08
 */
public class AppCutoverGroup extends BaseResp {
	
	private static final long serialVersionUID = 5391895839927532175L;
	//待切换用户id
	private String cutoverid;
	//待切换用户名称
	private String cutovername;
	//所属组id(所属用户id)
	private String groupid;
	
	public String getCutoverid() {
		return cutoverid;
	}
	public String getCutovername() {
		return cutovername;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setCutoverid(String cutoverid) {
		this.cutoverid = cutoverid;
	}
	public void setCutovername(String cutovername) {
		this.cutovername = cutovername;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
    
}