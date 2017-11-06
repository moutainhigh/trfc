package com.tianrui.api.resp.other.selfsystem;

import com.tianrui.api.resp.BaseResp;

/**
 * 自助终端查询公用接口
 * 采购发卡,销售发卡,其他如发卡,其他出发卡.
 * @author lixp 2017年11月3日 11:23:38
 *
 */
public class ApiSendCardQueryResp extends BaseResp {
	
	private static final long serialVersionUID = 4325189759681138634L;
	//通知单id
	private String id;
	//通知单号
	private String no;
	//物料名称
	private String cargo;
	//通知单状态
	private String status;
	//通知单订单日期
	private String orderDate;
	//创建时间
	private String createTime;
	//客户/供应商名称
	private String customer;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public ApiSendCardQueryResp(String id, String no, String cargo, String status, String orderDate, String createTime,
			String customer) {
		super();
		this.id = id;
		this.no = no;
		this.cargo = cargo;
		this.status = status;
		this.orderDate = orderDate;
		this.createTime = createTime;
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "ApiSendCardQueryResp [id=" + id + ", no=" + no + ", cargo=" + cargo + ", status=" + status
				+ ", orderDate=" + orderDate + ", createTime=" + createTime + ", customer=" + customer + "]";
	}
	
	

}