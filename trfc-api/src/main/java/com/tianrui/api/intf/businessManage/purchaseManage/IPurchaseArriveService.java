package com.tianrui.api.intf.businessManage.purchaseManage;

import java.util.List;

import com.tianrui.api.req.businessManage.app.AppNoticeOrderReq;
import com.tianrui.api.req.businessManage.app.AppOrderReq;
import com.tianrui.api.req.businessManage.app.AppOrderSaveReq;
import com.tianrui.api.req.businessManage.logisticsManage.PurchaseLogisticsQuery;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveQuery;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveSave;
import com.tianrui.api.resp.businessManage.app.AppNoticeOrderResp;
import com.tianrui.api.resp.businessManage.logisticsManage.PurchaseLogisticsResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * @Description 采购通知单Service接口
 * @author zhanggaohao
 * @version 2017年2月17日 上午9:00:07
 */
public interface IPurchaseArriveService {
	/**
	 * @Description 分页查询
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:58:51
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PaginationVO<PurchaseArriveResp> page(PurchaseArriveQuery query) throws Exception;
	/**
	 * @Description 更新通知单
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:59:02
	 * @param update
	 * @return
	 * @throws Exception
	 */
	Result updateOperation(PurchaseArriveSave update) throws Exception;
	/**
	 * @Description 根据id查询通知单
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:59:16
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PurchaseArriveResp findOne(String id) throws Exception;
	/**
	 * @Description 新增到货通知单
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:59:54
	 * @param save
	 * @return
	 * @throws Exception
	 */
	Result add(PurchaseArriveSave save) throws Exception;
	/**
	 * @Description 修改到货通知单
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午9:07:32
	 * @param update
	 * @return
	 * @throws Exception 
	 */
	Result update(PurchaseArriveSave update) throws Exception;
	/**
	 * @Description 根据ids获取采购到货通知单列表
	 * @author zhanggaohao
	 * @version 2017年2月23日 下午3:12:02
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	List<PurchaseArriveResp> selectByIds(List<String> ids) throws Exception;
	/**
	 * @Description 新增退货通知单
	 * @author zhanggaohao
	 * @version 2017年4月1日 下午4:17:10
	 * @param save
	 * @return
	 * @throws Exception 
	 */
	Result returnAdd(PurchaseArriveSave save) throws Exception;
	/**
	 * @Description 删除退货通知单
	 * @author zhanggaohao
	 * @version 2017年4月3日 上午10:20:39
	 * @param query
	 * @return
	 */
	Result delete(PurchaseArriveQuery query);
	/**
	 * @Description app采购通知单分页接口
	 * @author zhanggaohao
	 * @version 2017年4月14日 下午5:02:11
	 * @param req
	 * @return
	 */
	PaginationVO<AppNoticeOrderResp> appToPage(AppNoticeOrderReq req);
	/**
	 * @Description app采购通知单详情接口
	 * @author zhanggaohao
	 * @version 2017年4月15日 下午5:02:06
	 * @param req
	 * @return
	 */
	Result appToDetail(AppNoticeOrderReq req);
	/**
	 * @Description app采购在厂车辆查询接口
	 * @author zhanggaohao
	 * @version 2017年4月18日 下午1:59:21
	 * @param req
	 * @return
	 */
	Result appInfoFactoryVehicleAndMaterial(AppOrderReq req);
	/**
	 * @Description app采购通知单新增接口
	 * @author zhanggaohao
	 * @version 2017年4月18日 下午2:43:19
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	Result appToAddNotice(AppOrderSaveReq req) throws Exception;
	/**
	 * @Description app采购通知单作废接口
	 * @author zhanggaohao
	 * @version 2017年5月22日 上午9:15:58
	 * @param req
	 * @return
	 */
	Result appInvalid(AppNoticeOrderReq req);
	/**
	 * @Description 采购物流分页
	 * @param query
	 * @return
	 * @exception 
	 * @author zhanggaohao
	 * @version 2017年6月6日 上午9:18:43
	 */
	PaginationVO<PurchaseLogisticsResp> logisticsPage(PurchaseLogisticsQuery query);
	/**
	 * @annotation 强制出厂
	 * @param update
	 * @return
	 * @throws Exception 
	 */
    Result outfactory(PurchaseArriveSave update) throws Exception;
    /**
     * @annotation 作废
     * @param update
     * @return
     */
	Result invalid(PurchaseArriveSave update);

}
