package com.tianrui.service.impl.basicFile.businessControl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.businessControl.IMiningpointDbSettingService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.businessControl.MiningpointDbSettingQuery;
import com.tianrui.api.req.basicFile.businessControl.MiningpointDbSettingSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.businessControl.MiningpointDbSettingResp;
import com.tianrui.service.bean.basicFile.businessControl.MiningpointDbSetting;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.businessControl.MiningpointDbSettingMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
@Service
public class MiningpointDbSettingService implements IMiningpointDbSettingService{
	
	@Resource
	private MiningpointDbSettingMapper miningpointDbSettingMapper;
	@Resource
	private ISystemCodeService systemCodeService;
	@Resource
	private MaterielManageMapper materielManageMapper;
	@Resource
	private SystemUserMapper systemUserMapper;
	
	@Override
	public PaginationVO<MiningpointDbSettingResp> page(MiningpointDbSettingQuery query) {
		PaginationVO<MiningpointDbSettingResp> page = null;
		if(query != null){
			page = new PaginationVO<MiningpointDbSettingResp>();
			long count = miningpointDbSettingMapper.selectPrimaryPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo() - 1) * query.getPageSize());
				query.setLimit(query.getPageSize());
				List<MiningpointDbSettingResp> list = miningpointDbSettingMapper.selectPrimaryPage(query);
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
	public Result add(MiningpointDbSettingSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save!=null && StringUtils.isNotBlank(save.getMaterialid())
				&& StringUtils.isNotBlank(save.getMiningpointname())
				&& StringUtils.isNotBlank(save.getIsvalid())) {
			MiningpointDbSetting bean = new MiningpointDbSetting();
			bean.setMiningpointname(save.getMiningpointname());
			bean.setMaterialid(save.getMaterialid());
			List<MiningpointDbSetting> list = miningpointDbSettingMapper.selectSelective(bean);
			if (list == null || list.size() == 0) {
				bean.setId(UUIDUtil.getId());
				GetCodeReq codeReq = new GetCodeReq();
				codeReq.setCode("CKD");
				codeReq.setCodeType(true);
				codeReq.setUserid(save.getCurrId());
				bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
				bean.setIsvalid(save.getIsvalid());
				bean.setCreator(save.getCurrId());
				bean.setCreatetime(System.currentTimeMillis());
				bean.setModifier(save.getCurrId());
				bean.setModifytime(System.currentTimeMillis());
				bean.setRemark(save.getRemark());
				if(miningpointDbSettingMapper.insertSelective(bean) == 1 
						&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}
			}else{
				result.setErrorCode(ErrorCode.MININGPOINT_SETTING_ERROR);
			}
		}
		return result;
	}

	@Override
	public MiningpointDbSettingResp findOne(String id) throws Exception {
		MiningpointDbSettingResp resp = null;
		if(StringUtils.isNotBlank(id)){
			MiningpointDbSetting bean = miningpointDbSettingMapper.selectByPrimaryKey(id);
			resp = copyBean2Resp(bean, true);
		}
		return resp;
	}

	@Override
	public Result update(MiningpointDbSettingSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getId())
				&& StringUtils.isNotBlank(save.getMiningpointname())
				&& StringUtils.isNotBlank(save.getMaterialid())
				&& StringUtils.isNotBlank(save.getIsvalid())){
			MiningpointDbSetting bean = new MiningpointDbSetting();
			bean.setMiningpointname(save.getMiningpointname());
			bean.setMaterialid(save.getMaterialid());
			List<MiningpointDbSetting> list = miningpointDbSettingMapper.selectSelective(bean);
			if(list == null || list.size() == 0 || StringUtils.equals(list.get(0).getId(), save.getId())){
				bean.setId(save.getId());
				bean.setIsvalid(save.getIsvalid());
				bean.setModifier(save.getCurrId());
				bean.setModifytime(System.currentTimeMillis());
				bean.setRemark(save.getRemark());
				if(miningpointDbSettingMapper.updateByPrimaryKeySelective(bean) == 1){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}
			}else{
				result.setErrorCode(ErrorCode.MININGPOINT_SETTING_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result delete(String id) {
		Result result = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(id)){
			if(miningpointDbSettingMapper.deleteByPrimaryKey(id) == 1){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	
	private MiningpointDbSettingResp copyBean2Resp(MiningpointDbSetting bean, boolean flag) throws Exception {
		MiningpointDbSettingResp resp = null;
		if(bean != null){
			resp = new MiningpointDbSettingResp();
			PropertyUtils.copyProperties(resp, bean);
			if(flag){
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

	@Override
	public List<MiningpointDbSettingResp> selectByMaterialid(String materialid) {
		
		return miningpointDbSettingMapper.selectByMaterialid(materialid);
	}
	
	
}
