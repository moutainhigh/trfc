package com.tianrui.api.intf.businessManage.salesManage;

import java.util.List;

import com.tianrui.api.req.businessManage.salesManage.SalesApplicationDetailQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationDetailSave;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.smartfactory.common.vo.Result;

public interface ISalesApplicationDetailService {

	List<SalesApplicationDetailResp> findListBySalesApplicationId(SalesApplicationDetailQuery query) throws Exception;

	Result add(SalesApplicationDetailSave save) throws Exception;

	Result update(SalesApplicationDetailSave save) throws Exception;

	SalesApplicationDetailResp findOne(String id) throws Exception;

	List<SalesApplicationDetailResp> selectBySalesIds(List<String> salesIds) throws Exception;

	List<SalesApplicationDetailResp> selectByIds(List<String> ids) throws Exception;

}
