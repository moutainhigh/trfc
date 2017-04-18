package com.tianrui.api.intf.basicFile.measure;

import com.tianrui.api.req.basicFile.measure.DriverManageSave;
import com.tianrui.api.req.businessManage.app.AppDriverSaveReq;
import com.tianrui.api.req.businessManage.app.AppQueryReq;

import java.util.List;

import com.tianrui.api.req.basicFile.measure.DriverManageQuery;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.api.resp.businessManage.app.AppDriverResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 司机管理Service接口
 * @author zhanggaohao
 * @createtime 2016年12月28日 下午3:32:34
 * @classname IDriverManageService.java
 */
public interface IDriverManageService {

	PaginationVO<DriverManageResp> page(DriverManageQuery query) throws Exception;

	Result add(DriverManageSave save) throws Exception;

	Result update(DriverManageSave save) throws Exception;
	
	Result delete(DriverManageQuery query);

	Result findListByParmas(DriverManageQuery query) throws Exception;

	DriverManageResp findOne(String id) throws Exception;

	List<DriverManageResp> autoCompleteSearch(String likeName) throws Exception;
	/**
	 * @Description app司机分页查询接口
	 * @author zhanggaohao
	 * @version 2017年4月18日 上午11:16:20
	 * @param req
	 * @return
	 */
	PaginationVO<AppDriverResp> appToPage(AppQueryReq req);
	/**
	 * @Description app司机新增接口
	 * @author zhanggaohao
	 * @version 2017年4月18日 上午11:27:48
	 * @param body
	 * @return
	 */
	Result appDriverCreate(AppDriverSaveReq req);

}
