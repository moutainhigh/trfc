package com.tianrui.api.req.businessManage.salesManage;

public class SalesApplicationDetailSave {
	//主键id
    private String id;
    //销售申请单id
    private String salesid;
    //物料id
    private String materielid;
    //物料名称
    private String materielname;
    //仓库id
    private String warehouseid;
    //仓库名称
    private String warehousename;
    //单位 default:吨
    private String unit;
    //数量
    private Double salessum;
    //余量
    private Double margin;
    //出库占用量
    private Double storagequantity;
    //未出库占用量
    private Double unstoragequantity;
    //预提占用
    private Double pretendingtake;
    //含税单价
    private Double taxprice;
    //不含税单价
    private Double untaxprice;
    //税率
    private Double taxrate;
    //备注
    private String remarks;
    //nc审核状态（1=自由,2=审批通过,3=冻结,4=关闭,5=失败,7=审批中,8=审批不通过,9=删除）
    private String ncStatus;
    //NC子表ID
    private String ncId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSalesid() {
		return salesid;
	}
	public void setSalesid(String salesid) {
		this.salesid = salesid;
	}
	public String getMaterielid() {
		return materielid;
	}
	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}
	public String getMaterielname() {
		return materielname;
	}
	public void setMaterielname(String materielname) {
		this.materielname = materielname;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getSalessum() {
		return salessum;
	}
	public void setSalessum(Double salessum) {
		this.salessum = salessum;
	}
	public Double getMargin() {
		return margin;
	}
	public void setMargin(Double margin) {
		this.margin = margin;
	}
	public Double getStoragequantity() {
		return storagequantity;
	}
	public void setStoragequantity(Double storagequantity) {
		this.storagequantity = storagequantity;
	}
	public Double getUnstoragequantity() {
		return unstoragequantity;
	}
	public void setUnstoragequantity(Double unstoragequantity) {
		this.unstoragequantity = unstoragequantity;
	}
	public Double getPretendingtake() {
		return pretendingtake;
	}
	public void setPretendingtake(Double pretendingtake) {
		this.pretendingtake = pretendingtake;
	}
	public Double getTaxprice() {
		return taxprice;
	}
	public void setTaxprice(Double taxprice) {
		this.taxprice = taxprice;
	}
	public Double getUntaxprice() {
		return untaxprice;
	}
	public void setUntaxprice(Double untaxprice) {
		this.untaxprice = untaxprice;
	}
	public Double getTaxrate() {
		return taxrate;
	}
	public void setTaxrate(Double taxrate) {
		this.taxrate = taxrate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getNcStatus() {
		return ncStatus;
	}
	public void setNcStatus(String ncStatus) {
		this.ncStatus = ncStatus;
	}
	public String getNcId() {
		return ncId;
	}
	public void setNcId(String ncId) {
		this.ncId = ncId;
	}
}