package com.tianrui.api.resp.businessManage.cardManage;

import com.tianrui.api.resp.BaseResp;

public class CardReissueResp extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -553010288589400583L;
	//门禁id
	private String accessid;
    //门禁单号
    private String accesscode;
    //类型
    private String businesstype;
    //状态
    private String accesstype;
    //通知单号
    private String noticecode;
    //入厂时间
    private Long entertime;
    //出场时间字符串
    private String outtimeStr;
    //车牌号
    private String vehicleno;
    //物料名称
    private String materielname;
    //供应商
    private String suppliername;
    //供应商备注
    private String supplierremark;
    //净重
    private Double netweight;
    //RFID
    private String rfid;
    //矿口
    private String minemouthname;
    //IC卡序号
    private String icardno;
    //IC卡面编号
    private String icardcode;
    //IC卡类型
    private String cardtype;
    //订单编号
    private String applicationcode;
    //制单时间
    private String makebilltime;
    //采购组织
    private String orgname;
    //司机
    private String drivername;
    //身份证号
    private String driveridentityno;
    //到货量
    private Double arrivalamount;
    //部门
    private String departmentname;
    //状态(第一次过磅,二次过磅,....)
    private String status;
    //业务时间
    private String billtime;
    //制单人
    private String makebillname;
    //通知单备注
    private String applicationremark;
    //过磅单号
    private String poundcode;
    //收获单位
    private String receivedepartmentname;
    //发货单位
    private String senddepartmentname;
    //仓库
    private String warehousename;
    //毛重
    private Double grossweight;
    //皮重
    private Double tareweight;
    //提货量
    private Double takeamount;
    //预提量
    private Double pickupquantity;
    //过磅员
    private String weighername;
    //轻车时间
    private Long lighttime;
    //重车时间
    private Long weighttime;
    //订单量
    private Double purchasesum;
    //订单单价
    private Double price;
    //客户
    private String customername;
    //喷码
    private String spraycode;
    //批号
    private String batchnum;
    //公司名称
    private String companyname;
	public String getAccesscode() {
		return accesscode;
	}
	public void setAccesscode(String accesscode) {
		this.accesscode = accesscode;
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
	public String getNoticecode() {
		return noticecode;
	}
	public void setNoticecode(String noticecode) {
		this.noticecode = noticecode;
	}
	public Long getEntertime() {
		return entertime;
	}
	public void setEntertime(Long entertime) {
		this.entertime = entertime;
	}
	public String getOuttimeStr() {
		return outtimeStr;
	}
	public void setOuttimeStr(String outtimeStr) {
		this.outtimeStr = outtimeStr;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getMaterielname() {
		return materielname;
	}
	public void setMaterielname(String materielname) {
		this.materielname = materielname;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getSupplierremark() {
		return supplierremark;
	}
	public void setSupplierremark(String supplierremark) {
		this.supplierremark = supplierremark;
	}
	public Double getNetweight() {
		return netweight;
	}
	public void setNetweight(Double netweight) {
		this.netweight = netweight;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getMinemouthname() {
		return minemouthname;
	}
	public void setMinemouthname(String minemouthname) {
		this.minemouthname = minemouthname;
	}
	public String getIcardno() {
		return icardno;
	}
	public void setIcardno(String icardno) {
		this.icardno = icardno;
	}
	public String getIcardcode() {
		return icardcode;
	}
	public void setIcardcode(String icardcode) {
		this.icardcode = icardcode;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public String getApplicationcode() {
		return applicationcode;
	}
	public void setApplicationcode(String applicationcode) {
		this.applicationcode = applicationcode;
	}
	public String getMakebilltime() {
		return makebilltime;
	}
	public void setMakebilltime(String makebilltime) {
		this.makebilltime = makebilltime;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
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
	public Double getArrivalamount() {
		return arrivalamount;
	}
	public void setArrivalamount(Double arrivalamount) {
		this.arrivalamount = arrivalamount;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBilltime() {
		return billtime;
	}
	public void setBilltime(String billtime) {
		this.billtime = billtime;
	}
	public String getMakebillname() {
		return makebillname;
	}
	public void setMakebillname(String makebillname) {
		this.makebillname = makebillname;
	}
	public String getApplicationremark() {
		return applicationremark;
	}
	public void setApplicationremark(String applicationremark) {
		this.applicationremark = applicationremark;
	}
	public String getPoundcode() {
		return poundcode;
	}
	public void setPoundcode(String poundcode) {
		this.poundcode = poundcode;
	}
	public String getReceivedepartmentname() {
		return receivedepartmentname;
	}
	public void setReceivedepartmentname(String receivedepartmentname) {
		this.receivedepartmentname = receivedepartmentname;
	}
	public String getSenddepartmentname() {
		return senddepartmentname;
	}
	public void setSenddepartmentname(String senddepartmentname) {
		this.senddepartmentname = senddepartmentname;
	}
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
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
	public Double getTakeamount() {
		return takeamount;
	}
	public void setTakeamount(Double takeamount) {
		this.takeamount = takeamount;
	}
	public Double getPickupquantity() {
		return pickupquantity;
	}
	public void setPickupquantity(Double pickupquantity) {
		this.pickupquantity = pickupquantity;
	}
	public String getWeighername() {
		return weighername;
	}
	public void setWeighername(String weighername) {
		this.weighername = weighername;
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
	public Double getPurchasesum() {
		return purchasesum;
	}
	public void setPurchasesum(Double purchasesum) {
		this.purchasesum = purchasesum;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getSpraycode() {
		return spraycode;
	}
	public void setSpraycode(String spraycode) {
		this.spraycode = spraycode;
	}
	public String getBatchnum() {
		return batchnum;
	}
	public void setBatchnum(String batchnum) {
		this.batchnum = batchnum;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getAccessid() {
		return accessid;
	}
	public void setAccessid(String accessid) {
		this.accessid = accessid;
	}

}
