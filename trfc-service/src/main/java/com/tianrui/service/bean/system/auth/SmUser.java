package com.tianrui.service.bean.system.auth;

import java.util.Date;

public class SmUser {
    private String id;

    private String name;

    private String code;

    private String pkBaseDoc;

    private String userNote;

    private String pkPsndoc;

    private String pkCustomer;

    private String pkSupplier;

    private Integer baseDocType;

    private String orgId;

    private String groupId;

    private Date ts;

    private Long createTime;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPkBaseDoc() {
        return pkBaseDoc;
    }

    public void setPkBaseDoc(String pkBaseDoc) {
        this.pkBaseDoc = pkBaseDoc == null ? null : pkBaseDoc.trim();
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote == null ? null : userNote.trim();
    }

    public String getPkPsndoc() {
        return pkPsndoc;
    }

    public void setPkPsndoc(String pkPsndoc) {
        this.pkPsndoc = pkPsndoc == null ? null : pkPsndoc.trim();
    }

    public String getPkCustomer() {
        return pkCustomer;
    }

    public void setPkCustomer(String pkCustomer) {
        this.pkCustomer = pkCustomer == null ? null : pkCustomer.trim();
    }

    public String getPkSupplier() {
        return pkSupplier;
    }

    public void setPkSupplier(String pkSupplier) {
        this.pkSupplier = pkSupplier == null ? null : pkSupplier.trim();
    }

    public Integer getBaseDocType() {
        return baseDocType;
    }

    public void setBaseDocType(Integer baseDocType) {
        this.baseDocType = baseDocType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}