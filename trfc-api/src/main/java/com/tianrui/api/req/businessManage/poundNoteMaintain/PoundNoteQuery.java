package com.tianrui.api.req.businessManage.poundNoteMaintain;

import com.tianrui.api.req.BaseReq;
/**
 * @Description 磅单搜索条件
 * @author zhanggaohao
 * @version 2017年3月6日 上午11:28:59
 */
public class PoundNoteQuery extends BaseReq {

	private static final long serialVersionUID = -4903957026020526029L;
	//主键id
	private String id;
	//单据状态：（0：计量系统，1：补增，2：退货，3：作废）
	private String status;
	//是否红冲（0：否，1：是）
	private String redcollide;
	//推单状态（0：为推单，1：推单中，2：已推单）
	private String returnstatus;
	//过磅单号
	private String code;
	//订单编号
	private String billcode;
	//通知单code
	private String noticecode;
	//单据类型（0：采购到货通知单，1：采购退货通知单，销售提货通知单）
	private String billtype;
	//仓库id
	private String warehouseid;
	//供应商id
	private String supplierid;
	//物料id
	private String materielid;
	//车辆id
	private String vehicleid;
    //调入堆场id
    private String enteryardid;
    //调离堆场id
    private String leaveyardid;
	//运算符
	private String operator;
	//净重
	private Double netweight;
	//出厂编号id
	private String batchnumberid;
	//出厂编号
	private String serialnumber;
	//状态（0：删除，1：正常）
	private String state = "1";
	//开始时间
	private String starttime;
	//结束时间
	private Long endtime;
	//开始页数
	private Integer start;
	//条数
	private Integer limit;
	//当前登录用户
	private String currId;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the redcollide
	 */
	public String getRedcollide() {
		return redcollide;
	}

	/**
	 * @param redcollide the redcollide to set
	 */
	public void setRedcollide(String redcollide) {
		this.redcollide = redcollide;
	}

	/**
	 * @return the returnstatus
	 */
	public String getReturnstatus() {
		return returnstatus;
	}

	/**
	 * @param returnstatus the returnstatus to set
	 */
	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the billcode
	 */
	public String getBillcode() {
		return billcode;
	}

	/**
	 * @param billcode the billcode to set
	 */
	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	/**
	 * @return the noticecode
	 */
	public String getNoticecode() {
		return noticecode;
	}

	/**
	 * @param noticecode the noticecode to set
	 */
	public void setNoticecode(String noticecode) {
		this.noticecode = noticecode;
	}

	/**
	 * @return the billtype
	 */
	public String getBilltype() {
		return billtype;
	}

	/**
	 * @param billtype the billtype to set
	 */
	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	/**
	 * @return the warehouseid
	 */
	public String getWarehouseid() {
		return warehouseid;
	}

	/**
	 * @param warehouseid the warehouseid to set
	 */
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}

	/**
	 * @return the supplierid
	 */
	public String getSupplierid() {
		return supplierid;
	}

	/**
	 * @param supplierid the supplierid to set
	 */
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	/**
	 * @return the materielid
	 */
	public String getMaterielid() {
		return materielid;
	}

	/**
	 * @param materielid the materielid to set
	 */
	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}

	/**
	 * @return the vehicleid
	 */
	public String getVehicleid() {
		return vehicleid;
	}

	/**
	 * @param vehicleid the vehicleid to set
	 */
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	/**
	 * @return the enteryardid
	 */
	public String getEnteryardid() {
		return enteryardid;
	}

	/**
	 * @param enteryardid the enteryardid to set
	 */
	public void setEnteryardid(String enteryardid) {
		this.enteryardid = enteryardid;
	}

	/**
	 * @return the leaveyardid
	 */
	public String getLeaveyardid() {
		return leaveyardid;
	}

	/**
	 * @param leaveyardid the leaveyardid to set
	 */
	public void setLeaveyardid(String leaveyardid) {
		this.leaveyardid = leaveyardid;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the netweight
	 */
	public Double getNetweight() {
		return netweight;
	}

	/**
	 * @param netweight the netweight to set
	 */
	public void setNetweight(Double netweight) {
		this.netweight = netweight;
	}

	/**
	 * @return the batchnumberid
	 */
	public String getBatchnumberid() {
		return batchnumberid;
	}

	/**
	 * @return the serialnumber
	 */
	public String getSerialnumber() {
		return serialnumber;
	}

	/**
	 * @param batchnumberid the batchnumberid to set
	 */
	public void setBatchnumberid(String batchnumberid) {
		this.batchnumberid = batchnumberid;
	}

	/**
	 * @param serialnumber the serialnumber to set
	 */
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the starttime
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the endtime
	 */
	public Long getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}

	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * @return the currId
	 */
	public String getCurrId() {
		return currId;
	}

	/**
	 * @param currId the currId to set
	 */
	public void setCurrId(String currId) {
		this.currId = currId;
	}
}
