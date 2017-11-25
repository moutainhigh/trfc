package com.tianrui.api.intf.basicFile.finance;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.smartfactory.common.vo.Result;

public interface ICustomerRemainderService {

	/**
	 * 获取最大时间戳
	 * xcy 
	 * @return Result
	 * @date 2017年11月24日
	 */
	Result findMaxUtc(String orgId);

	/**
	 * 批量更新保存
	 * xcy 
	 * @return Result
	 * @date 2017年11月24日
	 */
	Result updateDataWithDC(List<JSONObject> list);

}
