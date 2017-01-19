package com.tianrui.api.resp.businessManage.salesManage;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.resp.basicFile.nc.CustomerManageResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

public class SalesApplicationResp extends BaseResp {
	
	private static final long serialVersionUID = -2559538852082320742L;

	private String id;

    private String code;

    private String status;

    private String source;

    private String billtype;
    
    private String billtypename;

    private String customerid;

    private Long billtime;
    
    private String billtimeStr;

    private String orgid;

    private String orgname;

    private String departmentid;

    private String departmentname;

    private String auditid;

    private String auditname;

    private Long audittime;
    
    private String audittimeStr;

    private String state;

    private String remarks;

    private String creator;
    
    private String creatorname;

    private Long createtime;
    
    private String createtimeStr;

    private String modifier;
    
    private String modifiername;

    private Long modifytime;
    
    private String modifytimeStr;
    
    private CustomerManageResp customerManageResp;
    
    private SalesApplicationDetailResp detailResp;

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

    public String getBilltype() {
        return billtype;
    }

    public String getBilltypename() {
		return billtypename;
	}

	public void setBilltypename(String billtypename) {
		this.billtypename = billtypename;
	}

	public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public Long getBilltime() {
        return billtime;
    }

    public void setBilltime(Long billtime) {
        this.billtime = billtime;
        this.billtimeStr = DateUtil.parse(billtime, "yyyy-MM-dd HH:mm:ss");
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname == null ? null : departmentname.trim();
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

    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
        this.createtimeStr = DateUtil.parse(createtime, "yyyy-MM-dd HH:mm:ss");
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

	public CustomerManageResp getCustomerManageResp() {
		return customerManageResp;
	}

	public void setCustomerManageResp(CustomerManageResp customerManageResp) {
		this.customerManageResp = customerManageResp;
	}

	public SalesApplicationDetailResp getDetailResp() {
		return detailResp;
	}

	public void setDetailResp(SalesApplicationDetailResp detailResp) {
		this.detailResp = detailResp;
	}

	public String getBilltimeStr() {
		return billtimeStr;
	}

	public void setBilltimeStr(String billtimeStr) {
		this.billtimeStr = billtimeStr;
	}

	public String getAudittimeStr() {
		return audittimeStr;
	}

	public void setAudittimeStr(String audittimeStr) {
		this.audittimeStr = audittimeStr;
	}

	public String getCreatetimeStr() {
		return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}

	public String getModifytimeStr() {
		return modifytimeStr;
	}

	public void setModifytimeStr(String modifytimeStr) {
		this.modifytimeStr = modifytimeStr;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}

	public String getModifiername() {
		return modifiername;
	}

	public void setModifiername(String modifiername) {
		this.modifiername = modifiername;
	}

}