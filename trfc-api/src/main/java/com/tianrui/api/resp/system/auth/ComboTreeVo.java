package com.tianrui.api.resp.system.auth;

import java.util.List;

import com.tianrui.api.resp.BaseResp;

public class ComboTreeVo extends BaseResp {
	private static final long serialVersionUID = -323841117249893811L;
	//菜单id
	private String id;
	//父级菜单id
	private String pid;
	//菜单名称
	private String text;
	//子菜单集合
	private List<ComboTreeVo> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<ComboTreeVo> getChildren() {
		return children;
	}
	public void setChildren(List<ComboTreeVo> children) {
		this.children = children;
	}

}
