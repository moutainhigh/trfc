package com.tianrui.api.intf.basicFile.measure;

import java.util.List;

import com.tianrui.api.req.basicFile.measure.YardManageQuery;
import com.tianrui.api.req.basicFile.measure.YardManageSave;
import com.tianrui.api.resp.basicFile.measure.YardManageResp;
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
	/**
	 * @Description 通过名称模糊查询
	 * @author zhanggaohao
	 * @version 2017年3月8日 下午2:10:01
	 * @param likeName
	 * @return
	 * @throws Exception
	 */
	List<YardManageResp> autoCompleteSearch(String likeName) throws Exception;
}
