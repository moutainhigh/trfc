package com.tianrui.web.action.basicFile.measure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.measure.IDriverManageService;
import com.tianrui.api.req.basicFile.measure.DriverManageQuery;
import com.tianrui.api.req.basicFile.measure.DriverManageSave;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@RequestMapping("/trfc/driver")
@Controller
public class DriverManageAction {
	
	private Logger log = LoggerFactory.getLogger(DriverManageAction.class);
	
	@Autowired
	private IDriverManageService driverManageService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/measure/driver");
		return view;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(DriverManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<DriverManageResp> page = driverManageService.page(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@RequestMapping("/addDriver")
	@ResponseBody
	public Result addDriver(DriverManageSave save){
		Result result = Result.getSuccessResult();
		try {
			result = driverManageService.addDriver(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/updateDriver")
	@ResponseBody
	public Result updateDriver(DriverManageSave save){
		Result result = Result.getSuccessResult();
		try {
			result = driverManageService.updateDriver(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/delDriver")
	@ResponseBody
	public Result deleteDriver(DriverManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = driverManageService.deleteDriver(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

}
