package com.tianrui.api.intf.access;

import com.tianrui.api.req.access.AccessRecordReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface IAccessRecordService {
	/**
	 * 新增数据
	 */
	Result add(AccessRecordReq req);
}
