package com.tianrui.service.mapper.businessManage.purchaseManage;

import java.util.List;

import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveQuery;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;

public interface PurchaseArriveMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(PurchaseArrive record);

    int insertSelective(PurchaseArrive record);

    PurchaseArrive selectByPrimaryKey(String id);

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
    
}