package com.tianrui.api.intf.basicFile.measure;

import com.tianrui.api.req.basicFile.measure.DriverManageReq;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

/**
 * 司机管理Service接口
 * @author zhanggaohao
 * @createtime 2016年12月28日 下午3:32:34
 * @classname IDriverManageService.java
 */
public interface IDriverManageService {

	PaginationVO<DriverManageResp> page(DriverManageReq req) throws Exception;

	int addDriver(DriverManageReq req) throws Exception;

	int updateDriver(DriverManageReq req) throws Exception;
	
	int deleteDriver(String id);

	int delDriver(DriverManageReq req) throws Exception;

}
