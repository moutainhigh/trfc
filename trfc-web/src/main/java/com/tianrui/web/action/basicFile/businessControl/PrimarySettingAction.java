package com.tianrui.web.action.basicFile.businessControl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.businessControl.IPrimarySettingService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.businessControl.PrimarySettingQuery;
import com.tianrui.api.req.basicFile.businessControl.PrimarySettingSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.businessControl.PrimarySettingResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trfc/primary")
public class PrimarySettingAction {
	
	Logger logger = LoggerFactory.getLogger(PrimarySettingAction.class);
	
	@Autowired
	private IPrimarySettingService primarySettingService;
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/businessControl/primarySetting");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(PrimarySettingQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<PrimarySettingResp> page = primarySettingService.page(query);
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
			codeReq.setCode("YF");
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
	public Result add(PrimarySettingSave save, HttpServletRequest request){
		Result result = Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setCurrId(user.getId());
			result = primarySettingService.add(save);
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
			result.setData(primarySettingService.findOne(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Result update(PrimarySettingSave save, HttpServletRequest request){
		Result result = Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setCurrId(user.getId());
			result = primarySettingService.update(save);
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
			result = primarySettingService.delete(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}