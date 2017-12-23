package com.tianrui.api.req.basicFile.businessControl;

import com.tianrui.api.req.BaseReq;
/**
 * 采矿点原发设置查询条件
 * @author lenovo
 *
 */
public class MiningpointDbSettingQuery extends BaseReq{
    
	private static final long serialVersionUID = 3605262341908705171L;

	//  
    private String id;

    //  原发编号
    private String code;

    //  采矿点名称
    private String miningpointname;

    //  物料id
    private String materialid;
    //开始时间
    private Long starttime;
    //终止时间
    private Long endtime;
    //是否有效（0：无效，1：有效）
    private String isvalid = "1";
    //页码
    private Integer start;
    //条数
    private Integer limit;

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