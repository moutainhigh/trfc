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
	
	Result page(TransportunitManageQuery query) throws Exception;

	Result addTransportunit(TransportunitManageSave save) throws Exception;

	Result updateTransportunit(TransportunitManageSave save) throws Exception;
	
	Result deleteTransportunit(TransportunitManageQuery query);
}
