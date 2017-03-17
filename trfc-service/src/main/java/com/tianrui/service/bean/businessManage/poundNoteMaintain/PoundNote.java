package com.tianrui.service.bean.businessManage.poundNoteMaintain;
/**
 * @Description 磅单实体Bean
 * @author zhanggaohao
 * @version 2017年3月4日 下午3:49:04
 */
public class PoundNote {
	//主键id
    private String id;
	//过磅单号
    private String code;
	//推单状态（0：未推单，1：推单中，2：已推单）
    private String returnstatus;
	//是否红冲（0：否，1：是）
    private String redcollide;
	//单据状态：（0：计量系统，1：补增，2：作废）
    private String status;
	//单据类型（0：采购到货通知单，1：采购退货通知单，销售提货通知单）
    private String billtype;
	//入库单id
    private String putinwarehouseid;
	//入库单编号
    private String putinwarehousecode;
    //订单id
    private String billid;
    //订单code
    private String billcode;
    //订单详情id
    private String billdetailid;
	//通知单id
    private String noticeid;
	//通知单code
    private String noticecode;
    //到货磅单
    private String arrivepoundnotecode;
    //发货单位id
    private String senddepartmentid;
    //发货单位名称
    private String senddepartmentname;
	//司机id
    private String driverid;
	//司机名称
    private String drivername;
	//司机身份证号
    private String driveridentityno;
	//车辆id
    private String vehicleid;
	//车牌号
    private String vehicleno;
	//接收单位id
    private String receivedepartmentid;
	//接收单位名称
    private String receivedepartmentname;
	//矿口id
    private String minemouthid;
	//矿口名称
    private String minemouthname;
	//仓库id
    private String warehouseid;
	//仓库名称
    private String warehousename;
	//堆场id
    private String yardid;
	//堆场名称
    private String yardname;
	//收料人id
    private String receiverpersonid;
	//收料人名称
    private String receiverpersonname;
    //收货时间
    private Long receivertime;
	//预提量
    private Double pickupquantity;
	//毛重
    private Double grossweight;
	//皮重
    private Double tareweight;
	//净重
    private Double netweight;
	//原发净重
    private Double originalnetweight;
	//扣重
    private Double deductionweight;
	//扣杂
    private Double deductionother;
	//销售批号id
    private String batchnumberid;
    //出厂编号
    private String serialnumber;
	//轻车时间
    private Long lighttime;
	//重车时间
    private Long weighttime;
	//制单人id
    private String makerid;
	//制单人名称
    private String makebillname;
	//制单时间
    private Long makebilltime;
	//状态（0：删除，1：正常）
    private String state;
    //创建人
    private String creator;
	//创建时间
    private Long createtime;
	//修改人
    private String modifier;
	//修改时间
    private Long modifytime;
	//备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getReturnstatus() {
        return returnstatus;
    }

    public void setReturnstatus(String returnstatus) {
        this.returnstatus = returnstatus == null ? null : returnstatus.trim();
    }

    public String getRedcollide() {
        return redcollide;
    }

    public void setRedcollide(String redcollide) {
        this.redcollide = redcollide == null ? null : redcollide.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public String getPutinwarehouseid() {
        return putinwarehouseid;
    }

    public void setPutinwarehouseid(String putinwarehouseid) {
        this.putinwarehouseid = putinwarehouseid == null ? null : putinwarehouseid.trim();
    }

    public String getPutinwarehousecode() {
        return putinwarehousecode;
    }

    public void setPutinwarehousecode(String putinwarehousecode) {
        this.putinwarehousecode = putinwarehousecode == null ? null : putinwarehousecode.trim();
    }

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid == null ? null : billid.trim();
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode == null ? null : billcode.trim();
	}

	/**
	 * @return the billdetailid
	 */
	public String getBilldetailid() {
		return billdetailid;
	}

	/**
	 * @param billdetailid the billdetailid to set
	 */
	public void setBilldetailid(String billdetailid) {
		this.billdetailid = billdetailid;
	}

	public String getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(String noticeid) {
        this.noticeid = noticeid == null ? null : noticeid.trim();
    }

    public String getNoticecode() {
        return noticecode;
    }

    public void setNoticecode(String noticecode) {
        this.noticecode = noticecode == null ? null : noticecode.trim();
    }

    /**
	 * @return the arrivepoundnotecode
	 */
	public String getArrivepoundnotecode() {
		return arrivepoundnotecode;
	}

	/**
	 * @param arrivepoundnotecode the arrivepoundnotecode to set
	 */
	public void setArrivepoundnotecode(String arrivepoundnotecode) {
		this.arrivepoundnotecode = arrivepoundnotecode;
	}

	/**
	 * @return the senddepartmentid
	 */
	public String getSenddepartmentid() {
		return senddepartmentid;
	}

	/**
	 * @param senddepartmentid the senddepartmentid to set
	 */
	public void setSenddepartmentid(String senddepartmentid) {
		this.senddepartmentid = senddepartmentid;
	}

	/**
	 * @return the senddepartmentname
	 */
	public String getSenddepartmentname() {
		return senddepartmentname;
	}

	/**
	 * @param senddepartmentname the senddepartmentname to set
	 */
	public void setSenddepartmentname(String senddepartmentname) {
		this.senddepartmentname = senddepartmentname;
	}

	public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername == null ? null : drivername.trim();
    }

    public String getDriveridentityno() {
        return driveridentityno;
    }

    public void setDriveridentityno(String driveridentityno) {
        this.driveridentityno = driveridentityno == null ? null : driveridentityno.trim();
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

    public String getReceivedepartmentid() {
        return receivedepartmentid;
    }

    public void setReceivedepartmentid(String receivedepartmentid) {
        this.receivedepartmentid = receivedepartmentid == null ? null : receivedepartmentid.trim();
    }

    public String getReceivedepartmentname() {
        return receivedepartmentname;
    }

    public void setReceivedepartmentname(String receivedepartmentname) {
        this.receivedepartmentname = receivedepartmentname == null ? null : receivedepartmentname.trim();
    }

    public String getMinemouthid() {
        return minemouthid;
    }

    public void setMinemouthid(String minemouthid) {
        this.minemouthid = minemouthid == null ? null : minemouthid.trim();
    }

    public String getMinemouthname() {
        return minemouthname;
    }

    public void setMinemouthname(String minemouthname) {
        this.minemouthname = minemouthname == null ? null : minemouthname.trim();
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

    public String getYardid() {
        return yardid;
    }

    public void setYardid(String yardid) {
        this.yardid = yardid == null ? null : yardid.trim();
    }

    public String getYardname() {
        return yardname;
    }

    public void setYardname(String yardname) {
        this.yardname = yardname == null ? null : yardname.trim();
    }

    public String getReceiverpersonid() {
        return receiverpersonid;
    }

    public void setReceiverpersonid(String receiverpersonid) {
        this.receiverpersonid = receiverpersonid == null ? null : receiverpersonid.trim();
    }

    public String getReceiverpersonname() {
        return receiverpersonname;
    }

    public void setReceiverpersonname(String receiverpersonname) {
        this.receiverpersonname = receiverpersonname == null ? null : receiverpersonname.trim();
    }

    /**
	 * @return the receivertime
	 */
	public Long getReceivertime() {
		return receivertime;
	}

	/**
	 * @param receivertime the receivertime to set
	 */
	public void setReceivertime(Long receivertime) {
		this.receivertime = receivertime;
	}

	public Double getPickupquantity() {
        return pickupquantity;
    }

    public void setPickupquantity(Double pickupquantity) {
        this.pickupquantity = pickupquantity;
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

    public Double getDeductionweight() {
        return deductionweight;
    }

    public void setDeductionweight(Double deductionweight) {
        this.deductionweight = deductionweight;
    }

    public Double getDeductionother() {
        return deductionother;
    }

    public void setDeductionother(Double deductionother) {
        this.deductionother = deductionother;
    }

    /**
	 * @return the batchnumberid
	 */
	public String getBatchnumberid() {
		return batchnumberid;
	}

	/**
	 * @param batchnumberid the batchnumberid to set
	 */
	public void setBatchnumberid(String batchnumberid) {
		this.batchnumberid = batchnumberid;
	}

	public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
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

    public String getMakerid() {
        return makerid;
    }

    public void setMakerid(String makerid) {
        this.makerid = makerid == null ? null : makerid.trim();
    }

    public String getMakebillname() {
        return makebillname;
    }

    public void setMakebillname(String makebillname) {
        this.makebillname = makebillname == null ? null : makebillname.trim();
    }

    public Long getMakebilltime() {
        return makebilltime;
    }

    public void setMakebilltime(Long makebilltime) {
        this.makebilltime = makebilltime;
    }

    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Long getModifytime() {
        return modifytime;
    }

    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoundNote [id=" + id + ", code=" + code + ", returnstatus=" + returnstatus + ", redcollide="
				+ redcollide + ", status=" + status + ", billtype=" + billtype + ", putinwarehouseid="
				+ putinwarehouseid + ", putinwarehousecode=" + putinwarehousecode + ", billid=" + billid + ", billcode="
				+ billcode + ", billdetailid=" + billdetailid + ", noticeid=" + noticeid + ", noticecode=" + noticecode
				+ ", arrivepoundnotecode=" + arrivepoundnotecode + ", senddepartmentid=" + senddepartmentid
				+ ", senddepartmentname=" + senddepartmentname + ", driverid=" + driverid + ", drivername=" + drivername
				+ ", driveridentityno=" + driveridentityno + ", vehicleid=" + vehicleid + ", vehicleno=" + vehicleno
				+ ", receivedepartmentid=" + receivedepartmentid + ", receivedepartmentname=" + receivedepartmentname
				+ ", minemouthid=" + minemouthid + ", minemouthname=" + minemouthname + ", warehouseid=" + warehouseid
				+ ", warehousename=" + warehousename + ", yardid=" + yardid + ", yardname=" + yardname
				+ ", receiverpersonid=" + receiverpersonid + ", receiverpersonname=" + receiverpersonname
				+ ", receivertime=" + receivertime + ", pickupquantity=" + pickupquantity + ", grossweight="
				+ grossweight + ", tareweight=" + tareweight + ", netweight=" + netweight + ", originalnetweight="
				+ originalnetweight + ", deductionweight=" + deductionweight + ", deductionother=" + deductionother
				+ ", serialnumber=" + serialnumber + ", lighttime=" + lighttime + ", weighttime=" + weighttime
				+ ", makerid=" + makerid + ", makebillname=" + makebillname + ", makebilltime=" + makebilltime
				+ ", state=" + state + ", creator=" + creator + ", createtime=" + createtime + ", modifier=" + modifier
				+ ", modifytime=" + modifytime + ", remark=" + remark + "]";
	}
}