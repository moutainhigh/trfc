package com.tianrui.web.action.basicFile.measure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.measure.IMinemouthManageService;
import com.tianrui.api.req.basicFile.measure.MinemouthManageQuery;
import com.tianrui.api.req.basicFile.measure.MinemouthManageSave;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/minemouth")
public class MinemouthManageAction {
	
	private Logger log=LoggerFactory.getLogger(MinemouthManageAction.class);
	
	@Autowired
	private IMinemouthManageService minemouthManageService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view=new ModelAndView("basicFile/measure/minemouth");
		return view;
	}
	

	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(MinemouthManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = minemouthManageService.page(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@RequestMapping(value="/addMinemouth",method=RequestMethod.POST)
	@ResponseBody
	public Result addMinemouth(MinemouthManageSave save){
		Result result = Result.getSuccessResult();
		try {
			result = minemouthManageService.addMinemouth(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/updateMinemouth",method=RequestMethod.POST)
	@ResponseBody
	public Result updateMinemouth(MinemouthManageSave save){
		Result result = Result.getSuccessResult();
		try {
			result = minemouthManageService.updateMinemouth(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/delMinemouth",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteMinemouth(MinemouthManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = minemouthManageService.deleteMinemouth(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
