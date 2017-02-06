package com.tianrui.web.action.quality.sales.batchnum;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.quality.sales.batchnum.ISalesBatchnumService;
import com.tianrui.api.req.quality.sales.batchnum.SalesBatchnumReq;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/quality/sales/batchnum")
public class SalesBatchnumAction {
	Logger log = LoggerFactory.getLogger(SalesBatchnumAction.class);
	@Resource
	private ISalesBatchnumService salesBatchnumService;
	
	@RequestMapping("/main")
	public ModelAndView show(){
		ModelAndView view = new ModelAndView("sell_pihao");
		return view;
	}
	
	/**
	 * 获取分页数据
	 */
	@RequestMapping("page")
	public Result page(SalesBatchnumReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = salesBatchnumService.page(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 删除数据
	 */
	@RequestMapping("delete")
	public Result delete(SalesBatchnumReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = salesBatchnumService.delete(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 新增数据
	 */
	@RequestMapping("add")
	public Result add(List<SalesBatchnumReq> list){
		Result rs = Result.getErrorResult();
		try {
			rs = salesBatchnumService.insertBatch(list);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 更新数据
	 */
	@RequestMapping("update")
	public Result update(SalesBatchnumReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = salesBatchnumService.update(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 新增页面预加载数据
	 */
	public Result materialData(){
		Result rs = Result.getErrorResult();
		try {
			rs = salesBatchnumService.materialData();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
}
