package com.tianrui.service.mapper.businessManage.purchaseManage;

import java.util.List;

import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.businessManage.app.AppOrderReq;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseApplicationQuery;
import com.tianrui.api.resp.android.HomeBillVo;
import com.tianrui.api.resp.businessManage.app.AppOrderResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationJoinDetailResp;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;

public interface PurchaseApplicationMapper {
    int deleteByPrimaryKey(String id);

    int insert(PurchaseApplication record);

    int insertSelective(PurchaseApplication record);

    PurchaseApplication selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PurchaseApplication record);

    int updateByPrimaryKey(PurchaseApplication record);

	long findPurchaseApplicationPageCount(PurchaseApplicationQuery query);

	List<PurchaseApplication> findPurchaseApplicationPage(PurchaseApplicationQuery query);
	
	long findPageGroupMaterielCount(PurchaseApplicationQuery query);
	
	List<PurchaseApplicationJoinDetailResp> findPageGroupMateriel(PurchaseApplicationQuery query);

	List<PurchaseApplication> selectByIds(List<String> list);
	
	Long  findMaxUtc();
	
	int insertBatch(List<PurchaseApplication> list);

	List<PurchaseApplication> selectSelective(PurchaseApplication record);
	
	long findAppToPageGroupMaterielCount(AppOrderReq req);
	
	List<AppOrderResp> findAppToPageGroupMateriel(AppOrderReq req);
	
	List<HomeBillVo> appHomeBill(HomePageParam param);
}