package com.tianrui.api.req.common;

import com.tianrui.api.req.BaseReq;

public class FileUploadReq extends BaseReq{

	private static final long serialVersionUID = 1298849672113160842L;
	/**
	 * Base64编码字符串
	 */
	private byte[] fileByte;
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
	
}
