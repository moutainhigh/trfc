package com.tianrui.api.intf.basicFile.other;

import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.api.req.basicFile.other.OtherBdCustomerReq;
import com.tianrui.api.resp.basicFile.other.OtherBdCustomerResp;
public interface IOtherBdCustomerService {
	
	PaginationVO<OtherBdCustomerResp> findCustomerByPage(OtherBdCustomerReq req) throws Exception;
	boolean insertCustomer(OtherBdCustomerReq req) throws Exception;
	boolean updateCustomer(OtherBdCustomerReq req) throws Exception;
	boolean deleteCustomer(String id) throws Exception;
	OtherBdCustomerReq findByPrimaryKey(String id) throws Exception;
	String getCustomerCode();
	String getCustomerInnercode();
	Boolean checkName(String name) throws Exception;
	
}
