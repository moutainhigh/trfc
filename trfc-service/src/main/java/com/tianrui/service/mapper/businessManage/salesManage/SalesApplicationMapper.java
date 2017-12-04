package com.tianrui.service.mapper.businessManage.salesManage;

import java.util.List;

import com.tianrui.api.req.android.BillListParam;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.businessManage.app.AppOrderReq;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.resp.android.BillListVo;
import com.tianrui.api.resp.android.HomeBillVo;
import com.tianrui.api.resp.businessManage.app.AppOrderResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationJoinDetailResp;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;

public interface SalesApplicationMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesApplication record);
    
    int insertBatch(List<SalesApplication> list);

    int insertSelective(SalesApplication record);

    SalesApplication selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesApplication record);

    int updateByPrimaryKey(SalesApplication record);
    
    List<SalesApplication> findSalesApplicationPage(SalesApplicationQuery query);
    
    long findSalesApplicationPageCount(SalesApplicationQuery query);

    List<SalesApplicationJoinDetailResp> findPageGroupMateriel(SalesApplicationQuery query);
	
	long findPageGroupMaterielCount(SalesApplicationQuery query);

	List<SalesApplication> selectSelective(SalesApplication record);
	
	Long  findMaxUtc();
	
	List<SalesApplication> selectByIds(List<String> list);

	long findAppToPageGroupMaterielCount(AppOrderReq req);

	List<AppOrderResp> findAppToPageGroupMateriel(AppOrderReq req);
	
	List<HomeBillVo> appHomeBill(HomePageParam param);
	
	List<BillListVo> appBillList(BillListParam param);
	
	long appBillListCount(BillListParam param);
	
	BillListVo appBillDetail(BillListParam param);
	
	List<BillListVo> appMoreBillList(BillListParam param);
	
	List<SalesApplication> listOneBillOneCarNotNotice();
}