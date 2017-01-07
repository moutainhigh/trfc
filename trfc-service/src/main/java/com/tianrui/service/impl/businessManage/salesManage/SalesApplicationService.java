package com.tianrui.service.impl.businessManage.salesManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationReq;
import com.tianrui.api.resp.basicFile.nc.CustomerManageResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
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
	private SalesApplicationDetailMapper salesApplicationDetailMapper;
	
	@Autowired
	private CustomerManageMapper customerManageMapper;
	
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
	public Result add(SalesApplicationReq req) throws Exception {
		Result result = Result.getSuccessResult();
		if(req != null){
			SalesApplication sa = new SalesApplication();
			sa.setCode(req.getCode());
			List<SalesApplication> list = salesApplicationMapper.selectSelective(sa);
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				return result;
			}
			PropertyUtils.copyProperties(sa, req);
			sa.setId(UUIDUtil.getId());
			sa.setStatus("0");
			sa.setSource("1");
			sa.setState("1");
			sa.setCreator(req.getCreator());
			sa.setCreatetime(System.currentTimeMillis());
			sa.setModifier(req.getCreator());
			sa.setModifytime(System.currentTimeMillis());
			if(salesApplicationMapper.insert(sa) > 0){
				SalesApplicationDetail sd = new SalesApplicationDetail();
				sd.setId(UUIDUtil.getId());
				sd.setSalesid(sa.getId());
				sd.setMaterielid(req.getMaterielid());
				sd.setMaterielname(req.getMaterielname());
				sd.setWarehouseid(req.getWarehouseid());
				sd.setWarehousename(req.getWarehousename());
				sd.setSalessum(req.getSalessum());
				sd.setTaxprice(req.getTaxprice());
				sd.setTaxrate(req.getTaxrate());
				sd.setUntaxprice(req.getUntaxprice());
				sa.setCreator(req.getCreator());
				sa.setCreatetime(System.currentTimeMillis());
				sa.setModifier(req.getCreator());
				sa.setModifytime(System.currentTimeMillis());
				if(salesApplicationDetailMapper.insertSelective(sd) > 0){
					result.setData("添加成功！");
				}
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result update(SalesApplicationReq req) throws Exception {
		Result result = Result.getSuccessResult();
		if(req != null){
			SalesApplication sa = new SalesApplication();
			PropertyUtils.copyProperties(sa, req);
//			sa.setModifier("");
			sa.setModifytime(System.currentTimeMillis());
			if(salesApplicationMapper.updateByPrimaryKeySelective(sa) > 0){
				SalesApplicationDetail sd = new SalesApplicationDetail();
				sd.setId(req.getDetailid());
				sd.setMaterielid(req.getMaterielid());
				sd.setMaterielname(req.getMaterielname());
				sd.setWarehouseid(req.getWarehouseid());
				sd.setWarehousename(req.getWarehousename());
				sd.setSalessum(req.getSalessum());
				sd.setTaxprice(req.getTaxprice());
				sd.setTaxrate(req.getTaxrate());
				sd.setUntaxprice(req.getUntaxprice());
				if(salesApplicationDetailMapper.updateByPrimaryKeySelective(sd) > 0){
					result.setData("修改成功！");
				}
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result audit(String id) {
		Result result = Result.getSuccessResult();
		if(StringUtils.isNotBlank(id)){
			SalesApplication sa = new SalesApplication();
			sa.setId(id);
			sa.setStatus("1");
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
	public Result unaudit(String id) {
		Result result = Result.getSuccessResult();
		if(StringUtils.isNotBlank(id)){
			SalesApplication sa = new SalesApplication();
			sa.setId(id);
			sa.setStatus("0");
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
	public Result delete(String id) {
		Result result = Result.getSuccessResult();
		if(StringUtils.isNotBlank(id)){
			SalesApplication sa = new SalesApplication();
			sa.setId(id);
			sa.setState("0");
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
			CustomerManage cm = customerManageMapper.selectByPrimaryKey(resp.getCustomerid());
			if(cm != null){
				CustomerManageResp cmResp = new CustomerManageResp();
				PropertyUtils.copyProperties(cmResp, cm);
				resp.setCustomerManageResp(cmResp);
			}
			SalesApplicationDetail detail = salesApplicationDetailMapper.selectBySalesId(bean.getId()).get(0);
			resp.setDetailResp(copyBean2Resp(detail));
		}
		return resp;
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