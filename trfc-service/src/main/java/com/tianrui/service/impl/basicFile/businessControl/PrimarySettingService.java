package com.tianrui.service.impl.basicFile.businessControl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.businessControl.IPrimarySettingService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.businessControl.PrimarySettingQuery;
import com.tianrui.api.req.basicFile.businessControl.PrimarySettingSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.businessControl.PrimarySettingResp;
import com.tianrui.service.bean.basicFile.businessControl.PrimarySetting;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.businessControl.PrimarySettingMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class PrimarySettingService implements IPrimarySettingService {
	
	@Autowired
	private PrimarySettingMapper primarySettingMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private SupplierManageMapper supplierManageMapper;
	@Autowired
	private MaterielManageMapper materielManageMapper;
	@Autowired
	private SystemUserMapper systemUserMapper;
	
	@Override
	public PaginationVO<PrimarySettingResp> page(PrimarySettingQuery query){
		PaginationVO<PrimarySettingResp> page = null;
		if(query != null){
			page = new PaginationVO<PrimarySettingResp>();
			long count = primarySettingMapper.selectPrimaryPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo() - 1) * query.getPageSize());
				query.setLimit(query.getPageSize());
				List<PrimarySettingResp> list = primarySettingMapper.selectPrimaryPage(query);
				page.setList(list);
			}
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
			page.setTotal(count);
		}
		return page;
	}

	@Transactional
	@Override
	public Result add(PrimarySettingSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getSupplierid())
				&& StringUtils.isNotBlank(save.getMaterialid())
				&& StringUtils.isNotBlank(save.getIsvalid())){
			PrimarySetting bean = new PrimarySetting();
			bean.setSupplierid(save.getSupplierid());
			bean.setMaterialid(save.getMaterialid());
			List<PrimarySetting> list = primarySettingMapper.selectSelective(bean);
			if(list == null || list.size() == 0){
				bean.setId(UUIDUtil.getId());
				GetCodeReq codeReq = new GetCodeReq();
				codeReq.setCode("YF");
				codeReq.setCodeType(true);
				codeReq.setUserid(save.getCurrId());
				bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
				bean.setIsvalid(save.getIsvalid());
				bean.setCreator(save.getCurrId());
				bean.setCreatetime(System.currentTimeMillis());
				bean.setModifier(save.getCurrId());
				bean.setModifytime(System.currentTimeMillis());
				bean.setRemark(save.getRemark());
				if(primarySettingMapper.insertSelective(bean) == 1 
						&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}
			}else{
				result.setErrorCode(ErrorCode.PRIMARY_SETTING_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result update(PrimarySettingSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getId())
				&& StringUtils.isNotBlank(save.getSupplierid())
				&& StringUtils.isNotBlank(save.getMaterialid())
				&& StringUtils.isNotBlank(save.getIsvalid())){
			PrimarySetting bean = new PrimarySetting();
			bean.setSupplierid(save.getSupplierid());
			bean.setMaterialid(save.getMaterialid());
			List<PrimarySetting> list = primarySettingMapper.selectSelective(bean);
			if(list == null || list.size() == 0 || StringUtils.equals(list.get(0).getId(), save.getId())){
				bean.setId(save.getId());
				bean.setIsvalid(save.getIsvalid());
				bean.setModifier(save.getCurrId());
				bean.setModifytime(System.currentTimeMillis());
				bean.setRemark(save.getRemark());
				if(primarySettingMapper.updateByPrimaryKeySelective(bean) == 1){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}
			}else{
				result.setErrorCode(ErrorCode.PRIMARY_SETTING_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public PrimarySettingResp findOne(String id) throws Exception {
		PrimarySettingResp resp = null;
		if(StringUtils.isNotBlank(id)){
			PrimarySetting bean = primarySettingMapper.selectByPrimaryKey(id);
			resp = copyBean2Resp(bean, true);
		}
		return resp;
	}

	@Override
	public Result delete(String id) {
		Result result = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(id)){
			if(primarySettingMapper.deleteByPrimaryKey(id) == 1){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
//	private List<PrimarySettingResp> copyBeanList2RespList(List<PrimarySetting> list, boolean flag) throws Exception {
//		List<PrimarySettingResp> listResp = null;
//		if(list != null && list.size() > 0){
//			listResp = new ArrayList<PrimarySettingResp>();
//			for(PrimarySetting driver : list){
//				listResp.add(copyBean2Resp(driver, flag));
//			}
//		}
//		return listResp;
//	}
	
	private PrimarySettingResp copyBean2Resp(PrimarySetting bean, boolean flag) throws Exception {
		PrimarySettingResp resp = null;
		if(bean != null){
			resp = new PrimarySettingResp();
			PropertyUtils.copyProperties(resp, bean);
			if(flag){
				SupplierManage supplier = supplierManageMapper.selectByPrimaryKey(bean.getSupplierid());
				if(supplier != null){
					resp.setSuppliername(supplier.getName());
				}
				MaterielManage materiel = materielManageMapper.selectByPrimaryKey(bean.getMaterialid());
				if(materiel != null){
					resp.setMaterialname(materiel.getName());
				}
				SystemUser user = systemUserMapper.selectByPrimaryKey(bean.getCreator());
				if(user != null){
					resp.setCreatename(user.getName());
				}
			}
		}
		return resp;
	}
	
}