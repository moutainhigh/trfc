package com.tianrui.service.bean.basicFile.nc;
/**
 * 供应商管理
 * @author zhanggaohao
 * @createtime 2016年12月16日 上午10:21:26
 * @classname SupplierManage.java
 */
public class SupplierManage {
    /**
     * 供应商id
     */
	private String id;
	/**
	 * 编码
	 */
    private String code;
    /**
     * 内码
     */
    private String internalcode;
    /**
     * 名称
     */
    private String name;
    /**
     * 简称
     */
    private String abbrname;
    /**
     * 拼音码
     */
    private String pinyincode;
    /**
     * 组织id
     */
    private String orgid;
    /**
     * 组织名称
     */
    private String orgname;
    /**
     * 矿口(0:否,1:是)
     */
    private String minemouth;
    /**
     * 司机身份验证(0:否,1:是)
     */
    private String drivercheck;
    /**
     * 状态(0:删除,1:正常)
     */
    private String state;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Long createtime;
    /**
     * 修改者
     */
    private String modifier;
    /**
     * 最后修改时间
     */
    private Long modifytime;
    /**
     * 数据中心时间戳
     */
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

	public Long getUtc() {
		return utc;
	}

	public void setUtc(Long utc) {
		this.utc = utc;
	}
    
}