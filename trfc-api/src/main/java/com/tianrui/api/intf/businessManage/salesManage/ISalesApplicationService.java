package com.tianrui.api.intf.businessManage.salesManage;

import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationSave;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

public interface ISalesApplicationService {

	PaginationVO<SalesApplicationResp> page(SalesApplicationQuery query) throws Exception;

	Result add(SalesApplicationSave save) throws Exception;

	Result update(SalesApplicationSave save) throws Exception;

	Result audit(SalesApplicationSave save);

	Result unaudit(SalesApplicationSave save);

	Result delete(SalesApplicationQuery query);

	SalesApplicationResp findOne(SalesApplicationQuery query) throws Exception;

}