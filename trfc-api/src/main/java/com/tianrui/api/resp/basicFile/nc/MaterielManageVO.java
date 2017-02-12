package com.tianrui.api.resp.basicFile.nc;

import com.tianrui.api.resp.BaseResp;

/**
 * (id,名称,规格)
 * @author lxy
 *
 */
public class MaterielManageVO extends BaseResp {

	private static final long serialVersionUID = -8179624773990886045L;
	/**
	 * ID
	 */
	private String id;
    /**
     * 名称
     */
    private String name;
 
    /**
     * 规格
     */
    private String spec;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

   
	
	
}
