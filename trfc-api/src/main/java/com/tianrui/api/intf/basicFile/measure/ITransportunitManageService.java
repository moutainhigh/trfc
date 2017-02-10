package com.tianrui.api.intf.basicFile.measure;

import com.tianrui.api.req.basicFile.measure.TransportunitManageQuery;
import com.tianrui.api.req.basicFile.measure.TransportunitManageSave;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 运输单位Service接口
 * @author YangZhenFu
 * @createtime 2017年2月5日 上午9:18:34
 * @classname TransportunitManageService.java
 */
public interface ITransportunitManageService {
	/**
	 * 分页查询数据
	 * @param query
	 * @return
	 * @throws Exception
	 */
	Result page(TransportunitManageQuery query) throws Exception;
	/**
	 * 新增运输单位
	 * @param save
	 * @return
	 * @throws Exception
	 */
	Result addTransportunit(TransportunitManageSave save) throws Exception;
	/**
	 * 修改运输单位信息
	 * @param save
	 * @return
	 * @throws Exception
	 */
	Result updateTransportunit(TransportunitManageSave save) throws Exception;
	/**
	 * 删除运输单位信息(假删除)
	 * @param query
	 * @return
	 */
	Result deleteTransportunit(TransportunitManageQuery query);
}
