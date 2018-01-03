package com.tianrui.service.bean.businessManage.poundNoteMaintain;
/**
 * @Description 磅单实体Bean
 * @author zhanggaohao
 * @version 2017年3月4日 下午3:49:04
 */
public class PoundNote {
	//主键id
    private String id;
	//过磅单号
    private String code;
	//推单状态（0：未推单，1：推单中，2：已推单）
    private String returnstatus;
	//是否红冲（0：否，1：是）
    private String redcollide;
	//单据状态：（0：计量系统，1：补增，2：退货，3：作废）
    private String status;
	//单据类型（0：采购到货通知单，1：采购退货通知单，2：销售提货通知单，4：厂内倒运，5：其它入库，7：其它出库）
    private String billtype;
	//入库单id
    private String putinwarehouseid;
	//入库单编号
    private String putinwarehousecode;
    //订单id
    private String billid;
    //订单code
    private String billcode;
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
	//重车时间
    private Long weighttime;
	//制单人id
    private String makerid;
	//制单人名称
    private String makebillname;
	//制单时间
    private Long makebilltime;
	//状态（0：删除，1：正常）
    private String state;
    //创建人
    private String creator;
	//创建时间
    private Long createtime;
	//修改人
    private String modifier;
	//修改时间
    private Long modifytime;
	//备注
    private String remark;
    //客户id
    private String customerid;
    //客户名称
    private String customername;
    //供应商id
    private String supplierid;
    //供应商名称
    private String suppliername;
    //单位
    private String department;
    //倒运单位id
    private String cutoverpartmentid;
    //倒运单位名称
    private String cutoverpartmentname;
    //物料id
    private String materialid;
    //物料名称
    private String materialname;
    //货物
    private String cargo;
    //调入堆场id
    private String enteryardid;
    //调入堆场名称
    private String enteryardname;
    //调离堆场id
    private String leaveyardid;
    //调离堆场名称
    private String leaveyardname;
    //红冲状态（0：未红冲，1：红冲中，2：已红冲）
    private String redColStatus;
    //补打状态（0：未补打 1：已补打）
    private String toniStatus;
    //采矿点ID
    private String miningpointId;
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
	public String getReturnstatus() {
		return returnstatus;
	}
	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}
	public String getRedcollide() {
		return redcollide;
	}
	public void setRedcollide(String redcollide) {
		this.redcollide = redcollide;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBilltype() {
		return billtype;
	}
	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}
	public String getPutinwarehouseid() {
		return putinwarehouseid;
	}
	public void setPutinwarehouseid(String putinwarehouseid) {
		this.putinwarehouseid = putinwarehouseid;
	}
	public String getPutinwarehousecode() {
		return putinwarehousecode;
	}
	public void setPutinwarehousecode(String putinwarehousecode) {
		this.putinwarehousecode = putinwarehousecode;
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
	public String getMaindeduction() {
		return maindeduction;
	}
	public void setMaindeduction(String maindeduction) {
		this.maindeduction = maindeduction;
	}
	public String getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
	}
	public String getNoticecode() {
		return noticecode;
	}
	public void setNoticecode(String noticecode) {
		this.noticecode = noticecode;
	}
	public String getArrivepoundnotecode() {
		return arrivepoundnotecode;
	}
	public void setArrivepoundnotecode(String arrivepoundnotecode) {
		this.arrivepoundnotecode = arrivepoundnotecode;
	}
	public String getSenddepartmentid() {
		return senddepartmentid;
	}
	public void setSenddepartmentid(String senddepartmentid) {
		this.senddepartmentid = senddepartmentid;
	}
	public String getSenddepartmentname() {
		return senddepartmentname;
	}
	public void setSenddepartmentname(String senddepartmentname) {
		this.senddepartmentname = senddepartmentname;
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
	public String getReceivedepartmentid() {
		return receivedepartmentid;
	}
	public void setReceivedepartmentid(String receivedepartmentid) {
		this.receivedepartmentid = receivedepartmentid;
	}
	public String getReceivedepartmentname() {
		return receivedepartmentname;
	}
	public void setReceivedepartmentname(String receivedepartmentname) {
		this.receivedepartmentname = receivedepartmentname;
	}
	public String getMinemouthid() {
		return minemouthid;
	}
	public void setMinemouthid(String minemouthid) {
		this.minemouthid = minemouthid;
	}
	public String getMinemouthname() {
		return minemouthname;
	}
	public void setMinemouthname(String minemouthname) {
		this.minemouthname = minemouthname;
	}
	public String getWarehouseid() {
		return warehouseid;
	}
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	public String getYardid() {
		return yardid;
	}
	public void setYardid(String yardid) {
		this.yardid = yardid;
	}
	public String getYardname() {
		return yardname;
	}
	public void setYardname(String yardname) {
		this.yardname = yardname;
	}
	public String getReceiverpersonid() {
		return receiverpersonid;
	}
	public void setReceiverpersonid(String receiverpersonid) {
		this.receiverpersonid = receiverpersonid;
	}
	public String getReceiverpersonname() {
		return receiverpersonname;
	}
	public void setReceiverpersonname(String receiverpersonname) {
		this.receiverpersonname = receiverpersonname;
	}
	public String getWeigherid() {
		return weigherid;
	}
	public void setWeigherid(String weigherid) {
		this.weigherid = weigherid;
	}
	public String getWeighername() {
		return weighername;
	}
	public void setWeighername(String weighername) {
		this.weighername = weighername;
	}
	public Long getReceivertime() {
		return receivertime;
	}
	public void setReceivertime(Long receivertime) {
		this.receivertime = receivertime;
	}
	public Double getPickupquantity() {
		return pickupquantity;
	}
	public void setPickupquantity(Double pickupquantity) {
		this.pickupquantity = pickupquantity;
	}
	public Double getGrossweight() {
		return grossweight;
	}
	public void setGrossweight(Double grossweight) {
		this.grossweight = grossweight;
	}
	public Double getTareweight() {
		return tareweight;
	}
	public void setTareweight(Double tareweight) {
		this.tareweight = tareweight;
	}
	public Double getNetweight() {
		return netweight;
	}
	public void setNetweight(Double netweight) {
		this.netweight = netweight;
	}
	public Double getOriginalnetweight() {
		return originalnetweight;
	}
	public void setOriginalnetweight(Double originalnetweight) {
		this.originalnetweight = originalnetweight;
	}
	public Double getDeductionweight() {
		return deductionweight;
	}
	public void setDeductionweight(Double deductionweight) {
		this.deductionweight = deductionweight;
	}
	public Double getDeductionother() {
		return deductionother;
	}
	public void setDeductionother(Double deductionother) {
		this.deductionother = deductionother;
	}
	public String getBatchnumberid() {
		return batchnumberid;
	}
	public void setBatchnumberid(String batchnumberid) {
		this.batchnumberid = batchnumberid;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public Long getLighttime() {
		return lighttime;
	}
	public void setLighttime(Long lighttime) {
		this.lighttime = lighttime;
	}
	public Long getWeighttime() {
		return weighttime;
	}
	public void setWeighttime(Long weighttime) {
		this.weighttime = weighttime;
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
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCutoverpartmentid() {
		return cutoverpartmentid;
	}
	public void setCutoverpartmentid(String cutoverpartmentid) {
		this.cutoverpartmentid = cutoverpartmentid;
	}
	public String getCutoverpartmentname() {
		return cutoverpartmentname;
	}
	public void setCutoverpartmentname(String cutoverpartmentname) {
		this.cutoverpartmentname = cutoverpartmentname;
	}
	public String getMaterialid() {
		return materialid;
	}
	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getEnteryardid() {
		return enteryardid;
	}
	public void setEnteryardid(String enteryardid) {
		this.enteryardid = enteryardid;
	}
	public String getEnteryardname() {
		return enteryardname;
	}
	public void setEnteryardname(String enteryardname) {
		this.enteryardname = enteryardname;
	}
	public String getLeaveyardid() {
		return leaveyardid;
	}
	public void setLeaveyardid(String leaveyardid) {
		this.leaveyardid = leaveyardid;
	}
	public String getLeaveyardname() {
		return leaveyardname;
	}
	public void setLeaveyardname(String leaveyardname) {
		this.leaveyardname = leaveyardname;
	}
	public String getRedColStatus() {
		return redColStatus;
	}
	public void setRedColStatus(String redColStatus) {
		this.redColStatus = redColStatus;
	}
	public String getToniStatus() {
		return toniStatus;
	}
	public void setToniStatus(String toniStatus) {
		this.toniStatus = toniStatus;
	}
	public String getMiningpointId() {
		return miningpointId;
	}
	public void setMiningpointId(String miningpointId) {
		this.miningpointId = miningpointId;
	}
	
}