package com.tianrui.api.intf.quality.sales.batchnum;


import com.tianrui.api.req.quality.sales.batchnum.SalesBatchnumReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface ISalesBatchnumService {
	/**
	 * 删除数据
	 */
	Result delete(SalesBatchnumReq req) throws Exception;
	/**
	 * 新增数据
	 */
	Result insertBatch(SalesBatchnumReq req) throws Exception;
	/**
	 * 修改数据
	 */
	Result update(SalesBatchnumReq req) throws Exception;
	/**
	 * 分页查询
	 */
	Result page(SalesBatchnumReq req) throws Exception;

	/**
	 * 通过id获取数据
	 */
	Result selectById(SalesBatchnumReq req) throws Exception;
	/**
	 * 检测批号是否重复
	 */
	Result checkFactoryCode(SalesBatchnumReq req) throws Exception;
	/**
	 * 获取化验人下拉框数据
	 */
	Result assayerData() throws Exception;
	}
