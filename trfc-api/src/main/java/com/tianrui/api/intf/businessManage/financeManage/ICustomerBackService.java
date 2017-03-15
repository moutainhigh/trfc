package com.tianrui.api.intf.businessManage.financeManage;

import com.tianrui.api.req.businessManage.financeManage.CustomerBackQuery;
import com.tianrui.api.req.businessManage.financeManage.CustomerBackSave;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 客户退补Service接口
 * @author YangZhenFu
 *
 */
public interface ICustomerBackService {
	Result page(CustomerBackQuery query) throws Exception;
	
	Result add(CustomerBackSave save) throws Exception;
	
	Result audit(CustomerBackQuery query) throws Exception;
	
	Result delete(CustomerBackQuery query) throws Exception;
}
