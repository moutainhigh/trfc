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
    
    long findPurchaseArrivePageCount(PurchaseArriveQuery record);
    
    List<PurchaseArrive> findPurchaseArrivePage(PurchaseArriveQuery record);

	List<PurchaseArrive> checkDriverAndVehicleIsUse(PurchaseArrive bean);
    
}