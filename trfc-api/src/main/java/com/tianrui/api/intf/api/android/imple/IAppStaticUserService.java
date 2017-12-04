package com.tianrui.api.intf.api.android.imple;
import com.tianrui.api.req.android.LoginUserParam;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.vo.AppResult;

/**
 * @annotation APP接口
 * @author zhanggaohao
 *
 */
public interface IAppStaticUserService {

	/**
	 * @annotation APP登录
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	AppResult appLogin(LoginUserParam param) throws Exception;
	
	SystemUserResp getUser(String id) throws Exception;
	
	/**
	 * @Description 根据id获取用户
	 * @author zhanggaohao
	 * @version 2017年4月13日 上午11:48:31
	 * @param id
	 * @param isFlush 是否刷新缓存
	 * @return
	 * @throws Exception 
	 */
	SystemUserResp get(String id, boolean isFlush) throws Exception;
	/**
	 * @Description 根据id获取用户
	 * @author zhanggaohao
	 * @version 2017年4月13日 下午2:47:02
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SystemUserResp get(String id) throws Exception;
}
