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
import com.tianrui.api.intf.basicFile.measure.IYardManageService;
import com.tianrui.api.intf.basicFile.nc.ICustomerManageService;
import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.intf.basicFile.nc.ISupplierManageService;
import com.tianrui.api.intf.basicFile.nc.IWarehouseManageService;
import com.tianrui.api.intf.handSetStatic.IHandSetStaticService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
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
}
