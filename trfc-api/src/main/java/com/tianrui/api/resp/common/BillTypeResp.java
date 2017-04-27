package com.tianrui.api.resp.common;

import com.tianrui.api.resp.BaseResp;

public class BillTypeResp extends BaseResp {

	private static final long serialVersionUID = -7648164673267312294L;
	//主键id
    private String id;
    //订单类型code
    private String code;
    //订单类型名称
    private String name;
    //类型（0：采购，1：销售）
    private String type;
    //是否默认显示（0：否，1：是）
    private String defaultshow;
    //状态：（0：删除，1：显示）
    private String state;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //最后修改人
    private String modifier;
    //最后修改时间
    private Long modifytime;
    //备注
    private String remarks;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the show
	 */
	public String getDefaultshow() {
		return defaultshow;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @return the createtime
	 */
	public Long getCreatetime() {
		return createtime;
	}
	/**
	 * @return the modifier
	 */
	public String getModifier() {
		return modifier;
	}
	/**
	 * @return the modifytime
	 */
	public Long getModifytime() {
		return modifytime;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @param show the show to set
	 */
	public void setDefaultshow(String defaultshow) {
		this.defaultshow = defaultshow;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	/**
	 * @param modifytime the modifytime to set
	 */
	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}