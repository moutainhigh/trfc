package com.tianrui.web.action.businessManage.otherManage;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.otherManage.IOtherArriveService;
import com.tianrui.api.req.businessManage.otherManage.OtherArriveReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("trfc/otherRKArrive")
public class OtherRKArriveAction {
	Logger logger = LoggerFactory.getLogger(OtherRKArriveAction.class);
	
	@Autowired
	private IOtherArriveService otherArriveService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/otherManage/otherRKArrive");
		return view;
	}
	
	@RequestMapping("addMain")
	public ModelAndView addHTML(){
		ModelAndView view = new ModelAndView("businessManage/otherManage/otherRKArrive_add");
		return view;
	}
	@RequestMapping("editMain")
	public ModelAndView editHTML(){
		ModelAndView view = new ModelAndView("businessManage/otherManage/otherRKArrive_edit");
		return view;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Result update(OtherArriveReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			req.setUserid(user.getId());
			rs = otherArriveService.update(req);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	
	@RequestMapping("updateOperation")
	@ResponseBody
	public Result updateOperation(OtherArriveReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			req.setUserid(user.getId());
			rs = otherArriveService.updateOperation(req);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Result add(OtherArriveReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			req.setUserid(user.getId());
			req.setBusinesstype("5");
			req.setCodekey("QRN");
			rs = otherArriveService.add(req);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(OtherArriveReq req){
		Result rs = Result.getSuccessResult();
		try {
			req.setBusinesstype("5");
			rs = otherArriveService.page(req);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	
	@RequestMapping("findOne")
	@ResponseBody
	public Result findOne(String id){
		Result rs = Result.getSuccessResult();
		try {
			rs = otherArriveService.findOne(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
    
    @RequestMapping("forceOutFactory")
    @ResponseBody
    public Result forceOutFactory(OtherArriveReq req,HttpServletRequest request){
        Result rs = Result.getSuccessResult();
        try {
            SystemUserResp user = SessionManager.getSessionUser(request);
            req.setUserid(user.getId());
            rs = otherArriveService.forceOutFactory(req);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return rs;
    }
	
}
