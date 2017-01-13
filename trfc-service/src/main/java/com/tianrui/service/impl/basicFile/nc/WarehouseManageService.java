package com.tianrui.service.impl.basicFile.nc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.basicFile.nc.IWarehouseManageService;
import com.tianrui.api.req.basicFile.nc.WarehouseManageQuery;
import com.tianrui.api.req.basicFile.nc.WarehouseManageSave;
import com.tianrui.api.resp.basicFile.nc.WarehouseManageResp;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 仓库管理Service
 * @author zhanggaohao
 * @createtime 2016年12月10日 上午10:21:40
 * @classname WarehouseManageService.java
 */
@Service
public class WarehouseManageService implements IWarehouseManageService {
	
	@Autowired
	private WarehouseManageMapper warehouseManageMapper;

	@Override
	public PaginationVO<WarehouseManageResp> page(WarehouseManageQuery query) throws Exception {
		PaginationVO<WarehouseManageResp> page = null;
		if(query != null){
			page = new PaginationVO<WarehouseManageResp>();
			long count = warehouseManageMapper.findWarehouseManagePageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<WarehouseManage> list = warehouseManageMapper.findWarehouseManagePage(query);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}

	@Override
	public WarehouseManageResp findOne(WarehouseManageQuery query) throws Exception {
		if(StringUtils.isNotBlank(query.getId())){
			return copyBean2Resp(warehouseManageMapper.selectByPrimaryKey(query.getId()));
		}
		return null;
	}

	@Override
	public Result addWarehouse(WarehouseManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			WarehouseManage wm = new WarehouseManage();
			wm.setCode(save.getCode());
			List<WarehouseManage> list = warehouseManageMapper.selectSelective(wm);
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				PropertyUtils.copyProperties(wm, save);
				wm.setId(UUIDUtil.getId());
				wm.setCreatetime(System.currentTimeMillis());
				if(warehouseManageMapper.insertSelective(wm) > 0){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}

	@Override
	public Result updateWarehouse(WarehouseManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			WarehouseManage wm = new WarehouseManage();
			PropertyUtils.copyProperties(wm, save);
//			wm.setModifier("");
			wm.setModifytime(System.currentTimeMillis());
			if(warehouseManageMapper.updateByPrimaryKeySelective(wm) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result deleteWarehouse(WarehouseManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			WarehouseManage wm = new WarehouseManage();
			PropertyUtils.copyProperties(wm, save);
			wm.setState("0");
//			wm.setModifier("");
			wm.setModifytime(System.currentTimeMillis());
			if(warehouseManageMapper.updateByPrimaryKeySelective(wm) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result findListByParmas(WarehouseManageQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		WarehouseManage w = new WarehouseManage();
		if(query != null){
			PropertyUtils.copyProperties(w, query);
		}
		List<WarehouseManage> list = warehouseManageMapper.selectSelective(w);
		result.setData(copyBeanList2RespList(list));
		return result;
	}
	
	private List<WarehouseManageResp> copyBeanList2RespList(List<WarehouseManage> list) throws Exception {
		List<WarehouseManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<WarehouseManageResp>();
			for(WarehouseManage wm : list){
				listResp.add(copyBean2Resp(wm));
			}
		}
		return listResp;
	}
	
	private WarehouseManageResp copyBean2Resp(WarehouseManage bean) throws Exception {
		WarehouseManageResp resp = null;
		if(bean != null){
			resp = new WarehouseManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
	
}
