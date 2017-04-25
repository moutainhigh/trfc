package com.tianrui.api.resp.businessManage.app;

import com.tianrui.api.resp.BaseResp;
/**
 * @Description app物料Vo
 * @author zhanggaohao
 * @version 2017年4月25日 上午9:42:48
 */
public class AppMaterialResp extends BaseResp{

	private static final long serialVersionUID = 280565475561660076L;
	//主键id
    private String id;
    //名称
    private String name;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
