package com.tianrui.api.intf.basicFile.other;

import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.api.req.basicFile.other.OtherBdCustomerReq;
public interface IOtherBdCustomerService {
	/**
	 * 获取列表信息
	 */
	Result findCustomerByPage(OtherBdCustomerReq req) throws Exception;
	/**
	 * 新增客户信息
	 */
	Result insertCustomer(OtherBdCustomerReq req) throws Exception;
	/**
	 * 更新客户信息
	 */
	Result updateCustomer(OtherBdCustomerReq req) throws Exception;
	/**
	 * 删除客户信息
	 */
	Result deleteCustomer(OtherBdCustomerReq req) throws Exception;
	/**
	 * 通过id查找客户信息
	 * @param id
	 * @return req
	 * @throws Exception
	 */
	Result findByPrimaryKey(OtherBdCustomerReq req) throws Exception;
	/**
	 * 获取客户编号
	 * @return String
	 */
	String getCustomerCode();
	/**
	 * 获取客户内码编号
	 * @return String
	 */
	String getCustomerInnercode();
	/**
	 * 检测名字是否重复
	 * @param req
	 * @return boolean
	 * @throws Exception
	 */
	Result checkName(OtherBdCustomerReq req) throws Exception;

}
