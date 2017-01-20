package com.tianrui.api.intf.basicFile.other;

import com.tianrui.api.req.basicFile.other.OtherBdMaterialReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface IOtherBdMaterialService {
	/**
	 * 其他物料Service接口
	 * @author Sandwang
	 */
	public Result page(OtherBdMaterialReq req) throws Exception;

	public Result addOtherBdMaterial(OtherBdMaterialReq req)throws Exception;
	
	public Result deleteByPrimaryKey(String id) throws Exception;
		
	public Result updateOtherBdMaterial(OtherBdMaterialReq req) throws Exception;
	
	public OtherBdMaterialReq findByPrimaryKey(String id) throws Exception;
	
	String getMaterialCode();
	String getMaterialInnercode();
}
