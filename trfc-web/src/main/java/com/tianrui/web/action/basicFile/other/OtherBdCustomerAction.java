package com.tianrui.web.action.basicFile.other;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.other.IOtherBdCustomerService;
import com.tianrui.api.req.basicFile.other.OtherBdCustomerReq;
import com.tianrui.api.resp.basicFile.other.OtherBdCustomerResp;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/basicFile/other/customer")
public class OtherBdCustomerAction {
	Logger log = LoggerFactory.getLogger(OtherBdCustomerAction.class);
	
	@Resource
	private IOtherBdCustomerService otherBdCustomerService;
	//加载 显示页
	@RequestMapping("/main")
	public ModelAndView list(){
		ModelAndView view = new ModelAndView("basicFile/other/client");
		return view;
	}
	//获取列表数据
	@RequestMapping("/page")
	@ResponseBody
	public Result page(OtherBdCustomerReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = otherBdCustomerService.findCustomerByPage(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	//删除数据
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(OtherBdCustomerReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = otherBdCustomerService.deleteCustomer(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	//更新前,获取原数据
	@RequestMapping("/toupdate")
	@ResponseBody
	public Result toUpdate(OtherBdCustomerReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = otherBdCustomerService.findByPrimaryKey(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	//更新数据
	@RequestMapping("/update")
	@ResponseBody
	public Result update(OtherBdCustomerReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = otherBdCustomerService.updateCustomer(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	//新增前,获取数据
	@RequestMapping("/toinsert")
	@ResponseBody
	public Result toInsert(){
		Result result = Result.getSuccessResult();
		OtherBdCustomerResp resp = new OtherBdCustomerResp();
		resp.setCode(otherBdCustomerService.getCustomerCode());
		resp.setInnercode(otherBdCustomerService.getCustomerInnercode());
		result.setData(resp);
		return result;
	}
	//提交新增数据
	@RequestMapping("/insert")
	@ResponseBody
	public Result insert(OtherBdCustomerReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = otherBdCustomerService.insertCustomer(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	//检测名称
	@RequestMapping("/checkName")
	@ResponseBody
	public Result checkName(OtherBdCustomerReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = otherBdCustomerService.checkName(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
}
