package com.tianrui.service.mapper.businessManage.purchaseManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageListItem;

public interface PurchaseStorageListItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(PurchaseStorageListItem record);

    int insertSelective(PurchaseStorageListItem record);

    PurchaseStorageListItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PurchaseStorageListItem record);

    int updateByPrimaryKey(PurchaseStorageListItem record);
    	
    List<PurchaseStorageListItem> selectByOrderIds(@Param("ids") List<String> ids);
}