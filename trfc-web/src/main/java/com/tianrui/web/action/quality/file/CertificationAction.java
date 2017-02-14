package com.tianrui.web.action.quality.file;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.quality.file.ICertificationService;
import com.tianrui.api.req.quality.file.CertificationReq;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/quality/sales/file/certification")
public class CertificationAction {
	Logger log = LoggerFactory.getLogger(CertificationAction.class);
	@Resource
	private ICertificationService certificationService;
	//显示页面
	@RequestMapping("/main")
	public ModelAndView show(){
		ModelAndView view = new ModelAndView("quality/file/certification");
		return view;
	}
	/**
	 * 获取分页数据
	 */
	@ResponseBody
	@RequestMapping("/page")
	public Result page(CertificationReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = certificationService.page(req);
			
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
	public Result delete(CertificationReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = certificationService.delete(req);
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
	public Result add(CertificationReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = certificationService.add(req);
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
	public Result update(CertificationReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = certificationService.update(req);
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
			rs = certificationService.materialData();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	
	
	
}
