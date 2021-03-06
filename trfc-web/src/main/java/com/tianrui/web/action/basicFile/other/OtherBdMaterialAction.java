package com.tianrui.web.action.basicFile.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.other.IOtherBdMaterialService;
import com.tianrui.api.req.basicFile.other.OtherBdMaterialReq;
import com.tianrui.api.resp.basicFile.other.OtherBdMaterialResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
@Controller
@RequestMapping("/trfc/basicFile/other/material")
public class OtherBdMaterialAction {
	private Logger log=LoggerFactory.getLogger(OtherBdMaterialAction.class);
	@Autowired
	private IOtherBdMaterialService otherBdMaterialService;
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view=new ModelAndView("basicFile/other/materials");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(OtherBdMaterialReq req){
		Result result=Result.getErrorResult();
		try {
			result=otherBdMaterialService.page(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}		
		return result;
		
	}
	@RequestMapping("toInsert")
	@ResponseBody
	public Result toInsert(){
		Result result = Result.getSuccessResult();
		try {
			OtherBdMaterialResp resp = new OtherBdMaterialResp();
			resp.setCode(otherBdMaterialService.getMaterialCode());
			resp.setInnercode(otherBdMaterialService.getMaterialInnercode());
			result.setData(resp);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
	
	@RequestMapping("addOtherBdMaterial")
	@ResponseBody
	public Result addOtherBdMaterial(OtherBdMaterialReq req){
		Result result=Result.getErrorResult();
		try {
			result=otherBdMaterialService.addOtherBdMaterial(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			
		}
		return result;
	}
	
	
	@RequestMapping("toUpdate")
	@ResponseBody
	public Result toUpdate(String id){
		Result result = Result.getSuccessResult();
		try {
			OtherBdMaterialReq req=otherBdMaterialService.findByPrimaryKey(id);
			result.setData(req);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}		
		return result;
		
	}
	@RequestMapping("updateOtherBdMaterial")
	@ResponseBody
	public Result updateOtherBdMaterial(OtherBdMaterialReq req){
		Result result = Result.getErrorResult();
		try {
			result=otherBdMaterialService.updateOtherBdMaterial(req);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	@RequestMapping("deleteOtherBdMaterial")
	@ResponseBody
	public Result deleteOtherBdMaterial(String id){
		Result result=Result.getErrorResult();		
		try {
			result=otherBdMaterialService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}		
		return result;
		
	}
	
}
