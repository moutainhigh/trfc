package com.tianrui.web.action.businessManage.purchaseManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.purchaseManage.IPushSingleService;
import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.api.resp.businessManage.purchaseManage.PushSingleResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;


/**
  * 推单管理
  * <p>Title:PushSingleAction </p>
  * <p>Description:TODO </p>
  * <p>Company: </p> 
  * @author   yangbobo
  * @date   2017年11月16日 下午2:01:03
 */
@RequestMapping("/trfc/PushSingleAction")
@Controller
public class PushSingleAction {
	private Logger log = LoggerFactory.getLogger(PushSingleAction.class);
	@Autowired
	private IPushSingleService pushSingleService;
	
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/purchaseManage/PushSingle");
		return view;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(PushSingleReq req){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<PushSingleResp> page = pushSingleService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
