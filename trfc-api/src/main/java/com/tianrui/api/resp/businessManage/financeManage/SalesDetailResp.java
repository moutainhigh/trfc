package com.tianrui.api.resp.businessManage.financeManage;

import com.tianrui.api.resp.BaseResp;

public class SalesDetailResp extends BaseResp{

	private static final long serialVersionUID = 1685438490051319224L;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public String getGoodcode() {
		return goodcode;
	}
	public void setGoodcode(String goodcode) {
		this.goodcode = goodcode;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
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
		this.deliveryunit = deliveryunit;
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
		this.state = state;
	}
    
    
    
}
