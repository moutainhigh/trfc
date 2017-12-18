package com.tianrui.web.api.subsystem.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.quality.sales.IAssayReportService;
import com.tianrui.api.intf.quality.sales.ISalesBatchnumService;
import com.tianrui.api.req.quality.sales.AssayReportReq;
import com.tianrui.api.req.quality.sales.SalesBatchnumReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;

@Controller
@RequestMapping("api/quality")
public class ApiQualityAction {
	private Logger log = LoggerFactory.getLogger(ApiQualityAction.class);
	@Autowired
	ISalesBatchnumService iSalesBatchnumService;
	@Autowired
	IAssayReportService iAssayReportService;
	
	/**
	 * 销售化验报告批号详情
	 * @Title: assayReportDetail 
	 * @Description: TODO
	 * @param @param req
	 * @param @return   
	 * @return ApiResult    
	 * @throws
	 */
	@RequestMapping(value="/assayReportDetail",method=RequestMethod.POST)
	@ApiParamRawType(AssayReportReq.class)
	@ResponseBody
	public ApiResult assayReportDetail(ApiParam<AssayReportReq> req){
		AssayReportReq assayReportReq =req.getBody();
		Result rs=Result.getErrorResult();
		try {
			 rs=iAssayReportService.findSelectDetail(assayReportReq.getBatchcode());
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * 批号余量计算
	 * @Title: assayReportDetail 
	 * @Description: TODO
	 * @param @param req
	 * @param @return   
	 * @return ApiResult    
	 * @throws
	 */
	@RequestMapping(value="/salesBatchnum",method=RequestMethod.POST)
	@ApiParamRawType(SalesBatchnumReq.class)
	@ResponseBody
	public ApiResult salesBatchnum(ApiParam<SalesBatchnumReq> req){
		SalesBatchnumReq salesBatchnumReq=req.getBody();
		Result rs=Result.getErrorResult();
		try {
			rs =iSalesBatchnumService.selectIdMargin(salesBatchnumReq.getMaterial(),salesBatchnumReq.getWeighed());
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
}
