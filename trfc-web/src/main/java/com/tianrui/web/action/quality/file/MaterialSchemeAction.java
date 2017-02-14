package com.tianrui.web.action.quality.file;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.intf.quality.file.IMaterialSchemeService;
import com.tianrui.api.req.quality.file.MaterialSchemeReq;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/quality/sales/file/MaterialScheme")
public class MaterialSchemeAction {
	Logger log = LoggerFactory.getLogger(MaterialSchemeAction.class);
	@Resource
	private IMaterialSchemeService materialSchemeService;
	@Resource
	private IMaterielManageService materielManageService;
	
	//显示页面
	@RequestMapping("/main")
	public ModelAndView show(){
		ModelAndView view = new ModelAndView("quality/file/materialScheme");
		return view;
	}
	/**
	 * 获取分页数据
	 */
	@ResponseBody
	@RequestMapping("/page")
	public Result page(MaterialSchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = materialSchemeService.page(req);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 删除数据
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Result delete(MaterialSchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = materialSchemeService.delete(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 批量新增数据
	 */
	@ResponseBody
	@RequestMapping("/add")
	public Result add(MaterialSchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = materialSchemeService.add(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}

	/**
	 * 更新数据
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Result update(MaterialSchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = materialSchemeService.update(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 获取下拉框数据
	 */
	@ResponseBody
	@RequestMapping("/selector")
	public Result materialData(){
		Result rs = Result.getErrorResult();
		try {
			rs = materielManageService.materialData();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	

	/**
	 * 检测批号是否重复
	 */
	@ResponseBody
	@RequestMapping("/check")
	public Result check(MaterialSchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = materialSchemeService.checkMaterialType(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
}
