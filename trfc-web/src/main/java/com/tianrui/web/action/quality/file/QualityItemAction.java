package com.tianrui.web.action.quality.file;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.quality.file.IQualityItemService;
import com.tianrui.api.req.quality.file.QualityItemReq;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/quality/sales/file/qualityItem")
public class QualityItemAction {
	Logger log = LoggerFactory.getLogger(QualitySchemeAction.class);
	@Resource
	private IQualityItemService qualityItemService;
	
	
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
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
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
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
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
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
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
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 获取所有列数据
	 */
	@ResponseBody
	@RequestMapping("/getColumns")
	public Result getColumns(){
		Result rs = Result.getErrorResult();
		try {
			rs = qualityItemService.getLine();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
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
			rs = qualityItemService.autoCompleteSearch(term.trim());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 获取列表(名称和列)
	 */
	@RequestMapping("/getLineAndName")
	@ResponseBody
	public Result getLineAndName(QualityItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualityItemService.getLineAndName(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	
	/**
	 * 校验项目名称是否存在
	 * @Title: selectaddName 
	 * @Description: TODO
	 * @param @param req
	 * @param @return   
	 * @return Result    
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/selectaddName")
	public Result selectaddName(QualityItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualityItemService.selectaddName(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.PARAM_NAME_ERROR);
		}
		return rs;
	}
}
