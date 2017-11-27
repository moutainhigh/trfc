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

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMinemouthname() {
		return minemouthname;
	}

	public void setMinemouthname(String minemouthname) {
		this.minemouthname = minemouthname;
	}

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



}
