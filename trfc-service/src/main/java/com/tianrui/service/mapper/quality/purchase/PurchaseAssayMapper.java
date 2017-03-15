package com.tianrui.service.mapper.quality.purchase;

import java.util.List;

import com.tianrui.api.req.quality.purchase.PurchaseAssayReq;
import com.tianrui.service.bean.quality.purchase.PurchaseAssay;

public interface PurchaseAssayMapper {
    int deleteByPrimaryKey(String id);

    int insert(PurchaseAssay record);

    int insertSelective(PurchaseAssay record);

    PurchaseAssay selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PurchaseAssay record);

    int updateByPrimaryKey(PurchaseAssay record);
    
    List<PurchaseAssay> page(PurchaseAssayReq req);
    
    int count(PurchaseAssayReq req);
}