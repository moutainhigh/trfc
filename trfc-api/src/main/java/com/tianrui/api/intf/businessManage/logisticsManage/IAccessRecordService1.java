package com.tianrui.api.intf.businessManage.logisticsManage;

import com.tianrui.api.req.businessManage.logisticsManage.AccessRecordQuery;
import com.tianrui.api.resp.businessManage.logisticsManage.AccessRecordResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

public interface IAccessRecordService1 {

	PaginationVO<AccessRecordResp> page(AccessRecordQuery query) throws Exception;

}
