package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.measure.ITransportunitManageService;
import com.tianrui.api.req.basicFile.measure.TransportunitManageQuery;
import com.tianrui.api.req.basicFile.measure.TransportunitManageSave;
import com.tianrui.api.resp.basicFile.measure.TransportunitManageResp;
import com.tianrui.service.bean.basicFile.measure.TransportunitManage;
import com.tianrui.service.mapper.basicFile.measure.TransportunitManageMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 运输单位Service
 * 
 * @author YangZhenFu
 * @createtime 2017年2月5日 上午10:07:52
 * @classname TransportunitManageService.java
 */
@Service
public class TransportunitManageService implements ITransportunitManageService{
	
	@Autowired
	private TransportunitManageMapper transportunitManageMapper;
	
	
	@Override
	public Result page(TransportunitManageQuery query) throws Exception {
		Result result=Result.getSuccessResult();
		if(query != null){
			PaginationVO<TransportunitManageResp> page = new PaginationVO<TransportunitManageResp>();
			query.setState("1");
			long count = transportunitManageMapper.findTransportunitPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<TransportunitManage> list = transportunitManageMapper.findTransportunitPage(query);
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
	public Result addTransportunit(TransportunitManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			TransportunitManage transportunit = new TransportunitManage();
			transportunit.setCode(save.getCode());
			transportunit.setName(save.getName());
			transportunit.setState("1");
			List<TransportunitManage> list = transportunitManageMapper.selectSelective(transportunit);
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				PropertyUtils.copyProperties(transportunit, save);
				transportunit.setId(UUIDUtil.getId());
				transportunit.setCreator("YZF");
				transportunit.setCreatetime(System.currentTimeMillis());
				transportunit.setModifier("YZF");
				transportunit.setModifytime(System.currentTimeMillis());
				if(transportunitManageMapper.insertSelective(transportunit) > 0){
					result.setData(transportunit);
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
	public Result updateTransportunit(TransportunitManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			TransportunitManage transportunit = new TransportunitManage();
			PropertyUtils.copyProperties(transportunit, save);
			save.setModifier("YZF");
			save.setModifytime(System.currentTimeMillis());
			if(transportunitManageMapper.updateByPrimaryKeySelective(transportunit) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Transactional
	@Override
	public Result deleteTransportunit(TransportunitManageQuery query) {
		Result result=Result.getParamErrorResult();
		if(query!=null && StringUtils.isNotBlank(query.getId())){
			TransportunitManage transportunit=new TransportunitManage();
			transportunit.setId(query.getId());
			transportunit.setState("0");
			if(transportunitManageMapper.updateByPrimaryKeySelective(transportunit)>0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	
	private List<TransportunitManageResp> copyBeanList2RespList(List<TransportunitManage> list) throws Exception {
		List<TransportunitManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<TransportunitManageResp>();
			for(TransportunitManage Transportunit : list){
				listResp.add(copyBean2Resp(Transportunit));
			}
		}
		return listResp;
	}
	
	private TransportunitManageResp copyBean2Resp(TransportunitManage bean) throws Exception {
		TransportunitManageResp resp = null;
		if(bean != null){
			resp = new TransportunitManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

}
