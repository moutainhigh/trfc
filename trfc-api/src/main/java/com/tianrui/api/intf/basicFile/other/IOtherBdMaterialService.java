package com.tianrui.api.intf.basicFile.other;

import com.tianrui.api.req.basicFile.nc.MaterielManageReq;
import com.tianrui.api.req.basicFile.other.OtherBdMaterialReq;
import com.tianrui.api.resp.basicFile.nc.MaterielManageResp;
import com.tianrui.api.resp.basicFile.other.OtherBdMaterialResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

public interface IOtherBdMaterialService {
	/**
	 * 其他物料Service接口
	 * @author Sandwang
	 */
	public PaginationVO<OtherBdMaterialResp> page(OtherBdMaterialReq req) throws Exception;

	public int addOtherBdMaterial(OtherBdMaterialReq req)throws Exception;
	
	public int deleteByPrimaryKey(String id) throws Exception;
		
	public int updateOtherBdMaterial(OtherBdMaterialReq req) throws Exception;
	
	public OtherBdMaterialReq findByPrimaryKey(String id) throws Exception;
	
	String getMaterialCode();
	String getMaterialInnercode();
}
