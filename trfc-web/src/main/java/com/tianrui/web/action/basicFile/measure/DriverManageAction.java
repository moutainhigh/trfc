package com.tianrui.web.action.basicFile.measure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.measure.IDriverManageService;
import com.tianrui.api.req.basicFile.measure.DriverManageReq;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@RequestMapping("driver")
@Controller
public class DriverManageAction {
	
	private Logger log = LoggerFactory.getLogger(DriverManageAction.class);
	
	@Autowired
	private IDriverManageService driverManageService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/measure/driver");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(DriverManageReq req){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<DriverManageResp> page = driverManageService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@RequestMapping("addDriver")
	@ResponseBody
	public Result addDriver(DriverManageReq req){
		Result result = Result.getSuccessResult();
		try {
			int n = driverManageService.addDriver(req);
			if(n > 0){
				result.setData(n);
			}else if(n == -1){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("updateDriver")
	@ResponseBody
	public Result updateDriver(DriverManageReq req){
		Result result = Result.getSuccessResult();
		try {
			int n = driverManageService.updateDriver(req);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("delDriver")
	@ResponseBody
	public Result delDriver(DriverManageReq req){
		Result result = Result.getSuccessResult();
		try {
			int n = driverManageService.delDriver(req);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

}
