package com.tianrui.api.intf.quality.sales.batchnum;

import java.util.List;

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
	Result insertBatch(List<SalesBatchnumReq> list) throws Exception;
	/**
	 * 修改数据
	 */
	Result update(SalesBatchnumReq req) throws Exception;
	/**
	 * 分页查询
	 */
	Result page(SalesBatchnumReq req) throws Exception;
	/**
	 * 新增页面预加载数据
	 */
	Result materialData() throws Exception;
}
