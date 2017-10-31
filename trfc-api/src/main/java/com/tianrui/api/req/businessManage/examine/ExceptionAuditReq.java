package com.tianrui.api.req.businessManage.examine;

import com.tianrui.api.req.BaseReq;

public class ExceptionAuditReq extends BaseReq {

    private static final long serialVersionUID = 1599253312437004267L;
    //异常审批主键
    private String id;
    //审批意见
    private String auditOpinion;
    //当前登录用户
    private String userId;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAuditOpinion() {
        return auditOpinion;
    }
    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "ExceptionAuditReq [id=" + id + ", auditOpinion=" + auditOpinion + ", userId=" + userId + "]";
    }
}
