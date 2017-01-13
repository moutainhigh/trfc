package com.tianrui.api.resp.system.base;

import java.util.Date;

import com.tianrui.api.resp.BaseResp;

public class SystemDataDictItemResp extends BaseResp{

	private static final long serialVersionUID = -4850041119460670864L;

	private String id;

    private String dictid;

    private String code;

    private String name;

    private String info;

    private Byte isvalid;

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

	public String getDictid() {
		return dictid;
	}

	public void setDictid(String dictid) {
		this.dictid = dictid;
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Byte getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Byte isvalid) {
		this.isvalid = isvalid;
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
		return "SystemDataDictItemResp [id=" + id + ", dictid=" + dictid + ", code=" + code + ", name=" + name
				+ ", info=" + info + ", isvalid=" + isvalid + ", creator=" + creator + ", createtime=" + createtime
				+ ", modifier=" + modifier + ", modifytime=" + modifytime + ", utc=" + utc + "]";
	}
    
}
