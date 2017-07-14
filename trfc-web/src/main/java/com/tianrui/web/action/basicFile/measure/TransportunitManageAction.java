package com.tianrui.web.action.basicFile.measure;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.measure.ITransportunitManageService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.measure.TransportunitManageQuery;
import com.tianrui.api.req.basicFile.measure.TransportunitManageSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trfc/transport")
public class TransportunitManageAction {
	
	private Logger log=LoggerFactory.getLogger(TransportunitManageAction.class);
	
	@Autowired 
	private ITransportunitManageService transportunitManageService;
    @Autowired
    private ISystemCodeService systemCodeService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view=new ModelAndView("basicFile/measure/transport");
		return view;
	}
	
	/**
	 * 分页查询数据
	 * @param query
	 * @return
	 */
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(TransportunitManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = transportunitManageService.page(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 初始化新增参数
	 * @param save
	 * @return
	 */
	@RequestMapping(value="/addView",method=RequestMethod.GET)
	@ResponseBody
	public Result addTransportunit(HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
            Map<String, String> map = new HashMap<String, String>();
            SystemUserResp user = SessionManager.getSessionUser(request);
            GetCodeReq codeReq = new GetCodeReq();
            codeReq.setCode("TC");
            codeReq.setCodeType(true);
            codeReq.setUserid(user.getId());
            map.put("code", systemCodeService.getCode(codeReq).getData().toString());
            codeReq.setCodeType(false);
            map.put("internalcode", systemCodeService.getCode(codeReq).getData().toString());
            map.put("orgName", Constant.ORG_NAME);
            result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 新增运输单位信息
	 * @param save
	 * @return
	 */
	@RequestMapping(value="/addTransport",method=RequestMethod.POST)
	@ResponseBody
	public Result addTransportunit(TransportunitManageSave save, HttpServletRequest request){
	    Result result = Result.getSuccessResult();
	    try {
            SystemUserResp user = SessionManager.getSessionUser(request);
            save.setCurrId(user.getId());
	        result = transportunitManageService.addTransportunit(save);
	    } catch (Exception e) {
	        log.error(e.getMessage(), e);
	        result.setErrorCode(ErrorCode.SYSTEM_ERROR);
	    }
	    return result;
	}
	
	/**
	 * 修改运输单位信息
	 * @param save
	 * @return
	 */
	@RequestMapping(value="/updateTransport",method=RequestMethod.POST)
	@ResponseBody
	public Result updateTransportunit(TransportunitManageSave save, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
            SystemUserResp user = SessionManager.getSessionUser(request);
            save.setCurrId(user.getId());
			result = transportunitManageService.updateTransportunit(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 删除运输单位信息
	 * @param query
	 * @return
	 */
	@RequestMapping(value="/delTransport",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteTransportunit(TransportunitManageQuery query){
		Result result = Result.getSuccessResult();
		try  {
			result = transportunitManageService.deleteTransportunit(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
