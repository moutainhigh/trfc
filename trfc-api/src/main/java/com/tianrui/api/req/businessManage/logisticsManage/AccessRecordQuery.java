package com.tianrui.api.req.businessManage.logisticsManage;

import com.tianrui.api.req.BaseReq;

/**
 * @Description 门禁记录query
 * @author zhanggaohao
 * @version 2017年2月20日 下午4:05:35
 */
public class AccessRecordQuery extends BaseReq {

	/** serialVersionUID */
	private static final long serialVersionUID = -4787479018851460354L;
    private String id;
    //门禁单号
    private String code;
    //业务类型（1：采购，2：销售，3：其他入库，4：其他出库）
    private String businesstype;
    //门禁类型（1：入厂，2：出厂）
    private String accesstype;
    //状态：（0：删除，1：显示）
    private String state;
    //物料id
    private String materielid;
    //车牌号
    private String vehicleid;
	//开始时间
    private Long starttime;
    //结束时间
    private Long endtime;
    //起始页数
    private Integer start;
    //条数
    private Integer limit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	public String getAccesstype() {
		return accesstype;
	}
	public void setAccesstype(String accesstype) {
		this.accesstype = accesstype;
	}
	public String getMaterielid() {
		return materielid;
	}
	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
