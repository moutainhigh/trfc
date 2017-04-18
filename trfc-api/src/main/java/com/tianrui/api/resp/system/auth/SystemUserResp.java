package com.tianrui.api.resp.system.auth;

import java.io.Serializable;

public class SystemUserResp implements Serializable{

	private static final long serialVersionUID = -5817774971738348903L;

	private String id;
	private String name;
	private String orgid;
	private String code;
	private String account;
	private String remark;
	private Byte isvalid;
	private Integer logincount;
	private Long lastLogintime;
    private String source;
    private String password;
    
    private String orgName;
    private String lastLogintimeStr;
	
 	//为APP服务的tokenId
  	private String tokenId;
	
	//身份类型：1客户，2供应商，3普通用户
  	private String identityTypes;
  	//手机号码
  	private String mobilePhone;
	
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
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Byte getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Byte isvalid) {
		this.isvalid = isvalid;
	}
	public Integer getLogincount() {
		return logincount;
	}
	public void setLogincount(Integer logincount) {
		this.logincount = logincount;
	}
	public Long getLastLogintime() {
		return lastLogintime;
	}
	public void setLastLogintime(Long lastLogintime) {
		this.lastLogintime = lastLogintime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getLastLogintimeStr() {
		return lastLogintimeStr;
	}
	public void setLastLogintimeStr(String lastLogintimeStr) {
		this.lastLogintimeStr = lastLogintimeStr;
	}
	/**
	 * @return the tokenId
	 */
	public String getTokenId() {
		return tokenId;
	}
	/**
	 * @param tokenId the tokenId to set
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	/**
	 * @return the identityTypes
	 */
	public String getIdentityTypes() {
		return identityTypes;
	}
	/**
	 * @param identityTypes the identityTypes to set
	 */
	public void setIdentityTypes(String identityTypes) {
		this.identityTypes = identityTypes;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
