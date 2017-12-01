package com.tianrui.api.intf.api.android.imple;

import com.tianrui.api.req.android.BillListParam;
import com.tianrui.api.req.android.BillSave;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.android.LoginUserParam;
import com.tianrui.api.req.android.MyPnListParam;
import com.tianrui.api.req.android.MyVehicleListParam;
import com.tianrui.api.req.android.NoticeListParam;
import com.tianrui.api.req.android.NoticeSave;
import com.tianrui.api.req.android.SearchKeyParam;
import com.tianrui.smartfactory.common.vo.AppResult;

public interface IAppSalesStaticService {
	/**
	 * @annotation 客户首页数据
	 * @param param
	 * @return
	 */
	AppResult home(HomePageParam param);
	/**
	 * @annotation 销售订单集合
	 * @param param
	 * @return
	 */
	AppResult billList(BillListParam param);
	/**
	 * @annotation 销售订单详情
	 * @param param
	 * @return
	 */
	AppResult billDetail(BillListParam param);
	/**
	 * @annotation 一单一车下单
	 * @param param
	 * @return
	 * @throws Exception
	 */
	AppResult saveBill(BillSave param) throws Exception;
	/**
	 * @annotation 订单删除（一单一车）
	 * @param param
	 * @return
	 * @throws Exception
	 */
	AppResult billDelete(BillListParam param) throws Exception;
	/**
	 * @annotation 派单
	 * @param param
	 * @return
	 * @throws Exception
	 */
	AppResult saveNotice(NoticeSave param) throws Exception;
	/**
	 * @annotation 一单多车派单列表
	 * @param param
	 * @return
	 */
	AppResult listMoreBill(BillListParam param);
	/**
	 * @annotation 一单多车派单
	 * @param param
	 * @return
	 * @throws Exception
	 */
	AppResult moreSendCar(NoticeSave param) throws Exception;
	/**
	 * @annotation 通知单列表
	 * @param param
	 * @return
	 */
	AppResult noticeList(NoticeListParam param);
	/**
	 * @annotation 通知单详情
	 * @param param
	 * @return
	 */
	AppResult noticeDetail(NoticeListParam param);
	/**
	 * @annotation 通知单修改
	 * @param param
	 * @return
	 * @throws Exception
	 */
	AppResult noticeUpdate(NoticeSave param) throws Exception;
	/**
	 * @annotation 通知单作废
	 * @param param
	 * @return
	 * @throws Exception
	 */
	AppResult noticeCancel(NoticeListParam param) throws Exception;
	/**
	 * @annotation 通知单作废
	 * @param param
	 * @return
	 */
	AppResult myVehicle(MyVehicleListParam param);
	/**
	 * @annotation 我的榜单
	 * @param param
	 * @return
	 */
	AppResult myPn(MyPnListParam param);
	/**
	 * @annotation 榜单详情
	 * @param param
	 * @return
	 */
	AppResult myPnDetail(MyPnListParam param);
	/**
	 * @annotation 客户切换列表
	 * @param param
	 * @return
	 */
	AppResult customerGroupUser(LoginUserParam param);
	/**
	 * 查询仓库id和名称
	 * @param body
	 * @return
	 */
	AppResult queryWarehouse(SearchKeyParam param);

}
