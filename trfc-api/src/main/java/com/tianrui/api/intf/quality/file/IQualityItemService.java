package com.tianrui.api.intf.quality.file;


import com.tianrui.api.req.quality.file.QualityItemReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface IQualityItemService {

	/**
	 * 删除数据
	 */
	Result delete(QualityItemReq req) throws Exception;
	/**
	 * 新增数据
	 */
	Result add(QualityItemReq req) throws Exception;
	/**
	 * 更新数据		
	 */
	Result update(QualityItemReq req) throws Exception;
	/**
	 * 分页查询
	 */
	Result page(QualityItemReq req) throws Exception;
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
