package com.tianrui.web.action.quality.sales;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.quality.file.IQualitySchemeItemService;
import com.tianrui.api.intf.quality.sales.IAssayReportService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.quality.file.QualitySchemeItemReq;
import com.tianrui.api.req.quality.sales.AssayReportReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/quality/sales/report")
public class AssayReportAction {
	@Resource
	private IAssayReportService assayReportService;
	@Resource
	private ISystemCodeService systemCodeService;
	@Resource
	private IQualitySchemeItemService qualitySchemeItemService;
	
	Logger log = LoggerFactory.getLogger(AssayReportAction.class);
	
	@RequestMapping("/main")
	private ModelAndView main(){
		ModelAndView view = new ModelAndView("quality/sales/assayReport");
		return view;
	}
	@RequestMapping("/addmain")
	private ModelAndView addmain(){
		ModelAndView view = new ModelAndView("quality/sales/assayReport_add");
		return view;
	}
	@RequestMapping("/editmain")
	private ModelAndView editmain(){
		ModelAndView view = new ModelAndView("quality/sales/assayReport_edit");
		return view;
	}
	@RequestMapping("/detailmain")
	private ModelAndView detailmain(){
		ModelAndView view = new ModelAndView("quality/sales/assayReport_detail");
		return view;
	}
	
	/**
	 * 获取分页数据
	 */
	@ResponseBody
	@RequestMapping("/page")
	public Result page(AssayReportReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = assayReportService.page(req);
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
	public Result delete(AssayReportReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = assayReportService.delete(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 新增数据
	 */
	@ResponseBody
	@RequestMapping("/save")
	public Result save(AssayReportReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = assayReportService.add(req);
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
	public Result update(AssayReportReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = assayReportService.update(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 查询数据(单个)
	 */
	@ResponseBody
	@RequestMapping("/selectOne")
	public Result selectOne(AssayReportReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = assayReportService.findOne(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
//	/**
//	 * 查询数据(单个)
//	 */
//	@ResponseBody
//	@RequestMapping("/qschemeSelect")
//	public Result qschemeSelect(AssayReportReq req){
//		Result rs = Result.getErrorResult();
//		try {
//			rs = assayReportService.mschemeData();
//		} catch (Exception e) {
//			log.error(e.getMessage(),e);
//		}
//		return rs;
//	}
	
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
	/**
	 * 获取详情+检测值
	 */
	@ResponseBody
	@RequestMapping("/getDetailAndVal")
	public Result getDetailAndVal(QualitySchemeItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeItemService.findDetailandVal(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 查询审核信息
	 */
	@ResponseBody
	@RequestMapping("/auditMsg")
	public Result auditMsg(AssayReportReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = assayReportService.findReportMsg(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
}
