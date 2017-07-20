package com.tianrui.service.bean.common;
/**
 * @annotation RFID
 * @author zhanggaohao
 * @date 2017年7月17日 上午10:19:35
 */
public class RFID {
    //主键id
    private String rfid;
    //状态（0：失效，1：有效）
    private Boolean state;
    //来源
    private String source;
    //类型：（1：复合卡，2：临时标签，3：正式标签）
    private Integer type;
    //说明
    private String info;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //最后修改人
    private String modifier;
    //最后修改时间
    private Long modifytime;
    public String getRfid() {
        return rfid;
    }
    public void setRfid(String rfid) {
        this.rfid = rfid;
    }
    public Boolean getState() {
        return state;
    }
    public void setState(Boolean state) {
        this.state = state;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
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
    @Override
    public String toString() {
        return "RFID [rfid=" + rfid + ", state=" + state + ", source=" + source + ", type=" + type + ", info=" + info
                + ", creator=" + creator + ", createtime=" + createtime + ", modifier=" + modifier + ", modifytime="
                + modifytime + "]";
    }

}