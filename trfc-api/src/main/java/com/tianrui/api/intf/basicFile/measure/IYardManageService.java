package com.tianrui.api.intf.basicFile.measure;

import com.tianrui.api.req.basicFile.measure.MinemouthManageQuery;
import com.tianrui.api.req.basicFile.measure.MinemouthManageSave;
import com.tianrui.api.req.basicFile.measure.YardManageQuery;
import com.tianrui.api.req.basicFile.measure.YardManageSave;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 堆场管理Service接口
 * @author YangZhenFu
 * @createtime 2017年3月4日 上午9:07:34
 * @classname IYardManageService.java
 */
public interface IYardManageService {
	/**
	 * 分页查询数据
	 * @param query
	 * @return
	 * @throws Exception
	 */
	Result page(YardManageQuery query) throws Exception;
	/**
	 * 新增堆场管理
	 * @param save
	 * @return
	 * @throws Exception
	 */
	Result addYard(YardManageSave save) throws Exception;
	/**
	 * 修改堆场管理信息
	 * @param save
	 * @return
	 * @throws Exception
	 */
	Result updateYard(YardManageSave save) throws Exception;
	/**
	 * 删除堆场管理信息(假删除)
	 * @param query
	 * @return
	 */
	Result deleteYard(YardManageQuery query);
}
