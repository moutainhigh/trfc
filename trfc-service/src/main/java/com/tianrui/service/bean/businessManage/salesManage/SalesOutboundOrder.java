package com.tianrui.service.bean.businessManage.salesManage;

import java.util.List;

/*
 * 销售出库单bean
 */
public class SalesOutboundOrder {
    private String id;
    //单据编号
    private String code;
    //销售订单NC主键
    private String ncId;
    //榜单ID
    private String poundId;
    //单据日期
    private String billdate;
    //销售组织
    private String pkOrg;
    //部门
    private String cdptid;
    //业务员
    private String cbizid;
    //数量
    private String ntotalnum;
    //运输方式
    private String cdilivertypeid;
    //制单人
    private String billmaker;
    //制单日期
    private String dmakedate;
    //出入库类型
    private String ctrantypeid;
    //时间戳
    private String ts;
    //状态 1已推送,0或者空未推送
    private String status;
    //创建时间
    private Long createTime;
    //子表信息
    private List<SalesOutboundOrderItem> list;
    
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

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate == null ? null : billdate.trim();
    }

    public String getPkOrg() {
        return pkOrg;
    }

    public void setPkOrg(String pkOrg) {
        this.pkOrg = pkOrg == null ? null : pkOrg.trim();
    }

    public String getCdptid() {
        return cdptid;
    }

    public void setCdptid(String cdptid) {
        this.cdptid = cdptid == null ? null : cdptid.trim();
    }

    public String getCbizid() {
        return cbizid;
    }

    public void setCbizid(String cbizid) {
        this.cbizid = cbizid == null ? null : cbizid.trim();
    }

    public String getNtotalnum() {
        return ntotalnum;
    }

    public void setNtotalnum(String ntotalnum) {
        this.ntotalnum = ntotalnum == null ? null : ntotalnum.trim();
    }

    public String getCdilivertypeid() {
        return cdilivertypeid;
    }

    public void setCdilivertypeid(String cdilivertypeid) {
        this.cdilivertypeid = cdilivertypeid == null ? null : cdilivertypeid.trim();
    }

    public String getBillmaker() {
        return billmaker;
    }

    public void setBillmaker(String billmaker) {
        this.billmaker = billmaker == null ? null : billmaker.trim();
    }

    public String getDmakedate() {
        return dmakedate;
    }

    public void setDmakedate(String dmakedate) {
        this.dmakedate = dmakedate == null ? null : dmakedate.trim();
    }

    public String getCtrantypeid() {
        return ctrantypeid;
    }

    public void setCtrantypeid(String ctrantypeid) {
        this.ctrantypeid = ctrantypeid == null ? null : ctrantypeid.trim();
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

	public List<SalesOutboundOrderItem> getList() {
		return list;
	}

	public void setList(List<SalesOutboundOrderItem> list) {
		this.list = list;
	}

    public String getPoundId() {
        return poundId;
    }

    public void setPoundId(String poundId) {
        this.poundId = poundId;
    }
    
}