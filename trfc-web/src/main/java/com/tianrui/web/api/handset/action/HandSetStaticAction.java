package com.tianrui.web.api.handset.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.basicFile.businessControl.IPrimarySettingService;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.intf.basicFile.measure.IYardManageService;
import com.tianrui.api.intf.basicFile.nc.ICustomerManageService;
import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.intf.basicFile.nc.ISupplierManageService;
import com.tianrui.api.intf.basicFile.nc.IWarehouseManageService;
import com.tianrui.api.intf.businessManage.otherManage.IOtherArriveService;
import com.tianrui.api.intf.handSetStatic.IHandSetStaticService;
import com.tianrui.api.intf.quality.purchase.IPurchaseAssayService;
import com.tianrui.api.intf.quality.purchase.IPurchaseMixedService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.req.businessManage.otherManage.OtherArriveReq;
import com.tianrui.api.req.quality.purchase.PurchaseAssayReq;
import com.tianrui.api.req.quality.purchase.PurchaseMixedReq;
import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiAuthValidation;
import com.tianrui.web.smvc.ApiNotTokenValidation;
import com.tianrui.web.smvc.ApiParamRawType;

@Controller
@RequestMapping("api/handset/static")
public class HandSetStaticAction {
	
	private Logger log = LoggerFactory.getLogger(HandSetStaticAction.class);
	
	@Autowired
	private ISupplierManageService supplierManageService;
	@Autowired
	private ICustomerManageService customerManageService;
	@Autowired
	private IWarehouseManageService warehouseManageService;
	@Autowired
	private IYardManageService yardManageService;
	@Autowired
	private IMaterielManageService materielManageService;
	@Autowired
	private IPrimarySettingService primarySettingService;
    @Autowired
    private IHandSetStaticService handSetStaticService;
    @Autowired
    private ISystemUserService systemUserService;
    @Autowired
    private IPurchaseMixedService purchaseMixedService;
    @Autowired
    private IPurchaseAssayService purchaseAssayService;
    @Autowired
    private IOtherArriveService otherArriveService;
    @Autowired
    private IVehicleManageService vehicleManageService;

	@RequestMapping(value="/supplier",method = RequestMethod.POST)
	@ApiParamRawType(HandSetRequestParam.class)
	@ApiNotTokenValidation
	@ResponseBody
	public ApiResult supplier(HandSetRequestParam req){
		Result rs = Result.getErrorResult();
		try {
			List<HandSetReturnResp> list = supplierManageService.handSetQueryAll(req);
			rs.setData(list);
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}

	@RequestMapping(value="/customer",method = RequestMethod.POST)
	@ApiParamRawType(HandSetRequestParam.class)
	@ApiNotTokenValidation
	@ResponseBody
	public ApiResult customer(HandSetRequestParam req){
		Result rs = Result.getErrorResult();
		try {
			List<HandSetReturnResp> list = customerManageService.handSetQueryAll(req);
			rs.setData(list);
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}

	@RequestMapping(value="/warehouse",method = RequestMethod.POST)
	@ApiParamRawType(HandSetRequestParam.class)
	@ApiNotTokenValidation
	@ResponseBody
	public ApiResult warehouse(HandSetRequestParam req){
		Result rs = Result.getErrorResult();
		try {
			List<HandSetReturnResp> list = warehouseManageService.handSetQueryAll(req);
			rs.setData(list);
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}

	@RequestMapping(value="/yard",method = RequestMethod.POST)
	@ApiParamRawType(HandSetRequestParam.class)
	@ApiNotTokenValidation
	@ResponseBody
	public ApiResult yard(HandSetRequestParam req){
		Result rs = Result.getErrorResult();
		try {
			List<HandSetReturnResp> list = yardManageService.handSetQueryAll(req);
			rs.setData(list);
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	@RequestMapping(value="/materiel",method = RequestMethod.POST)
	@ApiParamRawType(HandSetRequestParam.class)
	@ApiNotTokenValidation
	@ResponseBody
	public ApiResult materiel(HandSetRequestParam req){
		Result rs = Result.getErrorResult();
		try {
			List<HandSetReturnResp> list = materielManageService.handSetQueryAll(req);
			rs.setData(list);
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * 原发设置
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/primarySetting ",method = RequestMethod.POST)
	@ApiParamRawType(HandSetRequestParam.class)
	@ApiNotTokenValidation
	@ResponseBody
	public ApiResult primarySetting(HandSetRequestParam req){
		Result rs = Result.getErrorResult();
		try {
			rs = primarySettingService.handSetPrimarySetting(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	
	/**
	 * 
	 * @annotation 以下为增加手持机联网新增接口
	 * 
	 */
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ApiParamRawType(UserReq.class)
    @ResponseBody
    public ApiResult login(ApiParam<UserReq> req){
        Result rs = Result.getErrorResult();
        try {
            rs = systemUserService.login(req.getBody());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    
    @RequestMapping(value="/readICard",method = RequestMethod.POST)
    @ApiParamRawType(HandSetRequestParam.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult readICard(ApiParam<HandSetRequestParam> req){
        Result rs = Result.getErrorResult();
        try {
            req.getBody().setUserId(req.getHead().getUserId());
            rs = handSetStaticService.readICard(req.getBody());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    
    @RequestMapping(value="/receive", method = RequestMethod.POST)
    @ApiParamRawType(HandSetRequestParam.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult receive(ApiParam<HandSetRequestParam> req) {
        Result rs = Result.getErrorResult();
        try {
            req.getBody().setUserId(req.getHead().getUserId());
            rs = handSetStaticService.receive(req.getBody());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    /**
     * @annotation 整车退货
     * @param req
     * @return
     */
    @RequestMapping(value="/allReturnOfGoods", method = RequestMethod.POST)
    @ApiParamRawType(HandSetRequestParam.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult allReturnOfGoods(ApiParam<HandSetRequestParam> req) {
        Result rs = Result.getErrorResult();
        try {
            req.getBody().setUserId(req.getHead().getUserId());
            rs = handSetStaticService.allReturnOfGoods(req.getBody());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    /**
     * @annotation 退货确认
     * @param req
     * @return
     */
    @RequestMapping(value="/returnOfGoods", method = RequestMethod.POST)
    @ApiParamRawType(HandSetRequestParam.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult returnOfGoods(ApiParam<HandSetRequestParam> req) {
        Result rs = Result.getErrorResult();
        try {
            req.getBody().setUserId(req.getHead().getUserId());
            rs = handSetStaticService.returnOfGoods(req.getBody());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    /**
     * @annotation 混样 供应商 查询
     * @param likeKey日期 yyyy-MM-dd
     * @return
     */
    @RequestMapping(value="/mixedSupplierAutoComplate", method = RequestMethod.POST)
    @ApiParamRawType(HandSetRequestParam.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult mixedSupplierAutoComplate(ApiParam<HandSetRequestParam> req) {
        Result rs = Result.getSuccessResult();
        try {
            rs.setData(purchaseMixedService.supplierAutoComplate(req.getBody().getLikeKey()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    /**
     * @annotation 混样 矿口 查询
     * @param likeKey日期 yyyy-MM-dd
     * @return
     */
    @RequestMapping(value="/mixedMinemouthAutoComplate", method = RequestMethod.POST)
    @ApiParamRawType(HandSetRequestParam.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult mixedMinemouthAutoComplate(ApiParam<HandSetRequestParam> req) {
        Result rs = Result.getSuccessResult();
        try {
            rs.setData(purchaseMixedService.minemouthAutoComplate(req.getBody().getLikeKey()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    /**
     * @annotation 混样 物料 查询
     * @param likeKey日期 yyyy-MM-dd
     * @return
     */
    @RequestMapping(value="/mixedMaterialAutoComplate", method = RequestMethod.POST)
    @ApiParamRawType(HandSetRequestParam.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult mixedMaterialAutoComplate(ApiParam<HandSetRequestParam> req) {
        Result rs = Result.getSuccessResult();
        try {
            rs.setData(purchaseMixedService.materialAutoComplate(req.getBody().getLikeKey()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    /**
     * @annotation 采购混样新增
     * @param req
     * @return
     */
    @RequestMapping(value="/mixedSave", method = RequestMethod.POST)
    @ApiParamRawType(HandSetRequestParam.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult mixedSave(ApiParam<HandSetRequestParam> req) {
        Result rs = Result.getErrorResult();
        try {
            PurchaseAssayReq r = new PurchaseAssayReq();
            r.setSamplingid(req.getBody().getSamplingid());
            rs = purchaseAssayService.add(r);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    /**
     * @annotation 采购采样集合查询
     * @param req
     * @return
     */
    @RequestMapping(value="/mixedPage", method = RequestMethod.POST)
    @ApiParamRawType(PurchaseMixedReq.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult mixedPage(ApiParam<PurchaseMixedReq> req) {
        Result rs = Result.getErrorResult();
        try {
            rs = purchaseMixedService.mixedPage(req.getBody());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    /**
     * @annotation 厂内倒运通知单查询
     * @param req
     * @return
     */
    @RequestMapping(value="/listDYNotice", method = RequestMethod.POST)
    @ApiParamRawType(OtherArriveReq.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult listDYNotice(ApiParam<OtherArriveReq> req) {
        Result rs = Result.getErrorResult();
        try {
            req.getBody().setBusinesstype("4");
            rs = otherArriveService.page(req.getBody());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    /**
     * @annotation 厂内倒运新增
     * @param req
     * @return
     */
    @RequestMapping(value="/saveDYNotice", method = RequestMethod.POST)
    @ApiParamRawType(OtherArriveReq.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult saveDYNotice(ApiParam<OtherArriveReq> req) {
        Result rs = Result.getErrorResult();
        try {
            req.getBody().setUserid(req.getHead().getUserId());
            req.getBody().setBusinesstype("4");
            req.getBody().setCodekey("ND");
            req.getBody().setStarttime(DateUtil.parse(req.getBody().getStarttimeStr(), DateUtil.Y_M_D_H_M_S));
            req.getBody().setEndtime(DateUtil.parse(req.getBody().getEndtimeStr(), DateUtil.Y_M_D_H_M_S));
            rs = otherArriveService.add(req.getBody());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    /**
     * 
     * @annotation 内倒更新
     * @param req
     * @return
     */
    @RequestMapping(value="/updateDYNotice", method = RequestMethod.POST)
    @ApiParamRawType(OtherArriveReq.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult updateDYNotice(ApiParam<OtherArriveReq> req) {
        Result rs = Result.getErrorResult();
        try {
            req.getBody().setUserid(req.getHead().getUserId());
            req.getBody().setStarttime(DateUtil.parse(req.getBody().getStarttimeStr(), DateUtil.Y_M_D_H_M_S));
            req.getBody().setEndtime(DateUtil.parse(req.getBody().getEndtimeStr(), DateUtil.Y_M_D_H_M_S));
            rs = otherArriveService.update(req.getBody());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    /**
     * 内倒 ： 堆场，车辆，物料模糊查询
     */
    /**
     * @annotation 堆场模糊查询
     * @param req
     * @return
     */
    @RequestMapping(value="/yardAutoComplate", method = RequestMethod.POST)
    @ApiParamRawType(HandSetRequestParam.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult yardAutoComplate(ApiParam<HandSetRequestParam> req) {
        Result rs = Result.getSuccessResult();
        try {
            rs.setData(yardManageService.autoCompleteSearch(req.getBody().getTerm().trim()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    @RequestMapping(value="/vehicleAutoComplate", method = RequestMethod.POST)
    @ApiParamRawType(HandSetRequestParam.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult vehicleAutoComplate(ApiParam<HandSetRequestParam> req) {
        Result rs = Result.getSuccessResult();
        try {
            rs.setData(vehicleManageService.autoCompleteSearch("1", req.getBody().getTerm().trim(), null));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
    @RequestMapping(value="/materialAutoComplate", method = RequestMethod.POST)
    @ApiParamRawType(HandSetRequestParam.class)
    @ApiAuthValidation(callType="4")
    @ResponseBody
    public ApiResult materialAutoComplate(ApiParam<HandSetRequestParam> req) {
        Result rs = Result.getSuccessResult();
        try {
            rs.setData(materielManageService.autoCompleteSearch(req.getBody().getTerm().trim()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
}
