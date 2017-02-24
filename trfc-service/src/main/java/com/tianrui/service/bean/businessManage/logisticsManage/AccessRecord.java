package com.tianrui.service.bean.businessManage.logisticsManage;
/**
 * @Description 厂区门禁
 * @author zhanggaohao
 * @version 2017年2月20日 下午3:01:07
 */
public class AccessRecord {
	//主键id
    private String id;
    //门禁单号
    private String code;
    //业务类型（1：采购，2：销售，3：其他入库，4：其他出库）
    private String businesstype;
    //门禁类型（1：入厂，2：出厂）
    private String accesstype;
    //通知单id
    private String noticeid;
    //通知单号
    private String noticecode;
    //入厂硬件来源
    private String entersource;
    //入厂时间
    private Long entertime;
    //出厂硬件来源
    private String outsource;
    //出场时间
    private Long outtime;
    //状态：（0：删除，1：显示）
    private String state;
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
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype == null ? null : businesstype.trim();
    }

    public String getAccesstype() {
        return accesstype;
    }

    public void setAccesstype(String accesstype) {
        this.accesstype = accesstype == null ? null : accesstype.trim();
    }

    public String getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(String noticeid) {
        this.noticeid = noticeid == null ? null : noticeid.trim();
    }

    public String getNoticecode() {
        return noticecode;
    }

    public void setNoticecode(String noticecode) {
        this.noticecode = noticecode == null ? null : noticecode.trim();
    }

    public String getEntersource() {
        return entersource;
    }

    public void setEntersource(String entersource) {
        this.entersource = entersource == null ? null : entersource.trim();
    }

    public Long getEntertime() {
        return entertime;
    }

    public void setEntertime(Long entertime) {
        this.entertime = entertime;
    }

    public String getOutsource() {
        return outsource;
    }

    public void setOutsource(String outsource) {
        this.outsource = outsource == null ? null : outsource.trim();
    }

    public Long getOuttime() {
        return outtime;
    }

    public void setOuttime(Long outtime) {
        this.outtime = outtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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
}