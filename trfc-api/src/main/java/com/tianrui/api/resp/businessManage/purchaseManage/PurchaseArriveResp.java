package com.tianrui.api.resp.businessManage.purchaseManage;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;
/**
 * @Description 采购到货通知单Resp
 * @author zhanggaohao
 * @version 2017年2月14日 上午9:44:45
 */
public class PurchaseArriveResp extends BaseResp {
	/** serialVersionUID */
	private static final long serialVersionUID = 5250449009196691062L;
	//主键id
    private String id;
	//到货单号
    private String code;
	//审核状态（0：未审核，1：已审核）
    private String auditstatus;
	//来源（0：业务平台，1：客商平台，2：客商APP）
    private String source;
	//状态：（0：未入厂，1：一次过磅，2：二次过磅，3：作废，4：发卡，5：出厂，6：入厂，7：装车）
    private String status;
	//车辆id
    private String vehicleid;
	//车牌号
    private String vehicleno;
	//RFID
    private String vehiclerfid;
	//司机id
    private String driverid;
	//司机名称
    private String drivername;
	//司机身份证号
    private String driveridentityno;
	//采购订单id
    private String billid;
	//采购订单编号
    private String billcode;
    //采购订单详情id
    private String billdetailid;
	//作废/强制出厂人
    private String abnormalperson;
	//作废/强制出厂人名称
    private String abnormalpersonname;
	//作废/强制出厂时间
    private Long abnormaltime;
    //作废/强制出厂时间字符串
    private String abnormaltimeStr;
	//到货量
    private Double arrivalamount;
    //实际签收量
    private Double signamount;
	//单位 default='吨'
    private String unit;
	//制单人id
    private String makerid;
	//制单人名称
    private String makebillname;
	//制单时间
    private Long makebilltime;
    //制单时间字符串
    private String makebilltimeStr;
    //IC卡id
    private String icardid;
    //状态：（0：删除，1：正常）
    private String state;
	//创建人
    private String creator;
	//创建时间
    private Long createtime;
    //创建时间字符串
    private String createtimeStr;
	//最后修改人
    private String modifier;
	//最后修改时间
    private Long modifytime;
    //最后修改时间字符串
    private String modifytimeStr;
	//备注
    private String remark;
    //采购申请单
    private PurchaseApplicationResp purchaseApplicationResp;
    //采购申请单详情
    private PurchaseApplicationDetailResp purchaseApplicationDetailResp;
    /**
     * 仓库物料数量统计
     */
    //余量
    private String margin;
    //入库占用量
    private String storagequantity;
    //未入库占用量
    private String unstoragequantity;
    //到货占用量
    private String arrivalquantity;
    
    //通知单类型 1:采购到货通知单，2：采购退货通知单，3：销售提货通知单
    private String type;
    
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
	public String getAuditstatus() {
		return auditstatus;
	}
	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getVehiclerfid() {
		return vehiclerfid;
	}
	public void setVehiclerfid(String vehiclerfid) {
		this.vehiclerfid = vehiclerfid;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getDriveridentityno() {
		return driveridentityno;
	}
	public void setDriveridentityno(String driveridentityno) {
		this.driveridentityno = driveridentityno;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public String getBillcode() {
		return billcode;
	}
	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}
	public String getBilldetailid() {
		return billdetailid;
	}
	public void setBilldetailid(String billdetailid) {
		this.billdetailid = billdetailid;
	}
	public String getAbnormalperson() {
		return abnormalperson;
	}
	public void setAbnormalperson(String abnormalperson) {
		this.abnormalperson = abnormalperson;
	}
	public String getAbnormalpersonname() {
		return abnormalpersonname;
	}
	public void setAbnormalpersonname(String abnormalpersonname) {
		this.abnormalpersonname = abnormalpersonname;
	}
	public Long getAbnormaltime() {
		return abnormaltime;
	}
	public void setAbnormaltime(Long abnormaltime) {
		this.abnormaltime = abnormaltime;
		this.abnormaltimeStr = DateUtil.parse(abnormaltime, "yyyy-MM-dd HH:mm:ss");
	}
	public String getAbnormaltimeStr() {
		return abnormaltimeStr;
	}
	public void setAbnormaltimeStr(String abnormaltimeStr) {
		this.abnormaltimeStr = abnormaltimeStr;
	}
	public Double getArrivalamount() {
		return arrivalamount;
	}
	public void setArrivalamount(Double arrivalamount) {
		this.arrivalamount = arrivalamount;
	}
	/**
	 * @return the signamount
	 */
	public Double getSignamount() {
		return signamount;
	}
	/**
	 * @param signamount the signamount to set
	 */
	public void setSignamount(Double signamount) {
		this.signamount = signamount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getMakerid() {
		return makerid;
	}
	public void setMakerid(String makerid) {
		this.makerid = makerid;
	}
	public String getMakebillname() {
		return makebillname;
	}
	public void setMakebillname(String makebillname) {
		this.makebillname = makebillname;
	}
	public Long getMakebilltime() {
		return makebilltime;
	}
	public void setMakebilltime(Long makebilltime) {
		this.makebilltime = makebilltime;
		this.makebilltimeStr = DateUtil.parse(makebilltime, "yyyy-MM-dd HH:mm:ss");
	}
	public String getMakebilltimeStr() {
		return makebilltimeStr;
	}
	public void setMakebilltimeStr(String makebilltimeStr) {
		this.makebilltimeStr = makebilltimeStr;
	}
	public String getIcardid() {
		return icardid;
	}
	public void setIcardid(String icardid) {
		this.icardid = icardid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
		this.createtimeStr = DateUtil.parse(createtime, "yyyy-MM-dd HH:mm:ss");
	}
	public String getCreatetimeStr() {
		return createtimeStr;
	}
	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Long getModifytime() {
		return modifytime;
	}
	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
		this.modifytimeStr = DateUtil.parse(modifytime, "yyyy-MM-dd HH:mm:ss");
	}
	public String getModifytimeStr() {
		return modifytimeStr;
	}
	public void setModifytimeStr(String modifytimeStr) {
		this.modifytimeStr = modifytimeStr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public PurchaseApplicationResp getPurchaseApplicationResp() {
		return purchaseApplicationResp;
	}
	public void setPurchaseApplicationResp(PurchaseApplicationResp purchaseApplicationResp) {
		this.purchaseApplicationResp = purchaseApplicationResp;
	}
	public PurchaseApplicationDetailResp getPurchaseApplicationDetailResp() {
		return purchaseApplicationDetailResp;
	}
	public void setPurchaseApplicationDetailResp(PurchaseApplicationDetailResp purchaseApplicationDetailResp) {
		this.purchaseApplicationDetailResp = purchaseApplicationDetailResp;
	}
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	public String getStoragequantity() {
		return storagequantity;
	}
	public void setStoragequantity(String storagequantity) {
		this.storagequantity = storagequantity;
	}
	public String getUnstoragequantity() {
		return unstoragequantity;
	}
	public void setUnstoragequantity(String unstoragequantity) {
		this.unstoragequantity = unstoragequantity;
	}
	public String getArrivalquantity() {
		return arrivalquantity;
	}
	public void setArrivalquantity(String arrivalquantity) {
		this.arrivalquantity = arrivalquantity;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
    
}
