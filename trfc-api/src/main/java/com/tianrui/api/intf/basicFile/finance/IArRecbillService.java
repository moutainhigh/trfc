package com.tianrui.api.intf.basicFile.finance;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.BaseReq;

import com.tianrui.smartfactory.common.vo.Result;
/**
 * 销售收款service接口
 * @author lenovo
 *
 */
public interface IArRecbillService {
	/**
	 * 获取最大时间戳
	 * @param query
	 * @return
	 * @throws Exception
	 */
	Result findMaxUtc(BaseReq query) throws Exception;
	/**
	 * 把数据中心的最新数据更新到本地
	 * @param list
	 * @return
	 */
	Result updateDataWithDC(List<JSONObject> list);
	
	
}
