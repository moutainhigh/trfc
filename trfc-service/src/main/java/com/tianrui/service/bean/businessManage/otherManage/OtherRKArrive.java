package com.tianrui.service.bean.businessManage.otherManage;

/**
 * @author Administrator
 *
 */
public class OtherRKArrive {
	
	/**
     * 主键id
     */
    private String id;

    /**
     * 通知单号
     */
    private String code;

    /**
     * 供应�?
     */
    private String supplierid;

    /**
     * 审核状�??
     */
    private String auditstatus;

    /**
     * 单位(货物来源)
     */
    private String datasource;

    /**
     * 物料
     */
    private String materielid;

    /**
     * 状�??(0：未入厂�?1：一次过磅，2：二次过磅，3：作废，4：发卡，5：出厂，6：入厂，7：装�?)
     */
    private String status;

    /**
     * 货物
     */
    private String cargo;

    /**
     * 收货单位
     */
    private String receivedepartmentid;

    /**
     * 车辆
     */
    private String vehicleid;

    /**
     * 矿口
     */
    private String warehouseid;

    /**
     * 司机
     */
    private String driverid;

    /**
     * 数量
     */
    private Long count;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建�?
     */
    private String creator;

    /**
     * 创建时间
     */
    private Long createtime;

    /**
     * 修改�?
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Long modifytime;

    /**
     * 时间�?
     */
    private Long utc;

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

    public String getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(String supplierid) {
        this.supplierid = supplierid == null ? null : supplierid.trim();
    }

    public String getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(String auditstatus) {
        this.auditstatus = auditstatus == null ? null : auditstatus.trim();
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource == null ? null : datasource.trim();
    }

    public String getMaterielid() {
        return materielid;
    }

    public void setMaterielid(String materielid) {
        this.materielid = materielid == null ? null : materielid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo == null ? null : cargo.trim();
    }

    public String getReceivedepartmentid() {
        return receivedepartmentid;
    }

    public void setReceivedepartmentid(String receivedepartmentid) {
        this.receivedepartmentid = receivedepartmentid == null ? null : receivedepartmentid.trim();
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    public String getWarehouseid() {
        return warehouseid;
    }

    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid == null ? null : warehouseid.trim();
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Long getUtc() {
        return utc;
    }

    public void setUtc(Long utc) {
        this.utc = utc;
    }
}