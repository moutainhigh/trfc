package com.tianrui.service.impl.businessManage.financeManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.financeManage.ISalesDetailService;
import com.tianrui.api.req.businessManage.financeManage.SalesDetailQuery;
import com.tianrui.api.req.businessManage.financeManage.SalesDetailSave;
import com.tianrui.api.resp.businessManage.financeManage.SalesDetailResp;
import com.tianrui.service.bean.businessManage.financeManage.SalesDetail;
import com.tianrui.service.mapper.businessManage.financeManage.SalesDetailMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 销售明细service  
 * @author YangZhenFu
 * @createtime 2017年3月16日 上午10:26:52
 * @classname SalesDetailService.java
 */
@Service
public class SalesDetailService implements ISalesDetailService{

	@Autowired
	private SalesDetailMapper salesDetailMapper;
	
	@Override
	public Result page(SalesDetailQuery query) throws Exception {
		Result result=Result.getParamErrorResult();
		if(query!=null){
			PaginationVO<SalesDetailResp> page=new PaginationVO<SalesDetailResp>();
			query.setState("1");
			Long count=salesDetailMapper.findSalesDetailPageCount(query);
			if(count>0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SalesDetail> list=salesDetailMapper.findSalesDetailPage(query);
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
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	@Transactional
	@Override
	public Result add(SalesDetailSave save) throws Exception {
		Result result=Result.getSuccessResult();
		if(save!=null){
			SalesDetail bean=new SalesDetail();
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			bean.setState("1");
			bean.setOrgid(Constant.ORG_ID);
			bean.setOrgname(Constant.ORG_NAME);
			bean.setCreator(save.getUser());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getUser());
			bean.setModifytime(System.currentTimeMillis());
			bean.setUtc(System.currentTimeMillis());
			if(salesDetailMapper.insertSelective(bean)>0){
				result.setData("操作成功");
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	
	
	/**
	 * 集合转换
	 * @param List<SalesDetail> list
	 * @return List<SalesDetailResp> 
	 * @throws Exception
	 */
	private List<SalesDetailResp> copyBeanList2RespList(List<SalesDetail> list) throws Exception {
		List<SalesDetailResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesDetailResp>();
			for(SalesDetail begin : list){
				listResp.add(copyBeanList2RespList(begin));
			}
		}
		return listResp;
	}
	
	
	
	/**
	 * 实体bean类型转换
	 * @param SalesDetail bean
	 * @return SalesDetailResp
	 * @throws Exception
	 */
	private SalesDetailResp copyBeanList2RespList(SalesDetail bean) throws Exception {
		SalesDetailResp resp = null;
		if(bean != null){
			resp = new SalesDetailResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
}
