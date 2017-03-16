package com.tianrui.service.bean.businessManage.financeManage;

public class SalesDetail {
    private String id;
    //销售明细编号
    private String code;
    //订单编号
    private String ordercode;
    //提货单号
    private String goodcode;
    //组织id
    private String orgid;
    //组织名称
    private String orgname;
    //客户id
    private String customerid;
    //客户名称
    private String customername;
    //单价
    private Double price;
    //数量
    private Double number;
    //金额
    private Double money;
    //发货单位
    private String deliveryunit;
    //消费时间
    private Long consumetime;
    //状态（0：删除，1：正常）
    private String state;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //修改人
    private String modifier;
    //修改时间
    private Long modifytime;
    //NC同步时间戳
    private Long utc;	

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

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    public String getGoodcode() {
        return goodcode;
    }

    public void setGoodcode(String goodcode) {
        this.goodcode = goodcode == null ? null : goodcode.trim();
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

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getDeliveryunit() {
        return deliveryunit;
    }

    public void setDeliveryunit(String deliveryunit) {
        this.deliveryunit = deliveryunit == null ? null : deliveryunit.trim();
    }

    public Long getConsumetime() {
        return consumetime;
    }

    public void setConsumetime(Long consumetime) {
        this.consumetime = consumetime;
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

    public Long getUtc() {
        return utc;
    }

    public void setUtc(Long utc) {
        this.utc = utc;
    }
}