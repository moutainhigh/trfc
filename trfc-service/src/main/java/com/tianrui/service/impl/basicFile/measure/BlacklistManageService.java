package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.measure.IBlacklistManageService;
import com.tianrui.api.req.basicFile.businessControl.PrimarySettingSave;
import com.tianrui.api.req.basicFile.measure.BlacklistManageQuery;
import com.tianrui.api.req.basicFile.measure.BlacklistManageReq;
import com.tianrui.api.req.basicFile.measure.BlacklistManageSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.measure.BlacklistManageResp;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.api.resp.basicFile.measure.YardManageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.bean.basicFile.businessControl.PrimarySetting;
import com.tianrui.service.bean.basicFile.measure.BlacklistManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.measure.YardManage;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.impl.system.auth.SystemUserService;
import com.tianrui.service.mapper.basicFile.measure.BlacklistManageMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class BlacklistManageService implements IBlacklistManageService {
	
	@Autowired
	private BlacklistManageMapper blacklistManageMapper;
	@Autowired
	private SystemUserService userService;

	
	@Override
	public int deleteBlacklist(String id){
		int n = 0;
		if(StringUtils.isNotBlank(id)){
			n = blacklistManageMapper.deleteBlacklistByVid(id);
		}
		return n;
	}

	@Override
	public int addBlacklist(BlacklistManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			BlacklistManage blacklist = new BlacklistManage();
			blacklist.setVehicleno(req.getVehicleno());
			List<VehicleManage> list = this.blacklistManageMapper.selectSelective(blacklist);
			if(list != null && list.size() > 0){
				return -1;
			}
			PropertyUtils.copyProperties(blacklist, req);
			blacklist.setId(UUIDUtil.getId());
//			blacklist.setCreator("");
			blacklist.setCreatetime(System.currentTimeMillis());
//			blacklist.setModifier("");
			blacklist.setModifytime(System.currentTimeMillis());
			n = this.blacklistManageMapper.insert(blacklist);
		}
		return n;
	}

	@Override
	public Result page(BlacklistManageQuery query) throws Exception {
		Result result=Result.getSuccessResult();
		if(query!=null){
			PaginationVO<BlacklistManageResp> page = new PaginationVO<BlacklistManageResp>();
			long count=blacklistManageMapper.findBlacklistPageCount(query);
			if(count>0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<BlacklistManage> list=blacklistManageMapper.findBlacklistPage(query);
				page.setList(copyBeanList2RespList(list));
				//成功时保存数据
				result.setData(page);
			}
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
			page.setTotal(count);
		}
		return result;
	}

	
	

	@Override
	public Result addCarBlacklist(BlacklistManageSave save) throws Exception{
		Result result=Result.getParamErrorResult();
	if(save!=null){
		BlacklistManage blacklist=new BlacklistManage();
		blacklist.setVehicleno(save.getVehicleno());
		List<VehicleManage> list = this.blacklistManageMapper.selectSelective(blacklist);
		if(list != null && list.size() > 0){
			result.setErrorCode(ErrorCode.VEHICLE_EXIST);
		}
		PropertyUtils.copyProperties(blacklist, save);
		blacklist.setId(UUIDUtil.getId());
//		blacklist.setCreator("");
		blacklist.setCreatetime(System.currentTimeMillis());
//		blacklist.setModifier("");
		blacklist.setModifytime(System.currentTimeMillis());
		this.blacklistManageMapper.insert(blacklist);
	}
	return result;
	}

	/**
	 * 集合转换
	 * @param List<YardManage> list
	 * @return List<YardManageResp> 
	 * @throws Exception
	 */
	private List<BlacklistManageResp> copyBeanList2RespList(List<BlacklistManage> list) throws Exception {
		List<BlacklistManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<BlacklistManageResp>();
			for(BlacklistManage blacklist : list){
				listResp.add(copyBeanList2RespList(blacklist));
			}
		}
		return listResp;
	}


	/**
	 * 实体bean类型转换
	 * @param BlacklistManage bean
	 * @return BlacklistManageResp
	 * @throws Exception
	 */
	private BlacklistManageResp copyBeanList2RespList(BlacklistManage bean) throws Exception {
		BlacklistManageResp resp = null;
		if(bean != null){
			resp = new BlacklistManageResp();
			PropertyUtils.copyProperties(resp, bean);
			if(StringUtils.isNotBlank(bean.getCreator())){
				SystemUserResp user = userService.getUser(bean.getCreator());
				if(user != null){
					resp.setCreatorName(user.getName());
				}
			}
		}
		return resp;
	}

	
	@Override
	public Result deleteblacklist(BlacklistManageQuery query) {
		return null;
	}

	@Transactional
	@Override
	public Result add(BlacklistManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getVehicleno())){
			BlacklistManage bean = new BlacklistManage();
			bean.setVehicleno(save.getVehicleno());
			List<VehicleManage> list = blacklistManageMapper.selectSelective(bean);
			if(list == null || list.size() == 0){
				bean.setId(UUIDUtil.getId());
				GetCodeReq codeReq = new GetCodeReq();
				codeReq.setUserid(save.getVehicleno());
				bean.setCreator(save.getCreator());
				bean.setCreatetime(System.currentTimeMillis());
				bean.setRemarks(save.getRemarks());
				if(blacklistManageMapper.insertSelective(bean) == 1){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}
			}else{
				result.setErrorCode(ErrorCode.PRIMARY_SETTING_ERROR);
			}
		}
		return result;
	}

	
	
	
}