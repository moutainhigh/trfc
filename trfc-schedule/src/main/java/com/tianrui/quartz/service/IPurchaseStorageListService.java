package com.tianrui.quartz.service;

import java.util.List;

import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageList;

public interface IPurchaseStorageListService {
	public List<PurchaseStorageList> getPurchaseStorageList();
	
	public void returnPurchaseStorageList() throws Exception;
}
