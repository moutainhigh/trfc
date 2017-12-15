/**
 * 
 */
package com.tianrui.api.intf.businessManage.otherManage;

import com.tianrui.api.req.businessManage.otherManage.OtherArriveReq;
import com.tianrui.api.resp.businessManage.otherManage.OtherArriveResp;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * @author Administrator
 *
 */
public interface IOtherDYArriveService {
	
	/**
	 * 新增数据
	 */
	Result add(OtherArriveReq req) throws Exception;

	OtherArriveResp getById(String id) throws Exception;
	
}
