package com.tianrui.web.action.basicFile.measure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.measure.IMinemouthManageService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.measure.MinemouthManageQuery;
import com.tianrui.api.req.basicFile.measure.MinemouthManageSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.measure.MinemouthManageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trfc/minemouth")
public class MinemouthManageAction {
	
	private Logger log=LoggerFactory.getLogger(MinemouthManageAction.class);
	
	@Autowired
	private IMinemouthManageService minemouthManageService;
    @Autowired
    private ISystemCodeService systemCodeService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view=new ModelAndView("basicFile/measure/minemouth");
		return view;
	}
	
	/**
	 * 分页查询数据
	 * @param query
	 * @return
	 */
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(MinemouthManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = minemouthManageService.page(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
    
    /**
     * 初始化新增参数
     */
    @RequestMapping(value="/addView",method=RequestMethod.POST)
    @ResponseBody
    public Result addView(HttpServletRequest request){
        Result result = Result.getSuccessResult();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            SystemUserResp user = SessionManager.getSessionUser(request);
            GetCodeReq codeReq = new GetCodeReq();
            codeReq.setCode("KD");
            codeReq.setCodeType(true);
            codeReq.setUserid(user.getId());
            map.put("code", systemCodeService.getCode(codeReq).getData());
            result.setData(map);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return result;
    }
    
    /**
     * 新增矿口管理信息
     * @param save
     * @return
     */
    @RequestMapping(value="/addMinemouth",method=RequestMethod.POST)
    @ResponseBody
    public Result addMinemouth(MinemouthManageSave save, HttpServletRequest request){
        Result result = Result.getSuccessResult();
        try {
            SystemUserResp user = SessionManager.getSessionUser(request);
            save.setCurrId(user.getId());
            result = minemouthManageService.addMinemouth(save);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return result;
    }
	/**
	 * 修改矿口管理信息
	 * @param save
	 * @return
	 */
	@RequestMapping(value="/updateMinemouth",method=RequestMethod.POST)
	@ResponseBody
	public Result updateMinemouth(MinemouthManageSave save, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
            SystemUserResp user = SessionManager.getSessionUser(request);
            save.setCurrId(user.getId());
			result = minemouthManageService.updateMinemouth(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	/**
	 * 删除矿口管理信息
	 * @param query
	 * @return
	 */
	@RequestMapping(value="/delMinemouth",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteMinemouth(MinemouthManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = minemouthManageService.deleteMinemouth(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/autoCompleteSearch")
	@ResponseBody
	public List<MinemouthManageResp> autoCompleteSearch(String term){
		List<MinemouthManageResp> list = null;
		try {
			list = minemouthManageService.autoCompleteSearch(term.trim());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}
	
}
