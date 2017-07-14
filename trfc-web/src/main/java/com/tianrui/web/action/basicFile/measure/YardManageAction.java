package com.tianrui.web.action.basicFile.measure;

import java.util.HashMap;
import java.util.List;
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

import com.tianrui.api.intf.basicFile.measure.IYardManageService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.measure.YardManageQuery;
import com.tianrui.api.req.basicFile.measure.YardManageSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.measure.YardManageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trfc/yard")
public class YardManageAction {

	private Logger log=LoggerFactory.getLogger(YardManageAction.class);
	
	@Autowired
	private IYardManageService yardManageService;
    @Autowired
    private ISystemCodeService systemCodeService;
	
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view=new ModelAndView("basicFile/measure/yard");
		return view;
	}
	
	/**
	 * 分页查询数据
	 * @param query
	 * @return
	 */
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(YardManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = yardManageService.page(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 新增堆场管理信息
	 * @param save
	 * @return
	 */
	@RequestMapping(value="/addView",method=RequestMethod.GET)
	@ResponseBody
	public Result addView(HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
		    Map<String, String> map = new HashMap<String, String>();
		    SystemUserResp user = SessionManager.getSessionUser(request);
            GetCodeReq codeReq = new GetCodeReq();
            codeReq.setCode("DC");
            codeReq.setCodeType(true);
            codeReq.setUserid(user.getId());
            map.put("code", systemCodeService.getCode(codeReq).getData().toString());
            result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 新增堆场管理信息
	 * @param save
	 * @return
	 */
	@RequestMapping(value="/addYard",method=RequestMethod.POST)
	@ResponseBody
	public Result addYard(YardManageSave save, HttpServletRequest request){
	    Result result = Result.getSuccessResult();
	    try {
            SystemUserResp user = SessionManager.getSessionUser(request);
            save.setCurrId(user.getId());
	        result = yardManageService.addYard(save);
	    } catch (Exception e) {
	        log.error(e.getMessage(), e);
	        result.setErrorCode(ErrorCode.SYSTEM_ERROR);
	    }
	    return result;
	}
	/**
	 * 修改堆场管理信息
	 * @param save
	 * @return
	 */
	@RequestMapping(value="/updateYard",method=RequestMethod.POST)
	@ResponseBody
	public Result updateYard(YardManageSave save, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
            SystemUserResp user = SessionManager.getSessionUser(request);
            save.setCurrId(user.getId());
			result = yardManageService.updateYard(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	/**
	 * 删除堆场管理信息
	 * @param query
	 * @return
	 */
	@RequestMapping(value="/delYard",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteYard(YardManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = yardManageService.deleteYard(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/autoCompleteSearch")
	@ResponseBody
	public List<YardManageResp> autoCompleteSearch(String term){
		List<YardManageResp> list = null;
		try {
			list = yardManageService.autoCompleteSearch(term.trim());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}
	
}
