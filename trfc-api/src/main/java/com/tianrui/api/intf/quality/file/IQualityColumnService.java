package com.tianrui.api.intf.quality.file;

import com.tianrui.smartfactory.common.vo.Result;

/**
 * 质检-列维护 接口
 * @author Administrator
 *
 */
public interface IQualityColumnService {
	/**
	 * 查询数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Result findOne(String id) throws Exception;
	/**
	 * @Description 根据名称模糊查询
	 * @author zhanggaohao
	 * @version 2017年2月24日 上午10:25:41
	 * @param likeName
	 * @return
	 * @throws Exception 
	 */
	Result autoCompleteSearch(String likeName) throws Exception;
}
