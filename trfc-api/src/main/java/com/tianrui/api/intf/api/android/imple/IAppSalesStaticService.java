package com.tianrui.api.intf.api.android.imple;

import com.tianrui.api.req.android.BillListParam;
import com.tianrui.api.req.android.BillSave;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.android.LoginUserParam;
import com.tianrui.api.req.android.MyPnListParam;
import com.tianrui.api.req.android.MyVehicleListParam;
import com.tianrui.api.req.android.NoticeListParam;
import com.tianrui.api.req.android.NoticeSave;
import com.tianrui.smartfactory.common.vo.AppResult;

public interface IAppSalesStaticService {

	AppResult home(HomePageParam param);

	AppResult billList(BillListParam param);

	AppResult billDetail(BillListParam param);

	AppResult saveBill(BillSave param) throws Exception;

	AppResult billDelete(BillListParam param) throws Exception;

	AppResult saveNotice(NoticeSave param) throws Exception;

	AppResult listMoreBill(BillListParam param);

	AppResult moreSendCar(NoticeSave param) throws Exception;

	AppResult noticeList(NoticeListParam param);

	AppResult noticeDetail(NoticeListParam param);

	AppResult noticeUpdate(NoticeSave param) throws Exception;

	AppResult noticeCancel(NoticeListParam param) throws Exception;

	AppResult myVehicle(MyVehicleListParam param);

	AppResult myPn(MyPnListParam param);

	AppResult myPnDetail(MyPnListParam param);

	AppResult customerGroupUser(LoginUserParam param);

}
