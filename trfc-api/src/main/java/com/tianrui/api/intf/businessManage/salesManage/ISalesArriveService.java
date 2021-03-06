package com.tianrui.api.intf.businessManage.salesManage;

import java.util.List;

import com.tianrui.api.req.businessManage.app.AppNoticeOrderReq;
import com.tianrui.api.req.businessManage.app.AppOrderReq;
import com.tianrui.api.req.businessManage.app.AppOrderSaveReq;
import com.tianrui.api.req.businessManage.logisticsManage.SalesLogisticsQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiSalesArriveQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveSave;
import com.tianrui.api.resp.businessManage.app.AppNoticeOrderResp;
import com.tianrui.api.resp.businessManage.logisticsManage.SalesLogisticsResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 销售提货通知单Service接口
 * @author zhanggaohao
 * @createtime 2017年1月9日 下午2:23:16
 * @classname ISalesArriveService.java
 */
public interface ISalesArriveService {

	/**
	 * 分页查询销售提货通知单
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PaginationVO<SalesArriveResp> page(SalesArriveQuery query) throws Exception;
	/**
	 * 新增销售提货通知单
	 * @param save
	 * @return
	 * @throws Exception 
	 */
	Result add(SalesArriveSave save) throws Exception;
	/**
	 * 查询销售提货通知单
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	SalesArriveResp findOne(String id) throws Exception;
	/**
	 * 修改销售提货通知单
	 * @param save
	 * @return
	 * @throws Exception 
	 */
	Result update(SalesArriveSave save) throws Exception;
	/**
	 * 审核销售提货通知单
	 * @param query
	 * @return
	 */
	Result audit(SalesArriveQuery query);
	/**
	 * 反审销售提货通知单
	 * @param query
	 * @return
	 */
	Result unaudit(SalesArriveQuery query);
	/**
	 * 作废销售提货通知单
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	Result invalid(SalesArriveQuery query) throws Exception;
	/**
	 * 强制出厂销售提货通知单
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	Result outfactory(SalesArriveQuery query) throws Exception;
	/**
	 * 查询提货单详情api接口
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	Result detailApi(ApiSalesArriveQuery query) throws Exception;
	/**
	 * 查询等待出厂的数量
	 * @param query
	 * @return
	 */
	Result selectWaitingNumber(ApiDoorQueueQuery query);
	/**
	 * @Description 根据ids查询销售通知单列表
	 * @author zhanggaohao
	 * @version 2017年2月23日 下午2:54:52
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	List<SalesArriveResp> selectByIds(List<String> ids) throws Exception;
	/**
	 * 更新ic卡号到通知单
	 * @author lxy 
	 * @param save(id,icardid,icardno)
	 * @return
	 * @throws Exception
	 */
	Result updateCardno(SalesArriveSave save) throws Exception;
	/**
	 * @Description app销售通知单分页接口
	 * @author zhanggaohao
	 * @version 2017年4月14日 下午5:02:34
	 * @param req
	 * @return
	 */
	PaginationVO<AppNoticeOrderResp> appToPage(AppNoticeOrderReq req);
	/**
	 * @Description app销售通知单详情
	 * @author zhanggaohao
	 * @version 2017年4月15日 下午5:02:23
	 * @param req
	 * @return
	 */
	Result appToDetail(AppNoticeOrderReq req);
	/**
	 * @Description app销售在厂车辆查询接口
	 * @author zhanggaohao
	 * @version 2017年4月18日 下午1:59:57
	 * @param req
	 * @return
	 */
	Result appInfoFactoryVehicleAndMaterial(AppOrderReq req);
	/**
	 * @Description app销售通知单新增接口
	 * @author zhanggaohao
	 * @version 2017年4月18日 下午2:43:50
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	Result appToAddNotice(AppOrderSaveReq req) throws Exception;
	/**
	 * @Description app销售通知单作废接口
	 * @author zhanggaohao
	 * @version 2017年5月22日 上午9:20:30
	 * @param req
	 * @return
	 */
	Result appInvalid(AppNoticeOrderReq req);
	/**
	 * @Description 销售物流分页
	 * @param query
	 * @return
	 * @exception 
	 * @author zhanggaohao
	 * @version 2017年6月6日 上午10:36:23
	 */
	PaginationVO<SalesLogisticsResp> logisticsPage(SalesLogisticsQuery query);

}
