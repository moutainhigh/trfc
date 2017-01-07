package com.tianrui.api.intf.businessManage.salesManage;

import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationReq;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

public interface ISalesApplicationService {

	PaginationVO<SalesApplicationResp> page(SalesApplicationQuery query) throws Exception;

	Result add(SalesApplicationReq req) throws Exception;

	Result update(SalesApplicationReq req) throws Exception;

	Result audit(String id);

	Result unaudit(String id);

	Result delete(String id);

}