package com.tianrui.api.intf.basicFile.nc;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.basicFile.nc.CustomerManageQuery;
import com.tianrui.api.req.basicFile.nc.CustomerManageSave;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.resp.basicFile.nc.CustomerManageResp;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
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
	
	Result updateDataWithDC(List<JSONObject> list )throws Exception;

	List<CustomerManageResp> autoCompleteSearch(String trim) throws Exception;
	/**
	 * @Description 手持机获取客户集合接口
	 * @author zhanggaohao
	 * @version 2017年4月21日 下午2:46:34
	 * @param req
	 * @return
	 */
	List<HandSetReturnResp> handSetQueryAll(HandSetRequestParam req);
	/**
	 * @Description 根据名称编码手机号模糊查询未添加到组的客户
	 * @author zhanggaohao
	 * @version 2017年5月9日 下午3:41:23
	 * @param likeSearch
	 * @return
	 * @throws Exception
	 */
	List<CustomerManageResp> autoCompleteNotGroupSearch(String likeSearch) throws Exception;

}
