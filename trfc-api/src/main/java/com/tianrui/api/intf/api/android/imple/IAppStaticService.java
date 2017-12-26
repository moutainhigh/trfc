package com.tianrui.api.intf.api.android.imple;

import java.util.List;

import com.tianrui.api.req.android.AppMiningParam;
import com.tianrui.api.req.android.AppVersionParam;
import com.tianrui.api.req.android.BillListParam;
import com.tianrui.api.req.android.BillSave;
import com.tianrui.api.req.android.DriverSave;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.android.LoginUserParam;
import com.tianrui.api.req.android.MyPnListParam;
import com.tianrui.api.req.android.MyVehicleListParam;
import com.tianrui.api.req.android.NoticeListParam;
import com.tianrui.api.req.android.NoticeSave;
import com.tianrui.api.req.android.SearchKeyParam;
import com.tianrui.api.resp.basicFile.businessControl.MiningpointDbSettingResp;
import com.tianrui.smartfactory.common.vo.AppResult;

/**
 * @annotation APP接口
 * @author zhanggaohao
 *
 */
public interface IAppStaticService {

	/**
	 * @annotation APP登录
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	AppResult appLogin(LoginUserParam param) throws Exception;
	/**
	 * @annotation 登出
	 * @param param
	 * @return
	 */
	AppResult appLoginOut(LoginUserParam param);
	/**
	 * @annotation 修改密码
	 * @param param
	 * @return
	 */
	AppResult appUpdatePwd(LoginUserParam param);
	/**
	 * @annotation 绑定手机号
	 * @param param
	 * @return
	 */
	AppResult appBindPhoneNumber(LoginUserParam param);
	/**
	 * @annotation 解绑手机号
	 * @param param
	 * @return
	 */
	AppResult appUnBindPhoneNumber(LoginUserParam param);
	/**
	 * @annotation 首页数据
	 * @param param
	 * @return
	 */
	AppResult home(HomePageParam param);
	/**
	 * @annotation 订单列表
	 * @param param
	 * @return
	 */
	AppResult billList(BillListParam param);
	/**
	 * @annotation 订单详情
	 * @param param
	 * @return
	 */
	AppResult billDetail(BillListParam param);
	/**
	 * @annotation 订单作废
	 * @param param
	 * @return
	 */
	AppResult billDelete(BillListParam param);
	/**
	 * @annotation 新增一车一单订单
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	AppResult saveBill(BillSave param) throws Exception;
	/**
	 * @annotation 派车
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	AppResult saveNotice(NoticeSave param) throws Exception;
	/**
	 * @annotation 通知单列表
	 * @param param
	 * @return
	 */
	AppResult noticeList(NoticeListParam param);
	/**
	 * @annotation 通知单详情
	 * @param param
	 * @return
	 */
	AppResult noticeDetail(NoticeListParam param);
	/**
	 * @annotation 通知单修改
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	AppResult noticeUpdate(NoticeSave param) throws Exception;
	/**
	 * @annotation 通知单作废
	 * @param param
	 * @return
	 */
	AppResult noticeCancel(NoticeListParam param);
	/**
	 * @annotation 我的车辆
	 * @param param
	 * @return
	 */
	AppResult myVehicle(MyVehicleListParam param);
	/**
	 * @annotation 我的磅单
	 * @param param
	 * @return
	 */
	AppResult myPn(MyPnListParam param);
	/**
	 * @annotation 我的磅单详情
	 * @param param
	 * @return
	 */
	AppResult myPnDetail(MyPnListParam param);
	/**
	 * @annotation 新增司机
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	AppResult saveDriver(DriverSave param) throws Exception;
	/**
	 * @annotation 车辆模糊搜索
	 * @param param
	 * @return
	 */
	AppResult vehicleSearch(SearchKeyParam param);
	/**
	 * @annotation 司机模糊搜索
	 * @param param
	 * @return
	 */
	AppResult driverSearch(SearchKeyParam param);
	/**
	 * @annotation 物料模糊搜索
	 * @param param
	 * @return
	 */
	AppResult materialSearch(SearchKeyParam param);
	/**
	 * @annotation 查询用户组成员
	 * @param param
	 * @return
	 */
	AppResult queryGroupUser(LoginUserParam param);
	/**
	 * @annotation 切换用户
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	AppResult userCutover(LoginUserParam param) throws Exception;
	/**
	 * @annotation 熟车列表
	 * @param param
	 * @return
	 */
	AppResult userVehicle(MyVehicleListParam param);
	/**
	 * @annotation 熟司机列表
	 * @param param
	 * @return
	 */
	AppResult userDriver(MyVehicleListParam param);
	/**
	 * @annotation 通过版本号查询是否需要更新
	 * @param param
	 * @return
	 */
	AppResult appVersion(AppVersionParam param);
	/**
	 * 常用车辆和司机
	 * @param param
	 * @return
	 */
	AppResult vcAndDr(MyVehicleListParam param);
	/**
	 * 查询采矿口
	 * @param appMiningParam
	 * @return
	 */
	AppResult getMiningList(AppMiningParam appMiningParam);
}
