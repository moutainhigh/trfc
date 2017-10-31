package com.tianrui.api.intf.businessManage.logisticsManage;

import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.businessManage.logisticsManage.AccessRecordQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorSystemSave;
import com.tianrui.api.resp.businessManage.logisticsManage.AccessRecordResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

public interface IAccessRecordService {

	PaginationVO<AccessRecordResp> page(AccessRecordQuery query) throws Exception;
	/**
	 * 通过id或编号获取数据
	 */
	Result findOne(AccessRecordQuery query) throws Exception;
	/**
	 * @Description 添加门禁记录API接口
	 * @author zhanggaohao
	 * @version 2017年3月28日 下午4:16:51
	 * @param apiParam
	 * @return
	 * @throws Exception
	 */
	Result addAccessRecord(ApiDoorSystemSave apiParam) throws Exception;
	/**
	 * @Description 出厂验证API接口
	 * @author zhanggaohao
	 * @version 2017年3月28日 下午4:18:56
	 * @param checkApi
	 * @return
	 */
	Result leaveFactoryCheckApi(VehicleCheckApi checkApi);
	/**
	 * @Description 入厂验证API接口
	 * @author zhanggaohao
	 * @version 2017年3月28日 下午4:19:13
	 * @param checkApi
	 * @return
	 */
	Result enterFactoryCheckApi(VehicleCheckApi checkApi);
	/**
	 * @annotation 作废
	 * @param query
	 * @return
	 */
    Result invalid(AccessRecordQuery query);

}
