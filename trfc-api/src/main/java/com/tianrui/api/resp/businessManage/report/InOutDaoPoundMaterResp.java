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
    //车牌号
    private String vehicleno;
    //调入堆场
    private String enteryardname;
    //  调出堆场
    private String leaveyardname;
    
	public String getEnteryardname() {
		return enteryardname;
	}
	public void setEnteryardname(String enteryardname) {
		this.enteryardname = enteryardname;
	}
	public String getLeaveyardname() {
		return leaveyardname;
	}
	public void setLeaveyardname(String leaveyardname) {
		this.leaveyardname = leaveyardname;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
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
