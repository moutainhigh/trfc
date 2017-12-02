package com.tianrui.api.intf.businessManage.report;

import java.util.List;

import com.tianrui.api.req.businessManage.report.InOutDaoPoundQuery;
import com.tianrui.api.resp.businessManage.report.InOutDaoPoundResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

/**
 * 其他入库逐车明细
 * @author lenovo
 *
 */
public interface IAllotPoundService {
	PaginationVO<InOutDaoPoundResp> page(InOutDaoPoundQuery query) throws Exception;
	List<InOutDaoPoundResp> list(InOutDaoPoundQuery query) throws Exception;
}
