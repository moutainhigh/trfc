package com.tianrui.api.resp.businessManage.purchaseManage;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

public class PurchaseApplicationResp extends BaseResp {

	private static final long serialVersionUID = -1011023268304935996L;

	private String id;

    private String billno;

    private String status;

    private String source;

    private String typecode;
    
    private String typename;

    private String supplierid;

    private String suppliername;
    
    private Double sumnum;

    private String applytimeStr;
    
    private Long applytime;

    private String deptid;

    private String deptname;

    private String buyerid;

    private String buyername;

    private String auditid;

    private String auditname;

    private String audittimeStr;
    
    private Long audittime;

    private String remark;

    private String creator;

    private String creatortimeStr;

    private Long creatortime;

    private String modifier;

    private String modifytimeStr;
    
    private Long modifytime;
    
    private PurchaseApplicationDetailResp detailResp;

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
        this.applytimeStr = DateUtil.parse(applytime, "yyyy-MM-dd HH:mm:ss");
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
        this.audittimeStr = DateUtil.parse(audittime, "yyyy-MM-dd HH:mm:ss");
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
        this.creatortimeStr = DateUtil.parse(creatortime, "yyyy-MM-dd HH:mm:ss");
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
        this.modifytimeStr = DateUtil.parse(modifytime, "yyyy-MM-dd HH:mm:ss");
    }

	public PurchaseApplicationDetailResp getDetailResp() {
		return detailResp;
	}

	public void setDetailResp(PurchaseApplicationDetailResp detailResp) {
		this.detailResp = detailResp;
	}

	public String getApplytimeStr() {
		return applytimeStr;
	}

	public void setApplytimeStr(String applytimeStr) {
		this.applytimeStr = applytimeStr;
	}

	public String getAudittimeStr() {
		return audittimeStr;
	}

	public void setAudittimeStr(String audittimeStr) {
		this.audittimeStr = audittimeStr;
	}

	public String getCreatortimeStr() {
		return creatortimeStr;
	}

	public void setCreatortimeStr(String creatortimeStr) {
		this.creatortimeStr = creatortimeStr;
	}

	public String getModifytimeStr() {
		return modifytimeStr;
	}

	public void setModifytimeStr(String modifytimeStr) {
		this.modifytimeStr = modifytimeStr;
	}

}