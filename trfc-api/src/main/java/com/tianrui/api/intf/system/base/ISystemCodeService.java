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
	Result insert(SystemCodeReq req) throws Exception;
	/**
	 * 删除数据
	 * @throws Exception 
	 */
	Result delete(SystemCodeReq req) throws Exception;
	/**
	 * 修改数据
	 */
	Result update(SystemCodeReq req) throws Exception;
	/**
	 * 查询数据
	 */
	Result select(SystemCodeReq req) throws Exception;
	/**
	 * 获取编号
	 */
	Result getCode(SystemCodeReq req) throws Exception;
	/**
	 * 检测单据代号是否重复
	 */
	Result checkCode(SystemCodeReq req) throws Exception;
	/**
	 * 刷新编号(自增1)
	 */
	Result updateCodeItem(SystemCodeReq req) throws Exception;
	
}
