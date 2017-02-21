package com.tianrui.service.impl.businessManage.salesManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationDetailService;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationDetailQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationDetailSave;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SalesApplicationDetailService implements ISalesApplicationDetailService {
	
	@Autowired
	private SalesApplicationDetailMapper salesApplicationDetailMapper;
	
	@Override
	public Result add(SalesApplicationDetailSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			SalesApplicationDetail bean = new SalesApplicationDetail();
			PropertyUtils.copyProperties(bean, save);
			if(salesApplicationDetailMapper.insertSelective(bean) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result update(SalesApplicationDetailSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			SalesApplicationDetail bean = new SalesApplicationDetail();
			PropertyUtils.copyProperties(bean, save);
			if(salesApplicationDetailMapper.updateByPrimaryKeySelective(bean) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public List<SalesApplicationDetailResp> findListBySalesApplicationId(SalesApplicationDetailQuery query) throws Exception {
		List<SalesApplicationDetailResp> list = null;
		if(query != null && StringUtils.isNotBlank(query.getSalesid())){
			list = copyBeanList2RespList(salesApplicationDetailMapper.selectBySalesId(query.getSalesid()));
		}
		return list;
	}
	
	@Override
	public List<SalesApplicationDetailResp> selectBySalesIds(List<String> salesIds) throws Exception {
		List<SalesApplicationDetailResp> list = null;
		if(CollectionUtils.isNotEmpty(salesIds)){
			list = copyBeanList2RespList(salesApplicationDetailMapper.selectBySalesIds(salesIds));
		}
		return list;
	}
	
	private List<SalesApplicationDetailResp> copyBeanList2RespList(List<SalesApplicationDetail> list) throws Exception {
		List<SalesApplicationDetailResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesApplicationDetailResp>();
			for(SalesApplicationDetail bean : list){
				listResp.add(copyBean2Resp(bean));
			}
		}
		return listResp;
	}
	
	private SalesApplicationDetailResp copyBean2Resp(SalesApplicationDetail bean) throws Exception {
		SalesApplicationDetailResp resp = null;
		if(bean != null){
			resp = new SalesApplicationDetailResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

}