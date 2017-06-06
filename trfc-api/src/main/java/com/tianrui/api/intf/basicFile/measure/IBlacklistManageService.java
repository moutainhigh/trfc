package com.tianrui.api.intf.basicFile.measure;

import java.util.List;


import com.tianrui.api.req.basicFile.measure.BlacklistManageQuery;
import com.tianrui.api.req.basicFile.measure.BlacklistManageReq;
import com.tianrui.api.req.basicFile.measure.BlacklistManageSave;
import com.tianrui.api.req.basicFile.measure.YardManageQuery;
import com.tianrui.api.resp.basicFile.measure.BlacklistManageResp;
import com.tianrui.api.resp.basicFile.measure.YardManageResp;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 车辆黑名单Service接口
 * @author MaoZhenYu
 * @param <HttpServletRequest>
 * @classname IYardManageService.java
 */
public interface IBlacklistManageService {
	/**
	 * 删除黑名单信息
	 * @param query
	 * @return
	 */
	int deleteBlacklist(String id);
	/**
	 * 添加黑名单信息
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	int addBlacklist(BlacklistManageReq req) throws Exception;
	/**
	 * 分页查询数据
	 * @param query
	 * @return
	 * @throws Exception
	 */
	Result page(BlacklistManageQuery query) throws Exception;
	
	/**
	 * 添加车辆黑名单展示信息
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	Result addCarBlacklist(BlacklistManageSave save) throws Exception;
	/**
	 * 删除车辆黑名单信息(物理删除)
	 * @param query
	 * @return
	 */
	Result deleteblacklist(BlacklistManageQuery query);
	
	/**
	 * 添加车辆黑名单信息
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	Result add(BlacklistManageSave save) throws Exception;
	
}