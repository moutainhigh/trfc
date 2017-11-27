package com.tianrui.api.resp.businessManage.report;

/**
 * 采购报表物料统计
 * @author Administrator
 *
 */
public class ReportPurchaseMaterResp {


    //  供应商id
    private String supplierid;

    //  供应商名称
    private String suppliername;

    
    //  物料名称
    private String materialname;
    //  备注
    private String remark;
    //矿口名称
    private String minemouthname;

    //  毛重
    private Double sumGrossweight;

    //  皮重
    private Double sumTareweight;

    //  净重
    private Double sumNetweight;

    //  原发净重
    private Double sumOriginalnetweight;

    private Integer countVehicleNo;


	 //  
   private String id;

   //  到货单号
   private String billcode;

   //  过磅单号
   private String poundcode;


   //  堆场名称
   private String yardname;

 

   //  车辆id
   private String vehicleid;

   //  车牌号
   private String vehicleno;

   //  毛重
   private Double grossweight;

   //  皮重
   private Double tareweight;

   //  净重
   private Double netweight;

   //  原发净重
   private Double originalnetweight;

   //  轻车时间
   private Long lighttime;

   //  重车时间
   private Long weighttime;

   //  制单时间
   private Long billtime;

   //  签收人名称
   private String signpersonname;

   //  签收时间
   private Long signtime;
   
   private String signtimeStr;

   //  单据类型（0：到货通知单，1：退货通知单）
   private String type;

   //  司机名称
   private String drivername;

   //  司机id
   private String driverid;

   //  货物
   private String cargo;

   //  推单状态（0：未推单，1：推单中，2：已推单）
   private String returnstatus;

   //  榜单单据状态：（0：计量系统，1：补增，2：退货，3：作废）
   private String status;
   
   
 
   
	public Double getSumGrossweight() {
	return sumGrossweight;
	}
	
	public void setSumGrossweight(Double sumGrossweight) {
		this.sumGrossweight = sumGrossweight;
	}
	
	public Double getSumTareweight() {
		return sumTareweight;
	}
	
	public void setSumTareweight(Double sumTareweight) {
		this.sumTareweight = sumTareweight;
	}
	
	public Double getSumNetweight() {
		return sumNetweight;
	}
	
	public void setSumNetweight(Double sumNetweight) {
		this.sumNetweight = sumNetweight;
	}
	
	public Double getSumOriginalnetweight() {
		return sumOriginalnetweight;
	}
	
	public void setSumOriginalnetweight(Double sumOriginalnetweight) {
		this.sumOriginalnetweight = sumOriginalnetweight;
	}
	
	public Integer getCountVehicleNo() {
		return countVehicleNo;
	}
	
	public void setCountVehicleNo(Integer countVehicleNo) {
		this.countVehicleNo = countVehicleNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public String getPoundcode() {
		return poundcode;
	}

	public void setPoundcode(String poundcode) {
		this.poundcode = poundcode;
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

	public String getMinemouthname() {
		return minemouthname;
	}

	public void setMinemouthname(String minemouthname) {
		this.minemouthname = minemouthname;
	}

	public String getYardname() {
		return yardname;
	}

	public void setYardname(String yardname) {
		this.yardname = yardname;
	}

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
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

	public Long getBilltime() {
		return billtime;
	}

	public void setBilltime(Long billtime) {
		this.billtime = billtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSignpersonname() {
		return signpersonname;
	}

	public void setSignpersonname(String signpersonname) {
		this.signpersonname = signpersonname;
	}

	public Long getSigntime() {
		return signtime;
	}

	public void setSigntime(Long signtime) {
		this.signtime = signtime;
	}

	public String getSigntimeStr() {
		return signtimeStr;
	}

	public void setSigntimeStr(String signtimeStr) {
		this.signtimeStr = signtimeStr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
   
    
}
