package com.tianrui.web.action.system.auth;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tianrui.api.intf.system.auth.ISystemUserclientService;
import com.tianrui.api.intf.system.auth.ISystemUsersupplierService;
import com.tianrui.api.req.system.auth.SystemUserPswdReq;
import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.api.req.system.auth.SystemUserSaveReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;
@Controller
@RequestMapping("trfc/system/auth/userClient")
public class SystemUserclientAction {
	
	private Logger log = LoggerFactory.getLogger(SystemUserclientAction.class);
	
//	@Resource
//	private IsystemUserclientService systemUserclientService;
	@Resource
	private ISystemUserclientService systemUserclientService;
	@Resource
	private ISystemUsersupplierService systemUsersupplierService;
	//显示当前页
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("system/auth/userClientMgr");
		view.addObject("orgId", Constant.ORG_ID);
		view.addObject("orgName", Constant.ORG_NAME);
		return view;
	}
	
	//列表数据
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(SystemUserQueryReq req){
		Result rs= Result.getErrorResult();
		try {
			if(req.getIdentityTypes().equals("2")){
				rs = systemUsersupplierService.pages(req);
			}else {
				rs = systemUserclientService.pages(req);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	//编辑数据
	@RequestMapping(value="/editUser",method=RequestMethod.POST)
	@ResponseBody
	public Result editUser(SystemUserSaveReq req, HttpServletRequest request){
		Result rs= Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			req.setCurrUId(user.getId());
			if(req.getIdentityTypes().equals("2")){
				rs = systemUsersupplierService.editUser(req);
			}else{
				rs = systemUserclientService.editUser(req);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	//编辑数据
	@RequestMapping(value="/selectAccountUser",method=RequestMethod.POST)
	@ResponseBody
	public Result selectAccountUser(SystemUserSaveReq req, HttpServletRequest request) throws Exception {
		Result rs= Result.getErrorResult();
		if(req.getIdentityTypes().equals("2")){
			rs = systemUsersupplierService.selectAccountUser(req.getMobilePhone());
		}else {
			rs = systemUserclientService.selectAccountUser(req.getMobilePhone());
		}
		return rs;
		
	}
	//删除数据
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteUser(SystemUserQueryReq req){
		Result rs= Result.getErrorResult();
		try {
			if(req.getIdentityTypes().equals("2")){
				rs = systemUsersupplierService.delUser(req);
			}else {
				rs = systemUserclientService.delUser(req);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	//重置密码
	@RequestMapping(value="/resetPwd",method=RequestMethod.POST)
	@ResponseBody
	public Result resetPwd(SystemUserSaveReq req, HttpServletRequest request){
		Result rs= Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			req.setCurrUId(user.getId());
			if(req.getIdentityTypes().equals("2")){
				rs = systemUsersupplierService.resetPwd(req);
			}else{
				rs = systemUserclientService.resetPwd(req);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	
    //删除数据
    @RequestMapping(value="/updatePwd",method=RequestMethod.POST)
    @ResponseBody
    public Result updatePwd(SystemUserPswdReq req, HttpServletRequest request){
        Result rs= Result.getErrorResult();
        try {
            SystemUserResp user = SessionManager.getSessionUser(request);
            req.setCurrUId(user.getId());
            rs = systemUserclientService.updatePwd(req);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return rs;
    }
}
