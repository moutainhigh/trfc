package com.tianrui.web.action.quality.file;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.intf.quality.file.IQualitySchemeItemService;
import com.tianrui.api.intf.quality.file.ISupplierSchemeService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.quality.file.QualitySchemeItemReq;
import com.tianrui.api.req.quality.file.SupplierSchemeReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/quality/sales/file/supplierScheme")
public class SupplierSchemeAction {
	Logger log = LoggerFactory.getLogger(SupplierSchemeAction.class);
	@Resource
	private ISupplierSchemeService supplierSchemeService;
	@Resource
	private IMaterielManageService materielManageService;
	@Resource
	private IQualitySchemeItemService qualitySchemeItemService;
	@Resource
	private ISystemCodeService systemCodeService;

	//显示页面
	@RequestMapping("/main")
	public ModelAndView show(){
		ModelAndView view = new ModelAndView("quality/file/supplierScheme");
		return view;
	}
	//显示新增页面
	@RequestMapping("/addMain")
	public ModelAndView showAdd(){
		ModelAndView view = new ModelAndView("quality/file/supplierScheme_add");
		return view;
	}
	//显示编辑页面
		@RequestMapping("/editMain")
		public ModelAndView showEdit(){
			ModelAndView view = new ModelAndView("quality/file/supplierScheme_edit");
			return view;
		}

	/**
	 * 获取分页数据
	 */
	@ResponseBody
	@RequestMapping("/page")
	public Result page(SupplierSchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = supplierSchemeService.page(req);

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
	public Result delete(SupplierSchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = supplierSchemeService.delete(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 新增数据
	 */
	@ResponseBody
	@RequestMapping("/add")
	public Result add(SupplierSchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = supplierSchemeService.add(req);
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
	public Result update(SupplierSchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = supplierSchemeService.update(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 获取供应商数据
	 */
	@ResponseBody
	@RequestMapping("/getSupplierData")
	public Result getSupplierData(SupplierSchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = supplierSchemeService.getSupplierData(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 获取质检方案数据(通过物料id)
	 */
	@ResponseBody
	@RequestMapping("/getSchemeData")
	public Result getSchemeData(SupplierSchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = supplierSchemeService.getSchemeData(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 获取项目详情(通过物料id)
	 */
	@ResponseBody
	@RequestMapping("/getDetailData")
	public Result getDetailData(QualitySchemeItemReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = qualitySchemeItemService.findBySchemeId(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
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
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
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
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 通过id获取原数据
	 */
	@ResponseBody
	@RequestMapping("/selectById")
	public Result selectById(SupplierSchemeReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = supplierSchemeService.selectById(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
}
