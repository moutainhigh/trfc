package com.tianrui.service.bean.businessManage.report;

public class ReportPurchase {
    //  
    private String id;

    //  到货单号
    private String billcode;

    //  过磅单号
    private String poundcode;

    //  供应商id
    private String supplierid;

    //  供应商名称
    private String suppliername;
    //矿口id
  	private String minemouthid;

    //  矿口名称
    private String minemouthname;
    //  堆场id
    private String yardid;
    //  堆场名称
    private String yardname;
    //物料id
  	private String materielid;

    //  物料名称
    private String materialname;

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

    //  备注
    private String remark;

    //  签收人名称
    private String signpersonname;

    //  签收时间
    private Long signtime;

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

    
    /**
     * 查询条件
     */
    //供应商名称
    private String supplierNameLike;
    //车辆名称
    private String vehicleNoLike;
    //矿口
    private String minemouthNameLike;
    //司机名称
    private String driverNameLike;
    //开始时间
    private Long beginTimeLong;
    //结束时间
    private Long endTimeLong;
    //物料
    private String cargoNameLike;
    //备注
    private String remarkLike;
    //分页参数
    private Integer start;
    private Integer limit;

  //通知单号
      private String billcodeLike;
  //磅单号
      private String poundcodeLike;

	public String getBillcodeLike() {
		return billcodeLike;
	}

	public void setBillcodeLike(String billcodeLike) {
		this.billcodeLike = billcodeLike;
	}

	public String getPoundcodeLike() {
		return poundcodeLike;
	}

	public void setPoundcodeLike(String poundcodeLike) {
		this.poundcodeLike = poundcodeLike;
	}

	public String getMinemouthid() {
		return minemouthid;
	}

	public void setMinemouthid(String minemouthid) {
		this.minemouthid = minemouthid;
	}

	public String getYardid() {
		return yardid;
	}

	public void setYardid(String yardid) {
		this.yardid = yardid;
	}

	public String getMaterielid() {
		return materielid;
	}

	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode == null ? null : billcode.trim();
    }

    public String getPoundcode() {
        return poundcode;
    }

    public void setPoundcode(String poundcode) {
        this.poundcode = poundcode == null ? null : poundcode.trim();
    }

    public String getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(String supplierid) {
        this.supplierid = supplierid == null ? null : supplierid.trim();
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername == null ? null : suppliername.trim();
    }

    public String getMinemouthname() {
        return minemouthname;
    }

    public void setMinemouthname(String minemouthname) {
        this.minemouthname = minemouthname == null ? null : minemouthname.trim();
    }

    public String getYardname() {
        return yardname;
    }

    public void setYardname(String yardname) {
        this.yardname = yardname == null ? null : yardname.trim();
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname == null ? null : materialname.trim();
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSignpersonname() {
        return signpersonname;
    }

    public void setSignpersonname(String signpersonname) {
        this.signpersonname = signpersonname == null ? null : signpersonname.trim();
    }

    public Long getSigntime() {
        return signtime;
    }

    public void setSigntime(Long signtime) {
        this.signtime = signtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername == null ? null : drivername.trim();
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo == null ? null : cargo.trim();
    }

    public String getReturnstatus() {
        return returnstatus;
    }

    public void setReturnstatus(String returnstatus) {
        this.returnstatus = returnstatus == null ? null : returnstatus.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public String getSupplierNameLike() {
		return supplierNameLike;
	}

	public void setSupplierNameLike(String supplierNameLike) {
		this.supplierNameLike = supplierNameLike;
	}

	public String getVehicleNoLike() {
		return vehicleNoLike;
	}

	public void setVehicleNoLike(String vehicleNoLike) {
		this.vehicleNoLike = vehicleNoLike;
	}

	public String getMinemouthNameLike() {
		return minemouthNameLike;
	}

	public void setMinemouthNameLike(String minemouthNameLike) {
		this.minemouthNameLike = minemouthNameLike;
	}

	public String getDriverNameLike() {
		return driverNameLike;
	}

	public void setDriverNameLike(String driverNameLike) {
		this.driverNameLike = driverNameLike;
	}

	public Long getBeginTimeLong() {
		return beginTimeLong;
	}

	public void setBeginTimeLong(Long beginTimeLong) {
		this.beginTimeLong = beginTimeLong;
	}

	public Long getEndTimeLong() {
		return endTimeLong;
	}

	public void setEndTimeLong(Long endTimeLong) {
		this.endTimeLong = endTimeLong;
	}

	public String getCargoNameLike() {
		return cargoNameLike;
	}

	public void setCargoNameLike(String cargoNameLike) {
		this.cargoNameLike = cargoNameLike;
	}

	public String getRemarkLike() {
		return remarkLike;
	}

	public void setRemarkLike(String remarkLike) {
		this.remarkLike = remarkLike;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
    
    
}