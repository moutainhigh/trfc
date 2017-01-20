package com.tianrui.web.action.system.base;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.system.base.SystemCodeReq;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
@Controller
@RequestMapping("/trfc/system/base/code")
public class SystemCodeAction {
	Logger log = LoggerFactory.getLogger(SystemCodeAction.class);
	
	@Resource
	private ISystemCodeService systemCodeService;
	//显示当前页
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("system/base/systemCode");
		return view;
	}
	//列表数据
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(SystemCodeReq req){
		Result rs = Result.getErrorResult();
		try {
			 rs = systemCodeService.select(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	//修改
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result edit(SystemCodeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = systemCodeService.update(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	//新增
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result add(SystemCodeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = systemCodeService.insert(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	//删除
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result delete(SystemCodeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = systemCodeService.delete(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
		
	}
	//检测单据代号
	@RequestMapping(value="/checkCode",method=RequestMethod.POST)
	@ResponseBody
	public Result checkCode(SystemCodeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = systemCodeService.checkCode(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
}
