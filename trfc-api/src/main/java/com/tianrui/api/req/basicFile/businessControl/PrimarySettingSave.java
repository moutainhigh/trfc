package com.tianrui.api.req.basicFile.businessControl;

import com.tianrui.api.req.BaseReq;
/**
 * @Description 原发设置保存参数实体
 * @author zhanggaohao
 * @version 2017年5月12日 下午2:31:49
 */
public class PrimarySettingSave extends BaseReq {

	private static final long serialVersionUID = 4733590923277062875L;
	//主键id
	private String id;
	//原发设置编码
    private String code;
	//供应商id
    private String supplierid;
	//物料id
    private String materialid;
	//开始时间
    private Long starttime;
	//终止时间
    private Long endtime;
	//是否有效（0：无效，1：有效）
    private String isvalid;
	//创建人
    private String creator;
	//创建时间
    private Long createtime;
	//最后修改人
    private String modifier;
	//最后修改时间
    private Long modifytime;
	//备注
    private String remark;
    //当前登陆用户id
    private String currId;

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public String getMaterialid() {
		return materialid;
	}

	public Long getStarttime() {
		return starttime;
	}

	public Long getEndtime() {
		return endtime;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public String getCreator() {
		return creator;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public String getModifier() {
		return modifier;
	}

	public Long getModifytime() {
		return modifytime;
	}

	public String getRemark() {
		return remark;
	}

	public String getCurrId() {
		return currId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCurrId(String currId) {
		this.currId = currId;
	}

}