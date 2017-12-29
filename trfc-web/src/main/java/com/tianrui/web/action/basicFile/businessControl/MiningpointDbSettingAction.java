package com.tianrui.web.action.basicFile.businessControl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.businessControl.IMiningpointDbSettingService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.businessControl.MiningpointDbSettingQuery;
import com.tianrui.api.req.basicFile.businessControl.MiningpointDbSettingSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.businessControl.MiningpointDbSettingResp;
import com.tianrui.api.resp.basicFile.nc.SupplierManageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trfc/miningpoint")
public class MiningpointDbSettingAction {

	Logger logger = LoggerFactory.getLogger(MiningpointDbSettingAction.class);
	
	@Resource
	private IMiningpointDbSettingService miningpointDbSettingService;
	@Resource
	private ISystemCodeService systemCodeService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/businessControl/miningpointDbSetting");
		return view;
	}
	
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(MiningpointDbSettingQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<MiningpointDbSettingResp> page = miningpointDbSettingService.page(query);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("addView")
	@ResponseBody
	public Result addView(HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			SystemUserResp user = SessionManager.getSessionUser(request);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("CKD");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			map.put("code", systemCodeService.getCode(codeReq).getData());
			map.put("creator", user.getName());
			map.put("createtime", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			result.setData(map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	

	@RequestMapping("add")
	@ResponseBody
	public Result add(MiningpointDbSettingSave save, HttpServletRequest request){
		Result result = Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setCurrId(user.getId());
			result = miningpointDbSettingService.add(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("updateView")
	@ResponseBody
	public Result updateView(String id){
		Result result = Result.getSuccessResult();
		try {
			result.setData(miningpointDbSettingService.findOne(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Result update(MiningpointDbSettingSave save, HttpServletRequest request){
		Result result = Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setCurrId(user.getId());
			result = miningpointDbSettingService.update(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Result delete(String id){
		Result result = Result.getErrorResult();
		try {
			result = miningpointDbSettingService.delete(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping("selectByMaterialid")
	@ResponseBody
	public List<MiningpointDbSettingResp> selectByMaterialid(String materialid,String supplierid){
		List<MiningpointDbSettingResp> list = null;
		try {
			list = miningpointDbSettingService.selectByMaterialid(materialid,supplierid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping("/autoCompleteSearch")
	@ResponseBody
	public List<MiningpointDbSettingResp> autoCompleteSearch(String term,String materialid,String supplierid){
		List<MiningpointDbSettingResp> list = null;
		try {
			list = miningpointDbSettingService.autoCompleteSearch(term.trim(),materialid,supplierid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
}
