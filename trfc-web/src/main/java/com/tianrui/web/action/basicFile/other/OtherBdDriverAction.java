package com.tianrui.web.action.basicFile.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.other.IOtherBdDriverService;
import com.tianrui.api.req.basicFile.other.OtherBdDriverReq;
import com.tianrui.api.resp.basicFile.other.OtherBdDriverResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/basicFile/other/driver")
public class OtherBdDriverAction {
	
	private Logger log=LoggerFactory.getLogger(OtherBdDriverAction.class);
	
	@Autowired
	private IOtherBdDriverService otherBdDriverService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		return new ModelAndView("basicFile/other/driver");
	}
	
	/**
	 * 分页查询数据Action
	 * @param req
	 * @return
	 */
	@RequestMapping(value="page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(OtherBdDriverReq req){
		Result result=Result.getErrorResult();
		try {
			result = otherBdDriverService.page(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
		return result;
	}
	
	/**
	 * 获取新增时需要的编号和内码
	 * @return
	 */
	@RequestMapping(value="toAddOtherDriver",method=RequestMethod.POST)
	@ResponseBody
	public Result toAddOtherDriver(){
		Result result = Result.getSuccessResult();
		try {
			OtherBdDriverResp resp = new OtherBdDriverResp();
			resp.setCode(otherBdDriverService.getDriverCode());
			resp.setInnercode(otherBdDriverService.getDriverInnercode());
			result.setData(resp);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
	
	/**
	 * 新增其他司机信息Action
	 * @param req
	 * @return
	 */
	@RequestMapping(value="addOtherDriver",method=RequestMethod.POST)
	@ResponseBody
	public Result addOtherDriver(OtherBdDriverReq req){
		Result result=Result.getErrorResult();
		try {
			result = otherBdDriverService.addDriver(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 修改其他司机信息Action
	 * @param req
	 * @return
	 */
	@RequestMapping(value="editOtherDriver",method=RequestMethod.POST)
	@ResponseBody
	public Result editOtherDriver(OtherBdDriverReq req){
		Result result=Result.getErrorResult();
		try {
			result =otherBdDriverService.editDriver(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 删除其他司机信息Action
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteOtherDriver",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteOtherDriver(String id){
		Result result=Result.getErrorResult();
		try {
			result = otherBdDriverService.deleteDriver(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 名称重复检测Action
	 * @param name
	 * @return
	 */
	@RequestMapping(value="checkName",method=RequestMethod.POST)
	@ResponseBody
	public Result checkName(String name){
		Result result=Result.getErrorResult();
		try {
			result =  otherBdDriverService.checkName(name);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}
	
}
