package com.tianrui.service.bean.basicFile.nc;

/**
 * 物料管理Bean
 * @author zhanggaohao
 * @createtime 2016年12月7日 上午9:30:56
 * @classname MaterielManage.java
 */
public class MaterielManage {
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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getPinyincode() {
        return pinyincode;
    }

    public void setPinyincode(String pinyincode) {
        this.pinyincode = pinyincode == null ? null : pinyincode.trim();
    }

    public String getReceiptstatus() {
        return receiptstatus;
    }

    public void setReceiptstatus(String receiptstatus) {
        this.receiptstatus = receiptstatus == null ? null : receiptstatus.trim();
    }

    public String getFactorycode() {
        return factorycode;
    }

    public void setFactorycode(String factorycode) {
        this.factorycode = factorycode == null ? null : factorycode.trim();
    }

    public String getPackagetype() {
        return packagetype;
    }

    public void setPackagetype(String packagetype) {
        this.packagetype = packagetype == null ? null : packagetype.trim();
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype == null ? null : businesstype.trim();
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective == null ? null : effective.trim();
    }

    public String getBulkwritecard() {
        return bulkwritecard;
    }

    public void setBulkwritecard(String bulkwritecard) {
        this.bulkwritecard = bulkwritecard == null ? null : bulkwritecard.trim();
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

    public String getQueuingprefix() {
        return queuingprefix;
    }

    public void setQueuingprefix(String queuingprefix) {
        this.queuingprefix = queuingprefix == null ? null : queuingprefix.trim();
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