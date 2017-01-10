package com.tianrui.web.action.basicFile.nc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.api.req.basicFile.nc.MaterielManageSave;
import com.tianrui.api.resp.basicFile.nc.MaterielManageResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 物料管理Controller
 * @author zhanggaohao
 * @createtime 2016年12月7日 上午9:28:11
 * @classname MaterielManageAction.java
 */
@Controller
@RequestMapping("materiel")
public class MaterielManageAction {
	
	private Logger log = LoggerFactory.getLogger(MaterielManageAction.class);
	
	@Autowired
	private IMaterielManageService materielManageService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/nc/materiel");
		return view;
	}
	
	@RequestMapping("page")
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
	
	@RequestMapping("updateMater")
	@ResponseBody
	public Result updateMater(MaterielManageSave save){
		Result result = Result.getSuccessResult();
		try {
			result = materielManageService.updateMateriel(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("findAll")
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
	
}