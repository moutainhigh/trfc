package com.tianrui.web.action.basicFile.nc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.intf.common.IUpdateFromDcService;
import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.api.req.basicFile.nc.MaterielManageSave;
import com.tianrui.api.resp.basicFile.nc.MaterielManageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

/**
 * 物料管理Controller
 * @author zhanggaohao
 * @createtime 2016年12月7日 上午9:28:11
 * @classname MaterielManageAction.java
 */
@Controller
@RequestMapping("/trfc/materiel")
public class MaterielManageAction {
	
	private Logger log = LoggerFactory.getLogger(MaterielManageAction.class);
	
	@Autowired
	private IMaterielManageService materielManageService;
	@Autowired
	private IUpdateFromDcService updateFromDcService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/nc/materiel");
		return view;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(MaterielManageQuery req){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<MaterielManageResp> page = materielManageService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/updateMater")
	@ResponseBody
	public Result updateMater(MaterielManageSave save, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setCurrUId(user.getId());
			result = materielManageService.updateMateriel(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public Result findAll(){
		Result result = Result.getSuccessResult();
		try {
			result = materielManageService.findListByParmas(null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/autoCompleteSearch")
	@ResponseBody
	public List<MaterielManageResp> autoCompleteSearch(String term,@RequestParam(value="type", required=false, defaultValue="")String type){
		List<MaterielManageResp> list = null;
		try {
			list = materielManageService.autoCompleteSearch(term.trim(),type.trim());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping("/updateFromDc")
	@ResponseBody
	public Result updateFromDc(){
		Result result = Result.getErrorResult();
		try {
			result = updateFromDcService.updateFromDc("1");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}