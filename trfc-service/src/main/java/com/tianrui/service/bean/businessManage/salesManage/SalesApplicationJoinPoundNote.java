package com.tianrui.service.bean.businessManage.salesManage;
/**
 * @Description 销售磅单和销售订单一对多关系表
 * @author zhanggaohao
 * @version 2017年3月16日 上午11:23:36
 */
public class SalesApplicationJoinPoundNote {
	//主键id
    private String id;
	//订单id
    private String billid;
	//订单详情id
    private String billdetailid;
    //磅单id
    private String poundnoteid;
    //订单总数
    private Double billsum;
	//余量
    private Double margin;
	//出库占用量
    private Double outstoragequantity;
	//未出库占用量
    private Double unoutstoragequantity;
	//预提占用
    private Double pretendingtake;
	//提货量
    private Double takeamount;
	//状态（0：删除，1：显示）
    private String state;
    //创建人
    private String creator;
	//创建时间
    private Long createtime;
	//修改人
    private String modifier;
	//修改时间
    private Long modifytime;
    //备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid == null ? null : billid.trim();
    }

    public String getBilldetailid() {
        return billdetailid;
    }

    public void setBilldetailid(String billdetailid) {
        this.billdetailid = billdetailid == null ? null : billdetailid.trim();
    }

    public String getPoundnoteid() {
        return poundnoteid;
    }

    public void setPoundnoteid(String poundnoteid) {
        this.poundnoteid = poundnoteid == null ? null : poundnoteid.trim();
    }

    public Double getBillsum() {
        return billsum;
    }

    public void setBillsum(Double billsum) {
        this.billsum = billsum;
    }

    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    public Double getOutstoragequantity() {
        return outstoragequantity;
    }

    public void setOutstoragequantity(Double outstoragequantity) {
        this.outstoragequantity = outstoragequantity;
    }

    public Double getUnoutstoragequantity() {
        return unoutstoragequantity;
    }

    public void setUnoutstoragequantity(Double unoutstoragequantity) {
        this.unoutstoragequantity = unoutstoragequantity;
    }

    public Double getPretendingtake() {
        return pretendingtake;
    }

    public void setPretendingtake(Double pretendingtake) {
        this.pretendingtake = pretendingtake;
    }

    public Double getTakeamount() {
        return takeamount;
    }

    public void setTakeamount(Double takeamount) {
        this.takeamount = takeamount;
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