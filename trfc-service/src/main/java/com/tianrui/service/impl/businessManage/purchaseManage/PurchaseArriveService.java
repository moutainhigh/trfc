package com.tianrui.service.impl.businessManage.purchaseManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationDetailService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveQuery;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class PurchaseArriveService implements IPurchaseArriveService {

	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private IPurchaseApplicationService purchaseApplicationService;
	@Autowired
	private IPurchaseApplicationDetailService purchaseApplicationDetailService;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@Override
	public PaginationVO<PurchaseArriveResp> page(PurchaseArriveQuery query) throws Exception{
		PaginationVO<PurchaseArriveResp> page = null;
		if(query != null){
			page = new PaginationVO<PurchaseArriveResp>();
			query.setState("1");
			long count = purchaseArriveMapper.findPurchaseArrivePageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<PurchaseArrive> list = purchaseArriveMapper.findPurchaseArrivePage(query);
				page.setList(copyBeanList2RespList(list));
			}
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
			page.setTotal(count);
		}
		return page;
	}

	@Transactional
	@Override
	public Result add(PurchaseArriveSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			PurchaseArrive pa = new PurchaseArrive();
			pa.setVehicleid(save.getVehicleid());
			List<PurchaseArrive> listVehicle = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
			if(listVehicle != null && listVehicle.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			SalesArrive sa = new SalesArrive();
			sa.setVehicleid(save.getVehicleid());
			List<SalesArrive> listVehicle1 = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
			if(listVehicle1 != null && listVehicle1.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle1.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			pa.setVehicleid(null);
			pa.setDriverid(save.getDriverid());
			List<PurchaseArrive> listDriver = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
			if(listDriver != null && listDriver.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			sa.setVehicleid(null);
			sa.setDriverid(save.getDriverid());
			List<SalesArrive> listDriver1 = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
			if(listDriver1 != null && listDriver1.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver1.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			PropertyUtils.copyProperties(pa, save);
			pa.setId(UUIDUtil.getId());
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("DH");
			codeReq.setCodeType(true);
			codeReq.setUserid(save.getCurrId());
			pa.setCode(systemCodeService.getCode(codeReq).getData().toString());
			pa.setAuditstatus("0");
			pa.setStatus("0");
			pa.setState("1");
			pa.setSource("0");
			pa.setMakerid(save.getCurrId());
			pa.setMakebillname(systemUserService.getUser(save.getCurrId()).getName());
			pa.setCreator(save.getCurrId());
			pa.setCreatetime(System.currentTimeMillis());
			pa.setModifier(save.getCurrId());
			pa.setModifytime(System.currentTimeMillis());
			if(purchaseArriveMapper.insertSelective(pa) > 0 
					&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result updateOperation(PurchaseArriveSave update) throws Exception {
		Result result = Result.getParamErrorResult();
		if(update != null){
			PurchaseArrive pa = new PurchaseArrive();
			PropertyUtils.copyProperties(pa, update);
			if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) == 1){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}
		return result;
	}

	@Override
	public PurchaseArriveResp findOne(PurchaseArriveQuery query) throws Exception {
		PurchaseArriveResp resp = null;
		if(query != null && StringUtils.isNotBlank(query.getId())){
			resp = copyBean2Resp(purchaseArriveMapper.selectByPrimaryKey(query.getId()));
		}
		return resp;
	}
	
	private List<PurchaseArriveResp> copyBeanList2RespList(List<PurchaseArrive> list) throws Exception {
		List<PurchaseArriveResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<PurchaseArriveResp>();
			for(PurchaseArrive mater : list){
				listResp.add(copyBean2Resp(mater));
			}
		}
		return listResp;
	}
	
	private PurchaseArriveResp copyBean2Resp(PurchaseArrive bean) throws Exception {
		PurchaseArriveResp resp = null;
		if(bean != null){
			resp = new PurchaseArriveResp();
			PropertyUtils.copyProperties(resp, bean);
			if(StringUtils.isNotBlank(bean.getBillid())){
				resp.setPurchaseApplicationResp(purchaseApplicationService.findOne(bean.getBillid()));
			}
			if(StringUtils.isNotBlank(bean.getBilldetailid())){
				resp.setPurchaseApplicationDetailResp(purchaseApplicationDetailService.findOne(bean.getBilldetailid()));
			}
		}
		return resp;
	}
	
}
