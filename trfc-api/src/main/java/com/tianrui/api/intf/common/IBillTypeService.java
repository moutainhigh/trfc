package com.tianrui.api.intf.common;

import java.util.List;

import com.tianrui.api.req.common.BillTypeQuery;
import com.tianrui.api.resp.common.BillTypeResp;

public interface IBillTypeService {

	List<BillTypeResp> findListByParmas(BillTypeQuery query) throws Exception;
	/**
	 * @Description 根据名称模糊查询
	 * @author zhanggaohao
	 * @version 2017年2月25日 下午5:00:49
	 * @param trim
	 * @return
	 * @throws Exception 
	 */
	List<BillTypeResp> autoCompleteSearch(String trim) throws Exception;

}
