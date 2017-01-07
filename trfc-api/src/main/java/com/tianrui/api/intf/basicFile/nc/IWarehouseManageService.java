package com.tianrui.api.intf.basicFile.nc;

import java.util.List;

import com.tianrui.api.req.basicFile.nc.WarehouseManageReq;
import com.tianrui.api.resp.basicFile.nc.WarehouseManageResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
/**
 * 仓库管理Service接口
 * @author zhanggaohao
 * @createtime 2016年12月10日 上午10:22:44
 * @classname IWarehouseManageService.java
 */
public interface IWarehouseManageService {

	/**
	 * 查询分页
	 * @param req
	 * @return
	 * @throws Exception
	 */
	PaginationVO<WarehouseManageResp> page(WarehouseManageReq req) throws Exception;
	/**
	 * 查询仓库详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	WarehouseManageResp findOne(String id) throws Exception;
	/**
	 * 删除仓库
	 * @param id
	 * @return
	 */
	int deleteWarehouse(String id);
	/**
	 * 修改仓库
	 * @param req
	 * @return
	 * @throws Exception
	 */
	int updateWarehouse(WarehouseManageReq req) throws Exception;
	/**
	 * 新增仓库
	 * @param req
	 * @return
	 * @throws Exception
	 */
	int addWarehouse(WarehouseManageReq req) throws Exception;
	/**
	 * 查询仓库
	 * @param req
	 * @return
	 * @throws Exception
	 */
	List<WarehouseManageResp> selectSelective(WarehouseManageReq req) throws Exception;
	/**
	 * 查询全部仓库
	 * @param 
	 * @return
	 * @throws Exception
	 */
	List<WarehouseManageResp> findAll() throws Exception;
	
}

