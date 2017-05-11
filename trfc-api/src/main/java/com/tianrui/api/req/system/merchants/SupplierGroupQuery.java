package com.tianrui.api.req.system.merchants;

import com.tianrui.api.req.BaseReq;

/**
 * @Description 供应商用户查询条件
 * @author zhanggaohao
 * @version 2017年5月9日 上午9:17:08
 */
public class SupplierGroupQuery extends BaseReq {
	
	private static final long serialVersionUID = 5391895839927532175L;
	//主键
	private String id;
	//供应商主键id
    private String supplierid;
    //供应商编码
    private String suppliercode;
    //供应商名称
    private String suppliername;
    //组id
    private String groupid;

    private Integer start;
    
    private Integer limit;

	public String getId() {
		return id;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public String getSuppliercode() {
		return suppliercode;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public String getGroupid() {
		return groupid;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}