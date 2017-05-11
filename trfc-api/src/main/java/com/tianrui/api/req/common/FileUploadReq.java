package com.tianrui.api.req.common;

import com.tianrui.api.req.BaseReq;

public class FileUploadReq extends BaseReq{

	private static final long serialVersionUID = 1298849672113160842L;

	private byte[] fileByte;
	// 1：入厂，2：一次过磅，3：二次过磅，4：出厂，5：袋装，6：散装
	private String type;
	//通知单号
	private String billcode;
	//通知单类型
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
	private String billtype;
	/**
	 * 上传者id
	 */
	private String uId;

	public byte[] getFileByte() {
		return fileByte;
	}

	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getType() {
		return type;
	}

	public String getBillcode() {
		return billcode;
	}

	public String getBilltype() {
		return billtype;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}
	
}
