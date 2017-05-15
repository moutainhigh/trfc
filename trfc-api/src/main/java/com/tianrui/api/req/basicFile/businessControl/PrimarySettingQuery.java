package com.tianrui.api.req.basicFile.businessControl;

import com.tianrui.api.req.BaseReq;
/**
 * @Description 原发设置查询条件
 * @author zhanggaohao
 * @version 2017年5月12日 下午2:31:16
 */
public class PrimarySettingQuery extends BaseReq {

	private static final long serialVersionUID = -5214879399261560293L;
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
    private String isvalid = "1";
    //页码
    private Integer start;
    //条数
    private Integer limit;

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

	public Integer getStart() {
		return start;
	}

	public Integer getLimit() {
		return limit;
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

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}