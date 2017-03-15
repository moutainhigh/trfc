package com.tianrui.api.intf.basicFile.measure;

import java.util.List;

import com.tianrui.api.req.basicFile.measure.MinemouthManageQuery;
import com.tianrui.api.req.basicFile.measure.MinemouthManageSave;
import com.tianrui.api.resp.basicFile.measure.MinemouthManageResp;
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
	/**
	 * @Description 根据名称模糊查询
	 * @author zhanggaohao
	 * @version 2017年3月8日 上午11:59:25
	 * @param trim
	 * @return
	 * @throws Exception 
	 */
	List<MinemouthManageResp> autoCompleteSearch(String trim) throws Exception;
}
