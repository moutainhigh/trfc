package com.tianrui.api.intf.basicFile.businessControl;

import java.util.List;

import com.tianrui.api.req.basicFile.businessControl.MiningpointDbSettingQuery;
import com.tianrui.api.req.basicFile.businessControl.MiningpointDbSettingSave;
import com.tianrui.api.resp.basicFile.businessControl.MiningpointDbSettingResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 采矿点原发设置接口
 * @author lenovo
 *
 */
public interface IMiningpointDbSettingService {
	/**
	 * @Description 采矿点原发设置分页查询
	 * @author q
	 * @param query
	 * @return
	 */
	PaginationVO<MiningpointDbSettingResp> page(MiningpointDbSettingQuery query);

	Result add(MiningpointDbSettingSave save) throws Exception;

	MiningpointDbSettingResp findOne(String id) throws Exception;

	Result update(MiningpointDbSettingSave save) throws Exception;

	Result delete(String id);

	List<MiningpointDbSettingResp> selectByMaterialid(String materialid);


}
