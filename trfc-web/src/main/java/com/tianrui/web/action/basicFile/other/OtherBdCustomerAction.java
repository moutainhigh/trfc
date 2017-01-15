package com.tianrui.web.action.basicFile.other;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.other.IOtherBdCustomerService;
import com.tianrui.api.req.basicFile.other.OtherBdCustomerReq;
import com.tianrui.api.resp.basicFile.other.OtherBdCustomerResp;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("basicFile/other/customer")
public class OtherBdCustomerAction {
	@Resource
	private IOtherBdCustomerService otherBdCustomerService;
	//加载 显示页
	@RequestMapping("main")
	public ModelAndView list(){
		ModelAndView view = new ModelAndView("basicFile/other/client");
		return view;
	}
	//获取列表数据
	@RequestMapping("page")
	@ResponseBody
	public Result page(OtherBdCustomerReq req){
		return otherBdCustomerService.findCustomerByPage(req);
	}
	//删除数据
	@RequestMapping("delete")
	@ResponseBody
	public Result delete(OtherBdCustomerReq req){
		return otherBdCustomerService.deleteCustomer(req);
	}
	//更新前,获取原数据
	@RequestMapping("toupdate")
	@ResponseBody
	public Result toUpdate(OtherBdCustomerReq req){
		return otherBdCustomerService.findByPrimaryKey(req);
	}
	//更新数据
	@RequestMapping("update")
	@ResponseBody
	public Result update(OtherBdCustomerReq req){
	
		return otherBdCustomerService.updateCustomer(req);
	}
	//新增前,获取数据
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
	//提交新增数据
	@RequestMapping("insert")
	@ResponseBody
	public Result insert(OtherBdCustomerReq req){
		return otherBdCustomerService.insertCustomer(req);
	}
	//检测名称
	@RequestMapping("checkName")
	@ResponseBody
	public Result checkName(OtherBdCustomerReq req){
		return otherBdCustomerService.checkName(req);
	}
}
