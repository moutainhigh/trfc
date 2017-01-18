package com.tianrui.service.bean.basicFile.nc;
/**
 * 供应商管理
 * @author zhanggaohao
 * @createtime 2016年12月16日 上午10:21:26
 * @classname SupplierManage.java
 */
public class SupplierManage {
    private String id;

    private String code;

    private String internalcode;

    private String name;

    private String abbrname;

    private String pinyincode;

    private String orgid;

    private String orgname;

    private String minemouth;

    private String drivercheck;
    
    private String state;

    private String remarks;

    private String creator;

    private Long createtime;

    private String modifier;

    private Long modifytime;
    private long utc;

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

    public String getInternalcode() {
        return internalcode;
    }

    public void setInternalcode(String internalcode) {
        this.internalcode = internalcode == null ? null : internalcode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAbbrname() {
        return abbrname;
    }

    public void setAbbrname(String abbrname) {
        this.abbrname = abbrname == null ? null : abbrname.trim();
    }

    public String getPinyincode() {
        return pinyincode;
    }

    public void setPinyincode(String pinyincode) {
        this.pinyincode = pinyincode == null ? null : pinyincode.trim();
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

    public String getMinemouth() {
        return minemouth;
    }

    public void setMinemouth(String minemouth) {
        this.minemouth = minemouth == null ? null : minemouth.trim();
    }

    public String getDrivercheck() {
        return drivercheck;
    }

    public void setDrivercheck(String drivercheck) {
        this.drivercheck = drivercheck == null ? null : drivercheck.trim();
    }

    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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

	public long getUtc() {
		return utc;
	}

	public void setUtc(long utc) {
		this.utc = utc;
	}
    
}