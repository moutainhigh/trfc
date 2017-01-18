package com.tianrui.service.impl.basicFile.nc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.basicFile.nc.ISupplierManageService;
import com.tianrui.api.req.basicFile.nc.SupplierManageQuery;
import com.tianrui.api.req.basicFile.nc.SupplierManageSave;
import com.tianrui.api.resp.basicFile.nc.SupplierManageResp;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 供应商管理Service
 * @author zhanggaohao
 * @createtime 2016年12月19日 上午9:54:13
 * @classname SupplierManageService.java
 */
@Service
public class SupplierManageService implements ISupplierManageService {
	
	@Autowired
	private SupplierManageMapper supplierManageMapper;

	@Override
	public PaginationVO<SupplierManageResp> page(SupplierManageQuery query) throws Exception {
		PaginationVO<SupplierManageResp> page = null;
		if(query != null){
			page = new PaginationVO<SupplierManageResp>();
			query.setState("1");
			long count = supplierManageMapper.findSupplierPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SupplierManage> list = supplierManageMapper.findSupplierPage(query);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	@Override
	public Result updateSupplier(SupplierManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			SupplierManage sm = new SupplierManage();
			PropertyUtils.copyProperties(sm, save);
//			sm.setModifier("");
			sm.setModifytime(System.currentTimeMillis());
			if(supplierManageMapper.updateByPrimaryKeySelective(sm) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result findListByParmas(SupplierManageQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		SupplierManage sm = new SupplierManage();
		if(query != null){
			PropertyUtils.copyProperties(sm, query);
		}
		sm.setState("1");
		List<SupplierManage> list = supplierManageMapper.selectSelective(sm);
		result.setData(copyBeanList2RespList(list));
		return result;
	}
	
	private List<SupplierManageResp> copyBeanList2RespList(List<SupplierManage> list) throws Exception {
		List<SupplierManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SupplierManageResp>();
			for(SupplierManage wm : list){
				listResp.add(copyBean2Resp(wm));
			}
		}
		return listResp;
	}
	
	private SupplierManageResp copyBean2Resp(SupplierManage bean) throws Exception {
		SupplierManageResp resp = null;
		if(bean != null){
			resp = new SupplierManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

	@Override
	public Result findMaxUtc(SupplierManageQuery query) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(query !=null  ){
			Long max =supplierManageMapper.findMaxUtc();
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			rs.setData(max);
		}
		return rs;
	}

}
