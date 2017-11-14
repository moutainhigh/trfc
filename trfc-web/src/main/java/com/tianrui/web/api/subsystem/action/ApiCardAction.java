package com.tianrui.web.api.subsystem.action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.businessManage.cardManage.ICardService;
import com.tianrui.api.intf.common.IRFIDService;
import com.tianrui.api.req.businessManage.cardManage.CardApi;
import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiAuthValidation;
import com.tianrui.web.smvc.ApiParamRawType;


/**
 * 卡务相关
 * @author lixp 2017年1月7日 09:24:36
 *
 */
@Controller
@RequestMapping("api/card")
public class ApiCardAction {

	private Logger log = LoggerFactory.getLogger(ApiCardAction.class);
	
	@Autowired
	private ICardService cardService;
	
	@Autowired
	private IRFIDService rfidService;

	/**
	 * ic卡注册
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/icCardReg",method=RequestMethod.POST)
	@ApiParamRawType(CardApi.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult icCardReg(ApiParam<CardApi> req){
		CardApi cardApi =req.getBody();
		cardApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = cardService.addCardApi(cardApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * rfid串码注册
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/rfidReg",method=RequestMethod.POST)
	@ApiParamRawType(RFIDReq.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult rfidReg(ApiParam<RFIDReq> req){
		RFIDReq rfidReq=req.getBody();
		rfidReq.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = rfidService.save(rfidReq);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * rfid解绑 临时卡业务
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/unBindRfid",method=RequestMethod.POST)
	@ApiParamRawType(RFIDReq.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult unBindRfid(ApiParam<RFIDReq> req){
		RFIDReq rfidReq=req.getBody();
		rfidReq.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs=rfidService.unbind(rfidReq);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 校验IC卡
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	@ApiParamRawType(CardApi.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult validate(ApiParam<CardApi> req){
	    Result rs = Result.getErrorResult();
	    try {
	        rs = cardService.validate(req.getBody());
	    } catch (Exception e) {
	        rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
	        log.error(e.getMessage(),e);
	    }
	    return ApiResult.valueOf(rs);
	}
	
}
