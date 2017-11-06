package com.tianrui.service.bean.system.base;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 系统日志记录
 * 记录
 * @author lixp
 *
 */
@Document(collection = "sys_login_log")
public class SystemLoginLog implements Serializable {
	private static final long serialVersionUID = 6502400624083405945L;
	private String id;
	//操作时间
	private Long createDate;
	//登录结果
	private String loginResult;
	//访问ip地址
	private String ipAddr;
	//登录用户id
	private String currId;
	//登录用户编码
	private String operateUser;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public String getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getCurrId() {
		return currId;
	}
	public void setCurrId(String currId) {
		this.currId = currId;
	}
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	
	
}
