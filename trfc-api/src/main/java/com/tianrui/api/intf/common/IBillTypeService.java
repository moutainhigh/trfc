package com.tianrui.api.intf.common;

import com.tianrui.api.req.common.BillTypeQuery;
import com.tianrui.smartfactory.common.vo.Result;

public interface IBillTypeService {

	Result findListByParmas(BillTypeQuery query) throws Exception;

}
