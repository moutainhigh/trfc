package com.tianrui.api.resp.basicFile.businessControl;

import com.tianrui.api.req.BaseReq;
import com.tianrui.smartfactory.common.utils.DateUtil;

/**
 * 采矿点原发设置save
 * @author lenovo
 *
 */
public class MiningpointDbSettingResp extends BaseReq{
    /**
	 * 
	 */
	private static final long serialVersionUID = -365743861550064710L;
	//  
    private String id;
    //  原发编号
    private String code;
    //  采矿点名称
    private String miningpointname;
    //  物料id
    private String materialid;
    //物料名称
    private String materialname;
  //供应商id
    private String supplierid;
	//供应商名称
    private String suppliername;
    //  是否有效：（0：无效，1：有效）
    private String isvalid;
    //  创建人
    private String creator;
    //创建人名称
    private String createname;
    //  创建时间
    private Long createtime;
    //创建时间Str
    private String createtimeStr;
    //  修改人
    private String modifier;
    // 修改时间
    private Long modifytime;
    //  备注
    private String remark;
    //开始时间
    private Long starttime;
	//终止时间
    private Long endtime;
  

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

    public String getMiningpointname() {
        return miningpointname;
    }

    public void setMiningpointname(String miningpointname) {
        this.miningpointname = miningpointname == null ? null : miningpointname.trim();
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid == null ? null : materialid.trim();
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
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
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public String getCreatename() {
		return createname;
	}

	public void setCreatename(String createname) {
		this.createname = createname;
	}

	public String getCreatetimeStr() {
		return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
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

	public String getSupplierid() {
		return supplierid;
	}

	public String getSuppliername() {
		return suppliername;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

    
}