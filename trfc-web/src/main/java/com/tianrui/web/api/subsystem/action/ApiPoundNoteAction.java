package com.tianrui.web.api.subsystem.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.businessManage.poundNoteMaintain.IPoundNoteService;
import com.tianrui.api.req.businessManage.poundNoteMaintain.ApiPoundNoteQuery;
import com.tianrui.api.req.businessManage.poundNoteMaintain.ApiPoundNoteValidation;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiAuthValidation;
import com.tianrui.web.smvc.ApiParamRawType;

/**
 * @Description 磅单相关接口
 * @author zhanggaohao
 * @version 2017年3月25日 上午9:59:06
 */
@Controller
@RequestMapping("api/poundNote")
public class ApiPoundNoteAction {

	private Logger log = LoggerFactory.getLogger(ApiPoundNoteAction.class);

	@Autowired
	private IPoundNoteService poundNoteService;

	/**
	 * @Description 磅单上传
	 * @author zhanggaohao
	 * @version 2017年3月25日 上午9:59:20
	 * @param req
	 * @return
	 */
	public static void main(String[] args) {
	    ApiParam<ApiPoundNoteQuery> req = new ApiParam<ApiPoundNoteQuery>();
	    ApiPoundNoteQuery a = new ApiPoundNoteQuery();
	    a.setNetweight("123");
	    req.setBody(a);
        System.out.println(JSON.toJSONString(req.getBody()));
    }
	@RequestMapping(value = "/up/weight", method = RequestMethod.POST)
	@ApiParamRawType(ApiPoundNoteQuery.class)
	@ApiAuthValidation(callType = "2")
	@ResponseBody
	public ApiResult detail(ApiParam<ApiPoundNoteQuery> req) {
		Result rs = Result.getErrorResult();
		ApiPoundNoteQuery query = req.getBody();
		query.setCurrid(req.getHead().getUserId());
		try {
            LoggerFactory.getLogger("poundNote").info("上传榜单:参数 {}", JSON.toJSONString(req.getBody()));
			rs = poundNoteService.savePoundNoteWeight(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * @Description 磅房验证
	 * @author zhanggaohao
	 * @version 2017年3月27日 上午9:09:36
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/validation", method = RequestMethod.POST)
	@ApiParamRawType(ApiPoundNoteValidation.class)
	@ApiAuthValidation(callType = "2")
	@ResponseBody
	public ApiResult validation(ApiParam<ApiPoundNoteValidation> req) {
		Result rs = Result.getErrorResult();
		ApiPoundNoteValidation valid = req.getBody();
		valid.setCurrid(req.getHead().getUserId());
		try {
			rs = poundNoteService.validation(valid);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * @Description 历史皮重查询
	 * @author zhanggaohao
	 * @version 2017年3月27日 下午3:34:59
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/history/tareWeight", method = RequestMethod.POST)
	@ApiParamRawType(ApiPoundNoteQuery.class)
	@ApiAuthValidation(callType = "2")
	@ResponseBody
	public ApiResult tareWeight(ApiParam<ApiPoundNoteQuery> req) {
		Result rs = Result.getErrorResult();
		ApiPoundNoteQuery valid = req.getBody();
		valid.setCurrid(req.getHead().getUserId());
		try {
			rs = poundNoteService.tareWeight(valid);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * @Description 榜单查询
	 * 根绝通知单号以及服务类型获取榜单信息
	 * @author zhanggaohao
	 * @version 2017年3月27日 下午3:34:59
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryPountNote", method = RequestMethod.POST)
	@ApiParamRawType(ApiPoundNoteQuery.class)
	@ApiAuthValidation(callType = "2")
	@ResponseBody
	public ApiResult query(ApiParam<ApiPoundNoteQuery> req) {
		Result rs = Result.getErrorResult();
		ApiPoundNoteQuery valid = req.getBody();
		valid.setCurrid(req.getHead().getUserId());
		try {
			rs = poundNoteService.detail(valid);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 
	 * @annotation 查询签收信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/querySignDetail", method = RequestMethod.POST)
    @ApiParamRawType(ApiPoundNoteQuery.class)
    @ApiAuthValidation(callType = "2")
    @ResponseBody
    public ApiResult querySignDetail(ApiParam<ApiPoundNoteQuery> req) {
        Result rs = Result.getErrorResult();
        ApiPoundNoteQuery valid = req.getBody();
        valid.setCurrid(req.getHead().getUserId());
        try {
            rs = poundNoteService.querySignDetail(valid);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }

}
