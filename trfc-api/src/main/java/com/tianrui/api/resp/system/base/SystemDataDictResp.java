package com.tianrui.api.resp.system.base;

import java.util.Date;

import com.tianrui.api.resp.BaseResp;

public class SystemDataDictResp extends BaseResp{

	private static final long serialVersionUID = -1611856895070632454L;
	
	private String id;

    private String code;

    private String name;

    private String type;

    private Integer orderBy;

    private String info;

    private String creator;

    private Long createtime;

    private String modifier;

    private Long modifytime;

    private Date utc;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Long getModifytime() {
		return modifytime;
	}

	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}

	public Date getUtc() {
		return utc;
	}

	public void setUtc(Date utc) {
		this.utc = utc;
	}

	@Override
	public String toString() {
		return "SystemDataDictResp [id=" + id + ", code=" + code + ", name=" + name + ", type=" + type + ", orderBy="
				+ orderBy + ", info=" + info + ", creator=" + creator + ", createtime=" + createtime + ", modifier="
				+ modifier + ", modifytime=" + modifytime + ", utc=" + utc + "]";
	}

}
