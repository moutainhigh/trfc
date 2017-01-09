package com.tianrui.api.req.basicFile.other;

import com.tianrui.api.req.BaseReq;


public class OtherBdMaterialReq extends BaseReq{

	private static final long serialVersionUID = -1568452404155720974L;


	private String id;

    
    private String code;

    
    private String innercode;

    
    private String name;

    
    private String info;

    
    private Byte isvalid;

    
    private String orgid;

    
    private String orgname;

    
    private String remark;

    
    private String creator;

    
    private Long createtime;

    
    private String modifier;

    
    private Long modifytime;
    
    private int start;


	private int limit;

    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    
    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    
    public String getInnercode() {
        return innercode;
    }

    
    public void setInnercode(String innercode) {
        this.innercode = innercode == null ? null : innercode.trim();
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    
    public String getInfo() {
        return info;
    }

    
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    
    public Byte getIsvalid() {
        return isvalid;
    }

    
    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

    
    public String getOrgid() {
        return orgid;
    }

    
    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    
    public String getOrgname() {
        return orgname;
    }

    
    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    
    public String getCreator() {
        return creator;
    }

    
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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
        this.modifier = modifier == null ? null : modifier.trim();
    }

    
    public Long getModifytime() {
        return modifytime;
    }

   
    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }
    public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}
	
	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}


	
}
