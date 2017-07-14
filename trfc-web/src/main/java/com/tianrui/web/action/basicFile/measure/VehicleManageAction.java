package com.tianrui.web.action.basicFile.measure;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.measure.VehicleManageQuery;
import com.tianrui.api.req.basicFile.measure.VehicleManageSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;


@Controller
@RequestMapping("/trfc/vehicle")
public class VehicleManageAction {
	
	private Logger log = LoggerFactory.getLogger(VehicleManageAction.class);
	
	@Autowired
	private IVehicleManageService vehicleManageService;
	
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/measure/vehicle");
		return view;
	}
	

	@RequestMapping("/page")
	@ResponseBody
	public Result page(VehicleManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<VehicleManageResp> page = vehicleManageService.page(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
				
	@RequestMapping("/addView")
	@ResponseBody
	public Result addView(HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			SystemUserResp user = SessionManager.getSessionUser(request);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("CL");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			map.put("code", systemCodeService.getCode(codeReq).getData());
			map.put("orgId", Constant.ORG_ID);
			map.put("orgName", Constant.ORG_NAME);
			result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Result add(VehicleManageSave save, HttpServletRequest request){
		Result result = Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setCurrUId(user.getId());
			result = vehicleManageService.add(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Result update(VehicleManageSave save, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setCurrUId(user.getId());
			result = vehicleManageService.update(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(VehicleManageQuery query, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrUId(user.getId());
			result = vehicleManageService.delete(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/delblacklist")
	@ResponseBody
	public Result delblacklist(VehicleManageQuery query, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrUId(user.getId());
			result = vehicleManageService.delblacklist(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/addblacklistView")
	@ResponseBody
	public Result addblacklistView(HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			SystemUserResp user = SessionManager.getSessionUser(request);
			map.put("b_creator", user.getId());
			map.put("b_creatorname", user.getName());
			Date date = new Date();
			map.put("b_createtime", date.getTime());
			map.put("b_createtimeStr", DateUtil.getDateString(date, "yyyy-MM-dd HH:mm:ss"));
			result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/addblacklist")
	@ResponseBody
	public Result addblacklist(VehicleManageQuery query, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrUId(user.getId());
			result = vehicleManageService.addblacklist(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/autoCompleteSearch")
	@ResponseBody
	public List<VehicleManageResp> autoCompleteSearch(String term){
		List<VehicleManageResp> list = null;
		try {
			list = vehicleManageService.autoCompleteSearch("0", term.trim(), null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}

    @RequestMapping("/autoCompleteNotBlackSearch")
    @ResponseBody
    public List<VehicleManageResp> autoCompleteNotBlackSearch(String term){
        List<VehicleManageResp> list = null;
        try {
            list = vehicleManageService.autoCompleteSearch(null, term.trim(), Constant.ZERO_STRING);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }
	
	@RequestMapping("/autoCompleteDYSearch")
	@ResponseBody
	public List<VehicleManageResp> autoCompleteDYSearch(String term){
		List<VehicleManageResp> list = null;
		try {
			list = vehicleManageService.autoCompleteSearch("1", term.trim(), null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping("/findOne")
	@ResponseBody
	public VehicleManageResp findOne(String id){
		VehicleManageResp resp = null;
		try {
			resp = vehicleManageService.findOne(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return resp;
	}
}
