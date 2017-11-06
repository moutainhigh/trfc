package com.tianrui.api.intf.basicFile.businessControl;

import com.tianrui.api.req.basicFile.businessControl.PrimarySettingQuery;
import com.tianrui.api.req.basicFile.businessControl.PrimarySettingSave;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.resp.basicFile.businessControl.PrimarySettingResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * @Description 原发设置接口
 * @author zhanggaohao
 * @version 2017年5月12日 下午2:36:41
 */
public interface IPrimarySettingService {
	/**
	 * @Description 原发设置分页查询
	 * @author zhanggaohao
	 * @version 2017年5月12日 下午2:36:28
	 * @param query
	 * @return
	 */
	PaginationVO<PrimarySettingResp> page(PrimarySettingQuery query);
	/**
	 * @Description 新增原发设置
	 * @author zhanggaohao
	 * @version 2017年5月13日 上午10:25:38
	 * @param save
	 * @return
	 * @throws Exception 
	 */
	Result add(PrimarySettingSave save) throws Exception;
	/**
	 * @Description 修改原发设置
	 * @author zhanggaohao
	 * @version 2017年5月13日 上午10:54:42
	 * @param save
	 * @return
	 * @throws Exception
	 */
	Result update(PrimarySettingSave save) throws Exception;
	/**
	 * @Description 根据id查询原发设置
	 * @author zhanggaohao
	 * @version 2017年5月13日 上午10:58:57
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PrimarySettingResp findOne(String id) throws Exception;
	/**
	 * @Description 根据id删除原发设置
	 * @author zhanggaohao
	 * @version 2017年5月13日 上午11:01:53
	 * @param id
	 * @return
	 */
	Result delete(String id);
	/**
	 * @Description 手持机接口
	 * @author zhanggaohao
	 * @version 2017年5月15日 下午2:24:39
	 * @param param
	 * @return
	 */
    Result handSetPrimarySetting(HandSetRequestParam param);

}
