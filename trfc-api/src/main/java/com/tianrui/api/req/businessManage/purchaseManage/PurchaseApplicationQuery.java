package com.tianrui.api.req.businessManage.purchaseManage;

import com.tianrui.api.req.BaseReq;

/**
 * @Description 采购申请单查询
 * @author zhanggaohao
 * @version 2017年2月13日 上午9:31:43
 */
public class PurchaseApplicationQuery extends BaseReq {
	/** serialVersionUID */
	private static final long serialVersionUID = -6518350845471119170L;
	//主键id
    private String id;
	//订单编号
    private String code;
	//审核状态 0-未审核，1-已审核
    private String auditstatus;
	//来源 0-联机，1-脱机
    private String source;
	//供应商id
    private String supplierid;
	//供应商名称
    private String suppliername;
	//物料id
    private String materielid;
    //数据状态（0：删除，1：正常）
    private String state;
	//开始时间
    private Long starttime;
    //结束事件
    private Long endtime;
    //起始页
    private Integer start;
    //条数
    private Integer limit;
    
    private String currId;

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

	public String getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getMaterielid() {
		return materielid;
	}

	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getStarttime() {
		return starttime;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
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

	public String getCurrId() {
		return currId;
	}

	public void setCurrId(String currId) {
		this.currId = currId;
	}
    
}