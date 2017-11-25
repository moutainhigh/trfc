package com.tianrui.service.bean.businessManage.salesManage;
/**
 * @Description 销售订单与通知单关系及扣减量表
 * @author zhanggaohao
 */
public class SalesApplicationArrive {
	//主键ID
    private String id;
	//订单ID
    private String billId;
	//订单子表ID
    private String billDetailId;
	//通知单ID
    private String noticeId;
	//扣减量
    private Double number;
	//扣减顺序（从1开始）
    private Integer sequence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId == null ? null : billId.trim();
    }

    public String getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(String billDetailId) {
        this.billDetailId = billDetailId == null ? null : billDetailId.trim();
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId == null ? null : noticeId.trim();
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}