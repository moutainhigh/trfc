package com.tianrui.service.mapper.businessManage.purchaseManage;

import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;

public interface PurchaseApplicationDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(PurchaseApplicationDetail record);

    int insertSelective(PurchaseApplicationDetail record);

    PurchaseApplicationDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PurchaseApplicationDetail record);

    int updateByPrimaryKey(PurchaseApplicationDetail record);
    
    PurchaseApplicationDetail findPurchaseApplicationDetail(String purchaseid);
    
}