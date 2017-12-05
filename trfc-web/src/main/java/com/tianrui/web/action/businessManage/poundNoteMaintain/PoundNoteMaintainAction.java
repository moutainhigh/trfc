package com.tianrui.web.action.businessManage.poundNoteMaintain;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.poundNoteMaintain.IPoundNoteService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteCopyDTO;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteQuery;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.api.resp.common.UploadImageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trfc/poundNote")
public class PoundNoteMaintainAction {

	private Logger logger = LoggerFactory.getLogger(PoundNoteMaintainAction.class);
	
	@Autowired
	private IPoundNoteService poundNoteService;
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@RequestMapping("/purchase/main")
	public ModelAndView purchaseMain(){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/purchasePoundNote");
		return view;
	}
	
	@RequestMapping("/purchase/page")
	@ResponseBody
	public Result purchasePage(PoundNoteQuery query){
		Result result = Result.getSuccessResult();
		try {
			query.setModuleType("1");
			PaginationVO<PoundNoteResp> page = poundNoteService.purchasePage(query);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/purchaseNotice/returnAdd/page")
	@ResponseBody
	public Result purchaseNoticeReturnAddPage(PoundNoteQuery query){
		Result result = Result.getSuccessResult();
		try {
			query.setModuleType("2");
			PaginationVO<PoundNoteResp> page = poundNoteService.purchasePage(query);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/purchase/addView")
	@ResponseBody
	public ModelAndView purchaseAddView(HttpServletRequest request){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/purchasePoundNoteAdd");
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("RK");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			view.addObject("code", systemCodeService.getCode(codeReq).getData());
			view.addObject("nowDate", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			view.addObject("makebillname", user.getName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("/purchase/addPoundNote")
	@ResponseBody
	public Result addPurchasePoundNote(PoundNoteSave save, HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setMakerid(user.getId());
			save.setMakebillname(user.getName());
			result = poundNoteService.addPurchasePoundNote(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/purchase/returnAddView")
	@ResponseBody
	public ModelAndView returnAddView(String id, HttpServletRequest request){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/purchasePoundNoteReturnAdd");
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("RK");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			view.addObject("code", systemCodeService.getCode(codeReq).getData());
			view.addObject("poundNote", poundNoteService.findOne(id));
			view.addObject("nowDate", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			view.addObject("makebillname", user.getName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("/purchase/returnAddPoundNote")
	@ResponseBody
	public Result returnAddPoundNote(PoundNoteSave save, HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setMakerid(user.getId());
			save.setMakebillname(user.getName());
			result = poundNoteService.returnAddPoundNote(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/purchase/redcollide")
	@ResponseBody
	public Result purchaseRedcollide(PoundNoteQuery query, HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrId(user.getId());
			result = poundNoteService.purchaseRedcollide(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * @annotation 参照页面  已推单的必须红冲后才能参照，未推单的可以直接参照
	 * @return
	 */
	@RequestMapping("/purchase/copyView")
	public ModelAndView copyView(String id) {
	    ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/purchaseCopyView");
	    try {
            view.addObject("poundNote", poundNoteService.findOne(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
	    return view;
	}
	
	@RequestMapping("/purchase/copy")
    @ResponseBody
    public Result copy(PoundNoteCopyDTO copy, HttpServletRequest request) {
	    Result result = Result.getParamErrorResult();
	    try {
            SystemUserResp user = SessionManager.getSessionUser(request);
            copy.setCurrId(user.getId());
	        result = poundNoteService.copy(copy);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return result;
    }
	
	@RequestMapping("/purchase/invalid")
	@ResponseBody
	public Result purchaseInvalid(PoundNoteQuery query, HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrId(user.getId());
			result = poundNoteService.invalid(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@RequestMapping("/purchase/detail")
	public ModelAndView purchasePoundNoteDetail(String id){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/purchasePoundNoteDetail");
		try {
			PoundNoteResp poundNote = poundNoteService.findOne(id);
			view.addObject("poundNote", poundNote);
			List<UploadImageResp> resps = poundNoteService.getPoundImages(poundNote.getNoticecode());
			List<UploadImageResp> accessImages = new ArrayList<UploadImageResp>();
			List<UploadImageResp> poundImages = new ArrayList<UploadImageResp>();
			for(UploadImageResp resp : resps){
				if(StringUtils.equals(resp.getSource(), "1") || StringUtils.equals(resp.getSource(), "4")){
					accessImages.add(resp);
				}else if(StringUtils.equals(resp.getSource(), "3") ||	StringUtils.equals(resp.getSource(), "2")){
					poundImages.add(resp);
				}
			}
			view.addObject("accessImages",accessImages);
			view.addObject("poundImages",poundImages);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("/sales/main")
	public ModelAndView salesMain(){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/salesPoundNote");
		return view;
	}
	
	@RequestMapping("/sales/page")
	@ResponseBody
	public Result salesPage(PoundNoteQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<PoundNoteResp> page = poundNoteService.salesPage(query);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/sales/addView")
	public ModelAndView salesAddView(HttpServletRequest request){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/salesPoundNoteAdd");
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("CK");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			view.addObject("code", systemCodeService.getCode(codeReq).getData());
			view.addObject("nowDate", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			view.addObject("makebillname", user.getName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("/sales/addPoundNote")
	@ResponseBody
	public Result addSalesPoundNote(PoundNoteSave save, String bills, HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setMakerid(user.getId());
			save.setMakebillname(user.getName());
			result = poundNoteService.addSalesPoundNote(save, bills);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@RequestMapping("/sales/updateSerialNumberView")
	public ModelAndView updateSerialNumberView(String id){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/salesPoundNoteUpdateSerialNumber");
		try {
			view.addObject("poundNote", poundNoteService.findOne(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("/sales/updateSerialNumber")
	@ResponseBody
	public Result addSalesPoundNote(PoundNoteQuery query, HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrId(user.getId());
			result = poundNoteService.updateSerialNumber(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/sales/redcollide")
	@ResponseBody
	public Result salesRedcollide(PoundNoteQuery query, HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrId(user.getId());
			//result = poundNoteService.salesRedcollide(query);
			result.setError("销售没有红冲！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/sales/invalid")
	@ResponseBody
	public Result salesInvalid(PoundNoteQuery query, HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrId(user.getId());
			result = poundNoteService.invalid(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/sales/detail")
	public ModelAndView salesPoundNoteDetail(String id){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/salesPoundNoteDetail");
		try {
			PoundNoteResp poundNote = poundNoteService.findOne(id);
			view.addObject("poundNote", poundNote);
			List<UploadImageResp> resps = poundNoteService.getPoundImages(poundNote.getNoticecode());
			List<UploadImageResp> accessImages = new ArrayList<UploadImageResp>();
			List<UploadImageResp> poundImages = new ArrayList<UploadImageResp>();
			for(UploadImageResp resp : resps){
				if(StringUtils.equals(resp.getSource(), "1") || StringUtils.equals(resp.getSource(), "4")){
					accessImages.add(resp);
				}else if(StringUtils.equals(resp.getSource(), "3") ||	StringUtils.equals(resp.getSource(), "2")){
					poundImages.add(resp);
				}
			}
			view.addObject("accessImages",accessImages);
			view.addObject("poundImages",poundImages);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("/cutover/main")
	public ModelAndView cutoverMain(){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/cutoverPoundNote");
		return view;
	}
	
	@RequestMapping("/cutover/page")
	@ResponseBody
	public Result cutoverPoundNotePage(PoundNoteQuery query){
		Result result = Result.getSuccessResult();
		try {
			query.setBilltype("4");
			PaginationVO<PoundNoteResp> page = poundNoteService.otherPoundNotePage(query);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/cutover/addView")
	public ModelAndView cutoverAddView(HttpServletRequest request){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/cutoverPoundNoteAdd");
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("DY");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			view.addObject("code", systemCodeService.getCode(codeReq).getData());
			view.addObject("orgname", Constant.ORG_NAME);
			view.addObject("nowDate", DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S));
			view.addObject("makebillname", user.getName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("/cutover/add")
	@ResponseBody
	public Result cutoverAdd(PoundNoteSave save, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setMakerid(user.getId());
			save.setMakebillname(user.getName());
			result = poundNoteService.cutoverAdd(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/cutover/invalid")
	@ResponseBody
	public Result cutoverInvalid(PoundNoteQuery query, HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrId(user.getId());
			result = poundNoteService.invalid(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/otherInto/main")
	public ModelAndView otherIntoMain(){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/otherIntoPoundNote");
		return view;
	}
	
	@RequestMapping("/otherInto/page")
	@ResponseBody
	public Result otherIntoPoundNotePage(PoundNoteQuery query){
		Result result = Result.getSuccessResult();
		try {
			query.setBilltype("5");
			PaginationVO<PoundNoteResp> page = poundNoteService.otherPoundNotePage(query);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/otherInto/addView")
	public ModelAndView otherIntoAddView(HttpServletRequest request){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/otherIntoPoundNoteAdd");
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("QR");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			view.addObject("code", systemCodeService.getCode(codeReq).getData());
			view.addObject("orgname", Constant.ORG_NAME);
			view.addObject("nowDate", DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S));
			view.addObject("makebillname", user.getName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("/otherInto/add")
	@ResponseBody
	public Result otherIntoAdd(PoundNoteSave save, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setMakerid(user.getId());
			save.setMakebillname(user.getName());
			result = poundNoteService.otherIntoAdd(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/otherInto/invalid")
	@ResponseBody
	public Result otherIntoInvalid(PoundNoteQuery query, HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrId(user.getId());
			result = poundNoteService.invalid(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/otherOut/main")
	public ModelAndView otherOutMain(){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/otherOutPoundNote");
		return view;
	}
	
	@RequestMapping("/otherOut/page")
	@ResponseBody
	public Result otherOutPoundNotePage(PoundNoteQuery query){
		Result result = Result.getSuccessResult();
		try {
			query.setBilltype("7");
			PaginationVO<PoundNoteResp> page = poundNoteService.otherPoundNotePage(query);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/otherOut/addView")
	public ModelAndView otherOutAddView(HttpServletRequest request){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/otherOutPoundNoteAdd");
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("QC");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			view.addObject("code", systemCodeService.getCode(codeReq).getData());
			view.addObject("orgname", Constant.ORG_NAME);
			view.addObject("nowDate", DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S));
			view.addObject("makebillname", user.getName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("/otherOut/add")
	@ResponseBody
	public Result otherOutAdd(PoundNoteSave save, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setMakerid(user.getId());
			save.setMakebillname(user.getName());
			result = poundNoteService.otherOutAdd(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/otherOut/invalid")
	@ResponseBody
	public Result otherOutInvalid(PoundNoteQuery query, HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrId(user.getId());
			result = poundNoteService.invalid(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/print")
	@ResponseBody
	public Result print(PoundNoteQuery query, HttpServletRequest request){
	    Result result = Result.getParamErrorResult();
	    try {
	        SystemUserResp user = SessionManager.getSessionUser(request);
	        query.setCurrId(user.getId());
	        result = poundNoteService.print(query);
	    } catch (Exception e) {
	        logger.error(e.getMessage(), e);
	        result.setErrorCode(ErrorCode.SYSTEM_ERROR);
	    }
	    return result;
	}
}
