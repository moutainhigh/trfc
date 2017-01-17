package com.tianrui.web.action.system.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.system.base.ISystemDataDictService;
import com.tianrui.api.req.system.base.SystemDataDictItemReq;
import com.tianrui.api.req.system.base.SystemDataDictReq;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/system/base/dataDict")
public class SystemDataDictAction {
	@Autowired
	private ISystemDataDictService systemDataDictService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("system/base/fuzhu");
		return view;
	}
	
	/**
	 * 显示数据字典类别Action
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/listDict",method=RequestMethod.POST)
	@ResponseBody
	public Result listDataDict(SystemDataDictReq req){
		Result result = systemDataDictService.findSystemDataDicts(req);
		return result;
	}
	
	/**
	 * 增加数据字典类别Action
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/addDict",method=RequestMethod.POST)
	@ResponseBody
	public Result addDataDict(SystemDataDictReq req){
		Result result = systemDataDictService.addSystemDataDict(req);
		return result;
	}
	
	/**
	 * 修改数据字典类别Action
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/editDict",method=RequestMethod.POST)
	@ResponseBody
	public Result editDataDict(SystemDataDictReq req){
		Result result = systemDataDictService.editSystemDataDict(req);
		return result;
	}
	
	/**
	 * 删除数据字典类别Action
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteDict",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteDataDict(String id){
		Result result = systemDataDictService.deleteSystemDataDict(id);
		return result;
	}
	
	/**
	 * 显示数据字典明细Action
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/listItem",method=RequestMethod.POST)
	@ResponseBody
	public Result listItem(SystemDataDictItemReq req){
		Result result = systemDataDictService.findSystemDataDictItems(req);
		return result;
	}
	
	/**
	 * 增加数据字典明细Action
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/addItem",method=RequestMethod.POST)
	@ResponseBody
	public Result addItem(SystemDataDictItemReq req){
		Result result = systemDataDictService.addSystemDataDictItem(req);
		return result;
	}
	
	/**
	 * 修改数据字典明细Action
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/editItem",method=RequestMethod.POST)
	@ResponseBody
	public Result editItem(SystemDataDictItemReq req){
		Result result = systemDataDictService.editSystemDataDictItem(req);
		return result;
	}
	
	/**
	 * 删除数据字典明细Action
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteItem",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteItem(String id){
		Result result = systemDataDictService.deleteSystemDataDictItem(id);
		return result;
	}
}
