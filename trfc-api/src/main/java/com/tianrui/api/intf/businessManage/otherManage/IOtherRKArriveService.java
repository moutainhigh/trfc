/**
 * 
 */
package com.tianrui.api.intf.businessManage.otherManage;

import com.tianrui.api.req.businessManage.otherManage.OtherRKArriveReq;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * @author Administrator
 *
 */
public interface IOtherRKArriveService {
	/**
	 * 更新数据
	 */
	Result update(OtherRKArriveReq req) throws Exception;
	
	/**
	 * 新增数据
	 */
	Result add(OtherRKArriveReq req) throws Exception;
	
	/**
	 * 分页查询
	 */
	Result page(OtherRKArriveReq req) throws Exception;
	
}
