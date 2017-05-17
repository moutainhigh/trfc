package com.tianrui.service.bean.businessManage.purchaseManage;

import java.util.List;

/**
 * @Description 采购入库单实体bean
 * @author YangZhenFu
 * @version 2017年3月21日 下午14:45:52
 */
public class PurchaseStorageList {
    private String id;
    //采购入库单编号
    private String code;
    //采购单NC主键
    private String ncId;
    //库存组织
    private String pkOrg;
    //客户
    private String ccustomerid;
    //单据日期
    private String dbilldate;
    //部门
    private String cdptid;
    //数量
    private String ntotalnum;
    //制单人
    private String billmaker;
    //创建时间
    private String creationtime;
    //供应商
    private String cvendorid;
    //时间戳
    private String ts;
    //状态 1已推送,0或者空未推送
    private String status;
    //榜单ID
    private String poundId;
    //子表信息
    private List<PurchaseStorageListItem> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getNcId() {
        return ncId;
    }

    public void setNcId(String ncId) {
        this.ncId = ncId == null ? null : ncId.trim();
    }

    public String getPkOrg() {
        return pkOrg;
    }

    public void setPkOrg(String pkOrg) {
        this.pkOrg = pkOrg == null ? null : pkOrg.trim();
    }

    public String getCcustomerid() {
        return ccustomerid;
    }

    public void setCcustomerid(String ccustomerid) {
        this.ccustomerid = ccustomerid == null ? null : ccustomerid.trim();
    }

    public String getDbilldate() {
        return dbilldate;
    }

    public void setDbilldate(String dbilldate) {
        this.dbilldate = dbilldate == null ? null : dbilldate.trim();
    }

    public String getCdptid() {
        return cdptid;
    }

    public void setCdptid(String cdptid) {
        this.cdptid = cdptid == null ? null : cdptid.trim();
    }

    public String getNtotalnum() {
        return ntotalnum;
    }

    public void setNtotalnum(String ntotalnum) {
        this.ntotalnum = ntotalnum == null ? null : ntotalnum.trim();
    }

    public String getBillmaker() {
        return billmaker;
    }

    public void setBillmaker(String billmaker) {
        this.billmaker = billmaker == null ? null : billmaker.trim();
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime == null ? null : creationtime.trim();
    }

    public String getCvendorid() {
        return cvendorid;
    }

    public void setCvendorid(String cvendorid) {
        this.cvendorid = cvendorid == null ? null : cvendorid.trim();
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts == null ? null : ts.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public List<PurchaseStorageListItem> getList() {
		return list;
	}

	public void setList(List<PurchaseStorageListItem> list) {
		this.list = list;
	}

	public String getPoundId() {
		return poundId;
	}

	public void setPoundId(String poundId) {
		this.poundId = poundId;
	}
	
    
}