package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.measure.IMinemouthManageService;
import com.tianrui.api.req.basicFile.measure.MinemouthManageQuery;
import com.tianrui.api.req.basicFile.measure.MinemouthManageSave;
import com.tianrui.api.resp.basicFile.measure.MinemouthManageResp;
import com.tianrui.service.bean.basicFile.measure.MinemouthManage;
import com.tianrui.service.mapper.basicFile.measure.MinemouthManageMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 矿口管理Service
 * 
 * @author YangZhenFu
 * @createtime 2017年2月7日 上午10:51:52
 * @classname MinemouthManageService.java
 */
@Service
public class MinemouthManageService implements IMinemouthManageService{

	@Autowired
	private MinemouthManageMapper minemouthManageMapper;
	
	@Override
	public Result page(MinemouthManageQuery query) throws Exception {
		Result result=Result.getSuccessResult();
		if(query != null){
			PaginationVO<MinemouthManageResp> page = new PaginationVO<MinemouthManageResp>();
			query.setState("1");
			long count = minemouthManageMapper.findMinemouthPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<MinemouthManage> list = minemouthManageMapper.findMinemouthPage(query);
				page.setList(copyBeanList2RespList(list));
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				result.setData(page);
			}else{
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				result.setData(page);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result addMinemouth(MinemouthManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			MinemouthManage minemouth = new MinemouthManage();
			minemouth.setCode(save.getCode());
			minemouth.setName(save.getName());
			minemouth.setState("1");
			List<MinemouthManage> list = minemouthManageMapper.selectSelective(minemouth);
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				PropertyUtils.copyProperties(minemouth, save);
				minemouth.setId(UUIDUtil.getId());
				minemouth.setCreator("YZF");
				minemouth.setCreatetime(System.currentTimeMillis());
				minemouth.setModifier("YZF");
				minemouth.setModifytime(System.currentTimeMillis());
				if(minemouthManageMapper.insertSelective(minemouth) > 0){
					result.setData(minemouth);
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result updateMinemouth(MinemouthManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			MinemouthManage minemouth = new MinemouthManage();
			PropertyUtils.copyProperties(minemouth, save);
			save.setModifier("YZF");
			save.setModifytime(System.currentTimeMillis());
			if(minemouthManageMapper.updateByPrimaryKeySelective(minemouth) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result deleteMinemouth(MinemouthManageQuery query) {
		Result result=Result.getParamErrorResult();
		if(query!=null && StringUtils.isNotBlank(query.getId())){
			MinemouthManage minemouth=new MinemouthManage();
			minemouth.setId(query.getId());
			minemouth.setState("0");
			if(minemouthManageMapper.updateByPrimaryKeySelective(minemouth)>0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	
	private List<MinemouthManageResp> copyBeanList2RespList(List<MinemouthManage> list) throws Exception {
		List<MinemouthManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<MinemouthManageResp>();
			for(MinemouthManage minemouth : list){
				listResp.add(copyBean2Resp(minemouth));
			}
		}
		return listResp;
	}
	
	private MinemouthManageResp copyBean2Resp(MinemouthManage bean) throws Exception {
		MinemouthManageResp resp = null;
		if(bean != null){
			resp = new MinemouthManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
}
