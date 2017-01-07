package com.tianrui.web.action.basicFile.other;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.other.IOtherBdCustomerService;
import com.tianrui.api.req.basicFile.other.OtherBdCustomerReq;
import com.tianrui.api.resp.basicFile.other.OtherBdCustomerResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("other")
public class OtherBdCustomerAction {
	@Resource
	private IOtherBdCustomerService otherBdCustomerService;

	@RequestMapping("main")
	public ModelAndView list(){
		ModelAndView view = new ModelAndView("basicFile/other/client");
		return view;
	}
	@RequestMapping("page")
	@ResponseBody
	public Result page(OtherBdCustomerReq req){

		PaginationVO<OtherBdCustomerResp> page;
		Result result = Result.getSuccessResult();
		try {
			page = otherBdCustomerService.findCustomerByPage(req);
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		//System.out.println(page.getPageNo()+","+page.getPageSize()+","+page.getTotal());
		return result;
	}
	@RequestMapping("delete")
	@ResponseBody
	public Result delete(String id){

		Result result = Result.getSuccessResult();
		try {
			Boolean bl = otherBdCustomerService.deleteCustomer(id);
			if(!bl){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping("toupdate")
	@ResponseBody
	public Result toUpdate(String id){
		Result result = Result.getSuccessResult();
		try {
			OtherBdCustomerReq req = otherBdCustomerService.findByPrimaryKey(id);
			result.setData(req);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping("update")
	@ResponseBody
	public Result update(OtherBdCustomerReq req){
		Result result = Result.getSuccessResult();
		try {
			Boolean bl = otherBdCustomerService.updateCustomer(req);
			if(!bl){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping("toinsert")
	@ResponseBody
	public Result toInsert(){
		Result result = Result.getSuccessResult();
		OtherBdCustomerResp resp = new OtherBdCustomerResp();
		resp.setCode(otherBdCustomerService.getCustomerCode());
		resp.setInnercode(otherBdCustomerService.getCustomerInnercode());
		result.setData(resp);
		return result;
	}
	@RequestMapping("insert")
	@ResponseBody
	public Result insert(OtherBdCustomerReq req){
		Result result = Result.getSuccessResult();
		try {
			Boolean bl = otherBdCustomerService.insertCustomer(req);
			if(!bl){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping("checkName")
	@ResponseBody
	public Result checkName(String name){
		Result result = Result.getSuccessResult();
		try {
			Boolean bl = otherBdCustomerService.checkName(name);
			result.setData(bl);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
