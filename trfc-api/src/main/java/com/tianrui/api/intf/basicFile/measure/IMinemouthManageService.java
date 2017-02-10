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
	/**
	 * 分页查询数据
	 * @param query
	 * @return
	 * @throws Exception
	 */
	Result page(MinemouthManageQuery query) throws Exception;
	/**
	 * 新增矿口管理
	 * @param save
	 * @return
	 * @throws Exception
	 */
	Result addMinemouth(MinemouthManageSave save) throws Exception;
	/**
	 * 修改矿口管理信息
	 * @param save
	 * @return
	 * @throws Exception
	 */
	Result updateMinemouth(MinemouthManageSave save) throws Exception;
	/**
	 * 删除矿口管理信息(假删除)
	 * @param query
	 * @return
	 */
	Result deleteMinemouth(MinemouthManageQuery query);
}
