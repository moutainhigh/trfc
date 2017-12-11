/**
 * 
 */
package com.tianrui.api.intf.businessManage.otherManage;

import com.tianrui.api.req.businessManage.otherManage.OtherArriveReq;
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
	
	
}
