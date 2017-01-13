package com.tianrui.api.intf.basicFile.nc;

import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.api.req.basicFile.nc.MaterielManageSave;
import com.tianrui.api.resp.basicFile.nc.MaterielManageResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 物料管理Service接口
 * @author zhanggaohao
 * @createtime 2016年12月7日 上午9:30:26
 * @classname IMaterielManageService.java
 */
public interface IMaterielManageService {
	
	/**
	 * 查询分页
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public PaginationVO<MaterielManageResp> page(MaterielManageQuery query) throws Exception;
	/**
	 * 新增物料管理
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public Result addMateriel(MaterielManageSave save) throws Exception;
	/**
	 * 删除物料管理
	 * @param id
	 * @return
	 */
	public Result deleteMateriel(MaterielManageQuery query);
	/**
	 * 更新物料管理
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public Result updateMateriel(MaterielManageSave save) throws Exception;
	/**
	 * 根据条件查询物料
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public Result findListByParmas(MaterielManageQuery query) throws Exception;
	/**
	 * 根据id查询物料
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	public MaterielManageResp findOne(MaterielManageQuery query) throws Exception;

}