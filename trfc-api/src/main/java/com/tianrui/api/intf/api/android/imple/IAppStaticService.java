package com.tianrui.api.intf.api.android.imple;

import com.tianrui.api.req.android.BillListParam;
import com.tianrui.api.req.android.BillSave;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.android.LoginUserParam;
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
	 */
	AppResult saveNotice(BillSave param);
}
