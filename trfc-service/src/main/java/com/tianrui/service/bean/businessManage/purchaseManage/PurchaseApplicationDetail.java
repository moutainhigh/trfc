package com.tianrui.service.bean.businessManage.purchaseManage;
/**
 * @Description 采购申请单详情Bean
 * @author zhanggaohao
 * @version 2017年2月13日 上午9:13:14
 */
public class PurchaseApplicationDetail {
	//主键id
    private String id;
	//采购申请单id
    private String purchaseid;
    //组织id
    private String orgid;
    //组织名称
    private String orgname;
	//物料id
    private String materielid;
	//物料名称
    private String materielname;
	//物料规格
    private String materielspec;
	//物料类型
    private String materieltype;
	//数量
    private Double purchasesum;
	//备注
    private String remark;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(String purchaseid) {
        this.purchaseid = purchaseid == null ? null : purchaseid.trim();
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

    public String getMaterielid() {
        return materielid;
    }

    public void setMaterielid(String materielid) {
        this.materielid = materielid == null ? null : materielid.trim();
    }

    public String getMaterielname() {
        return materielname;
    }

    public void setMaterielname(String materielname) {
        this.materielname = materielname == null ? null : materielname.trim();
    }

    public String getMaterielspec() {
        return materielspec;
    }

    public void setMaterielspec(String materielspec) {
        this.materielspec = materielspec == null ? null : materielspec.trim();
    }

    public String getMaterieltype() {
        return materieltype;
    }

    public void setMaterieltype(String materieltype) {
        this.materieltype = materieltype == null ? null : materieltype.trim();
    }

    public Double getPurchasesum() {
        return purchasesum;
    }

    public void setPurchasesum(Double purchasesum) {
        this.purchasesum = purchasesum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}