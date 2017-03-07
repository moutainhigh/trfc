package com.tianrui.web.action.system.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.system.base.ISystemDataDictService;
import com.tianrui.api.req.system.base.SystemDataDictItemReq;
import com.tianrui.api.req.system.base.SystemDataDictReq;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/system/base/dataDict")
public class SystemDataDictAction {
	
	private Logger log = LoggerFactory.getLogger(SystemDataDictAction.class);
	
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
		Result result=Result.getErrorResult();
		try {
			result = systemDataDictService.findSystemDataDicts(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
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
		Result result=Result.getErrorResult();
		try {
			result = systemDataDictService.addSystemDataDict(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
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
		Result result=Result.getErrorResult();
		try {
			result = systemDataDictService.editSystemDataDict(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
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
		Result result=Result.getErrorResult();
		try {
			result = systemDataDictService.deleteSystemDataDict(id);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
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
		Result result=Result.getErrorResult();
		try {
			result = systemDataDictService.findSystemDataDictItems(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
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
		Result result=Result.getErrorResult();
		try {
			result = systemDataDictService.addSystemDataDictItem(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
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
		Result result=Result.getErrorResult();
		try {
			result = systemDataDictService.editSystemDataDictItem(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
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
		Result result=Result.getErrorResult();
		try {
			result = systemDataDictService.deleteSystemDataDictItem(id);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	/**
	 * 模糊查询下拉框
	 * @param term
	 * @return
	 */
	@RequestMapping("/autoCompleteSearch")
	@ResponseBody
	public Result autoCompleteSearch(SystemDataDictItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = systemDataDictService.autoCompleteSearch(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return rs;
	}
}
