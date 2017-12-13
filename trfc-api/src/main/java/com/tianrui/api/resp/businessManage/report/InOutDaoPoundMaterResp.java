package com.tianrui.api.resp.businessManage.report;
/**
 * 调拨报表调拨物料统计
 * @author lenovo
 *
 */
public class InOutDaoPoundMaterResp {
	 //  物料名称
    private String materialname;
    //  毛重
    private Double sumGrossweight;

    //  皮重
    private Double sumTareweight;

    //  净重
    private Double sumNetweight;
    //车数
    private Integer countVehicleNo;
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
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
	public Integer getCountVehicleNo() {
		return countVehicleNo;
	}
	public void setCountVehicleNo(Integer countVehicleNo) {
		this.countVehicleNo = countVehicleNo;
	}

    
}
