package com.tianrui.api.req.businessManage.cardManage;

import com.tianrui.api.req.BaseReq;

public class CardSaveReq extends BaseReq {

	private static final long serialVersionUID = -5312655966933356188L;
	//卡序号
	private String cardno;
	//卡面编号
	private String cardcode;
	//卡类型
	private String cardtype;
	
	//当前用户id
	private String currUid;
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public String getCurrUid() {
		return currUid;
	}
	public void setCurrUid(String currUid) {
		this.currUid = currUid;
	}
	
	
	
}