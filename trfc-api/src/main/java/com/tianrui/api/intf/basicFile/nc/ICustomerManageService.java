package com.tianrui.api.intf.basicFile.nc;

import com.tianrui.api.req.basicFile.nc.CustomerManageQuery;
import com.tianrui.api.req.basicFile.nc.CustomerManageSave;
import com.tianrui.api.resp.basicFile.nc.CustomerManageResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 客户管理Service接口
 * @author zhanggaohao
 * @createtime 2016年12月27日 下午3:53:09
 * @classname ICustomerManageService.java
 */
public interface ICustomerManageService {

	PaginationVO<CustomerManageResp> page(CustomerManageQuery query) throws Exception;

	Result updateCustomer(CustomerManageSave req) throws Exception;

	Result findListByParmas(CustomerManageQuery query) throws Exception;
	
	Result findMaxUtc(CustomerManageQuery query) throws Exception;

	CustomerManageResp findOne(CustomerManageQuery query) throws Exception;

}
