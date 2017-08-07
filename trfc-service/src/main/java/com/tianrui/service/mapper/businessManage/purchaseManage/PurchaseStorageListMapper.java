package com.tianrui.service.mapper.businessManage.purchaseManage;

import java.util.List;

import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageList;

public interface PurchaseStorageListMapper {
    int deleteByPrimaryKey(String id);

    int insert(PurchaseStorageList record);

    int insertSelective(PurchaseStorageList record);

    PurchaseStorageList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PurchaseStorageList record);

    int updateByPrimaryKey(PurchaseStorageList record);

	List<PurchaseStorageList> selectIncrementalData(PurchaseStorageList query);
	
	PurchaseStorageList selectByPoundNoteId(String noticeId);
}