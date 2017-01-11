package com.tianrui.api.req.businessManage.salesManage;

import com.tianrui.api.req.BaseReq;
/**
 * 门禁记录
 * @author zhanggaohao
 * @createtime 2017年1月9日 下午4:24:40
 */
public class ApiDoorSystemSave extends BaseReq {

	private static final long serialVersionUID = 2459911624693685605L;
	//通知单号
	private String notionformcode;
	//Ic卡号
	private String iccode;
	//门禁类型 1入厂2出厂
	private String type;
	//记录时间
	private String time;
	
	private String currUid;

	public String getNotionformcode() {
		return notionformcode;
	}

	public void setNotionformcode(String notionformcode) {
		this.notionformcode = notionformcode;
	}

	public String getIccode() {
		return iccode;
	}

	public void setIccode(String iccode) {
		this.iccode = iccode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCurrUid() {
		return currUid;
	}

	public void setCurrUid(String currUid) {
		this.currUid = currUid;
	}

	
	
	
	
}
