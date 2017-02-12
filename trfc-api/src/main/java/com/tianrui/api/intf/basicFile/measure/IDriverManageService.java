package com.tianrui.api.intf.basicFile.measure;

import com.tianrui.api.req.basicFile.measure.DriverManageSave;
import com.tianrui.api.req.basicFile.measure.DriverManageQuery;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
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

	DriverManageResp findOne(DriverManageQuery query) throws Exception;

}
