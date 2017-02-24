package com.tianrui.api.intf.quality.file;

import com.tianrui.api.req.quality.file.SupplierSchemeReq;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 供应商标准方案接口
 * @author Administrator
 *
 */
public interface ISupplierSchemeService {
	
	/**
	 * 删除数据
	 */
	Result delete(SupplierSchemeReq req) throws Exception;
	/**
	 * 更新数据
	 */
	Result update(SupplierSchemeReq req) throws Exception;
	/**
	 * 新增数据
	 */
	Result add(SupplierSchemeReq req) throws Exception;
	/**
	 * 分页查询
	 */
	Result page(SupplierSchemeReq req) throws Exception;
	/**
	 * 获取质量标准详细
	 */
	Result getSchemeData(SupplierSchemeReq req) throws Exception;
	/**
	 * 获取供应商详细
	 */
	Result getSupplierData(SupplierSchemeReq req) throws Exception;
	/**
	 * 根据id查询数据
	 */
	Result selectById(SupplierSchemeReq req) throws Exception;
}
