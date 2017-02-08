package com.tianrui.api.intf.basicFile.measure;

import com.tianrui.api.req.basicFile.measure.MinemouthManageQuery;
import com.tianrui.api.req.basicFile.measure.MinemouthManageSave;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 矿口管理Service接口
 * @author YangZhenFu
 * @createtime 2017年2月7日 上午9:57:34
 * @classname IMinemouthManageService.java
 */
public interface IMinemouthManageService {
	Result page(MinemouthManageQuery query) throws Exception;

	Result addMinemouth(MinemouthManageSave save) throws Exception;

	Result updateMinemouth(MinemouthManageSave save) throws Exception;
	
	Result deleteMinemouth(MinemouthManageQuery query);
}
