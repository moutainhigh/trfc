package com.tianrui.api.intf.businessManage.report;

import java.util.List;

import com.tianrui.api.req.businessManage.report.InOutDaoPoundQuery;
import com.tianrui.api.resp.businessManage.report.InOutDaoPoundMaterResp;
import com.tianrui.api.resp.businessManage.report.InOutDaoPoundResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

/**
 * 调拨逐车明细
 * @author lenovo
 *
 */
public interface IAllotPoundService {
	/**
	 * 调拨逐车分页
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PaginationVO<InOutDaoPoundResp> page(InOutDaoPoundQuery query) throws Exception;
	/**
	 * 调拨逐车返回全部
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<InOutDaoPoundResp> list(InOutDaoPoundQuery query) throws Exception;
	/**
	 * 调拨逐车物料调入堆场统计分页
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PaginationVO<InOutDaoPoundMaterResp> materPage(InOutDaoPoundQuery query) throws Exception;
	/**
	 * 调拨逐车物料调入堆场统计返回全部
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<InOutDaoPoundMaterResp> materList(InOutDaoPoundQuery query) throws Exception;
	/**
	 * 调拨逐车物料调出堆场统计分页
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PaginationVO<InOutDaoPoundMaterResp> materDCPage(InOutDaoPoundQuery query) throws Exception;
	/**
	 * 调拨逐车物料调出堆场统计返回全部
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<InOutDaoPoundMaterResp> materDCList(InOutDaoPoundQuery query) throws Exception;
	/**
	 * 调拨逐车物料车号统计分页
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PaginationVO<InOutDaoPoundMaterResp> materVehiclenoPage(InOutDaoPoundQuery query) throws Exception;
	/**
	 * 调拨逐车物料车号统计返回全部
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<InOutDaoPoundMaterResp> materVehiclenoList(InOutDaoPoundQuery query) throws Exception;
}
