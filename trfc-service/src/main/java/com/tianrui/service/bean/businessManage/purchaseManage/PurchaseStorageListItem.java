package com.tianrui.service.bean.businessManage.purchaseManage;
/**
 * @Description 采购入库单子表实体bean
 * @author YangZhenFu
 * @version 2017年3月21日 下午14:45:52
 */
public class PurchaseStorageListItem {
    private String id;
    //采购入库单主键
    private String purchaseStorageListId;
    //库存组织
    private String pkOrg;
    //物料
    private String cmaterialoid;
    //单位
    private String castunitid;
    //实收主数量
    private String nunm;
    //应收主数量
    private String nshouldnum;
    //仓库
    private String pkCreqwareid;
    //时间戳
    private String ts;
    //创建时间
    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPurchaseStorageListId() {
        return purchaseStorageListId;
    }

    public void setPurchaseStorageListId(String purchaseStorageListId) {
        this.purchaseStorageListId = purchaseStorageListId == null ? null : purchaseStorageListId.trim();
    }

    public String getPkOrg() {
        return pkOrg;
    }

    public void setPkOrg(String pkOrg) {
        this.pkOrg = pkOrg == null ? null : pkOrg.trim();
    }

    public String getCmaterialoid() {
        return cmaterialoid;
    }

    public void setCmaterialoid(String cmaterialoid) {
        this.cmaterialoid = cmaterialoid == null ? null : cmaterialoid.trim();
    }

    public String getCastunitid() {
        return castunitid;
    }

    public void setCastunitid(String castunitid) {
        this.castunitid = castunitid == null ? null : castunitid.trim();
    }

    public String getNshouldnum() {
        return nshouldnum;
    }

    public void setNshouldnum(String nshouldnum) {
        this.nshouldnum = nshouldnum == null ? null : nshouldnum.trim();
    }

    public String getNunm() {
        return nunm;
    }

    public void setNunm(String nunm) {
        this.nunm = nunm == null ? null : nunm.trim();
    }

    public String getPkCreqwareid() {
        return pkCreqwareid;
    }

    public void setPkCreqwareid(String pkCreqwareid) {
        this.pkCreqwareid = pkCreqwareid == null ? null : pkCreqwareid.trim();
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts == null ? null : ts.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}