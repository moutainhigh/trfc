package com.tianrui.service.impl.businessManage.financeManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.financeManage.ISalesLedgerService;
import com.tianrui.api.req.businessManage.financeManage.SalesLedgerQuery;
import com.tianrui.api.req.businessManage.financeManage.SalesLedgerSave;
import com.tianrui.api.resp.businessManage.financeManage.SalesLedgerResp;
import com.tianrui.service.bean.businessManage.financeManage.SalesLedger;
import com.tianrui.service.mapper.businessManage.financeManage.SalesLedgerMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 收款台账service  
 * @author YangZhenFu
 * @createtime 2017年3月15日 上午11:20:52
 * @classname SalesLedgerService.java
 */
@Service
public class SalesLedgerService implements ISalesLedgerService{
	
	@Autowired
	private SalesLedgerMapper salesLedgerMapper;
	
	
	@Override
	public Result page(SalesLedgerQuery query) throws Exception {
		Result result=Result.getParamErrorResult();
		if(query!=null){
			PaginationVO<SalesLedgerResp> page=new PaginationVO<SalesLedgerResp>();
			query.setState("1");
			Long count=salesLedgerMapper.findSalesLedgerPageCount(query);
			if(count>0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SalesLedger> list=salesLedgerMapper.findSalesLedgerPage(query);
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
	public Result add(SalesLedgerSave save) throws Exception {
		Result result=Result.getSuccessResult();
		if(save!=null){
			SalesLedger bean=new SalesLedger();
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			bean.setOrgid(Constant.ORG_ID);
			bean.setOrgname(Constant.ORG_NAME);
			bean.setState("1");
			bean.setCreator(save.getUser());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getUser());
			bean.setModifytime(System.currentTimeMillis());
			bean.setUtc(System.currentTimeMillis());
			if(salesLedgerMapper.insertSelective(bean)>0){
				result.setData("操作成功！");
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
	 * @param List<SalesLedger> list
	 * @return List<SalesLedgerResp> 
	 * @throws Exception
	 */
	private List<SalesLedgerResp> copyBeanList2RespList(List<SalesLedger> list) throws Exception {
		List<SalesLedgerResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesLedgerResp>();
			for(SalesLedger begin : list){
				listResp.add(copyBeanList2RespList(begin));
			}
		}
		return listResp;
	}
	
	
	
	/**
	 * 实体bean类型转换
	 * @param SalesLedger bean
	 * @return SalesLedgerResp
	 * @throws Exception
	 */
	private SalesLedgerResp copyBeanList2RespList(SalesLedger bean) throws Exception {
		SalesLedgerResp resp = null;
		if(bean != null){
			resp = new SalesLedgerResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
}
