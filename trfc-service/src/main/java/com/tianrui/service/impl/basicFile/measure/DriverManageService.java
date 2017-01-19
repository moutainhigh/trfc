package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.basicFile.measure.IDriverManageService;
import com.tianrui.api.req.basicFile.measure.DriverManageQuery;
import com.tianrui.api.req.basicFile.measure.DriverManageSave;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class DriverManageService implements IDriverManageService {
	
	@Autowired
	private DriverManageMapper driverManageMapper;

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

	@Override
	public Result addDriver(DriverManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			DriverManage driver = new DriverManage();
			driver.setIdentityno(save.getIdentityno());
			driver.setState("1");
			List<DriverManage> list = driverManageMapper.selectSelective(driver);
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				PropertyUtils.copyProperties(driver, save);
				driver.setId(UUIDUtil.getId());
//				driver.setCreator("");
				driver.setCreatetime(System.currentTimeMillis());
//				driver.setModifier("");
				driver.setModifytime(System.currentTimeMillis());
				if(driverManageMapper.insertSelective(driver) > 0){
					result.setData(driver);
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}

	@Override
	public Result updateDriver(DriverManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			DriverManage driver = new DriverManage();
			PropertyUtils.copyProperties(driver, save);
//			save.setModifier("");
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
	public Result deleteDriver(DriverManageQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			DriverManage driver = new DriverManage();
			driver.setId(query.getId());
			driver.setState("0");
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
	public DriverManageResp findOne(DriverManageQuery query) throws Exception{
		if(query != null && StringUtils.isNotBlank(query.getId())){
			return copyBean2Resp(driverManageMapper.selectByPrimaryKey(query.getId()));
		}
		return null;
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

}