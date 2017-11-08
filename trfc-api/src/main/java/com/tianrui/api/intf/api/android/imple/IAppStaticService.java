package com.tianrui.api.intf.api.android.imple;

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
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	AppResult appLogin(LoginUserParam param) throws Exception;
	/**
	 * @annotation 修改密码
	 * @param req
	 * @return
	 */
	AppResult appUpdatePwd(LoginUserParam param);
	/**
	 * @annotation 绑定手机号
	 * @param req
	 * @return
	 */
	AppResult appBindPhoneNumber(LoginUserParam param);
	/**
	 * @annotation 解绑手机号
	 * @param req
	 * @return
	 */
	AppResult appUnBindPhoneNumber(LoginUserParam param);
	/**
	 * @annotation 首页数据
	 * @param body
	 * @return
	 */
	AppResult home(HomePageParam param);

}
