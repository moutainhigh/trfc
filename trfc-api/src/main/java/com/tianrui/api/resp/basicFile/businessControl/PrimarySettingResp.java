package com.tianrui.api.resp.basicFile.businessControl;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;
/**
 * @Description 原发设置查询结果
 * @author zhanggaohao
 * @version 2017年5月12日 下午2:31:30
 */
public class PrimarySettingResp extends BaseResp {

	private static final long serialVersionUID = 4733590923277062875L;
	//主键id
	private String id;
	//原发设置编码
    private String code;
	//供应商id
    private String supplierid;
	//供应商名称
    private String suppliername;
	//物料id
    private String materialid;
	//物料名称
    private String materialname;
	//开始时间
    private Long starttime;
	//终止时间
    private Long endtime;
	//是否有效（0：无效，1：有效）
    private String isvalid;
	//创建人
    private String creator;
    //创建人名称
    private String createname;
	//创建时间
    private Long createtime;
    //创建时间Str
    private String createtimeStr;
	//最后修改人
    private String modifier;
	//最后修改时间
    private Long modifytime;
	//备注
    private String remark;

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public String getMaterialid() {
		return materialid;
	}

	public String getMaterialname() {
		return materialname;
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

	public String getCreatename() {
		return createname;
	}

	public Long getCreatetime() {
		return createtime;
	}
	
	public String getCreatetimeStr() {
		return createtimeStr;
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

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
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

	public void setCreatename(String createname) {
		this.createname = createname;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
		this.createtimeStr = DateUtil.parse(createtime, "yyyy-MM-dd HH:mm:ss");
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
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

}