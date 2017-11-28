package com.tianrui.api.intf.businessManage.financeManage;

import com.tianrui.api.req.businessManage.financeManage.CustomerRemainderQuery;
import com.tianrui.api.req.businessManage.financeManage.CustomerRemainderSave;
import com.tianrui.smartfactory.common.vo.Result;

public interface ICustomerRemainderQueryService {

	/**
	 * 分页查询
	 * xcy 
	 * @return Result
	 * @throws Exception 
	 * @date 2017年11月27日
	 */
	Result pageQuery(CustomerRemainderQuery crQuery) throws Exception;

	/**
	 * 新增客户余额
	 * xcy 
	 * @return Result
	 * @date 2017年11月27日
	 */
	//Result customRemainSave(CustomerRemainderSave crSave);

	/**
	 * 审核一条数据
	 * xcy 
	 * @return Result
	 * @date 2017年11月27日
	 */
	//Result queryById(CustomerRemainderQuery query);

}
