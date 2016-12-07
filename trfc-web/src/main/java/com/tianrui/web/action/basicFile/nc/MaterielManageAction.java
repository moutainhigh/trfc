package com.tianrui.web.action.basicFile.nc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.req.basicFile.nc.MaterielManageReq;
import com.tianrui.api.resp.basicFile.nc.MaterielManageResp;
import com.tianrui.service.impl.basicFile.nc.MaterielManageService;
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
	private MaterielManageService materielManageService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/nc/materiel");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(MaterielManageReq req){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<MaterielManageResp> page = materielManageService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setCode("-1");
			result.setError("系统异常，请联系管理员！");
			return result;
		}
		return result;
	}
	
	@RequestMapping("updateMater")
	@ResponseBody
	public Result updateMater(MaterielManageReq req){
		Result result = Result.getSuccessResult();
		try {
			int count = materielManageService.updateMateriel(req);
			if(count == 1){
				result.setData(count);
			}else{
				result.setCode("-2");
				result.setError("修改失败，请稍后重试！");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setCode("-1");
			result.setError("系统异常，请联系管理员！");
			return result;
		}
		return result;
	}
	
}