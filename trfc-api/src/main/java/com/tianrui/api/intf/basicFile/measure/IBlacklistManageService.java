package com.tianrui.api.intf.basicFile.measure;

import com.tianrui.api.req.basicFile.measure.BlacklistManageQuery;
import com.tianrui.api.req.basicFile.measure.BlacklistManageSave;
import com.tianrui.api.resp.basicFile.measure.BlacklistResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 车辆黑名单Service接口
 * @author MaoZhenYu
 * @param <HttpServletRequest>
 * @classname IYardManageService.java
 */
public interface IBlacklistManageService {
	/**
	 * 分页查询数据
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PaginationVO<BlacklistResp> page(BlacklistManageQuery query) throws Exception;
	
	/**
	 * 添加车辆黑名单展示信息
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	Result add(BlacklistManageSave save) throws Exception;
	/**
	 * 删除车辆黑名单信息(物理删除)
	 * @param query
	 * @return
	 */
    Result del(BlacklistManageQuery query);

    int deleteBlacklist(String id);

}