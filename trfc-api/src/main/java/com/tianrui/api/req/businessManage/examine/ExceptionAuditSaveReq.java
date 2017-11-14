package com.tianrui.api.req.businessManage.examine;

import com.tianrui.api.req.BaseReq;

public class ExceptionAuditSaveReq extends BaseReq {

    private static final long serialVersionUID = 1599253312437004267L;
    //通知单号
    private String noticeNo;
    //通知单类型
    private String noticeType;
    //流水号
    private String seqNo;
    
    private String currUid;
	public String getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getCurrUid() {
		return currUid;
	}
	public void setCurrUid(String currUid) {
		this.currUid = currUid;
	}
	
   
}
