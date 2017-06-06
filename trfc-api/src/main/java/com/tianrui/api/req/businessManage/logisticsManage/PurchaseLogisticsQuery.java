package com.tianrui.api.req.businessManage.logisticsManage;

import com.tianrui.api.req.BaseReq;

/**
 * @Description 采购物流查询条件
 * @author zhanggaohao
 * @version 2017年6月5日 下午5:26:35
 */
public class PurchaseLogisticsQuery extends BaseReq {

	/** serialVersionUID */
	private static final long serialVersionUID = 6710203722446155888L;
	//订单号
	private String billCode;
	//通知单号
	private String noticeCode;
	//供应商id
	private String supplierId;
	//物料id
	private String materielId;
	//车辆id
	private String vehicleId;
	//开始时间
	private Long startTime;
	//结束时间
	private Long endTime;
	//页码
	private Integer start;
	//条数
	private Integer limit;

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getNoticeCode() {
		return noticeCode;
	}

	public void setNoticeCode(String noticeCode) {
		this.noticeCode = noticeCode;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getMaterielId() {
		return materielId;
	}

	public void setMaterielId(String materielId) {
		this.materielId = materielId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}
