package com.tianrui.service.impl.businessManage.salesManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.measure.IDriverManageService;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.intf.businessManage.cardManage.ICardService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationDetailService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiSalesArriveQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.api.resp.businessManage.cardManage.CardResp;
import com.tianrui.api.resp.businessManage.salesManage.ApiDoorQueueResp;
import com.tianrui.api.resp.businessManage.salesManage.ApiSalesArriveResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.RFIDMapper;
import com.tianrui.service.mongo.impl.CodeGenDaoImpl;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 销售提货通知单Service
 * @author zhanggaohao
 * @createtime 2017年1月9日 下午2:22:43
 * @classname SalesArriveService.java
 */
@Service
public class SalesArriveService implements ISalesArriveService {

	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private ISalesApplicationService salesApplicationService;
	@Autowired
	private ISalesApplicationDetailService salesApplicationDetailService;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	@Autowired
	private RFIDMapper rfidMapper;
	@Autowired
	private CodeGenDaoImpl codeGenDaoImpl;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private IVehicleManageService vehicleManageService;
	@Autowired
	private IDriverManageService driverManageService;
	@Autowired
	private ICardService cardService;
	
	@Override
	public PaginationVO<SalesArriveResp> page(SalesArriveQuery query) throws Exception {
		PaginationVO<SalesArriveResp> page = null;
		if(query != null){
			page = new PaginationVO<SalesArriveResp>();
			query.setState("1");
			long count = salesArriveMapper.findSalesArrivePageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo() - 1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SalesArrive> list = salesArriveMapper.findSalesArrivePage(query);
				List<SalesArriveResp> listResp = copyBeanList2RespList(list, false);
				ListArriveRespSetListApplicationResp(listResp);
				page.setList(listResp);
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	@Transactional
	@Override
	public Result add(SalesArriveSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getBillid()) 
				&& StringUtils.isNotBlank(save.getVehicleid())
				&& StringUtils.isNotBlank(save.getBilldetailid())){
			SalesArrive bean = new SalesArrive();
			bean.setVehicleid(save.getVehicleid());
			List<SalesArrive> listVehicle = salesArriveMapper.checkDriverAndVehicleIsUse(bean);
			if(listVehicle != null && listVehicle.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			PurchaseArrive pa = new PurchaseArrive();
			pa.setVehicleid(save.getVehicleid());
			List<PurchaseArrive> listVehicle1 = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
			if(listVehicle1 != null && listVehicle1.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle1.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			bean.setVehicleid(null);
			bean.setDriverid(save.getDriverid());
			List<SalesArrive> listDriver = salesArriveMapper.checkDriverAndVehicleIsUse(bean);
			if(listDriver != null && listDriver.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			pa.setVehicleid(null);
			pa.setDriverid(save.getDriverid());
			List<PurchaseArrive> listDriver1 = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
			if(listDriver1 != null && listDriver1.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver1.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			VehicleManageResp vehicle = vehicleManageService.findOne(save.getVehicleid());
			if(vehicle != null){
				bean.setVehicleno(vehicle.getVehicleno());
				bean.setVehiclerfid(vehicle.getRfid());
			}
			DriverManageResp driver = driverManageService.findOne(save.getDriverid());
			if(driver != null){
				bean.setDrivername(driver.getName());
				bean.setDriveridentityno(driver.getIdentityno());
			}
			CardResp card = cardService.findOne(save.getIcardid());
			if(card != null){
				bean.setIcardno(card.getCardno());
			}
			SalesApplicationResp salesApplicationResp = salesApplicationService.findOne(save.getBillid(), false);
			if(salesApplicationResp != null){
				bean.setBillcode(salesApplicationResp.getCode());
			}
			SalesApplicationDetailResp salesApplicationDetailResp = salesApplicationDetailService.findOne(save.getBilldetailid());
			if(salesApplicationDetailResp != null){
				bean.setUnit(salesApplicationDetailResp.getUnit());
			}
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("TH");
			codeReq.setCodeType(true);
			codeReq.setUserid(save.getCurrUId());
			bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
			bean.setAuditstatus("0");
			bean.setStatus("0");
			bean.setState("1");
			bean.setSource("0");
			bean.setMakerid(save.getCurrUId());
			bean.setMakebillname(systemUserService.getUser(save.getCurrUId()).getName());
			bean.setCreator(save.getCurrUId());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getCurrUId());
			bean.setModifytime(System.currentTimeMillis());
			if(salesArriveMapper.insertSelective(bean) > 0 
					&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result update(SalesArriveSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getBillid()) 
				&& StringUtils.isNotBlank(save.getVehicleid())){
			SalesArrive bean = new SalesArrive();
			bean.setId(save.getId());
			bean.setVehicleid(save.getVehicleid());
			List<SalesArrive> listVehicle = salesArriveMapper.checkDriverAndVehicleIsUse(bean);
			if(listVehicle != null && listVehicle.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			PurchaseArrive pa = new PurchaseArrive();
			pa.setVehicleid(save.getVehicleid());
			List<PurchaseArrive> listVehicle1 = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
			if(listVehicle1 != null && listVehicle1.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle1.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			bean.setVehicleid(null);
			bean.setDriverid(save.getDriverid());
			List<SalesArrive> listDriver = salesArriveMapper.checkDriverAndVehicleIsUse(bean);
			if(listDriver != null && listDriver.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			pa.setVehicleid(null);
			pa.setDriverid(save.getDriverid());
			List<PurchaseArrive> listDriver1 = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
			if(listDriver1 != null && listDriver1.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver1.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			PropertyUtils.copyProperties(bean, save);
			VehicleManageResp vehicle = vehicleManageService.findOne(save.getVehicleid());
			if(vehicle != null){
				bean.setVehicleno(vehicle.getVehicleno());
				bean.setVehiclerfid(vehicle.getRfid());
			}
			DriverManageResp driver = driverManageService.findOne(save.getDriverid());
			if(driver != null){
				bean.setDrivername(driver.getName());
				bean.setDriveridentityno(driver.getIdentityno());
			}
			CardResp card = cardService.findOne(save.getIcardid());
			if(card != null){
				bean.setIcardno(card.getCardno());
			}
			SalesApplicationResp salesApplicationResp = salesApplicationService.findOne(save.getBillid(), false);
			if(salesApplicationResp != null){
				bean.setBillcode(salesApplicationResp.getCode());
			}
			SalesApplicationDetailResp salesApplicationDetailResp = salesApplicationDetailService.findOne(save.getBilldetailid());
			if(salesApplicationDetailResp != null){
				bean.setUnit(salesApplicationDetailResp.getUnit());
			}
			bean.setModifier(save.getCurrUId());
			bean.setModifytime(save.getCreatetime());
			if(salesArriveMapper.updateByPrimaryKeySelective(bean) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public SalesArriveResp findOne(String id) throws Exception {
		if(StringUtils.isNotBlank(id)){
			return copyBean2Resp(salesArriveMapper.selectByPrimaryKey(id), true);
		}
		return null;
	}
	
	@Override
	public List<SalesArriveResp> selectByIds(List<String> ids) throws Exception{
		List<SalesArriveResp> listResp = null;
		if(CollectionUtils.isNotEmpty(ids)){
			listResp = copyBeanList2RespList(salesArriveMapper.selectByIds(ids), false);
			ListArriveRespSetListApplicationResp(listResp);
		}
		return listResp;
	}
	
	private void ListArriveRespSetListApplicationResp(List<SalesArriveResp> listArrive) throws Exception{
		if(CollectionUtils.isNotEmpty(listArrive)){
			List<String> ids = new ArrayList<String>();
			List<String> detailIds = new ArrayList<String>();
			for(SalesArriveResp resp : listArrive){
				ids.add(resp.getBillid());
				detailIds.add(resp.getBilldetailid());
			}
			List<SalesApplicationResp> listApplication = salesApplicationService.selectByIds(ids);
			if(CollectionUtils.isNotEmpty(listApplication)){
				for(SalesArriveResp arriveResp : listArrive){
					for(SalesApplicationResp applicationResp : listApplication){
						if(StringUtils.equals(arriveResp.getBillid(), applicationResp.getId())){
							arriveResp.setSalesApplication(applicationResp);
						}
					}
				}
			}
			List<SalesApplicationDetailResp> listApplicationDetail = salesApplicationDetailService.selectByIds(detailIds);
			if(CollectionUtils.isNotEmpty(listApplicationDetail)){
				for(SalesArriveResp arriveResp : listArrive){
					for(SalesApplicationDetailResp applicationDetailResp : listApplicationDetail){
						if(StringUtils.equals(arriveResp.getBilldetailid(), applicationDetailResp.getId())){
							arriveResp.setSalesApplicationDetail(applicationDetailResp);
						}
					}
				}
			}
		}
	}
	
	private List<SalesArriveResp> copyBeanList2RespList(List<SalesArrive> list, boolean setApplication) throws Exception {
		List<SalesArriveResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesArriveResp>();
			for(SalesArrive sa : list){
				listResp.add(copyBean2Resp(sa, setApplication));
			}
		}
		return listResp;
	}
	
	private SalesArriveResp copyBean2Resp(SalesArrive bean, boolean setApplication) throws Exception {
		SalesArriveResp resp = null;
		if(bean != null){
			resp = new SalesArriveResp();
			PropertyUtils.copyProperties(resp, bean);
			if(setApplication){
				resp.setSalesApplication(salesApplicationService.findOne(bean.getBillid(), false));
				resp.setSalesApplicationDetail(salesApplicationDetailService.findOne(bean.getBilldetailid()));
			}
		}
		return resp;
	}

	@Override
	public Result audit(SalesArriveQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesArrive sa = new SalesArrive();
			sa.setId(query.getId());
			sa.setAuditstatus("1");
			sa.setModifier(query.getCurrUId());
			sa.setModifytime(System.currentTimeMillis());
			if(salesArriveMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result unaudit(SalesArriveQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesArrive sa = new SalesArrive();
			sa.setId(query.getId());
			sa.setAuditstatus("0");
			sa.setModifier(query.getCurrUId());
			sa.setModifytime(System.currentTimeMillis());
			if(salesArriveMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result invalid(SalesArriveQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesArrive sa = new SalesArrive();
			sa.setId(query.getId());
			sa.setStatus("3");
			sa.setAbnormalperson(query.getCurrUId());
			sa.setAbnormalpersonname(systemUserService.getUser(query.getCurrUId()).getName());
			sa.setAbnormaltime(System.currentTimeMillis());
			sa.setModifier(query.getCurrUId());
			sa.setModifytime(System.currentTimeMillis());
			if(salesArriveMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result outfactory(SalesArriveQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesArrive sa = new SalesArrive();
			sa.setId(query.getId());
			sa.setStatus("5");
			sa.setAbnormalperson(query.getCurrUId());
			sa.setAbnormalpersonname(systemUserService.getUser(query.getCurrUId()).getName());
			sa.setAbnormaltime(System.currentTimeMillis());
			sa.setModifier(query.getCurrUId());
			sa.setModifytime(System.currentTimeMillis());
			if(salesArriveMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result detailApi(ApiSalesArriveQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getVehicleno()) && StringUtils.isNotBlank(query.getRfid())) {
			VehicleManage vehicle = new VehicleManage();
			vehicle.setState("1");
			vehicle.setVehicleno(query.getVehicleno());
			List<VehicleManage> list = vehicleManageMapper.selectSelective(vehicle);
			//判断车牌号是否存在且唯一
			if (list != null && list.size() == 1) {
				RFID rfid = new RFID();
				rfid.setRfid(query.getRfid());
				rfid.setState(true);
				long count = rfidMapper.selectSelectiveCount(rfid);
				//判断RFID是否已注册且唯一
				if(count == 1){
					if(StringUtils.equals(query.getRfid(), list.get(0).getRfid())){
						SalesArrive sa = new SalesArrive();
						sa.setState("1");
						sa.setVehicleid(list.get(0).getId());
						List<SalesArrive> listSales = salesArriveMapper.selectSelective(sa);
						if(listSales == null || listSales.size() == 0){
							result.setErrorCode(ErrorCode.VEHICLE_NOT_ARRIVE);
						}else{
							SalesArriveResp resp = copyBean2Resp(listSales.get(0), true);
							SalesApplicationResp salesApplicationResp = resp.getSalesApplication();
							SalesApplicationDetailResp salesApplicationDetailResp = resp.getSalesApplicationDetail();
							ApiSalesArriveResp api = new ApiSalesArriveResp();
							api.setVehicleno(resp.getVehicleno());
							api.setCustomerid(salesApplicationResp.getCustomerid());
							api.setCustomer(salesApplicationResp.getCustomername());
							api.setMaterielid(salesApplicationDetailResp.getMaterielid());
							api.setMateriel(salesApplicationDetailResp.getMaterielname());
							if(StringUtils.isNotBlank(salesApplicationDetailResp.getMaterielname()) && salesApplicationDetailResp.getMaterielname().contains("水泥")){
								if(salesApplicationDetailResp.getMaterielname().contains("袋装")){
									api.setCementtype("1");
									api.setBatchnumber(resp.getSerialnumber());
								}
								if(salesApplicationDetailResp.getMaterielname().contains("散装")){
									api.setCementtype("2");
								}
							}
							api.setServicetype("2");
							api.setNotionformcode(resp.getCode());
							api.setPrimary("");
							api.setVehicleid(resp.getId());
							api.setMinemouth("");
							api.setNumber(String.valueOf(resp.getTakeamount()==null?"":resp.getTakeamount()));
							result.setData(api);
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						}
					}else{
						result.setErrorCode(ErrorCode.RFID_VEHICLE_NOT_EXIST);
					}
				}else{
					result.setErrorCode(ErrorCode.RFID_NOT_EXIST);
				}
			}else{
				result.setErrorCode(ErrorCode.VEHICLE_NOT_EXIST);
			}
		}
		return result;
	}
	
	@Override
	public Result selectWaitingNumber(ApiDoorQueueQuery api){
		Result result = Result.getSuccessResult();
		SalesArriveQuery query = new SalesArriveQuery();
		query.setMaterielid(api.getMateriel());
		query.setPackagetype(api.getPackagetype());
		int waitingnumber = salesArriveMapper.selectWaitingNumber(query);
		ApiDoorQueueResp resp =new ApiDoorQueueResp();
		resp.setQueuenumber(String.valueOf(waitingnumber));
		//序号。。。
		resp.setSmallticket(codeGenDaoImpl.codeGen(1));
		result.setData(resp);
		return result;
	}

}
