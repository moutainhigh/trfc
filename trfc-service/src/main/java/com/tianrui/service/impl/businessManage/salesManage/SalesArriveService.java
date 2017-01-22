package com.tianrui.service.impl.businessManage.salesManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.basicFile.measure.IDriverManageService;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.basicFile.measure.DriverManageQuery;
import com.tianrui.api.req.basicFile.measure.VehicleManageQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiSalesArriveQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveSave;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.api.resp.basicFile.nc.CustomerManageResp;
import com.tianrui.api.resp.basicFile.nc.MaterielManageResp;
import com.tianrui.api.resp.businessManage.salesManage.ApiDoorQueueResp;
import com.tianrui.api.resp.businessManage.salesManage.ApiSalesArriveResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
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
	private IDriverManageService driverManageService;
	
	@Autowired
	private IVehicleManageService vehicleManageService;
	
	@Autowired
	private ISalesApplicationService salesApplicationService;
	
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	
	@Autowired
	private RFIDMapper rfidMapper;
	
	@Autowired
	private CodeGenDaoImpl codeGenDaoImpl;
	
	@Autowired
	private ISystemUserService systemUserService;
	
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
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	@Override
	public Result add(SalesArriveSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			SalesArrive bean = new SalesArrive();
			bean.setVehicleid(save.getVehicleid());
			List<SalesArrive> list1 = salesArriveMapper.selectSelective(bean);
			if(list1 != null && list1.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+list1.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			bean.setVehicleid(null);
			bean.setDriverid(save.getDriverid());
			List<SalesArrive> list2 = salesArriveMapper.selectSelective(bean);
			if(list2 != null && list2.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+list2.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			bean.setAuditstatus("1");
			bean.setStatus("0");
			bean.setState("1");
			bean.setSource("0");
//			bean.setCreator("");
//			bean.setModifier("");
			bean.setModifytime(save.getCreatetime());
			if(salesArriveMapper.insertSelective(bean) > 0){
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
		if(save != null){
			SalesArrive sa = salesArriveMapper.selectByPrimaryKey(save.getId());
			SalesArrive bean = new SalesArrive();
			bean.setVehicleid(save.getVehicleid());
			List<SalesArrive> list1 = salesArriveMapper.selectSelective(bean);
			if(list1 != null && list1.size() > 0){
				for(SalesArrive s : list1){
					if(!StringUtils.equals(s.getVehicleid(), sa.getVehicleid())){
						result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
						result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+list1.get(0).getCode()+"，如有疑问请与销售处联系！");
						return result;
					}
				}
			}
			bean.setVehicleid(null);
			bean.setDriverid(save.getDriverid());
			List<SalesArrive> list2 = salesArriveMapper.selectSelective(bean);
			if(list2 != null && list2.size() > 0){
				for(SalesArrive s : list1){
					if(!StringUtils.equals(s.getDriverid(), sa.getDriverid())){
						result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
						result.setError("此司机己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+list2.get(0).getCode()+"，如有疑问请与销售处联系！");
						return result;
					}
				}
			}
			PropertyUtils.copyProperties(bean, save);
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
	public SalesArriveResp findOne(SalesArriveQuery query) throws Exception {
		if(query != null && StringUtils.isNotBlank(query.getId())){
			return copyBean2Resp(salesArriveMapper.selectByPrimaryKey(query.getId()));
		}
		return null;
	}
	
	private List<SalesArriveResp> copyBeanList2RespList(List<SalesArrive> list) throws Exception {
		List<SalesArriveResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesArriveResp>();
			for(SalesArrive sa : list){
				listResp.add(copyBean2Resp(sa));
			}
		}
		return listResp;
	}
	
	private SalesArriveResp copyBean2Resp(SalesArrive bean) throws Exception {
		SalesArriveResp resp = null;
		if(bean != null){
			resp = new SalesArriveResp();
			PropertyUtils.copyProperties(resp, bean);
			if(StringUtils.isNotBlank(bean.getDriverid())){
				DriverManageQuery query = new DriverManageQuery();
				query.setId(bean.getDriverid());
				resp.setDriver(driverManageService.findOne(query));
			}
			if(StringUtils.isNotBlank(bean.getVehicleid())){
				VehicleManageQuery query = new VehicleManageQuery();
				query.setId(bean.getVehicleid());
				resp.setVehicle(vehicleManageService.findOne(query));
			}
			if(StringUtils.isNotBlank(bean.getBillid())){
				SalesApplicationQuery query = new SalesApplicationQuery();
				query.setId(bean.getBillid());
				resp.setSalesApplication(salesApplicationService.findOne(query));
			}
			if(StringUtils.isNotBlank(bean.getAbnormalperson())){
				SystemUserResp user = systemUserService.getUser(bean.getAbnormalperson());
				resp.setAbnormalpersonname(user.getName());
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
	public Result invalid(SalesArriveQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesArrive sa = new SalesArrive();
			sa.setId(query.getId());
			sa.setStatus("3");
			sa.setAbnormalperson(query.getCurrUId());
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
	public Result outfactory(SalesArriveQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesArrive sa = new SalesArrive();
			sa.setId(query.getId());
			sa.setStatus("5");
			sa.setAbnormalperson(query.getCurrUId());
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
							SalesArriveResp resp = copyBean2Resp(listSales.get(0));
							VehicleManageResp vehicleResp = resp.getVehicle();
							SalesApplicationResp salesApplicationResp = resp.getSalesApplication();
							CustomerManageResp customerResp = salesApplicationResp.getCustomerManageResp();
							SalesApplicationDetailResp salesApplicationDetailResp = salesApplicationResp.getDetailResp();
							MaterielManageResp materielResp = salesApplicationDetailResp.getMateriel();
							ApiSalesArriveResp api = new ApiSalesArriveResp();
							api.setVehicleno(vehicleResp.getVehicleno());
							api.setCustomerid(customerResp.getId());
							api.setCustomer(customerResp.getName());
							api.setMaterielid(materielResp.getId());
							api.setMateriel(materielResp.getName());
//							if(StringUtils.equals(materielResp.getPackagetype(), "0")){
//								api.setCementtype("1");
//								api.setBatchnumber(resp.getSerialnumber());
//							}
//							if(StringUtils.equals(materielResp.getPackagetype(), "1")){
//								api.setCementtype("2");
//							}
							if(StringUtils.isNotBlank(materielResp.getName()) && materielResp.getName().contains("水泥")){
								if(materielResp.getName().contains("袋装")){
									api.setCementtype("1");
									api.setBatchnumber(resp.getSerialnumber());
								}
								if(materielResp.getName().contains("散装")){
									api.setCementtype("2");
								}
							}
							api.setServicetype("2");
							api.setNotionformcode(resp.getCode());
							api.setPrimary("");
							api.setVehicleid(vehicleResp.getId());
							api.setMinemouth("");
							api.setNumber(resp.getTakeamount().toString());
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
