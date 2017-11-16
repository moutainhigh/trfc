package com.tianrui.service.mapper.businessManage.purchaseManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.businessManage.app.AppNoticeOrderReq;
import com.tianrui.api.req.businessManage.app.AppOrderReq;
import com.tianrui.api.req.businessManage.logisticsManage.PurchaseLogisticsQuery;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveQuery;
import com.tianrui.api.resp.android.HomeNoticeVo;
import com.tianrui.api.resp.businessManage.app.AppNoticeOrderResp;
import com.tianrui.api.resp.businessManage.app.AppVehicleInFactoryResp;
import com.tianrui.api.resp.businessManage.logisticsManage.PurchaseLogisticsResp;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;

public interface PurchaseArriveMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(PurchaseArrive record);

    int insertSelective(PurchaseArrive record);

    PurchaseArrive selectByPrimaryKey(String id);
    PurchaseArrive selectByCode(String code);

    int updateByPrimaryKeySelective(PurchaseArrive record);

    int updateByPrimaryKey(PurchaseArrive record);
    /**
     * @Description 分页查询总条数
     * @author zhanggaohao
     * @version 2017年2月17日 上午8:55:32
     * @param record
     * @return
     */
    long findPurchaseArrivePageCount(PurchaseArriveQuery record);
    /**
     * 
     * @Description 分页查询
     * @author zhanggaohao
     * @version 2017年2月17日 上午8:55:48
     * @param record
     * @return
     */
    List<PurchaseArrive> findPurchaseArrivePage(PurchaseArriveQuery record);
	/**
	 * 
	 * @Description 检查车辆和司机是否已有正在执行的通知单
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:56:02
	 * @param bean
	 * @return
	 */
	List<PurchaseArrive> checkDriverAndVehicleIsUse(PurchaseArrive bean);
	/**
	 * @Description 根据ids查询采购到货通知单
	 * @author zhanggaohao
	 * @version 2017年2月23日 下午3:18:25
	 * @param list
	 * @return
	 */
	List<PurchaseArrive> selectByIds(List<String> list);
	/**
	 * @Description 验证是否有采购通知单
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午10:00:17
	 * @param vehicleno
	 * @return
	 */
	PurchaseArrive hasPurchaseArrive(String vehicleno);
	/**
	 * @Description 根据条件查询采购通知单
	 * @author zhanggaohao
	 * @version 2017年3月24日 下午2:07:38
	 * @param pa
	 * @return
	 */
	List<PurchaseArrive> selectSelective(PurchaseArrive pa);
    /**
     * @Description 通过IC卡id查询通知单
     * @author zhanggaohao
     * @version 2017年3月28日 下午1:57:17
     * @param icardid
     * @return
     */
	PurchaseArrive checkICUse(String icardid);
	/**
	 * @Description 根据车辆查询通知单
	 * @author zhanggaohao
	 * @version 2017年4月7日 下午2:41:57
	 * @param vehicleno
	 * @param vehiclerfid
	 * @return
	 */
	List<PurchaseArrive> validNoticeByVehicle(@Param("vehicleno")String vehicleno, @Param("vehiclerfid")String vehiclerfid);
	/**
	 * @Description app采购通知单分页接口
	 * @author zhanggaohao
	 * @version 2017年4月15日 上午11:26:16
	 * @param req
	 * @return
	 */
	long findAppNoticePageCount(AppNoticeOrderReq req);
	/**
	 * @Description app采购通知单分页接口
	 * @author zhanggaohao
	 * @version 2017年4月15日 上午11:26:16
	 * @param req
	 * @return
	 */
	List<AppNoticeOrderResp> findAppNoticePage(AppNoticeOrderReq req);
	/**
	 * @Description app采购在厂车辆查询接口
	 * @author zhanggaohao
	 * @version 2017年4月18日 下午2:14:29
	 * @param req
	 * @return
	 */
	List<AppVehicleInFactoryResp> appInfoFactoryVehicleAndMaterial(AppOrderReq req);
	/**
	 * @Description 采购物流分页
	 * @param query
	 * @return
	 * @exception 
	 * @author zhanggaohao
	 * @version 2017年6月6日 上午9:16:51
	 */
	List<PurchaseLogisticsResp> selectLogisticsPage(PurchaseLogisticsQuery query);
	/**
	 * @Description 采购物料分页总条数
	 * @param query
	 * @return
	 * @exception 
	 * @author zhanggaohao
	 * @version 2017年6月6日 上午9:16:40
	 */
	long selectLogisticsPageCount(PurchaseLogisticsQuery query);
	/**
	 * @annotation 根据车辆ID查找在厂通知单
	 * @param vehicleId
	 * @return
	 */
	PurchaseArrive getByVehicleId(String vehicleId);
	/**
	 * @annotation 根据车辆把上次强制出厂的单据改为出厂
	 * @param vehicleId
	 * @return
	 */
	void emptyForceOutFactoryByVehicle(String vehicleId);
	/**
	 * @annotation app首页通知单
	 * @param param
	 * @return
	 */
	List<HomeNoticeVo> appHomeNotice(HomePageParam param);
	/**
	 * @annotation app首页在厂车辆
	 * @param param
	 * @return
	 */
	List<String> appHomeVehicle(HomePageParam param);
}