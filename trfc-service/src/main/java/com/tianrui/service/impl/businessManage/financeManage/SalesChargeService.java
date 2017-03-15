package com.tianrui.service.impl.businessManage.financeManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.financeManage.ISalesChargeService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.financeManage.SalesChargeQuery;
import com.tianrui.api.req.businessManage.financeManage.SalesChargeSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.financeManage.SalesChargeResp;
import com.tianrui.service.bean.businessManage.financeManage.SalesCharge;
import com.tianrui.service.mapper.businessManage.financeManage.SalesChargeMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 销售收款Service
 * @author YangZhenFu
 * @createtime 2017年3月6日 下午15:23:52
 * @classname SalesChargeService.java
 */
@Service
public class SalesChargeService implements ISalesChargeService{
	
	@Autowired
	private SalesChargeMapper salesChargeMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@Override
	public Result page(SalesChargeQuery query) throws Exception {
		Result result=Result.getParamErrorResult();
		if(query!=null){
			PaginationVO<SalesChargeResp> page=new PaginationVO<SalesChargeResp>();
			query.setState("1");
			Long count=salesChargeMapper.findSalesChargePageCount(query);
			if(count>0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SalesCharge>  list=salesChargeMapper.findSalesChargePage(query);
				page.setList(copyBeanList2RespList(list));
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				//保存数据
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
	public Result add(SalesChargeSave save) throws Exception {
		Result result=Result.getSuccessResult();
		if(save!=null){
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("D22006");
			codeReq.setCodeType(true);
			codeReq.setUserid(save.getMakeid());
			SalesCharge bean=new SalesCharge();
			bean.setCode(String.valueOf(systemCodeService.getCode(codeReq).getData()));
			List<SalesCharge> list=salesChargeMapper.selectSelective(bean);
			if(list!=null && list.size()>0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				return result;
			}
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			bean.setOrgid(Constant.ORG_ID);
			bean.setOrgname(Constant.ORG_NAME);
			bean.setChargeunit(Constant.ORG_NAME);
			bean.setCreator(save.getUser());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getUser());
			bean.setModifytime(System.currentTimeMillis());
			if(salesChargeMapper.insertSelective(bean)>0){
				result.setData("操作成功！");
				if(!StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	/**
	 * 集合转换
	 * @param List<SalesCharge> list
	 * @return List<SalesChargeResp> 
	 * @throws Exception
	 */
	private List<SalesChargeResp> copyBeanList2RespList(List<SalesCharge> list) throws Exception {
		List<SalesChargeResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesChargeResp>();
			for(SalesCharge begin : list){
				listResp.add(copyBeanList2RespList(begin));
			}
		}
		return listResp;
	}
	
	
	
	/**
	 * 实体bean类型转换
	 * @param SalesCharge bean
	 * @return SalesChargeResp
	 * @throws Exception
	 */
	private SalesChargeResp copyBeanList2RespList(SalesCharge bean) throws Exception {
		SalesChargeResp resp = null;
		if(bean != null){
			resp = new SalesChargeResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
}
