package com.tianrui.web.action.quality.file;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.quality.file.IQualityItemService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.quality.file.QualityItemReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/quality/sales/file/qualityItem")
public class QualityItemAction {
	Logger log = LoggerFactory.getLogger(QualitySchemeAction.class);
	@Resource
	private IQualityItemService qualityItemService;
	@Resource
	private ISystemCodeService systemCodeService;
	
	//显示页面
	@RequestMapping("/main")
	public ModelAndView show(){
		ModelAndView view = new ModelAndView("quality/file/qualityItem");
		return view;
	}
	
	/**
	 * 获取分页数据
	 */
	@ResponseBody
	@RequestMapping("/page")
	public Result page(QualityItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualityItemService.page(req);
			
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
	public Result delete(QualityItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualityItemService.delete(req);
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
	public Result add(QualityItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualityItemService.add(req);
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
	public Result update(QualityItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualityItemService.update(req);
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
