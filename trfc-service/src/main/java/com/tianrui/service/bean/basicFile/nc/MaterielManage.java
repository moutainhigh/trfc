package com.tianrui.service.bean.basicFile.nc;

/**
 * 物料管理Bean
 * @author zhanggaohao
 * @createtime 2016年12月7日 上午9:30:56
 * @classname MaterielManage.java
 */
public class MaterielManage {
	
    private String id;
    //编码
    private String code;
    //内码
    private String internalcode;
    //名称
    private String name;
    //物料简称
    private String abbrname;
    //规格
    private String spec;
    //型号
    private String model;
    //拼音助记码
    private String pinyincode;
    //收获确认（0：否，1：是）
    private String receiptstatus;
    //出厂编号（0：否，1：是）
    private String factorycode;
    //包装类型（0：袋装，1：水泥散装，2：其他散装）
    private String packagetype;
    //业务类型（0：采购，1：销售，2：共有）
    private String businesstype;
    //有效性（0：否，1：是）
    private String effective;
    //散装写卡（0：否，1：是）
    private String bulkwritecard;
    //所属组织
    private String orgid;
    //组织名称
    private String orgname;
    //排队前缀
    private String queuingprefix;
    //状态：（0：删除，1：正常）
    private String state;
    //备注
    private String remarks;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //最后修改人
    private String modifier;
    //最后修改时间
    private Long modifytime;
    //nc同步时间戳
    private Long utc;

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

	public Long getUtc() {
		return utc;
	}

	public void setUtc(Long utc) {
		this.utc = utc;
	}
    
}