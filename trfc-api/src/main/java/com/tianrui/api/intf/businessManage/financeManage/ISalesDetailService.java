package com.tianrui.api.intf.businessManage.financeManage;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.BaseReq;
import com.tianrui.api.req.businessManage.financeManage.SalesDetailQuery;
import com.tianrui.api.req.businessManage.financeManage.SalesDetailSave;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 销售明细Service接口
 * @author YangZhenFu
 *
 */
public interface ISalesDetailService {
	
	Result page(SalesDetailQuery query) throws Exception;
	
	Result add(SalesDetailSave save) throws Exception;
	/**
	 * 查询最大时间戳
	 * @param query
	 * @return
	 * @throws Exception
	 */
	Result findMaxUtc(BaseReq query)throws Exception;
	
	/**
	 * 把数据中心的最新数据更新到本地
	 * @param list
	 * @return
	 */
	Result updateDataWithDC(List<JSONObject> list);
}
