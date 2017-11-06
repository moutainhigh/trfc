package com.tianrui.api.intf.other.selfsystem;

import com.tianrui.api.req.other.selfsystem.ApiCheckRfidReq;
import com.tianrui.api.req.other.selfsystem.ApiInspectionReportQueryReq;
import com.tianrui.api.req.other.selfsystem.ApiPoundQueryReq;
import com.tianrui.api.req.other.selfsystem.ApiSendCardQueryReq;
import com.tianrui.api.req.other.selfsystem.ApiSendCardReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface ISelfSystemService {
	/**
	 * @annotation 采购发卡查询 只查询采购已经下单还为入厂且不失效的通知单
	 * @param param
	 * @return
	 */
	Result purchaseSendCardQuery(ApiSendCardQueryReq query);

	/**
	 * @annotation 销售发卡查询 只查询销售已经下单还为入厂且不失效的通知单
	 * @param param
	 * @return
	 */
	Result saleSendCardQuery(ApiSendCardQueryReq query);
	/**
	 * @annotation 其他入厂查询 只查询其他入厂已经下单还为入厂且不失效的通知单
	 * @param param
	 * @return
	 */
	Result otherEntrySendCardQuery(ApiSendCardQueryReq query);
	/**
	 * @annotation 其他出厂查询 只查询其他出产已经下单还为入厂且不失效的通知单
	 * @param param
	 * @return
	 */
	Result otherExitSendCardQuery(ApiSendCardQueryReq query);
	/**
	 * 发卡操作
	 * @param query
	 * @return
	 */
	Result sendCard(ApiSendCardReq paran);
	
	/**
	 * 榜单查询
	 * @param query
	 * @return
	 */
	Result poundQuery(ApiPoundQueryReq query);
	
	/**
	 * 质检报告查询  只根据榜单号查询
	 * @param query
	 * @return
	 */
	Result inspectionReportQuery(ApiInspectionReportQueryReq query);
	
	/**
	 * 质检报告打印 只根据榜单号查询
	 * @param query
	 * @return
	 */
	Result inspectionReportPrint(ApiInspectionReportQueryReq query );
	
	/**
	 * 质检报告打印记录查询  只根据榜单号查询
	 * @return
	 */
	Result inspectionReportPrintQuery(ApiInspectionReportQueryReq query);
	
	/**
	 * 验证符合卡是否有效
	 * @param apiCheckRfidReq
	 * @return
	 */
	Result checkCompositecard(ApiCheckRfidReq apiCheckRfidReq );
	
}
