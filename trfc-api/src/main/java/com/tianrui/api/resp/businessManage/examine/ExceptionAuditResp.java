package com.tianrui.api.resp.businessManage.examine;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

public class ExceptionAuditResp extends BaseResp {

    private static final long serialVersionUID = -5381734226758619362L;
    private String id;
    //榜单ID
    private String pnId;
    //业务类型：（1：空车出厂，2：补包，3：回包，4：无需补包）
    private Integer type;
    //补回包数量
    private Integer number;
    //审批状态（0：未审批，1：已审批）
    private Boolean auditStatus;
    //审批意见
    private String auditOpinion;
    //审批人
    private String auditPerson;
    //审批人名称
    private String auditPersonName;
    //审批时间
    private Long auditTime;
    //审批时间字符串
    private String auditTimeStr;
    //状态：（0：删除，1：正常）
    private Boolean state;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //最后修改人
    private String modifier;
    //最后修改时间
    private Long modifytime;
    //备注
    private String remark;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPnId() {
        return pnId;
    }
    public void setPnId(String pnId) {
        this.pnId = pnId;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public Boolean getAuditStatus() {
        return auditStatus;
    }
    public void setAuditStatus(Boolean auditStatus) {
        this.auditStatus = auditStatus;
    }
    public String getAuditOpinion() {
        return auditOpinion;
    }
    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }
    public String getAuditPerson() {
        return auditPerson;
    }
    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson;
    }
    public String getAuditPersonName() {
        return auditPersonName;
    }
    public void setAuditPersonName(String auditPersonName) {
        this.auditPersonName = auditPersonName;
    }
    public Long getAuditTime() {
        return auditTime;
    }
    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
        this.auditTimeStr = DateUtil.parse(auditTime, DateUtil.Y_M_D_H_M_S);
    }
    public String getAuditTimeStr() {
        return auditTimeStr;
    }
    public void setAuditTimeStr(String auditTimeStr) {
        this.auditTimeStr = auditTimeStr;
    }
    public Boolean getState() {
        return state;
    }
    public void setState(Boolean state) {
        this.state = state;
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
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "ExceptionAuditQueryResp [id=" + id + ", pnId=" + pnId + ", type=" + type + ", number=" + number
                + ", auditStatus=" + auditStatus + ", auditOpinion=" + auditOpinion + ", auditPerson=" + auditPerson
                + ", auditPersonName=" + auditPersonName + ", auditTime=" + auditTime + ", auditTimeStr=" + auditTimeStr
                + ", state=" + state + ", creator=" + creator + ", createtime=" + createtime + ", modifier=" + modifier
                + ", modifytime=" + modifytime + ", remark=" + remark + "]";
    }
}
