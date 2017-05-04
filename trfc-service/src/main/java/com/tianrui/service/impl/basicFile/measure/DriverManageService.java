package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.measure.IDriverManageService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.measure.DriverManageQuery;
import com.tianrui.api.req.basicFile.measure.DriverManageSave;
import com.tianrui.api.req.businessManage.app.AppDriverSaveReq;
import com.tianrui.api.req.businessManage.app.AppQueryReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.api.resp.businessManage.app.AppDriverResp;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class DriverManageService implements IDriverManageService {
	
	@Autowired
	private DriverManageMapper driverManageMapper;
	@Autowired
	private ISystemCodeService systemCodeService;

	@Override
	public PaginationVO<DriverManageResp> page(DriverManageQuery query) throws Exception {
		PaginationVO<DriverManageResp> page = null;
		if(query != null){
			page = new PaginationVO<DriverManageResp>();
			long count = driverManageMapper.findDriverPageCount(query);
			if(count > 0){
				query.setState("1");
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<DriverManage> list = driverManageMapper.findDriverPage(query);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}

	@Transactional
	@Override
	public Result add(DriverManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			DriverManage driver = new DriverManage();
			if(StringUtils.isNotBlank(save.getIdentityno()) && save.getIdentityno().length()==18){
			driver.setIdentityno(save.getIdentityno());
			driver.setState("1");
			List<DriverManage> list = driverManageMapper.selectSelective(driver);
			if(list == null || list.size() == 0){
				PropertyUtils.copyProperties(driver, save);
				driver.setId(UUIDUtil.getId());
				GetCodeReq codeReq = new GetCodeReq();
				codeReq.setCode("DR");
				codeReq.setCodeType(true);
				codeReq.setUserid(save.getCurrId());
				driver.setCode(String.valueOf(systemCodeService.getCode(codeReq).getData()));
				codeReq.setCodeType(false);
				driver.setInternalcode(String.valueOf(systemCodeService.getCode(codeReq).getData()));
				driver.setOrgid(Constant.ORG_ID);
				driver.setOrgname(Constant.ORG_NAME);
				driver.setCreator(save.getCurrId());
				driver.setCreatetime(System.currentTimeMillis());
				driver.setModifier(save.getCurrId());
				driver.setModifytime(System.currentTimeMillis());
				if(driverManageMapper.insertSelective(driver) > 0){
					systemCodeService.updateCodeItem(codeReq);
					codeReq.setCodeType(true);
					systemCodeService.updateCodeItem(codeReq);
					result.setData(driver);
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}
			}else{
				result.setError("身份证号码必须为18位!");
			}
		}
		return result;
	}

	@Override
	public Result update(DriverManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			DriverManage driver = new DriverManage();
			PropertyUtils.copyProperties(driver, save);
			save.setModifier(save.getCurrId());
			save.setModifytime(System.currentTimeMillis());
			if(driverManageMapper.updateByPrimaryKeySelective(driver) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result delete(DriverManageQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			DriverManage driver = new DriverManage();
			driver.setId(query.getId());
			driver.setState("0");
			driver.setModifier(query.getCurrId());
			driver.setModifytime(System.currentTimeMillis());
			if(driverManageMapper.updateByPrimaryKeySelective(driver) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result findListByParmas(DriverManageQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		DriverManage driver = new DriverManage();
		if(query != null){
			PropertyUtils.copyProperties(driver, query);
		}
		driver.setState("1");
		List<DriverManage> list = driverManageMapper.selectSelective(driver);
		result.setData(copyBeanList2RespList(list));
		return result;
	}
	
	@Override
	public DriverManageResp findOne(String id) throws Exception{
		if( StringUtils.isNotBlank(id) ){
			return copyBean2Resp(driverManageMapper.selectByPrimaryKey(id));
		}
		return null;
	}

	@Override
	public List<DriverManageResp> autoCompleteSearch(String likeName) throws Exception {
		return copyBeanList2RespList(driverManageMapper.autoCompleteSearch(likeName));
	}
	
	private List<DriverManageResp> copyBeanList2RespList(List<DriverManage> list) throws Exception {
		List<DriverManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<DriverManageResp>();
			for(DriverManage driver : list){
				listResp.add(copyBean2Resp(driver));
			}
		}
		return listResp;
	}
	
	private DriverManageResp copyBean2Resp(DriverManage bean) throws Exception {
		DriverManageResp resp = null;
		if(bean != null){
			resp = new DriverManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

	@Override
	public PaginationVO<AppDriverResp> appToPage(AppQueryReq req) {
		PaginationVO<AppDriverResp> page = null;
		if (req != null) {
			page = new PaginationVO<AppDriverResp>();
			long count = driverManageMapper.appQueryDriverPageCount(req);
			if (count > 0) {
				req.setStart((req.getPageNo() - 1) * req.getPageSize());
				req.setLimit(req.getPageSize());
				List<AppDriverResp> list = this.driverManageMapper.appQueryDriverPage(req);
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		return page;
	}

	@Override
	public Result appDriverCreate(AppDriverSaveReq req) throws Exception {
		Result result = Result.getParamErrorResult();
		if(req != null
				&& StringUtils.isNotBlank(req.getName())
				&& StringUtils.isNotBlank(req.getIdNo())){
			DriverManage bean = new DriverManage();
			bean.setId(UUIDUtil.getId());
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("DR");
			codeReq.setCodeType(true);
			codeReq.setUserid(req.getUserId());
			bean.setCode(String.valueOf(systemCodeService.getCode(codeReq).getData()));
			codeReq.setCodeType(false);
			bean.setInternalcode(String.valueOf(systemCodeService.getCode(codeReq).getData()));
			bean.setName(req.getName());
			bean.setAbbrname(req.getAbbrname());
			bean.setTelephone(req.getMobile());
			bean.setAddress(req.getAddr());
			bean.setIdentityno(req.getIdNo());
			bean.setIsvalid("1");
			bean.setOrgid(Constant.ORG_ID);
			bean.setOrgname(Constant.ORG_NAME);
			bean.setState("1");
			bean.setRemarks(req.getRemark());
			bean.setCreator(req.getUserId());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(req.getUserId());
			bean.setModifytime(System.currentTimeMillis());
			if(driverManageMapper.insertSelective(bean) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

}