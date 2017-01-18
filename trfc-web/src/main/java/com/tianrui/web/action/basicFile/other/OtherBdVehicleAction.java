package com.tianrui.web.action.basicFile.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.other.IOtherBdVehicleService;
import com.tianrui.api.req.basicFile.other.OtherBdVehicleReq;
import com.tianrui.api.resp.basicFile.other.OtherBdVehicleResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/other/otherVehicle")
public class OtherBdVehicleAction {
	
	private Logger log = LoggerFactory.getLogger(OtherBdVehicleAction.class);
	
	@Autowired
	private IOtherBdVehicleService otherBdVehicleService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/other/other_car");
		return view;
	}
	
	/**
	 * 分页查询数据Action
	 * @param req
	 * @return
	 */
	@RequestMapping(value="page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(OtherBdVehicleReq req){
		Result result = otherBdVehicleService.page(req);
		return result;
	}
	
	/**
	 * 获取新增时需要的编号和内码
	 * @return
	 */
	@RequestMapping(value="toAddOtherVehicle",method=RequestMethod.POST)
	@ResponseBody
	public Result toAddOtherVehicle(){
		Result result = Result.getSuccessResult();
		try {
			OtherBdVehicleResp resp = new OtherBdVehicleResp();
			resp.setCode(otherBdVehicleService.getVehicleCode());
			resp.setInnercode(otherBdVehicleService.getVehicleInnercode());
			result.setData(resp);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
	
	/**
	 * 新增其他车辆信息Action
	 * @param req
	 * @return
	 */
	@RequestMapping(value="addOtherVehicle",method=RequestMethod.POST)
	@ResponseBody
	public Result addOtherVehicle(OtherBdVehicleReq req){
		Result result = otherBdVehicleService.addVehicle(req);
		return result;
	}
	
	/**
	 * 修改其他车辆信息Action
	 * @param req
	 * @return
	 */
	@RequestMapping(value="editOtherVehicle",method=RequestMethod.POST)
	@ResponseBody
	public Result editOtherVehicle(OtherBdVehicleReq req){
		Result result =otherBdVehicleService.editVehicle(req);
		return result;
	}
	
	/**
	 * 删除其他车辆信息Action
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteOtherVehicle",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteOtherVehicle(String id){
		Result result = otherBdVehicleService.deleteVehicle(id);
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
		Result result =  otherBdVehicleService.checkName(name);
		return result;
	}
	
}
