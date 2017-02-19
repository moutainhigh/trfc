package com.tianrui.api.resp.quality.file;

import com.tianrui.api.resp.BaseResp;
/**
 *	质检方案项目的简洁 出参类
 * @author Administrator
 *
 */
public class QualitySchemeItemRespSP extends BaseResp {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -916306547794941489L;
	/**
	 * id
	 */
	private String id;
	
	/**
	 * 项目id
	 */
	private String itemid;
	/**
	 * 项目编号
	 */
	private String itemcode;
	/**
	 * 项目名称
	 */
	private String itemname;
	/**
	 * 有效性
	 */
	private String invalid;
	/**
	 * 备注
	 */
	private String remark;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getInvalid() {
		return invalid;
	}
	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
