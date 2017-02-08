package com.tianrui.api.resp.businessManage.salesManage;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.resp.basicFile.nc.MaterielManageResp;
import com.tianrui.api.resp.basicFile.nc.WarehouseManageResp;

public class SalesApplicationDetailResp extends BaseResp {

	private static final long serialVersionUID = -1813964842063776104L;

	private String id;

    private String salesid;

    private String materielid;

    private String materielname;

    private String warehouseid;

    private String warehousename;

    private String unit;

    private Double salessum;

    private Double taxprice;

    private Double untaxprice;

    private Double taxrate;

    private String remarks;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSalesid() {
        return salesid;
    }

    public void setSalesid(String salesid) {
        this.salesid = salesid == null ? null : salesid.trim();
    }

    public String getMaterielid() {
        return materielid;
    }

    public void setMaterielid(String materielid) {
        this.materielid = materielid == null ? null : materielid.trim();
    }

    public String getMaterielname() {
        return materielname;
    }

    public void setMaterielname(String materielname) {
        this.materielname = materielname == null ? null : materielname.trim();
    }

    public String getWarehouseid() {
        return warehouseid;
    }

    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid == null ? null : warehouseid.trim();
    }

    public String getWarehousename() {
        return warehousename;
    }

    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename == null ? null : warehousename.trim();
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
        this.remarks = remarks == null ? null : remarks.trim();
    }

}