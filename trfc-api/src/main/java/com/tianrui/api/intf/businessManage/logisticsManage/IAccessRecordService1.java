package com.tianrui.api.intf.businessManage.logisticsManage;

import com.tianrui.api.req.businessManage.logisticsManage.AccessRecordQuery;
import com.tianrui.api.resp.businessManage.logisticsManage.AccessRecordResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

public interface IAccessRecordService1 {

	PaginationVO<AccessRecordResp> page(AccessRecordQuery query) throws Exception;
	/**
	 * 通过id或编号获取数据
	 */
	Result findOne(AccessRecordQuery query) throws Exception;

}
