package com.tianrui.api.resp.businessManage.poundNoteMaintain;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

public class PoundNoteResp extends BaseResp {

	private static final long serialVersionUID = 2216553760770399508L;
	//主键id
    private String id;
	//过磅单号
    private String code;
	//推单状态（0：未推单，1：推单中，2：已推单）
    private String returnstatus;
	//是否红冲（0：否，1：是）
    private String redcollide;
	//单据状态：（0：计量系统，1：补增，2：作废）
    private String status;
    //单据类型（0：采购到货通知单，1：采购退货通知单，2：销售提货通知单）
    private String billtype;
	//入库单id
    private String putinwarehouseid;
	//入库单编号
    private String putinwarehousecode;
    //订单id
    private String billid;
    //订单code
    private String billcode;
    //订单数量
    private Double billsum;
    //订单详情id
    private String billdetailid;
    //主单扣量（0：否，1：是）
    private String maindeduction;
	//通知单id
    private String noticeid;
	//通知单code
    private String noticecode;
    //到货磅单
    private String arrivepoundnotecode;
    //发货单位id
    private String senddepartmentid;
    //发货单位名称
    private String senddepartmentname;
	//供应商id
    private String supplierid;
    //供应商名称
    private String suppliername;
    //供应商备注
    private String supplierremark;
    //客户id
    private String customerid;
    //客户名称
    private String customername;
    //物料
    private String materielname;
    //司机id
    private String driverid;
	//司机名称
    private String drivername;
	//司机身份证号
    private String driveridentityno;
	//车辆id
    private String vehicleid;
	//车牌号
    private String vehicleno;
    //RFID
    private String vehiclerfid;
	//接收单位id
    private String receivedepartmentid;
	//接收单位名称
    private String receivedepartmentname;
	//矿口id
    private String minemouthid;
	//矿口名称
    private String minemouthname;
	//仓库id
    private String warehouseid;
	//仓库名称
    private String warehousename;
	//堆场id
    private String yardid;
	//堆场名称
    private String yardname;
	//收料人id
    private String receiverpersonid;
	//收料人名称
    private String receiverpersonname;
	//过磅员id
    private String weigherid;
	//过磅员名称
    private String weighername;
    //收货时间
    private Long receivertime;
    //收货时间字符串
    private String receivertimeStr;
	//预提量
    private Double pickupquantity;
	//毛重
    private Double grossweight;
	//皮重
    private Double tareweight;
	//净重
    private Double netweight;
	//原发净重
    private Double originalnetweight;
	//扣重
    private Double deductionweight;
	//扣杂
    private Double deductionother;
	//销售批号id
    private String batchnumberid;
    //出厂编号
    private String serialnumber;
	//轻车时间
    private Long lighttime;
    //轻车时间Str
    private String lighttimeStr;
	//重车时间
    private Long weighttime;
    //重车时间Str
    private String weighttimeStr;
	//制单人id
    private String makerid;
	//制单人名称
    private String makebillname;
	//制单时间
    private Long makebilltime;
    //制单时间Str
    private String makebilltimeStr;
	//状态（0：删除，1：正常）
    private String state;
    //创建人
    private String creator;
	//创建时间
    private Long createtime;
    //创建时间Str
    private String createtimeStr;
	//修改人
    private String modifier;
	//修改时间
    private Long modifytime;
    //修改时间Str
    private String modifytimeStr;
	//备注
    private String remark;
    //订单详情
    private List<PurchaseApplicationResp> purchaseApplicationList;
    //订单详情
    private List<SalesApplicationResp> salesApplicationList;
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
	 * @return the putinwarehouseid
	 */
	public String getPutinwarehouseid() {
		return putinwarehouseid;
	}
	/**
	 * @param putinwarehouseid the putinwarehouseid to set
	 */
	public void setPutinwarehouseid(String putinwarehouseid) {
		this.putinwarehouseid = putinwarehouseid;
	}
	/**
	 * @return the putinwarehousecode
	 */
	public String getPutinwarehousecode() {
		return putinwarehousecode;
	}
	/**
	 * @param putinwarehousecode the putinwarehousecode to set
	 */
	public void setPutinwarehousecode(String putinwarehousecode) {
		this.putinwarehousecode = putinwarehousecode;
	}
	/**
	 * @return the billid
	 */
	public String getBillid() {
		return billid;
	}
	/**
	 * @param billid the billid to set
	 */
	public void setBillid(String billid) {
		this.billid = billid;
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
	 * @return the billsum
	 */
	public Double getBillsum() {
		return billsum;
	}
	/**
	 * @param billsum the billsum to set
	 */
	public void setBillsum(Double billsum) {
		this.billsum = billsum;
	}
	/**
	 * @return the billdetailid
	 */
	public String getBilldetailid() {
		return billdetailid;
	}
	/**
	 * @param billdetailid the billdetailid to set
	 */
	public void setBilldetailid(String billdetailid) {
		this.billdetailid = billdetailid;
	}
	/**
	 * @return the maindeduction
	 */
	public String getMaindeduction() {
		return maindeduction;
	}
	/**
	 * @param maindeduction the maindeduction to set
	 */
	public void setMaindeduction(String maindeduction) {
		this.maindeduction = maindeduction;
	}
	/**
	 * @return the noticeid
	 */
	public String getNoticeid() {
		return noticeid;
	}
	/**
	 * @param noticeid the noticeid to set
	 */
	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
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
	 * @return the arrivepoundnotecode
	 */
	public String getArrivepoundnotecode() {
		return arrivepoundnotecode;
	}
	/**
	 * @param arrivepoundnotecode the arrivepoundnotecode to set
	 */
	public void setArrivepoundnotecode(String arrivepoundnotecode) {
		this.arrivepoundnotecode = arrivepoundnotecode;
	}
	/**
	 * @return the senddepartmentid
	 */
	public String getSenddepartmentid() {
		return senddepartmentid;
	}
	/**
	 * @param senddepartmentid the senddepartmentid to set
	 */
	public void setSenddepartmentid(String senddepartmentid) {
		this.senddepartmentid = senddepartmentid;
	}
	/**
	 * @return the senddepartmentname
	 */
	public String getSenddepartmentname() {
		return senddepartmentname;
	}
	/**
	 * @param senddepartmentname the senddepartmentname to set
	 */
	public void setSenddepartmentname(String senddepartmentname) {
		this.senddepartmentname = senddepartmentname;
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
	 * @return the suppliername
	 */
	public String getSuppliername() {
		return suppliername;
	}
	/**
	 * @param suppliername the suppliername to set
	 */
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	/**
	 * @return the supplierremark
	 */
	public String getSupplierremark() {
		return supplierremark;
	}
	/**
	 * @param supplierremark the supplierremark to set
	 */
	public void setSupplierremark(String supplierremark) {
		this.supplierremark = supplierremark;
	}
	/**
	 * @return the customerid
	 */
	public String getCustomerid() {
		return customerid;
	}
	/**
	 * @return the customername
	 */
	public String getCustomername() {
		return customername;
	}
	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	/**
	 * @param customername the customername to set
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	/**
	 * @return the materielname
	 */
	public String getMaterielname() {
		return materielname;
	}
	/**
	 * @param materielname the materielname to set
	 */
	public void setMaterielname(String materielname) {
		this.materielname = materielname;
	}
	/**
	 * @return the driverid
	 */
	public String getDriverid() {
		return driverid;
	}
	/**
	 * @param driverid the driverid to set
	 */
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	/**
	 * @return the drivername
	 */
	public String getDrivername() {
		return drivername;
	}
	/**
	 * @param drivername the drivername to set
	 */
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	/**
	 * @return the driveridentityno
	 */
	public String getDriveridentityno() {
		return driveridentityno;
	}
	/**
	 * @param driveridentityno the driveridentityno to set
	 */
	public void setDriveridentityno(String driveridentityno) {
		this.driveridentityno = driveridentityno;
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
	 * @return the vehicleno
	 */
	public String getVehicleno() {
		return vehicleno;
	}
	/**
	 * @param vehicleno the vehicleno to set
	 */
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	/**
	 * @return the vehiclerfid
	 */
	public String getVehiclerfid() {
		return vehiclerfid;
	}
	/**
	 * @param vehiclerfid the vehiclerfid to set
	 */
	public void setVehiclerfid(String vehiclerfid) {
		this.vehiclerfid = vehiclerfid;
	}
	/**
	 * @return the receivedepartmentid
	 */
	public String getReceivedepartmentid() {
		return receivedepartmentid;
	}
	/**
	 * @param receivedepartmentid the receivedepartmentid to set
	 */
	public void setReceivedepartmentid(String receivedepartmentid) {
		this.receivedepartmentid = receivedepartmentid;
	}
	/**
	 * @return the receivedepartmentname
	 */
	public String getReceivedepartmentname() {
		return receivedepartmentname;
	}
	/**
	 * @param receivedepartmentname the receivedepartmentname to set
	 */
	public void setReceivedepartmentname(String receivedepartmentname) {
		this.receivedepartmentname = receivedepartmentname;
	}
	/**
	 * @return the minemouthid
	 */
	public String getMinemouthid() {
		return minemouthid;
	}
	/**
	 * @param minemouthid the minemouthid to set
	 */
	public void setMinemouthid(String minemouthid) {
		this.minemouthid = minemouthid;
	}
	/**
	 * @return the minemouthname
	 */
	public String getMinemouthname() {
		return minemouthname;
	}
	/**
	 * @param minemouthname the minemouthname to set
	 */
	public void setMinemouthname(String minemouthname) {
		this.minemouthname = minemouthname;
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
	 * @return the warehousename
	 */
	public String getWarehousename() {
		return warehousename;
	}
	/**
	 * @param warehousename the warehousename to set
	 */
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	/**
	 * @return the yardid
	 */
	public String getYardid() {
		return yardid;
	}
	/**
	 * @param yardid the yardid to set
	 */
	public void setYardid(String yardid) {
		this.yardid = yardid;
	}
	/**
	 * @return the yardname
	 */
	public String getYardname() {
		return yardname;
	}
	/**
	 * @param yardname the yardname to set
	 */
	public void setYardname(String yardname) {
		this.yardname = yardname;
	}
	/**
	 * @return the receiverpersonid
	 */
	public String getReceiverpersonid() {
		return receiverpersonid;
	}
	/**
	 * @param receiverpersonid the receiverpersonid to set
	 */
	public void setReceiverpersonid(String receiverpersonid) {
		this.receiverpersonid = receiverpersonid;
	}
	/**
	 * @return the receiverpersonname
	 */
	public String getReceiverpersonname() {
		return receiverpersonname;
	}
	/**
	 * @param receiverpersonname the receiverpersonname to set
	 */
	public void setReceiverpersonname(String receiverpersonname) {
		this.receiverpersonname = receiverpersonname;
	}
	/**
	 * @return the weigherid
	 */
	public String getWeigherid() {
		return weigherid;
	}
	/**
	 * @param weigherid the weigherid to set
	 */
	public void setWeigherid(String weigherid) {
		this.weigherid = weigherid;
	}
	/**
	 * @return the weighername
	 */
	public String getWeighername() {
		return weighername;
	}
	/**
	 * @param weighername the weighername to set
	 */
	public void setWeighername(String weighername) {
		this.weighername = weighername;
	}
	/**
	 * @return the receivertime
	 */
	public Long getReceivertime() {
		return receivertime;
	}
	/**
	 * @param receivertime the receivertime to set
	 */
	public void setReceivertime(Long receivertime) {
		this.receivertime = receivertime;
		this.receivertimeStr = DateUtil.parse(receivertime, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * @return the receivertimeStr
	 */
	public String getReceivertimeStr() {
		return receivertimeStr;
	}
	/**
	 * @param receivertimeStr the receivertimeStr to set
	 */
	public void setReceivertimeStr(String receivertimeStr) {
		this.receivertimeStr = receivertimeStr;
	}
	/**
	 * @return the pickupquantity
	 */
	public Double getPickupquantity() {
		return pickupquantity;
	}
	/**
	 * @param pickupquantity the pickupquantity to set
	 */
	public void setPickupquantity(Double pickupquantity) {
		this.pickupquantity = pickupquantity;
	}
	/**
	 * @return the grossweight
	 */
	public Double getGrossweight() {
		return grossweight;
	}
	/**
	 * @param grossweight the grossweight to set
	 */
	public void setGrossweight(Double grossweight) {
		this.grossweight = grossweight;
	}
	/**
	 * @return the tareweight
	 */
	public Double getTareweight() {
		return tareweight;
	}
	/**
	 * @param tareweight the tareweight to set
	 */
	public void setTareweight(Double tareweight) {
		this.tareweight = tareweight;
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
	 * @return the originalnetweight
	 */
	public Double getOriginalnetweight() {
		return originalnetweight;
	}
	/**
	 * @param originalnetweight the originalnetweight to set
	 */
	public void setOriginalnetweight(Double originalnetweight) {
		this.originalnetweight = originalnetweight;
	}
	/**
	 * @return the deductionweight
	 */
	public Double getDeductionweight() {
		return deductionweight;
	}
	/**
	 * @param deductionweight the deductionweight to set
	 */
	public void setDeductionweight(Double deductionweight) {
		this.deductionweight = deductionweight;
	}
	/**
	 * @return the deductionother
	 */
	public Double getDeductionother() {
		return deductionother;
	}
	/**
	 * @param deductionother the deductionother to set
	 */
	public void setDeductionother(Double deductionother) {
		this.deductionother = deductionother;
	}
	/**
	 * @return the batchnumberid
	 */
	public String getBatchnumberid() {
		return batchnumberid;
	}
	/**
	 * @param batchnumberid the batchnumberid to set
	 */
	public void setBatchnumberid(String batchnumberid) {
		this.batchnumberid = batchnumberid;
	}
	/**
	 * @return the serialnumber
	 */
	public String getSerialnumber() {
		return serialnumber;
	}
	/**
	 * @param serialnumber the serialnumber to set
	 */
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	/**
	 * @return the lighttime
	 */
	public Long getLighttime() {
		return lighttime;
	}
	/**
	 * @param lighttime the lighttime to set
	 */
	public void setLighttime(Long lighttime) {
		this.lighttime = lighttime;
		this.lighttimeStr = DateUtil.parse(lighttime, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * @return the lighttimeStr
	 */
	public String getLighttimeStr() {
		return lighttimeStr;
	}
	/**
	 * @param lighttimeStr the lighttimeStr to set
	 */
	public void setLighttimeStr(String lighttimeStr) {
		this.lighttimeStr = lighttimeStr;
	}
	/**
	 * @return the weighttime
	 */
	public Long getWeighttime() {
		return weighttime;
	}
	/**
	 * @param weighttime the weighttime to set
	 */
	public void setWeighttime(Long weighttime) {
		this.weighttime = weighttime;
		this.weighttimeStr = DateUtil.parse(weighttime, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * @return the weighttimeStr
	 */
	public String getWeighttimeStr() {
		return weighttimeStr;
	}
	/**
	 * @param weighttimeStr the weighttimeStr to set
	 */
	public void setWeighttimeStr(String weighttimeStr) {
		this.weighttimeStr = weighttimeStr;
	}
	/**
	 * @return the makerid
	 */
	public String getMakerid() {
		return makerid;
	}
	/**
	 * @param makerid the makerid to set
	 */
	public void setMakerid(String makerid) {
		this.makerid = makerid;
	}
	/**
	 * @return the makebillname
	 */
	public String getMakebillname() {
		return makebillname;
	}
	/**
	 * @param makebillname the makebillname to set
	 */
	public void setMakebillname(String makebillname) {
		this.makebillname = makebillname;
	}
	/**
	 * @return the makebilltime
	 */
	public Long getMakebilltime() {
		return makebilltime;
	}
	/**
	 * @param makebilltime the makebilltime to set
	 */
	public void setMakebilltime(Long makebilltime) {
		this.makebilltime = makebilltime;
		this.makebilltimeStr = DateUtil.parse(makebilltime, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * @return the makebilltimeStr
	 */
	public String getMakebilltimeStr() {
		return makebilltimeStr;
	}
	/**
	 * @param makebilltimeStr the makebilltimeStr to set
	 */
	public void setMakebilltimeStr(String makebilltimeStr) {
		this.makebilltimeStr = makebilltimeStr;
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
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @return the createtime
	 */
	public Long getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
		this.createtimeStr = DateUtil.parse(createtime, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * @return the createtimeStr
	 */
	public String getCreatetimeStr() {
		return createtimeStr;
	}
	/**
	 * @param createtimeStr the createtimeStr to set
	 */
	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}
	/**
	 * @return the modifier
	 */
	public String getModifier() {
		return modifier;
	}
	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	/**
	 * @return the modifytime
	 */
	public Long getModifytime() {
		return modifytime;
	}
	/**
	 * @param modifytime the modifytime to set
	 */
	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
		this.modifytimeStr = DateUtil.parse(modifytime, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * @return the modifytimeStr
	 */
	public String getModifytimeStr() {
		return modifytimeStr;
	}
	/**
	 * @param modifytimeStr the modifytimeStr to set
	 */
	public void setModifytimeStr(String modifytimeStr) {
		this.modifytimeStr = modifytimeStr;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the purchaseApplicationList
	 */
	public List<PurchaseApplicationResp> getPurchaseApplicationList() {
		return purchaseApplicationList;
	}
	/**
	 * @param purchaseApplicationList the purchaseApplicationList to set
	 */
	public void setPurchaseApplicationList(List<PurchaseApplicationResp> purchaseApplicationList) {
		this.purchaseApplicationList = purchaseApplicationList;
	}
	/**
	 * @return the salesApplicationList
	 */
	public List<SalesApplicationResp> getSalesApplicationList() {
		return salesApplicationList;
	}
	/**
	 * @param salesApplicationList the salesApplicationList to set
	 */
	public void setSalesApplicationList(List<SalesApplicationResp> salesApplicationList) {
		this.salesApplicationList = salesApplicationList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoundNoteResp [id=" + id + ", code=" + code + ", returnstatus=" + returnstatus + ", redcollide="
				+ redcollide + ", status=" + status + ", billtype=" + billtype + ", putinwarehouseid="
				+ putinwarehouseid + ", putinwarehousecode=" + putinwarehousecode + ", billid=" + billid + ", billcode="
				+ billcode + ", billsum=" + billsum + ", billdetailid=" + billdetailid + ", noticeid=" + noticeid
				+ ", noticecode=" + noticecode + ", arrivepoundnotecode=" + arrivepoundnotecode + ", senddepartmentid="
				+ senddepartmentid + ", senddepartmentname=" + senddepartmentname + ", supplierid=" + supplierid
				+ ", suppliername=" + suppliername + ", supplierremark=" + supplierremark + ", materielname="
				+ materielname + ", driverid=" + driverid + ", drivername=" + drivername + ", driveridentityno="
				+ driveridentityno + ", vehicleid=" + vehicleid + ", vehicleno=" + vehicleno + ", receivedepartmentid="
				+ receivedepartmentid + ", receivedepartmentname=" + receivedepartmentname + ", minemouthid="
				+ minemouthid + ", minemouthname=" + minemouthname + ", warehouseid=" + warehouseid + ", warehousename="
				+ warehousename + ", yardid=" + yardid + ", yardname=" + yardname + ", receiverpersonid="
				+ receiverpersonid + ", receiverpersonname=" + receiverpersonname + ", pickupquantity=" + pickupquantity
				+ ", grossweight=" + grossweight + ", tareweight=" + tareweight + ", netweight=" + netweight
				+ ", originalnetweight=" + originalnetweight + ", deductionweight=" + deductionweight
				+ ", deductionother=" + deductionother + ", serialnumber=" + serialnumber + ", lighttime=" + lighttime
				+ ", lighttimeStr=" + lighttimeStr + ", weighttime=" + weighttime + ", weighttimeStr=" + weighttimeStr
				+ ", makerid=" + makerid + ", makebillname=" + makebillname + ", makebilltime=" + makebilltime
				+ ", makebilltimeStr=" + makebilltimeStr + ", state=" + state + ", creator=" + creator + ", createtime="
				+ createtime + ", createtimeStr=" + createtimeStr + ", modifier=" + modifier + ", modifytime="
				+ modifytime + ", modifytimeStr=" + modifytimeStr + ", remark=" + remark + "]";
	}
	
	public SalesApplicationResp getMainApplication(){
		if (this.salesApplicationList != null && this.salesApplicationList.size() > 0) {
			for (SalesApplicationResp resp : this.salesApplicationList) {
				if(StringUtils.equals(resp.getId(), this.billid)){
					return resp;
				}
			}
		}
		return null;
	}
	
	public SalesApplicationDetailResp getMainApplicationDetail(){
		if (this.salesApplicationList != null && this.salesApplicationList.size() > 0) {
			for (SalesApplicationResp resp : this.salesApplicationList) {
				if(resp.getList() != null && StringUtils.equals(resp.getList().get(0).getId(), this.billdetailid)){
					return resp.getList().get(0);
				}
			}
		}
		return null;
	}

	public SalesApplicationResp getOneApplication(){
		if(CollectionUtils.isNotEmpty(salesApplicationList) && salesApplicationList.size() > 0){
			return salesApplicationList.get(0);
		}
		return new SalesApplicationResp();
	}
	
	public SalesApplicationResp getTwoApplication(){
		if(CollectionUtils.isNotEmpty(salesApplicationList) && salesApplicationList.size() > 1){
			return salesApplicationList.get(1);
		}
		return new SalesApplicationResp();
	}
	
	public SalesApplicationResp getThreeApplication(){
		if(CollectionUtils.isNotEmpty(salesApplicationList) && salesApplicationList.size() > 2){
			return salesApplicationList.get(2);
		}
		return new SalesApplicationResp();
	}
}
