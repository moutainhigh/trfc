package com.tianrui.web.action.quality.file;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.intf.quality.file.IQualityItemService;
import com.tianrui.api.intf.quality.file.IQualitySchemeItemService;
import com.tianrui.api.intf.quality.file.IQualitySchemeService;
import com.tianrui.api.req.quality.file.QualitySchemeItemReq;
import com.tianrui.api.req.quality.file.QualitySchemeReq;
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
	private IQualitySchemeItemService qualitySchemeItemService;
	@Resource
	private IQualityItemService qualityItemService;
	
	//显示页面
	@RequestMapping("/main")
	public ModelAndView show(){
		ModelAndView view = new ModelAndView("quality/file/qualityScheme");
		return view;
	}
	//显示页面
		@RequestMapping("/item")
		public ModelAndView item(){
			ModelAndView view = new ModelAndView("quality/file/qualityScheme_item");
			return view;
		}
		//显示页面
		@RequestMapping("/standard")
		public ModelAndView standard(){
			ModelAndView view = new ModelAndView("quality/file/qualityScheme_standard");
			return view;
		}
		//显示页面
				@RequestMapping("/addBatchMain")
				public ModelAndView addBatchMain(){
					ModelAndView view = new ModelAndView("quality/file/qualityScheme_item_addBatch");
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
	 * 获取单据类型下拉框数据
	 */
	@ResponseBody
	@RequestMapping("/billsData")
	public Result billsData(){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeService.billsData();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}

	/**
	 * 获取分页数据
	 */
	@ResponseBody
	@RequestMapping("/inquire")
	public Result inquire(QualitySchemeItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeItemService.findBySchemeId(req);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 删除数据
	 */
	@ResponseBody
	@RequestMapping("/deleteItem")
	public Result deleteItem(QualitySchemeItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeItemService.delete(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 删除数据
	 */
	@ResponseBody
	@RequestMapping("/deleteBatchItem")
	public Result deleteBatchItem(QualitySchemeItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeItemService.deleteBatch(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 新增数据
	 */
	@ResponseBody
	@RequestMapping("/addItem")
	public Result addItem(QualitySchemeItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeItemService.add(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
 	* 批量新增数据
 	*/
	@ResponseBody
	@RequestMapping("/addBatchItem")
	public Result addBatchItem(QualitySchemeItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeItemService.addBatch(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 更新数据
	 */
	@ResponseBody
	@RequestMapping("/updateItem")
	public Result updateItem(QualitySchemeItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeItemService.update(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 批量更新数据
	 */
	@ResponseBody
	@RequestMapping("/updateBatchItem")
	public Result updateBatchItem(QualitySchemeItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeItemService.updateBatch(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}

	/**
	 * 获取方案信息
	 */
	@ResponseBody
	@RequestMapping("/getSchemeData")
	public Result itemlData(QualitySchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeService.findById(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 模糊查询下拉框
	 * @param term
	 * @return
	 */
	@RequestMapping("/autoCompleteSearch")
	@ResponseBody
	public Result autoCompleteSearch(String term){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeService.autoCompleteSearch(term.trim());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return rs;
	}
	/**
	 * 删除数据
	 */
	@ResponseBody
	@RequestMapping("/getOldItem")
	public Result getOldItem(String schemeid){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeItemService.getOldItem(schemeid);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
}
