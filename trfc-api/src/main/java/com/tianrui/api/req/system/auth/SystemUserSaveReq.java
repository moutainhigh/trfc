package com.tianrui.api.req.system.auth;

import com.tianrui.api.req.BaseReq;
/**
 * 用户登录
 * @author lixp
 *
 */
public class SystemUserSaveReq extends BaseReq {

	private static final long serialVersionUID = 2174287526269962856L;
	private String id;
	
	//编码
	private String code;
	//账户
	private String account;
	//手机号码
	private String mobilePhone;
	//名称
	private String name;
	private String password;
	//组织
	private String orgId;
	//说明
	private String remark;
    //是否有效 0 无效  1有效
    private String isvalid="0";
	//当前用户
	private String currUId;
	private String identityTypes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
	public String getCurrUId() {
		return currUId;
	}
	public void setCurrUId(String currUId) {
		this.currUId = currUId;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getIdentityTypes() {
		return identityTypes;
	}
	public void setIdentityTypes(String identityTypes) {
		this.identityTypes = identityTypes;
	}
	
}