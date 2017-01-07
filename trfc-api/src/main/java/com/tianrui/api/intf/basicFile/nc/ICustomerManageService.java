package com.tianrui.api.intf.basicFile.nc;

import java.util.List;

import com.tianrui.api.req.basicFile.nc.CustomerManageReq;
import com.tianrui.api.resp.basicFile.nc.CustomerManageResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

/**
 * 客户管理Service接口
 * @author zhanggaohao
 * @createtime 2016年12月27日 下午3:53:09
 * @classname ICustomerManageService.java
 */
public interface ICustomerManageService {

	PaginationVO<CustomerManageResp> page(CustomerManageReq req) throws Exception;

	int updateCustomer(CustomerManageReq req) throws Exception;

	List<CustomerManageResp> selectSelective(CustomerManageReq req) throws Exception;

	List<CustomerManageResp> findAll() throws Exception;

}
