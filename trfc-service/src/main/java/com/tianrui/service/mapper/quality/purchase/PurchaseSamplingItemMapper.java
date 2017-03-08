package com.tianrui.service.mapper.quality.purchase;

import java.util.List;

import com.tianrui.service.bean.quality.purchase.PurchaseSamplingItem;

public interface PurchaseSamplingItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(PurchaseSamplingItem record);

    int insertSelective(PurchaseSamplingItem record);

    PurchaseSamplingItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PurchaseSamplingItem record);

    int updateByPrimaryKey(PurchaseSamplingItem record);
    
    List<PurchaseSamplingItem> findBySamplingid(String samplingid);
}