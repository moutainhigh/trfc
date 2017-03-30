package com.tianrui.api.req.system.auth;

import com.tianrui.api.req.BaseReq;
/**
 * 菜单管理req
 * @author lixp
 */
public class SystemMenuQueryReq extends BaseReq {

	private static final long serialVersionUID = 2174287526269962856L;
    private String id;
    //模块编码
    private String code;
    //模块名称
    private String name;
    //上级模块id
    private String roleid;
    //上级模块名称
    private String roleType;
    //有效性    默认无效 0无效 1有效
    private Byte isvalid;
	//当前登录用户id
	private String currUId;
	private Integer start;
	private Integer limit;

	public String getCurrUId() {
		return currUId;
	}

	public void setCurrUId(String currUId) {
		this.currUId = currUId;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Byte getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Byte isvalid) {
		this.isvalid = isvalid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	
	
}