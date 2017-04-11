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
	 * @return the storagequantity
	 */
	public Double getStoragequantity() {
		return storagequantity;
	}

	/**
	 * @return the unstoragequantity
	 */
	public Double getUnstoragequantity() {
		return unstoragequantity;
	}

	/**
	 * @return the pretendingtake
	 */
	public Double getPretendingtake() {
		return pretendingtake;
	}

	/**
	 * @param margin the margin to set
	 */
	public void setMargin(Double margin) {
		this.margin = margin;
	}

	/**
	 * @param storagequantity the storagequantity to set
	 */
	public void setStoragequantity(Double storagequantity) {
		this.storagequantity = storagequantity;
	}

	/**
	 * @param unstoragequantity the unstoragequantity to set
	 */
	public void setUnstoragequantity(Double unstoragequantity) {
		this.unstoragequantity = unstoragequantity;
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