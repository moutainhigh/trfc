package com.tianrui.service.mapper.businessManage.purchaseManage;

import java.util.List;

import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveQuery;
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
}