package com.tianrui.api.intf.basicFile.nc;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.basicFile.nc.SupplierManageQuery;
import com.tianrui.api.req.basicFile.nc.SupplierManageSave;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.resp.basicFile.nc.SupplierManageResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 供应商管理ISupplierManageService接口
 * @author zhanggaohao
 * @createtime 2016年12月19日 上午9:54:48
 * @classname ISupplierManageService.java
 */
public interface ISupplierManageService {

	PaginationVO<SupplierManageResp> page(SupplierManageQuery query) throws Exception;

	Result updateSupplier(SupplierManageSave save) throws Exception;
	
	Result findListByParmas(SupplierManageQuery query) throws Exception;
	
	SupplierManageResp findOne(String id) throws Exception;
	/**
	 * 获取最大时间戳
	 * @param query
	 * @return
	 * @throws Exception
	 */
	Result findMaxUtc(SupplierManageQuery query) throws Exception;
	/**
	 * 把数据中心的最新数据更新到本地
	 * @param list
	 * @return
	 */
	Result updateDataWithDC(List<JSONObject> list);
	/**
	 * @Description 根据名称模糊查询
	 * @author zhanggaohao
	 * @version 2017年2月24日 上午10:25:41
	 * @param likeName
	 * @return
	 * @throws Exception 
	 */
	List<SupplierManageResp> autoCompleteSearch(String likeName) throws Exception;
	/**
	 * @Description 手持机获取供应商集合接口
	 * @author zhanggaohao
	 * @version 2017年4月21日 下午1:42:45
	 * @param req
	 * @return
	 */
	Result HandSetQueryAll(HandSetRequestParam req);

}
