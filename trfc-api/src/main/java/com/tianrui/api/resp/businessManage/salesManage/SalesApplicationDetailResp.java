package com.tianrui.api.resp.businessManage.salesManage;

import com.tianrui.api.resp.BaseResp;

public class SalesApplicationDetailResp extends BaseResp {

	private static final long serialVersionUID = -1813964842063776104L;

    //主键id
    private String id="";
    //销售申请单id
    private String salesid="";
    //物料id
    private String materielid="";
    //物料名称
    private String materielname="";
    //仓库id
    private String warehouseid="";
    //仓库名称
    private String warehousename="";
    //单位 default:吨
    private String unit="";
    //数量
    private Double salessum=0d;
    //余量
    private Double margin=0d;
    //出库占用量
    private Double storagequantity=0d;
    //未出库占用量
    private Double unstoragequantity=0d;
    //预提占用
    private Double pretendingtake=0d;
    //含税单价
    private  Double taxprice=0d;
    //不含税单价
    private Double untaxprice=0d;
    //税率
    private Double taxrate=0d;
    //备注
    private String remarks="";
    //TODO为出库数量 折扣额  发货仓库
    //为出库占用 预提占用 余量
    
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

    /**
	 * @return the margin
	 */
	public Double getMargin() {
		return margin;
	}

	/**
	 * @param margin the margin to set
	 */
	public void setMargin(Double margin) {
		this.margin = margin;
	}

	/**
	 * @return the storagequantity
	 */
	public Double getStoragequantity() {
		return storagequantity;
	}

	/**
	 * @param storagequantity the storagequantity to set
	 */
	public void setStoragequantity(Double storagequantity) {
		this.storagequantity = storagequantity;
	}

	/**
	 * @return the unstoragequantity
	 */
	public Double getUnstoragequantity() {
		return unstoragequantity;
	}

	/**
	 * @param unstoragequantity the unstoragequantity to set
	 */
	public void setUnstoragequantity(Double unstoragequantity) {
		this.unstoragequantity = unstoragequantity;
	}

	/**
	 * @return the pretendingtake
	 */
	public Double getPretendingtake() {
		return pretendingtake;
	}

	/**
	 * @param pretendingtake the pretendingtake to set
	 */
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
        this.remarks = remarks == null ? null : remarks.trim();
    }

}