package com.tianrui.web.action.quality.sales;



import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.intf.quality.sales.ISalesBatchnumService;
import com.tianrui.api.req.quality.sales.SalesBatchnumReq;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/quality/sales/batchnum")
public class SalesBatchnumAction {
	Logger log = LoggerFactory.getLogger(SalesBatchnumAction.class);
	@Resource
	private ISalesBatchnumService salesBatchnumService;
	@Resource
	private IMaterielManageService materielManageService;
	//显示页面
	@RequestMapping("/main")
	public ModelAndView show(){
		ModelAndView view = new ModelAndView("quality/sales/batchnum");
		return view;
	}
	//显示新增页面
	@RequestMapping("/addMain")
	public ModelAndView addMain(){
		ModelAndView view = new ModelAndView("quality/sales/batchnum_add");
		return view;
	}
	//显示修改页面
	@RequestMapping("/editMain")
	public ModelAndView editMain(){
		ModelAndView view = new ModelAndView("quality/sales/batchnum_edit");
		return view;
	}
	//显示修改页面
	@RequestMapping("/detailMain")
	public ModelAndView detailMain(){
		ModelAndView view = new ModelAndView("quality/sales/batchnum_detail");
		return view;
	}
	/**
	 * 获取分页数据
	 */
	@ResponseBody
	@RequestMapping("/page")
	public Result page(SalesBatchnumReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = salesBatchnumService.page(req);
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
	public Result delete(SalesBatchnumReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = salesBatchnumService.delete(req);
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
	public Result add(SalesBatchnumReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = salesBatchnumService.insertBatch(req);
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
	public Result update(SalesBatchnumReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = salesBatchnumService.update(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 新增页面预加载数据
	 */
	@ResponseBody
	@RequestMapping("/selector")
	public Result materialData(){
		Result rs = Result.getErrorResult();
		try {
			rs = materielManageService.materialData();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 获取化验人下拉框数据
	 */
	@ResponseBody
	@RequestMapping("/userData")
	public Result userData(){
		Result rs = Result.getErrorResult();
		try {
			rs = salesBatchnumService.assayerData();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}

	/**
	 * 获取原数据
	 */
	@ResponseBody
	@RequestMapping("/copy")
	public Result copy(SalesBatchnumReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = salesBatchnumService.selectById(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 检测批号是否重复
	 */
	@ResponseBody
	@RequestMapping("/check")
	public Result check(SalesBatchnumReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = salesBatchnumService.checkFactoryCode(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}

}
