/**
 * 
 */
package com.tianrui.api.intf.businessManage.otherManage;

import com.tianrui.api.req.businessManage.otherManage.AppOtherArriveReq;
import com.tianrui.api.req.businessManage.otherManage.OtherArriveReq;
import com.tianrui.api.resp.businessManage.otherManage.AppOtherArriveResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * @author Administrator
 *
 */
public interface IOtherArriveService {
	/**
	 * 更新数据
	 */
	Result update(OtherArriveReq req) throws Exception;
	
	/**
	 * 新增数据
	 */
	Result add(OtherArriveReq req) throws Exception;
	
	/**
	 * 分页查询
	 */
	Result page(OtherArriveReq req) throws Exception;
	/**
	 * id查询数据
	 */
	Result findOne(String id) throws Exception;
	/**
	 * 更新状态
	 */
	Result updateOperation(OtherArriveReq req) throws Exception;

    PaginationVO<AppOtherArriveResp> appPage(AppOtherArriveReq req) throws Exception;
    /**
     * @annotation 强制出厂
     * @param req
     * @return
     */
    Result forceOutFactory(OtherArriveReq req);
}
