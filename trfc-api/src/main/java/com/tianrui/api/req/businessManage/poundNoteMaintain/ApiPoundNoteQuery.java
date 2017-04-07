package com.tianrui.api.req.businessManage.poundNoteMaintain;

import com.tianrui.api.req.BaseReq;

public class ApiPoundNoteQuery extends BaseReq {
	private static final long serialVersionUID = -6639269016047238506L;
	//车牌号
	private String vehicleno;
	//RFID
	private String rfid;
	//皮重或毛重（1：皮重，2：毛重）
	private String type;
	//业务类型
	//0采购到货   
	//1采购退货
	//2销售提货
	//3销售退货
	//4厂内倒运
	//5其它入库
	//6其它入库退货
	//7其它出库
	//8其它出库退货
	//9工程车辆
	private String servicetype;
	//通知单号
	private String notionformcode;
	//数量
	private String number;
	//时间
	private String time;
	//当前登录用户
	private String currid;
	//查询皮重的条数
	private Integer limit;

	/**
	 * @return the vehicleno
	 */
	public String getVehicleno() {
		return vehicleno;
	}

	/**
	 * @return the rfid
	 */
	public String getRfid() {
		return rfid;
	}

	/**
	 * @return the currid
	 */
	public String getCurrid() {
		return currid;
	}

	/**
	 * @param currid the currid to set
	 */
	public void setCurrid(String currid) {
		this.currid = currid;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the servicetype
	 */
	public String getServicetype() {
		return servicetype;
	}

	/**
	 * @return the notionformcode
	 */
	public String getNotionformcode() {
		return notionformcode;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param vehicleno the vehicleno to set
	 */
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	/**
	 * @param rfid the rfid to set
	 */
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param servicetype the servicetype to set
	 */
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	/**
	 * @param notionformcode the notionformcode to set
	 */
	public void setNotionformcode(String notionformcode) {
		this.notionformcode = notionformcode;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}
