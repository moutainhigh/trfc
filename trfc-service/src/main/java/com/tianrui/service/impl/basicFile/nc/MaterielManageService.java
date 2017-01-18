package com.tianrui.service.impl.basicFile.nc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.api.req.basicFile.nc.MaterielManageSave;
import com.tianrui.api.resp.basicFile.nc.MaterielManageResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 物料管理Service
 * @author zhanggaohao
 * @createtime 2016年12月7日 上午9:28:46
 * @classname MaterielManageService.java
 */
@Service
public class MaterielManageService implements IMaterielManageService {
	
	@Autowired
	private MaterielManageMapper materielManageMapper;

	@Override
	public PaginationVO<MaterielManageResp> page(MaterielManageQuery req) throws Exception {
		PaginationVO<MaterielManageResp> page = null;
		if(req != null){
			page = new PaginationVO<MaterielManageResp>();
			long count = materielManageMapper.findMaterielManagePageCount(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<MaterielManage> list = materielManageMapper.findMaterielManagePage(req);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		return page;
	}

	@Override
	public Result deleteMateriel(MaterielManageQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			MaterielManage mater = new MaterielManage();
			mater.setId(query.getId());
			mater.setState("0");
			if(materielManageMapper.updateByPrimaryKeySelective(mater) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result updateMateriel(MaterielManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			MaterielManage mater = new MaterielManage();
			PropertyUtils.copyProperties(mater, save);
//			mater.setModifier("");
			mater.setModifytime(System.currentTimeMillis());
			if(materielManageMapper.updateByPrimaryKeySelective(mater) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result addMateriel(MaterielManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			MaterielManage mater = new MaterielManage();
			PropertyUtils.copyProperties(mater, save);
			mater.setId(UUIDUtil.getId());
			mater.setState("1");
//			mater.setCreator("");
			mater.setCreatetime(System.currentTimeMillis());
//			mater.setModifier("");
			mater.setModifytime(System.currentTimeMillis());
			if(materielManageMapper.insertSelective(mater) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result findListByParmas(MaterielManageQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		MaterielManage m = new MaterielManage();
		if(query != null){
			PropertyUtils.copyProperties(m, query);
		}
		List<MaterielManage> list = materielManageMapper.selectSelective(m);
		result.setData(copyBeanList2RespList(list));
		return result;
	}
	
	private List<MaterielManageResp> copyBeanList2RespList(List<MaterielManage> list) throws Exception {
		List<MaterielManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<MaterielManageResp>();
			for(MaterielManage mater : list){
				listResp.add(copyBean2Resp(mater));
			}
		}
		return listResp;
	}
	
	private MaterielManageResp copyBean2Resp(MaterielManage bean) throws Exception {
		MaterielManageResp resp = null;
		if(bean != null){
			resp = new MaterielManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

	@Override
	public MaterielManageResp findOne(MaterielManageQuery query) throws Exception {
		if(query != null && StringUtils.isNotBlank(query.getId())){
			return copyBean2Resp(materielManageMapper.selectByPrimaryKey(query.getId()));
		}
		return null;
	}

	@Override
	public Result findMaxUtc(MaterielManageQuery query) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(query !=null  ){
			Long max =materielManageMapper.findMaxUtc();
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			rs.setData(max);
		}
		return rs;
	}
	
}
