package com.tianrui.service.mapper.businessManage.purchaseManage;

import java.util.List;

import com.tianrui.api.req.businessManage.purchaseManage.PurchaseApplicationReq;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;

public interface PurchaseApplicationMapper {
    int deleteByPrimaryKey(String id);

    int insert(PurchaseApplication record);

    int insertSelective(PurchaseApplication record);

    PurchaseApplication selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PurchaseApplication record);

    int updateByPrimaryKey(PurchaseApplication record);
    
    List<PurchaseApplication> findPurchaseApplicationPage(PurchaseApplicationReq req);
    
    long findPurchaseApplicationPageCount(PurchaseApplicationReq req);
    
}