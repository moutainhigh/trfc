package com.tianrui.api.req.android;

public class AppMiningParam extends AppBase {

	private static final long serialVersionUID = 4631504344714344550L;
	//物料id
	private String materId;
	//供应商id
	private String supplierid;
	
	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public String getMaterId() {
		return materId;
	}

	public void setMaterId(String materId) {
		this.materId = materId;
	}

	public AppMiningParam(String materId) {
		super();
		this.materId = materId;
	}

	public AppMiningParam() {
		super();
	}
	
	
}