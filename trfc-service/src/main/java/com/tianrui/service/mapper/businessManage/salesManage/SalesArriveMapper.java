package com.tianrui.service.mapper.businessManage.salesManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
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
	 * @Description 验证是否有销售通知单
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午10:33:24
	 * @param vehicleno
	 * @return
	 */
	SalesArrive hasPurchaseArrive(String vehicleno);
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
}