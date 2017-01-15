package com.tianrui.web.action.system.base;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.system.base.SystemCodeReq;
import com.tianrui.smartfactory.common.vo.Result;
@Controller
@RequestMapping("system/base/code")
public class SystemCodeAction {
	@Resource
	private ISystemCodeService systemCodeService;
	//显示当前页
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("system/base/systemCode");
		return view;
	}
	//列表数据
	@RequestMapping(value="page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(SystemCodeReq req){
		return systemCodeService.select(req);
	}
	//修改
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public Result edit(SystemCodeReq req){
		return systemCodeService.update(req);
	}
	//新增
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public Result add(SystemCodeReq req){
		return systemCodeService.insert(req);
	}
	//删除
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Result delete(SystemCodeReq req){
		return systemCodeService.delete(req);
	}
	//检测单据代号
	@RequestMapping(value="checkCode",method=RequestMethod.POST)
	@ResponseBody
	public Result checkCode(SystemCodeReq req){
		return systemCodeService.checkCode(req);
	}
}
