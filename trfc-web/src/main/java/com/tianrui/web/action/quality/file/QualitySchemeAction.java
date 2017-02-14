package com.tianrui.web.action.quality.file;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.intf.quality.file.IQualitySchemeService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.quality.file.QualitySchemeReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/quality/sales/file/qualityScheme")
public class QualitySchemeAction {
	Logger log = LoggerFactory.getLogger(QualitySchemeAction.class);
	@Resource
	private IQualitySchemeService qualitySchemeService;
	@Resource
	private IMaterielManageService materielManageService;
	@Resource
	private ISystemCodeService systemCodeService;
	
	//显示页面
	@RequestMapping("/main")
	public ModelAndView show(){
		ModelAndView view = new ModelAndView("quality/file/qualityScheme");
		return view;
	}
	/**
	 * 获取分页数据
	 */
	@ResponseBody
	@RequestMapping("/page")
	public Result page(QualitySchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeService.page(req);
			
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
	public Result delete(QualitySchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeService.delete(req);
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
	public Result add(QualitySchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeService.add(req);
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
	public Result update(QualitySchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeService.update(req);
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
	 * 获取编号
	 */
	@ResponseBody
	@RequestMapping("/getCode")
	public Result getCode(GetCodeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = systemCodeService.getCode(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 刷新编号(增1)
	 */
	@ResponseBody
	@RequestMapping("/updateCode")
	public Result updateCode(GetCodeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = systemCodeService.updateCodeItem(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
}
