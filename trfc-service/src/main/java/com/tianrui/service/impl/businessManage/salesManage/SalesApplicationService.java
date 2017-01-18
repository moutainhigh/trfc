package com.tianrui.service.impl.businessManage.salesManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.nc.ICustomerManageService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationDetailService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.basicFile.nc.CustomerManageQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationDetailQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationDetailSave;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationSave;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SalesApplicationService implements ISalesApplicationService {
	
	@Autowired
	private SalesApplicationMapper salesApplicationMapper;
	
	@Autowired
	private ISalesApplicationDetailService salesApplicationDetailService;
	
	@Autowired
	private ICustomerManageService customerManageService;
	
	@Autowired
	private ISystemUserService systemUserService;
	
	@Override
	public PaginationVO<SalesApplicationResp> page(SalesApplicationQuery query) throws Exception{
		PaginationVO<SalesApplicationResp> page = null;
		if(query != null){
			page = new PaginationVO<SalesApplicationResp>();
			query.setState("1");
			long count = salesApplicationMapper.findSalesApplicationPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SalesApplication> list = salesApplicationMapper.findSalesApplicationPage(query);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	@Transactional
	@Override
	public Result add(SalesApplicationSave save) throws Exception {
		Result result = Result.getSuccessResult();
		if(save != null){
			SalesApplication sa = new SalesApplication();
			sa.setCode(save.getCode());
			List<SalesApplication> list = salesApplicationMapper.selectSelective(sa);
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				return result;
			}
			PropertyUtils.copyProperties(sa, save);
			sa.setId(UUIDUtil.getId());
			sa.setStatus("0");
			sa.setSource("1");
			sa.setState("1");
			sa.setBilltime(System.currentTimeMillis());
			sa.setCreator(save.getCreator());
			sa.setCreatetime(System.currentTimeMillis());
			sa.setModifier(save.getCreator());
			sa.setModifytime(System.currentTimeMillis());
			if(salesApplicationMapper.insertSelective(sa) > 0){
				SalesApplicationDetailSave sd = new SalesApplicationDetailSave();
				sd.setId(UUIDUtil.getId());
				sd.setSalesid(sa.getId());
				sd.setMaterielid(save.getMaterielid());
				sd.setMaterielname(save.getMaterielname());
				sd.setWarehouseid(save.getWarehouseid());
				sd.setWarehousename(save.getWarehousename());
				sd.setUnit(save.getUnit());
				sd.setSalessum(save.getSalessum());
				sd.setTaxprice(save.getTaxprice());
				sd.setTaxrate(save.getTaxrate());
				sd.setUntaxprice(save.getUntaxprice());
				sa.setCreator(save.getCreator());
				sa.setCreatetime(System.currentTimeMillis());
				sa.setModifier(save.getCreator());
				sa.setModifytime(System.currentTimeMillis());
				result = salesApplicationDetailService.add(sd);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result update(SalesApplicationSave save) throws Exception {
		Result result = Result.getSuccessResult();
		if(save != null){
			SalesApplication sa = new SalesApplication();
			PropertyUtils.copyProperties(sa, save);
//			sa.setModifier("");
			sa.setModifytime(System.currentTimeMillis());
			if(salesApplicationMapper.updateByPrimaryKeySelective(sa) > 0){
				SalesApplicationDetailSave sd = new SalesApplicationDetailSave();
				sd.setId(save.getDetailid());
				sd.setMaterielid(save.getMaterielid());
				sd.setMaterielname(save.getMaterielname());
				sd.setWarehouseid(save.getWarehouseid());
				sd.setWarehousename(save.getWarehousename());
				sd.setSalessum(save.getSalessum());
				sd.setTaxprice(save.getTaxprice());
				sd.setTaxrate(save.getTaxrate());
				sd.setUntaxprice(save.getUntaxprice());
				result = salesApplicationDetailService.update(sd);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result audit(SalesApplicationSave save) {
		Result result = Result.getSuccessResult();
		if(save != null && StringUtils.isNotBlank(save.getId())){
			SalesApplication sa = new SalesApplication();
			sa.setId(save.getId());
			sa.setStatus("1");
			sa.setAuditid(save.getAuditid());
			sa.setAuditname(save.getAuditname());
			sa.setAudittime(System.currentTimeMillis());
			if(salesApplicationMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setData("操作成功！");
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result unaudit(SalesApplicationSave save) {
		Result result = Result.getSuccessResult();
		if(save != null && StringUtils.isNotBlank(save.getId())){
			SalesApplication sa = new SalesApplication();
			sa.setId(save.getId());
			sa.setStatus("0");
			sa.setAuditid(save.getAuditid());
			sa.setAuditname(save.getAuditname());
			sa.setAudittime(System.currentTimeMillis());
			if(salesApplicationMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setData("操作成功！");
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result delete(SalesApplicationQuery query) {
		Result result = Result.getSuccessResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesApplication sa = new SalesApplication();
			sa.setId(query.getId());
			sa.setState("0");
			sa.setModifier(query.getCurrid());
			sa.setModifytime(System.currentTimeMillis());
			if(salesApplicationMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setData("操作成功！");
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Override
	public SalesApplicationResp findOne(SalesApplicationQuery query) throws Exception {
		if(query != null && StringUtils.isNotBlank(query.getId())){
			return copyBean2Resp(salesApplicationMapper.selectByPrimaryKey(query.getId()));
		}
		return null;
	}
	
	private List<SalesApplicationResp> copyBeanList2RespList(List<SalesApplication> list) throws Exception {
		List<SalesApplicationResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesApplicationResp>();
			for(SalesApplication sales : list){
				listResp.add(copyBean2Resp(sales));
			}
		}
		return listResp;
	}
	
	private SalesApplicationResp copyBean2Resp(SalesApplication bean) throws Exception {
		SalesApplicationResp resp = null;
		if(bean != null){
			resp = new SalesApplicationResp();
			PropertyUtils.copyProperties(resp, bean);
			if(StringUtils.isNotBlank(bean.getCustomerid())){
				CustomerManageQuery query = new CustomerManageQuery();
				query.setId(bean.getCustomerid());
				resp.setCustomerManageResp(customerManageService.findOne(query));
			}
			SalesApplicationDetailQuery query = new SalesApplicationDetailQuery();
			query.setSalesid(bean.getId());
			resp.setDetailResp(salesApplicationDetailService.findListBySalesApplicationId(query).get(0));
			if(StringUtils.isNotBlank(resp.getCreator())){
				resp.setCreatorname(systemUserService.getUser(resp.getCreator()).getName());
			}
		}
		return resp;
	}
	
}