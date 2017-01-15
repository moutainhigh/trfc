package com.tianrui.service.bean.system.base;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 系统业务日志
 * 针对表的增删改操作
 * @author Administrator
 *
 */
@Document(collection = "sys_operate_log")
public class SystemOperateLog implements Serializable {
	private static final long serialVersionUID = 552034045802169057L;
	private String id;
	//操作类型   1新增 2修改 3删除  4其他
	private int type;
	//操作时间
	private Long createDate;
	//访问表名称
	private String tableName;
	//主键
	private String primaryKey;
	//访问模块
	private String module;
	//访问ip地址
	private String ipAddr;
	//操作用户id
	private String currId;
	//操作用户编码
	private String operateUser;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	 
}
