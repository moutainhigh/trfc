package com.tianrui.api.intf.system.base;

import com.tianrui.api.req.system.base.SystemCodeReq;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 自定义编码接口
 * @author lixiaoyong
 */
public interface ISystemCodeService {
	
	/**
	 * 新增数据
	 */
	Result insert(SystemCodeReq req);
	/**
	 * 删除数据
	 */
	Result delete(SystemCodeReq req);
	/**
	 * 修改数据
	 */
	Result update(SystemCodeReq req);
	/**
	 * 查询数据
	 */
	Result select(SystemCodeReq req);
	/**
	 * 获取编号
	 */
	Result getCode(SystemCodeReq req);
}
