package com.tianrui.api.intf.quality.file;

import com.tianrui.api.req.quality.file.MaterialSchemeReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface IMaterialSchemeService {
	
	/**
	 * 删除数据
	 */
	Result delete(MaterialSchemeReq req) throws Exception;
	/**
	 * 新增数据
	 */
	Result add(MaterialSchemeReq req) throws Exception;
	/**
	 * 更新数据
	 */
	Result update(MaterialSchemeReq req) throws Exception;
	/**
	 * 分页查询
	 */
	Result page(MaterialSchemeReq req) throws Exception;
	
	/**
	 * 检测物料品种是否重复
	 */
	Result checkMaterialType(MaterialSchemeReq req) throws Exception;
}
