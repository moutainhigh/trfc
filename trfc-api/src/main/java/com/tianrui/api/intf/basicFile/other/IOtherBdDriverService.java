package com.tianrui.api.intf.basicFile.other;

import com.tianrui.api.req.basicFile.other.OtherBdDriverReq;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 其他司机Service接口
 * @author YangZhenFu
 */
public interface IOtherBdDriverService {
	/*
	 * 分页查询数据
	 */
	Result page(OtherBdDriverReq req) throws Exception;
	
	/*
	 * 新增其他司机信息
	 */
	Result addDriver(OtherBdDriverReq req) throws Exception;
	
	/*
	 * 修改其他司机信息
	 */
	Result editDriver(OtherBdDriverReq req) throws Exception;
	
	/*
	 * 删除其他司机信息
	 */
	Result deleteDriver(String id);
	
	/*
	 * 获取司机编号
	 */
	String getDriverCode();
	
	/*
	 * 获取司机内码
	 */
	String getDriverInnercode();
	
	/*   
	 * 检查名称
	 */
	Result checkName(String name);
}
