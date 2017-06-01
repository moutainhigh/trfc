package com.tianrui.api.req.system.merchants;

import com.tianrui.api.req.BaseReq;

/**
 * @Description 客户用户查询条件
 * @author zhanggaohao
 * @version 2017年5月9日 上午9:17:08
 */
public class CustomerGroupQuery extends BaseReq {
	
	private static final long serialVersionUID = 5391895839927532175L;
	//主键
	private String id;
	//客户主键id
    private String customerid;
    //供应商编码
    private String customercode;
    //供应商名称
    private String customername;
    //组id
    private String groupid;

    private Integer start;
    
    private Integer limit;

	public String getId() {
		return id;
	}

	public String getCustomerid() {
		return customerid;
	}

	public String getCustomercode() {
		return customercode;
	}

	public String getCustomername() {
		return customername;
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

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
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