package com.tianrui.api.resp.basicFile.nc;

import com.tianrui.api.resp.BaseResp;

public class MaterielManageResp extends BaseResp {
	
	private static final long serialVersionUID = -6954277099067256000L;

	private String id;

    private String code;

    private String internalcode;

    private String name;
    
    private String abbrname;

    private String spec;

    private String model;

    private String pinyincode;

    private String receiptstatus;

    private String factorycode;

    private String packagetype;

    private String businesstype;

    private String effective;

    private String bulkwritecard;

    private String orgid;
    
    private String orgname;

    private String queuingprefix;

    private String remarks;

    private String creator;

    private Long createtime;

    private String modifier;

    private Long modifytime;

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
	public String getInternalcode() {
		return internalcode;
	}
	public void setInternalcode(String internalcode) {
		this.internalcode = internalcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public String getAbbrname() {
    	return abbrname;
    }
    public void setAbbrname(String abbrname) {
    	this.abbrname = abbrname == null ? null : abbrname.trim();
    }
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPinyincode() {
		return pinyincode;
	}
	public void setPinyincode(String pinyincode) {
		this.pinyincode = pinyincode;
	}
	public String getReceiptstatus() {
		return receiptstatus;
	}
	public void setReceiptstatus(String receiptstatus) {
		this.receiptstatus = receiptstatus;
	}
	public String getFactorycode() {
		return factorycode;
	}
	public void setFactorycode(String factorycode) {
		this.factorycode = factorycode;
	}
	public String getPackagetype() {
		return packagetype;
	}
	public void setPackagetype(String packagetype) {
		this.packagetype = packagetype;
	}
	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	public String getEffective() {
		return effective;
	}
	public void setEffective(String effective) {
		this.effective = effective;
	}
	public String getBulkwritecard() {
		return bulkwritecard;
	}
	public void setBulkwritecard(String bulkwritecard) {
		this.bulkwritecard = bulkwritecard;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getQueuingprefix() {
		return queuingprefix;
	}
	public void setQueuingprefix(String queuingprefix) {
		this.queuingprefix = queuingprefix;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
    
}
