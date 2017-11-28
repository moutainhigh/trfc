package com.tianrui.service.mapper.businessManage.salesManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.android.MyVehicleListParam;
import com.tianrui.api.req.android.NoticeListParam;
import com.tianrui.api.req.businessManage.app.AppNoticeOrderReq;
import com.tianrui.api.req.businessManage.app.AppOrderReq;
import com.tianrui.api.req.businessManage.logisticsManage.SalesLogisticsQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.api.resp.android.HomeNoticeVo;
import com.tianrui.api.resp.android.MyVehicleListVo;
import com.tianrui.api.resp.android.NoticeListVo;
import com.tianrui.api.resp.businessManage.app.AppNoticeOrderResp;
import com.tianrui.api.resp.businessManage.app.AppVehicleInFactoryResp;
import com.tianrui.api.resp.businessManage.logisticsManage.SalesLogisticsResp;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;

public interface SalesArriveMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesArrive record);

    int insertSelective(SalesArrive record);

    SalesArrive selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesArrive record);

    int updateByPrimaryKey(SalesArrive record);
    
    List<SalesArrive> selectSelective(SalesArrive record);
    /**
     * @Description 分页和查询总条数
     * @author zhanggaohao
     * @version 2017年2月17日 上午8:58:24
     * @param query
     * @return
     */
	long findSalesArrivePageCount(SalesArriveQuery query);
	/**
	 * @Description 分页查询
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:58:16
	 * @param query
	 * @return
	 */
	List<SalesArrive> findSalesArrivePage(SalesArriveQuery query);
	/**
	 * @Description 检查IC卡是否正在使用中
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:57:28
	 * @param query
	 * @return
	 */
	SalesArrive checkICUse(String icardid);
	/**
	 * @Description 查询车辆等待数量
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:57:22
	 * @param query
	 * @return
	 */
	int selectWaitingNumber(SalesArriveQuery query);
	/**
	 * @Description 检查车辆和司机是否已有正在执行的销售通知单
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:56:52
	 * @param record
	 * @return
	 */
	List<SalesArrive> checkDriverAndVehicleIsUse(SalesArrive record);
	/**
	 * @Description 根据ids查询列表
	 * @author zhanggaohao
	 * @version 2017年2月23日 下午2:52:32
	 * @param ids
	 * @return
	 */
	List<SalesArrive> selectByIds(List<String> ids);
	/**
	 * @Description 验证是否有销售通知单（未完成的通知单，即未作废，未出厂的）
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午10:33:24
	 * @param vehicleno
	 * @return
	 */
	SalesArrive hasSalesArrive(String vehicleno);
	/**
	 * @Description 根据通知单号查询通知单
	 * @author zhanggaohao
	 * @version 2017年3月25日 下午3:44:40
	 * @param code
	 * @return
	 */
	SalesArrive selectByCode(String code);
	/**
	 * @Description 根据车辆查询通知单
	 * @author zhanggaohao
	 * @version 2017年4月7日 下午2:35:58
	 * @param vehicleno
	 * @param vehiclerfid
	 * @return
	 */
	List<SalesArrive> validNoticeByVehicle(@Param("vehicleno")String vehicleno, @Param("vehiclerfid")String vehiclerfid);
	/**
	 * @Description app销售通知单分页接口
	 * @author zhanggaohao
	 * @version 2017年4月15日 下午2:24:18
	 * @param req
	 * @return
	 */
	long findAppNoticePageCount(AppNoticeOrderReq req);
	/**
	 * @Description app销售通知单分页接口
	 * @author zhanggaohao
	 * @version 2017年4月15日 下午2:24:58
	 * @param req
	 * @return
	 */
	List<AppNoticeOrderResp> findAppNoticePage(AppNoticeOrderReq req);
	/**
	 * @Description app销售在厂车辆查询接口
	 * @author zhanggaohao
	 * @version 2017年4月18日 下午2:14:29
	 * @param req
	 * @return
	 */
	List<AppVehicleInFactoryResp> appInfoFactoryVehicleAndMaterial(AppOrderReq req);
	/**
	 * @Description 销售物流分页
	 * @param query
	 * @return
	 * @exception 
	 * @author zhanggaohao
	 * @version 2017年6月6日 下午2:11:13
	 */
	List<SalesLogisticsResp> selectLogisticsPage(SalesLogisticsQuery query);
	/**
	 * @Description 销售物流分页总条数
	 * @param query
	 * @return
	 * @exception 
	 * @author zhanggaohao
	 * @version 2017年6月6日 下午2:11:23
	 */
	long selectLogisticsPageCount(SalesLogisticsQuery query);
	/**
	 * @annotation 根据车辆ID查询有效的通知单（未出厂，未作废）
	 * @param vehicleId
	 * @return
	 */
	SalesArrive getByVehicleId(String vehicleId);
	/**
	 * @annotation 根据车牌号查询有效的通知单（未出厂，未作废）
	 * @param vehicleNo
	 * @return
	 */
	SalesArrive getByVehicleNo(String vehicleNo);
	/**
	 * @annotation 根据车牌号把上次强制出厂的单据改为出厂
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
	/**
	 * @annotation 客商APP通知单列表
	 * @param param
	 * @return
	 */
	List<NoticeListVo> appNoticeList(NoticeListParam param);
	/**
	 * @annotation 客商APP通知单总条数
	 * @param param
	 * @return
	 */
	long appNoticeListCount(NoticeListParam param);
	/**
	 * @annotation 客商APP通知单详情
	 * @param param
	 * @return
	 */
	NoticeListVo appNoticeDetail(NoticeListParam param);
	/**
	 * @annotation 客商APP我的车辆列表
	 * @param param
	 * @return
	 */
	List<MyVehicleListVo> appMyVehicleList(MyVehicleListParam param);
	/**
	 * @annotation 客商APP我的车辆总条数
	 * @param param
	 * @return
	 */
	long appMyVehicleListCount(MyVehicleListParam param);
}