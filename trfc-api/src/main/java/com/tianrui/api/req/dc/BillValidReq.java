package com.tianrui.api.req.dc;

import com.tianrui.api.req.BaseReq;

public class BillValidReq extends BaseReq {

	private static final long serialVersionUID = 189714727775054586L;
	private String id;
	private String detailId;
	private String status;
	private String msg;
	private String ncId;
	private String detailNcId;
	private String auditid;
	private String auditname;
	private Long audittime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getNcId() {
		return ncId;
	}
	public void setNcId(String ncId) {
		this.ncId = ncId;
	}
	public String getDetailNcId() {
		return detailNcId;
	}
	public void setDetailNcId(String detailNcId) {
		this.detailNcId = detailNcId;
	}
	public String getAuditid() {
		return auditid;
	}
	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}
	public String getAuditname() {
		return auditname;
	}
	public void setAuditname(String auditname) {
		this.auditname = auditname;
	}
	public Long getAudittime() {
		return audittime;
	}
	public void setAudittime(Long audittime) {
		this.audittime = audittime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BillValidReq [id=" + id + ", detailId=" + detailId + ", status=" + status + ", msg=" + msg + ", ncId="
				+ ncId + ", detailNcId=" + detailNcId + ", auditid=" + auditid + ", auditname=" + auditname
				+ ", audittime=" + audittime + "]";
	}
}
