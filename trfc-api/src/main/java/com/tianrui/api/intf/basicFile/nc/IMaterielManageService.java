package com.tianrui.api.intf.basicFile.nc;

import java.util.List;

import com.tianrui.api.req.basicFile.nc.MaterielManageReq;
import com.tianrui.api.resp.basicFile.nc.MaterielManageResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

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
	public PaginationVO<MaterielManageResp> page(MaterielManageReq req) throws Exception;
	/**
	 * 新增物料管理
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public int addMateriel(MaterielManageReq req) throws Exception;
	/**
	 * 查询物料管理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MaterielManageResp findOne(String id) throws Exception;
	/**
	 * 删除物料管理
	 * @param id
	 * @return
	 */
	public int deleteMateriel(String id);
	/**
	 * 更新物料管理
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public int updateMateriel(MaterielManageReq req) throws Exception;
	/**
	 * 查询全部物料
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<MaterielManageResp> findAll() throws Exception;
	/**
	 * 查询物料
	 * @param 
	 * @return
	 * @throws Exception
	 */
	List<MaterielManageResp> selectSelective(MaterielManageReq req) throws Exception;

}