package com.tianrui.api.intf.businessManage.financeManage;

import com.tianrui.api.req.businessManage.financeManage.CustomerBeginQuery;
import com.tianrui.api.req.businessManage.financeManage.CustomerBeginSave;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 客户期初Service接口
 * @author YangZhenFu
 *
 */
public interface ICustomerBeginService {
	//分页查询
	Result page(CustomerBeginQuery query) throws Exception ;
	//新增客户期初
	Result add(CustomerBeginSave save) throws Exception;
	//审核
	Result audit(CustomerBeginQuery query) throws Exception;
	//删除
	Result delete(CustomerBeginQuery query) throws Exception;
}
