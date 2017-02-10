package com.tianrui.web.action.basicFile.measure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.measure.ITransportunitManageService;
import com.tianrui.api.req.basicFile.measure.TransportunitManageQuery;
import com.tianrui.api.req.basicFile.measure.TransportunitManageSave;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/transport")
public class TransportunitManageAction {
	
	private Logger log=LoggerFactory.getLogger(TransportunitManageAction.class);
	
	@Autowired 
	private ITransportunitManageService transportunitManageService;
	
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
	 * 新增运输单位信息
	 * @param save
	 * @return
	 */
	@RequestMapping(value="/addTransport",method=RequestMethod.POST)
	@ResponseBody
	public Result addTransportunit(TransportunitManageSave save){
		Result result = Result.getSuccessResult();
		try {
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
	public Result updateTransportunit(TransportunitManageSave save){
		Result result = Result.getSuccessResult();
		try {
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
