package com.tianrui.api.intf.businessManage.financeManage;

import com.tianrui.api.req.businessManage.financeManage.CustomerBeginQuery;
import com.tianrui.api.req.businessManage.financeManage.CustomerBeginSave;
import com.tianrui.smartfactory.common.vo.Result;

public interface ICustomerBeginService {
	Result page(CustomerBeginQuery query) throws Exception ;
	
	Result add(CustomerBeginSave save) throws Exception;
	
	Result audit(CustomerBeginQuery query) throws Exception;
	
	Result delete(CustomerBeginQuery query) throws Exception;
}
