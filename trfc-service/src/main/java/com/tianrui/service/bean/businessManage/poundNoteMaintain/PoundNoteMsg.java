package com.tianrui.service.bean.businessManage.poundNoteMaintain;
/**
 * @Description 推送结果信息
 * @author zhanggaohao
 * @version 2017年3月23日 下午1:49:48
 */
public class PoundNoteMsg {
	//主键id
    private String id;
    //磅单id
    private String poundnoteid;
    //磅单编号
    private String poundnotecode;
    //状态（1：上传）
    private String status;
    //推送时间
    private Long returntime;
    //推送结果
    private String returnmsg;
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

    public String getPoundnoteid() {
        return poundnoteid;
    }

    public void setPoundnoteid(String poundnoteid) {
        this.poundnoteid = poundnoteid == null ? null : poundnoteid.trim();
    }

    public String getPoundnotecode() {
        return poundnotecode;
    }

    public void setPoundnotecode(String poundnotecode) {
        this.poundnotecode = poundnotecode == null ? null : poundnotecode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getReturntime() {
        return returntime;
    }

    public void setReturntime(Long returntime) {
        this.returntime = returntime;
    }

    public String getReturnmsg() {
        return returnmsg;
    }

    public void setReturnmsg(String returnmsg) {
        this.returnmsg = returnmsg == null ? null : returnmsg.trim();
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