package com.tianrui.service.mapper.quality.purchase;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.api.resp.quality.purchase.PurchaseSamplingItemResp;
import com.tianrui.service.bean.quality.purchase.PurchaseSamplingItem;

public interface PurchaseSamplingItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(PurchaseSamplingItem record);

    int insertSelective(PurchaseSamplingItem record);

    PurchaseSamplingItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PurchaseSamplingItem record);

    int updateByPrimaryKey(PurchaseSamplingItem record);
    
    List<PurchaseSamplingItem> findBySamplingid(String samplingid);
    
    List<HandSetReturnResp> listAutoComplateByDay(@Param("day")long day, @Param("id")String id, @Param("name")String name);
    
    List<PurchaseSamplingItemResp> findRespByPid(List<String> list);
    
}