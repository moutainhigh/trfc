package com.tianrui.api.req.basicFile.businessControl;

import com.tianrui.api.req.BaseReq;

/**
 * 采矿点原发设置save
 * @author lenovo
 *
 */
public class MiningpointDbSettingSave extends BaseReq{
    /**
	 * 
	 */
	private static final long serialVersionUID = -365743861550064710L;

	//  
    private String id;

    //  原发编号
    private String code;

    //  采矿点名称
    private String miningpointname;

    //供应商id
    private String supplierid;
    
    //  物料id
    private String materialid;

    //  是否有效：（0：无效，1：有效）
    private String isvalid;

    //  创建人
    private String creator;

    //  创建时间
    private Long createtime;

    //  修改人
    private String modifier;

    // 修改时间
    private Long modifytime;

    //  备注
    private String remark;
    //当前登陆用户id
    private String currId;
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

    public String getMiningpointname() {
        return miningpointname;
    }

    public void setMiningpointname(String miningpointname) {
        this.miningpointname = miningpointname == null ? null : miningpointname.trim();
    }

    public String getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(String supplierid) {
        this.supplierid = supplierid == null ? null : supplierid.trim();
    }
    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid == null ? null : materialid.trim();
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getCurrId() {
		return currId;
	}

	public void setCurrId(String currId) {
		this.currId = currId;
	}
    
}