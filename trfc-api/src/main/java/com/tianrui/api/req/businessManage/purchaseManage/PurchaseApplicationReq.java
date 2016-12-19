package com.tianrui.api.req.businessManage.purchaseManage;

import com.tianrui.api.req.BaseReq;

public class PurchaseApplicationReq extends BaseReq {

	private static final long serialVersionUID = -1158903230519610412L;

	private String id;

    private String billno;

    private String status;

    private String source;

    private String typecode;
    
    private String typename;

    private String supplierid;

    private String suppliername;
    
    private Double sumnum;

    private Long applytime;

    private String deptid;

    private String deptname;

    private String buyerid;

    private String buyername;

    private String auditid;

    private String auditname;

    private Long audittime;

    private String remark;

    private String creator;

    private Long creatortime;

    private String modifier;

    private Long modifytime;
    
    private Long starttime;
    
    private Long endtime;

    private int start;
    
    private int limit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno == null ? null : billno.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode == null ? null : typecode.trim();
    }
    
    public String getTypename() {
    	return typename;
    }
    
    public void setTypename(String typename) {
    	this.typename = typename == null ? null : typename.trim();
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
    
    public Double getSumnum() {
    	return sumnum;
    }
    
    public void setSumnum(Double sumnum) {
    	this.sumnum = sumnum;
    }

    public Long getApplytime() {
        return applytime;
    }

    public void setApplytime(Long applytime) {
        this.applytime = applytime;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid == null ? null : buyerid.trim();
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername == null ? null : buyername.trim();
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

    public String getAuditname() {
        return auditname;
    }

    public void setAuditname(String auditname) {
        this.auditname = auditname == null ? null : auditname.trim();
    }

    public Long getAudittime() {
        return audittime;
    }

    public void setAudittime(Long audittime) {
        this.audittime = audittime;
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

    public Long getCreatortime() {
        return creatortime;
    }

    public void setCreatortime(Long creatortime) {
        this.creatortime = creatortime;
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

	public Long getStarttime() {
		return starttime;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}