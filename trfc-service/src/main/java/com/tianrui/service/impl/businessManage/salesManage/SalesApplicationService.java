package com.tianrui.service.impl.businessManage.salesManage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.basicFile.finance.IPrmTariffService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPushSingleService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationDetailService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.app.AppOrderReq;
import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationDetailQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationSave;
import com.tianrui.api.req.dc.BillValidReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.app.AppOrderDetailResp;
import com.tianrui.api.resp.businessManage.app.AppOrderResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationJoinDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.service.bean.basicFile.finance.PrmTariff;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.system.auth.Organization;
import com.tianrui.service.bean.system.auth.SmUser;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.system.auth.OrganizationMapper;
import com.tianrui.service.mapper.system.auth.SmUserMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.common.ApiParamUtils;
import com.tianrui.smartfactory.common.common.HttpUtils;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.enums.BillTypeEnum;
import com.tianrui.smartfactory.common.utils.DateUtil;
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
	private ISalesApplicationDetailService salesApplicationDetailService;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private CustomerManageMapper customerManageMapper;
	@Autowired
	private MaterielManageMapper materielManageMapper;
	@Autowired
	private WarehouseManageMapper warehouseManageMapper;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private DriverManageMapper driverManageMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private OtherArriveMapper otherArriveMapper;
	@Autowired
	private SalesApplicationArriveMapper salesApplicationArriveMapper;
	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private IPrmTariffService prmTariffService;
	@Autowired
	private SmUserMapper smUserMapper;
	@Autowired
	private SystemUserMapper systemUserMapper;
	@Autowired
	private IPushSingleService pushSingleService;
	
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
				List<SalesApplicationResp> listResp = copyBeanList2RespList(list, true);
				listRespSetListDetailResp(listResp);
				page.setList(listResp);
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	@Override
	public PaginationVO<SalesApplicationJoinDetailResp> pageGroupMateriel(SalesApplicationQuery query) throws Exception{
		PaginationVO<SalesApplicationJoinDetailResp> page = null;
		if(query != null){
			page = new PaginationVO<SalesApplicationJoinDetailResp>();
			query.setState("1");
			long count = salesApplicationMapper.findPageGroupMaterielCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SalesApplicationJoinDetailResp> list = salesApplicationMapper.findPageGroupMateriel(query);
				//获取水泥包装类型
//				if(CollectionUtils.isNotEmpty(list)){
//					for(SalesApplicationJoinDetailResp resp : list){
//						MaterielManage materiel = materielManageMapper.selectByPrimaryKey(resp.getMaterielid());
//						if(materiel!=null){
//							resp.setPackagetype(materiel.getPackagetype());
//						}
//					}
//				}
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	//校验车辆是否可用
	private boolean validVehicle(VehicleManage vehicle, Result result) {
		boolean flag = false;
		if (vehicle != null && StringUtils.equals(vehicle.getState(), Constant.ONE_STRING)) {
			if (StringUtils.equals(vehicle.getIsvalid(), Constant.ONE_STRING)) {
				if (StringUtils.equals(vehicle.getIsblacklist(), Constant.ZERO_STRING)) {
					PurchaseArrive pa = new PurchaseArrive();
					pa.setVehicleid(vehicle.getId());
					List<PurchaseArrive> paList = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
					if (CollectionUtils.isNotEmpty(paList)) {
		                result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		                result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+paList.get(0).getCode()+"，如有疑问请与销售处联系！");
		            } else {
		            	SalesArrive sa = new SalesArrive();
		                sa.setVehicleid(vehicle.getId());
		                List<SalesArrive> saList = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
		                if (CollectionUtils.isNotEmpty(saList)) {
		                    result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		                    result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+saList.get(0).getCode()+"，如有疑问请与销售处联系！");
		                } else {
		                	OtherArrive oa = new OtherArrive();
		                    oa.setVehicleid(vehicle.getId());
		                    List<OtherArrive> oaList = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
		                    if (CollectionUtils.isNotEmpty(oaList)) {
		                        if(StringUtils.equals(oaList.get(0).getBusinesstype(), Constant.FIVE_STRING)){
		                            result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		                            result.setError("此车辆己有其他入库通知单、待出厂后进行派车，现有车辆业务单据号为:"+oaList.get(0).getCode()+"，如有疑问请与销售处联系！");
		                        }else if(StringUtils.equals(oaList.get(0).getBusinesstype(), Constant.SEVEN_STRING)){
		                            result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		                            result.setError("此车辆己有其他出库通知单、待出厂后进行派车，现有车辆业务单据号为:"+oaList.get(0).getCode()+"，如有疑问请与销售处联系！");
		                        } else {
		                        	flag = true;
		                        }
		                    } else {
	                        	flag = true;
	                        }
		                }
		            }
				} else {
					result.setErrorCode(ErrorCode.VEHICLE_IS_BLACK);
				}
			} else {
				result.setErrorCode(ErrorCode.VEHICLE_IS_WX);
			}
		} else {
			result.setErrorCode(ErrorCode.VEHICLE_NOT_EXIST);
		}
		return flag;
	}

	private boolean validDriver(DriverManage driver, Result result) {
		boolean flag = false;
		if (driver != null && StringUtils.equals(driver.getState(), Constant.ONE_STRING)) {
			if (StringUtils.equals(driver.getIsvalid(), Constant.ONE_STRING)) {
				flag = true;
			} else {
				result.setErrorCode(ErrorCode.DRIVER_IS_WX);
			}
		} else {
			result.setErrorCode(ErrorCode.DRIVER_NOT_EXIST);
		}
		return flag;
	}

	private boolean validMaterial(MaterielManage material, Result result) {
		boolean flag = false;
		if (material != null && StringUtils.equals(material.getState(), Constant.ONE_STRING)) {
			if (StringUtils.equals(material.getEffective(), Constant.ONE_STRING)) {
				flag = true;
			} else {
				result.setErrorCode(ErrorCode.MATERIAL_IS_WX);
			}
		} else {
			result.setErrorCode(ErrorCode.MATERIAL_NOT_EXIST);
		}
		return flag;
	}

	private boolean validWarehouse(WarehouseManage warehouse, Result result) {
		boolean flag = false;
		if (warehouse != null && StringUtils.equals(warehouse.getState(), Constant.ONE_STRING)) {
			flag = true;
		} else {
			result.setErrorCode(ErrorCode.WAREHOUSE_NOT_EXIST);
		}
		return flag;
	}
	
	@Transactional
	@Override
	public Result add(SalesApplicationSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getBillType())
				&& StringUtils.isNotBlank(save.getCustomer())
				&& StringUtils.isNotBlank(save.getSalesOrg())
				&& StringUtils.isNotBlank(save.getMateriel())
				&& StringUtils.isNotBlank(save.getWarehouse())
				&& save.getBillTime() != null
				&& save.getNumber() != null){
			if (StringUtils.equals(save.getBillType(), Constant.ZERO_STRING)) {
				//校验车辆
				if (StringUtils.isNotBlank(save.getVehicle())) {
					VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(save.getVehicle());
					if (validVehicle(vehicle, result)) {
						if (StringUtils.isNotBlank(save.getDriver())) {
							DriverManage driver = driverManageMapper.selectByPrimaryKey(save.getDriver());
							if (validDriver(driver, result)) {
								result = setSalesApplicationValue(save, vehicle, driver);
							}
						} else {
							result = setSalesApplicationValue(save, vehicle, null);
						}
					}
				}
			} else {
				result = setSalesApplicationValue(save, null, null);
			}
		}
		return result;
	}

	private Result setSalesApplicationValue(SalesApplicationSave save, VehicleManage vehicle, DriverManage driver) throws Exception {
		Result result = Result.getSuccessResult();
		SalesApplication sa = new SalesApplication();
		sa.setId(UUIDUtil.getId());
		sa.setCode(getCode("XXSO", save.getUserId()));
		sa.setStatus(Constant.ZERO_STRING);
		sa.setSource(Constant.ONE_STRING);
		sa.setBilltypeid(save.getBillType());
		sa.setBilltypename(BillTypeEnum.getName(save.getBillType()));
		CustomerManage customer = customerManageMapper.selectByPrimaryKey(save.getCustomer());
		if(customer != null){
			sa.setCustomerid(customer.getId());
			sa.setCustomername(customer.getName());
			sa.setChannelcode(customer.getChannelcode());
			sa.setSalesmanid(customer.getSalesmanid());
			sa.setSalesmanname(customer.getSalesmanname());
			sa.setTransportcompanyid(customer.getTransportcompanyid());
			sa.setTransportcompanyname(customer.getTransportcompanyname());
			sa.setDepartmentid(customer.getDepartmentid());
			sa.setDepartmentname(customer.getDepartmentname());
		}
		sa.setBilltime(save.getBillTime());
		Organization org = organizationMapper.selectByPrimaryKey(save.getSalesOrg());
		if (org != null) {
			sa.setOrgid(org.getId());
			sa.setOrgname(org.getName());
		}
		sa.setState(Constant.ONE_STRING);
		sa.setMakerid(save.getUserId());
		sa.setMakebillname(save.getUserName());
		sa.setMakebilltime(System.currentTimeMillis());
		sa.setCreator(save.getUserId());
		sa.setCreatetime(System.currentTimeMillis());
		sa.setRemarks(save.getRemark());
		sa.setBillSource(Constant.ONE_NUMBER);
		sa.setValidStatus(Constant.ZERO_STRING);
		sa.setPushStatus(Constant.ZERO_STRING);
		sa.setNcStatus(Constant.ZERO_STRING);
		if (vehicle != null) {
			sa.setVehicleId(vehicle.getId());
			sa.setVehicleNo(vehicle.getVehicleno());
			sa.setRfid(vehicle.getRfid());
		}
		if (driver != null) {
			sa.setDriverId(driver.getId());
			sa.setDriverName(driver.getName());
		}
		salesApplicationMapper.insertSelective(sa);
		updateCode("XXSO", save.getUserId());
		//子表
		SalesApplicationDetail sad = new SalesApplicationDetail();
		sad.setId(UUIDUtil.getId());
		sad.setSalesid(sa.getId());
		MaterielManage materiel = materielManageMapper.selectByPrimaryKey(save.getMateriel());
		if (materiel != null) {
			sad.setMaterielid(materiel.getId());
			sad.setMaterielname(materiel.getName());
		}
		WarehouseManage warehouse = warehouseManageMapper.selectByPrimaryKey(save.getWarehouse());
		if (warehouse != null) {
			sad.setWarehouseid(warehouse.getId());
			sad.setWarehousename(warehouse.getName());
		}
		sad.setUnit("吨");
		sad.setSalessum(save.getNumber());
		if (StringUtils.equals(save.getBillType(), Constant.ZERO_STRING)) {
			sad.setMargin(0D);
		} else {
			sad.setMargin(save.getNumber());
		}
		sad.setStoragequantity(0D);
		sad.setUnstoragequantity(0D);
		sad.setPretendingtake(0D);
		//单价
		Result ptRs = prmTariffService.getPrmTariffByMater(sad.getMaterielid());
		if (ptRs != null && ptRs.getData() != null) {
			PrmTariff pt = (PrmTariff) ptRs.getData();
			sad.setTaxprice(Double.valueOf(pt.getNprice1()));
		}
		salesApplicationDetailMapper.insertSelective(sad);
		return result;
	}
	
	@Transactional
	@Override
	public Result update(SalesApplicationSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getId())
				&& StringUtils.isNotBlank(save.getDetailId())
				&& StringUtils.isNotBlank(save.getBillType())
				&& StringUtils.isNotBlank(save.getCustomer())
				&& StringUtils.isNotBlank(save.getSalesOrg())
				&& StringUtils.isNotBlank(save.getMateriel())
				&& StringUtils.isNotBlank(save.getWarehouse())
				&& save.getBillTime() != null
				&& save.getNumber() != null){
			SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(save.getId());
			SalesApplicationDetail sad = salesApplicationDetailMapper.selectByPrimaryKey(save.getDetailId());
			if (sa != null && sad != null) {
				if (StringUtils.equals(sa.getStatus(), Constant.ZERO_STRING)) {
					if (StringUtils.equals(sa.getValidStatus(), Constant.ZERO_STRING)) {
						if (StringUtils.equals(sa.getSource(), Constant.ONE_STRING)) {
							if (StringUtils.equals(save.getBillType(), Constant.ZERO_STRING)) {
								//校验车辆
								if (StringUtils.isNotBlank(save.getVehicle())) {
									VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(save.getVehicle());
									if (validVehicle(vehicle, result)) {
										if (StringUtils.isNotBlank(save.getDriver())) {
											DriverManage driver = driverManageMapper.selectByPrimaryKey(save.getDriver());
											if (validDriver(driver, result)) {
												result = updateSalesApplicationValue(sa, sad, save, vehicle, driver);
											}
										} else {
											result = updateSalesApplicationValue(sa, sad, save, vehicle, null);
										}
									}
								}
							} else {
								result = updateSalesApplicationValue(sa, sad, save, null, null);
							}
						} else {
							result.setErrorCode(ErrorCode.APPLICATION_NOT_UPDATE3);
						}
					} else {
						result.setErrorCode(ErrorCode.APPLICATION_NOT_UPDATE2);
					}
				} else {
					result.setErrorCode(ErrorCode.APPLICATION_NOT_UPDATE1);
				}
			} else {
				result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
			}
		}
		return result;
	}
	
	private Result updateSalesApplicationValue(SalesApplication sa, SalesApplicationDetail sad, SalesApplicationSave save,
			VehicleManage vehicle, DriverManage driver) {
		Result result = Result.getSuccessResult();
		sa.setBilltime(save.getBillTime());
		CustomerManage customer = customerManageMapper.selectByPrimaryKey(save.getCustomer());
		if (customer != null) {
			sa.setCustomerid(customer.getId());
			sa.setCustomername(customer.getName());
			sa.setChannelcode(customer.getChannelcode());
			sa.setSalesmanid(customer.getSalesmanid());
			sa.setSalesmanname(customer.getSalesmanname());
			sa.setTransportcompanyid(customer.getTransportcompanyid());
			sa.setTransportcompanyname(customer.getTransportcompanyname());
			sa.setDepartmentid(customer.getDepartmentid());
			sa.setDepartmentname(customer.getDepartmentname());
		}
		Organization org = organizationMapper.selectByPrimaryKey(save.getSalesOrg());
		if (org != null) {
			sa.setOrgid(org.getId());
			sa.setOrgname(org.getName());
		}
		if (vehicle != null) {
			sa.setVehicleId(vehicle.getId());
			sa.setVehicleNo(vehicle.getVehicleno());
			sa.setRfid(vehicle.getRfid());
		}
		if (driver != null) {
			sa.setDriverId(driver.getId());
			sa.setDriverName(driver.getName());
		}
		sa.setModifier(save.getUserId());
		sa.setModifytime(System.currentTimeMillis());
		sa.setRemarks(save.getRemark());
		salesApplicationMapper.updateByPrimaryKeySelective(sa);
		MaterielManage materiel = materielManageMapper.selectByPrimaryKey(save.getMateriel());
		if (materiel != null) {
			sad.setMaterielid(materiel.getId());
			sad.setMaterielname(materiel.getName());
		}
		WarehouseManage warehouse = warehouseManageMapper.selectByPrimaryKey(save.getWarehouse());
		if (warehouse != null) {
			sad.setWarehouseid(warehouse.getId());
			sad.setWarehousename(warehouse.getName());
		}
		sad.setSalessum(save.getNumber());
		if (StringUtils.equals(save.getBillType(), Constant.ZERO_STRING)) {
			sad.setMargin(0D);
		} else {
			sad.setMargin(save.getNumber());
		}
		//单价
		Result ptRs = prmTariffService.getPrmTariffByMater(sad.getMaterielid());
		if (ptRs != null && ptRs.getData() != null) {
			PrmTariff pt = (PrmTariff) ptRs.getData();
			sad.setTaxprice(Double.valueOf(pt.getNprice1()));
		}
		salesApplicationDetailMapper.updateByPrimaryKeySelective(sad);
		return result;
	}

	private List<SmUser> getSmUser(String id) throws Exception {
		List<SmUser> smUserList = null;
		SystemUser user = systemUserMapper.selectByPrimaryKey(id);
		if(user != null){
			SmUser smUser = new SmUser();
			smUser.setCode(user.getCode());
			smUserList = smUserMapper.selectSelective(smUser);
		}
		return smUserList;
	}

	private void billPush(SalesApplication sa, SalesApplicationDetail sad) throws Exception {
		SalesApplicationResp sar = new SalesApplicationResp(); 
		PropertyUtils.copyProperties(sar, sa);
		SalesApplicationDetailResp sadr = new SalesApplicationDetailResp(); 
		PropertyUtils.copyProperties(sadr, sad);
		
		List<SmUser> smUserList = null;
		smUserList = getSmUser(sar.getAuditid());
		if(CollectionUtils.isNotEmpty(smUserList)){
			sar.setAuditid(smUserList.get(0).getId());
		}
		smUserList = getSmUser(sar.getMakerid());
		if(CollectionUtils.isNotEmpty(smUserList)){
			sar.setMakerid(smUserList.get(0).getId());
		}
		smUserList = getSmUser(sar.getCreator());
		if(CollectionUtils.isNotEmpty(smUserList)){
			sar.setCreator(smUserList.get(0).getId());
		}
		smUserList = getSmUser(sar.getModifier());
		if(CollectionUtils.isNotEmpty(smUserList)){
			sar.setModifier(smUserList.get(0).getId());
		}
		
		List<SalesApplicationResp> sarList = new ArrayList<SalesApplicationResp>();
		List<SalesApplicationDetailResp> sadrList = new ArrayList<SalesApplicationDetailResp>();
		sadrList.add(sadr);
		sar.setList(sadrList);
		sarList.add(sar);
		
		ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(sarList), Constant.URL_DOMAIN + Constant.URL_RETURN_SALESAPPLICATION);
		PushSingleReq ps = new PushSingleReq();
		ps.setId(UUIDUtil.getId());
		ps.setRequisitionNum(sa.getCode());
		ps.setRequisitionType(Constant.TWO_STRING);
		ps.setCreatetime(System.currentTimeMillis());
		ps.setModifytime(System.currentTimeMillis());
		if(apiResult != null){
			if (StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
				sa.setSource(Constant.ZERO_STRING);
				sa.setPushStatus(Constant.ONE_STRING);
                ps.setPushStatus(Constant.ONE_STRING);
			} else {
                ps.setPushStatus(Constant.THREE_STRING);
			}
            ps.setReasonFailure(apiResult.getError());
            ps.setDesc1(apiResult.getCode());
		} else {
			ps.setPushStatus(Constant.THREE_STRING);
		    ps.setReasonFailure("FC-DC业务平台自制销售申请单推单失败，连接超时。");
		    ps.setDesc1("-1");
		}
		pushSingleService.savePushSingle(ps);
	}

	@Transactional
	@Override
	public Result audit(SalesApplicationQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(query.getId());
			List<SalesApplicationDetail> detailList = salesApplicationDetailMapper.selectBySalesId(query.getId());
			if (sa != null && CollectionUtils.isNotEmpty(detailList)) {
				if (StringUtils.equals(sa.getStatus(), Constant.ZERO_STRING)) {
					if (StringUtils.equals(sa.getSource(), Constant.ONE_STRING)) {
						sa.setStatus(Constant.ONE_STRING);
						sa.setAuditid(query.getAuditid());
						sa.setAuditname(query.getAuditname());
						sa.setAudittime(System.currentTimeMillis());
						sa.setModifier(query.getCurrid());
						sa.setModifytime(System.currentTimeMillis());
						billPush(sa, detailList.get(0));
						salesApplicationMapper.updateByPrimaryKeySelective(sa);
						result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					} else {
						result.setErrorCode(ErrorCode.APPLICATION_DONT_AUDIT);
					}
				} else {
					result.setErrorCode(ErrorCode.APPLICATION_DONT_NEED_AUDIT);
				}
			} else {
				result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result unaudit(SalesApplicationQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(query.getId());
			if (sa != null) {
				if (StringUtils.equals(sa.getStatus(), Constant.ONE_STRING)) {
					if (StringUtils.equals(sa.getSource(), Constant.ONE_STRING)) {
						sa.setStatus(Constant.ZERO_STRING);
						sa.setAuditid(query.getAuditid());
						sa.setAuditname(query.getAuditname());
						sa.setAudittime(System.currentTimeMillis());
						sa.setModifier(query.getCurrid());
						sa.setModifytime(System.currentTimeMillis());
						salesApplicationMapper.updateByPrimaryKeySelective(sa);
						result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					} else {
						result.setErrorCode(ErrorCode.APPLICATION_DONT_UNAUDIT);
					}
				} else {
					result.setErrorCode(ErrorCode.APPLICATION_DONT_NEED_UNAUDIT);
				}
			} else {
				result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result delete(SalesApplicationQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(query.getId());
			if (sa != null) {
				if (StringUtils.equals(sa.getStatus(), Constant.ZERO_STRING)) {
					if (StringUtils.equals(sa.getSource(), Constant.ONE_STRING)) {
						sa.setState(Constant.ZERO_STRING);
						sa.setValidStatus(Constant.TWO_STRING);
						sa.setModifier(query.getCurrid());
						sa.setModifytime(System.currentTimeMillis());
						salesApplicationMapper.updateByPrimaryKeySelective(sa);
						result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					} else {
						result.setErrorCode(ErrorCode.APPLICATION_NOT_DELETE5);
					}
				} else {
					result.setErrorCode(ErrorCode.APPLICATION_NOT_DELETE4);
				}
			} else {
				result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Override
	public SalesApplicationResp findOne(String id, boolean setDetail) throws Exception {
		if(StringUtils.isNotBlank(id)){
			return copyBean2Resp(salesApplicationMapper.selectByPrimaryKey(id), setDetail);
		}
		return null;
	}
	
	@Override
	public List<SalesApplicationResp> selectByIds(List<String> ids, boolean isSetDetail) throws Exception{
		List<SalesApplicationResp> listResp = null;
		if(CollectionUtils.isNotEmpty(ids)){
			listResp = copyBeanList2RespList(salesApplicationMapper.selectByIds(ids), false);
			if(isSetDetail){
				listRespSetListDetailResp(listResp);
			}
		}
		return listResp;
	}
	
	private void listRespSetListDetailResp(List<SalesApplicationResp> listResp) throws Exception{
		if(CollectionUtils.isNotEmpty(listResp)){
			List<String> ids = new ArrayList<String>();
			for(SalesApplicationResp resp : listResp){
				ids.add(resp.getId());
			}
			List<SalesApplicationDetailResp> listDetailResp = salesApplicationDetailService.selectBySalesIds(ids);
			if(CollectionUtils.isNotEmpty(listDetailResp)){
				for(SalesApplicationResp resp : listResp){
					List<SalesApplicationDetailResp> list = new ArrayList<SalesApplicationDetailResp>();
					for(SalesApplicationDetailResp detailResp : listDetailResp){
						if(StringUtils.equals(resp.getId(), detailResp.getSalesid())){
							list.add(detailResp);
						}
					}
					resp.setList(list);
				}
			}
		}
	}
	
	private List<SalesApplicationResp> copyBeanList2RespList(List<SalesApplication> list, boolean setDetail) throws Exception {
		List<SalesApplicationResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesApplicationResp>();
			for(SalesApplication sales : list){
				listResp.add(copyBean2Resp(sales, setDetail));
			}
		}
		return listResp;
	}
	
	private SalesApplicationResp copyBean2Resp(SalesApplication bean, boolean setDetail) throws Exception {
		SalesApplicationResp resp = null;
		if(bean != null){
			resp = new SalesApplicationResp();
			PropertyUtils.copyProperties(resp, bean);
			if(setDetail){
				SalesApplicationDetailQuery query = new SalesApplicationDetailQuery();
				query.setSalesid(bean.getId());
				resp.setList(salesApplicationDetailService.findListBySalesApplicationId(query));
			}
			if (StringUtils.isNotBlank(bean.getDriverId())) {
				DriverManage driver = driverManageMapper.selectByPrimaryKey(bean.getDriverId());
				resp.setIdNo(driver.getIdentityno());
			}
		}
		return resp;
	}

	@Override
	public Result findMaxUtc(SalesApplicationQuery req) throws Exception {
		Result rs = Result.getErrorResult();
		if(req !=null  ){
			Long utc =salesApplicationMapper.findMaxUtc();
			rs.setData(utc);
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return rs;
	}

	@Transactional
	@Override
	public Result updateDataWithDC(List<JSONObject> list) throws Exception {
		Result rs = Result.getErrorResult();
		if(CollectionUtils.isNotEmpty(list) ){
			Set<String> ids =getAllIds();
			List<SalesApplication> toUpdate =new ArrayList<SalesApplication>();
			List<SalesApplicationDetail> toUpdateItem =new ArrayList<SalesApplicationDetail>();
			List<SalesApplication> toSave =new ArrayList<SalesApplication>();
			List<SalesApplicationDetail> toSaveItem =new ArrayList<SalesApplicationDetail>();
			
			for( JSONObject jsonObject:list ){
				String id =jsonObject.getString("id");
				if( ids.contains(id) ){
					toUpdate.add(converUpdateJson2Bean(jsonObject));
					toUpdateItem.addAll(converUpdateJson2ItemList(jsonObject,id));
				}else{
					toSave.add(converJson2Bean(jsonObject));
					toSaveItem.addAll(converJson2ItemList(jsonObject,id));
				}
			}
			
			if( CollectionUtils.isNotEmpty(toSave) ){
				salesApplicationMapper.insertBatch(toSave);
				salesApplicationDetailMapper.insertBatch(toSaveItem);
			}
			
			if( CollectionUtils.isNotEmpty(toUpdate) ){
				for( SalesApplication update :toUpdate){
					salesApplicationMapper.updateByPrimaryKeySelective(update);
				}
				for( SalesApplicationDetail updateItem :toUpdateItem){
					salesApplicationDetailMapper.updateByPrimaryKeySelective(updateItem);
				}
			}
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return rs;
	}

	private SalesApplication converUpdateJson2Bean(JSONObject jsonItem) {
		SalesApplication item  =new SalesApplication();
		item.setId(jsonItem.getString("id"));
		item.setNcId(jsonItem.getString("ncId"));
		item.setNcStatus(jsonItem.getString("status"));
		//TS
		if(StringUtils.isNotBlank(jsonItem.getString("ts"))){
			item.setUtc(Long.valueOf(jsonItem.getString("ts")));
		}
		return item;
	}

	private List<SalesApplicationDetail> converUpdateJson2ItemList(JSONObject jsonItem, String id) {
		List<SalesApplicationDetail> itemList = new ArrayList<SalesApplicationDetail>();
		if( jsonItem.getJSONArray("list") !=null ){
			JSONArray arr =jsonItem.getJSONArray("list");
			if( arr.size()>0){
				for(int i=0;i<arr.size();i++){
					JSONObject itemJon=JSONObject.parseObject(arr.get(0).toString());
					SalesApplicationDetail saleItem = new SalesApplicationDetail();
					saleItem.setId(itemJon.getString("id"));
					saleItem.setNcId(itemJon.getString("ncId"));
					saleItem.setNcStatus(itemJon.getString("status"));
					itemList.add(saleItem);
				}
			}
		}
		return itemList;
	}

	private Set<String> getAllIds(){
		Set<String> rs = new HashSet<String>();
		List<SalesApplication> list=salesApplicationMapper.selectSelective(null);
		for(SalesApplication item:list){
			rs.add(item.getId());
		}
		return rs;
	}
	
	private SalesApplication converJson2Bean(JSONObject jsonItem){
		SalesApplication item  =new SalesApplication();
		item.setId(jsonItem.getString("id"));
		//编码
		item.setCode(jsonItem.getString("code"));
		//状态
		item.setStatus(Constant.ONE_STRING);
		item.setState(Constant.ONE_STRING);
		//来源
		item.setSource(Constant.ZERO_STRING);
		//类型
		String billtypeid = jsonItem.getString("type");
		item.setBilltypeid(billtypeid);
		item.setBilltypename(BillTypeEnum.getName(billtypeid));
		//客户
		item.setCustomerid(jsonItem.getString("customerId"));
		item.setCustomername(jsonItem.getString("customerName"));
//		String customerid = jsonItem.getString("customerId");
//		if(StringUtils.isNotBlank(customerid)){
//			item.setCustomerid(customerid);
//			CustomerManage cm = customerManageMapper.selectByPrimaryKey(customerid);
//			if(cm != null && StringUtils.isNotBlank(cm.getName())){
//				item.setCustomername(cm.getName());
//			}
//		}
		//区域码
		item.setChannelcode(jsonItem.getString("channelTypeCode"));
		//业务员
		item.setSalesmanid(jsonItem.getString("salesPsnId"));
		item.setSalesmanname(jsonItem.getString("salesPsn"));
		//订单日期
		item.setBilltime(DateUtil.parse(jsonItem.getString("orderData"), "yyyy-MM-dd HH:mm:ss"));
		//销售组织
		item.setOrgid(jsonItem.getString("orgId"));
		item.setOrgname(jsonItem.getString("orgName"));
		//运输公司
		item.setTransportcompanyid(jsonItem.getString("transComp"));
		item.setTransportcompanyname(jsonItem.getString("transport"));
		//部门
		item.setDepartmentid(jsonItem.getString("deptId"));
		item.setDepartmentname(jsonItem.getString("dept"));
		//审核人
		item.setAuditid(jsonItem.getString("auditPerson"));
		item.setAuditname(jsonItem.getString("auditName"));
		//审核日期
		item.setAudittime(DateUtil.parse(jsonItem.getString("auditData"), "yyyy-MM-dd HH:mm:ss"));
		//制单人
		item.setMakerid(jsonItem.getString("singleId"));
		item.setMakebillname(jsonItem.getString("billMaker"));
		//制单日期
		item.setMakebilltime(DateUtil.parse(jsonItem.getString("singleData"), "yyyy-MM-dd HH:mm:ss"));
		item.setRemarks(jsonItem.getString("remark"));
		item.setCreatetime(System.currentTimeMillis());
		//TS
		if(StringUtils.isNotBlank(jsonItem.getString("ts"))){
			item.setUtc(Long.valueOf(jsonItem.getString("ts")));
		}
		item.setVehicleNo(jsonItem.getString("vehicleno"));
		item.setNcId(jsonItem.getString("ncId"));
		item.setBillSource(Constant.ZERO_NUMBER);
		item.setValidStatus(Constant.ZERO_STRING);
		item.setNcStatus(jsonItem.getString("status"));
		return item;
	}
	private List<SalesApplicationDetail> converJson2ItemList(JSONObject jsonItem,String id){
		List<SalesApplicationDetail> itemList = new ArrayList<SalesApplicationDetail>();
		if( jsonItem.getJSONArray("list") !=null ){
			JSONArray arr =jsonItem.getJSONArray("list");
			if( arr.size()>0){
				for(int i=0;i<arr.size();i++){
					JSONObject itemJon=JSONObject.parseObject(arr.get(0).toString());
					SalesApplicationDetail saleItem = new SalesApplicationDetail();
					saleItem.setId(itemJon.getString("id"));
					saleItem.setSalesid(id);
					//物料
					saleItem.setMaterielid(itemJon.getString("materialId"));
					saleItem.setMaterielname(itemJon.getString("materialName"));
//					String materielid = itemJon.getString("materialId");
//					if(StringUtils.isNotBlank(materielid)){
//						saleItem.setMaterielid(materielid);
//						MaterielManage m = materielManageMapper.selectByPrimaryKey(materielid);
//						if(m != null && StringUtils.isNotBlank(m.getName())){
//							saleItem.setMaterielname(m.getName());
//						}
//					}
					saleItem.setWarehouseid(itemJon.getString("csendstordocId"));
					saleItem.setWarehousename(itemJon.getString("csendstordocName"));
					saleItem.setUnit("吨");
					saleItem.setSalessum(Double.valueOf(itemJon.getString("number")));
					saleItem.setMargin(saleItem.getSalessum());
					saleItem.setStoragequantity(0D);
					saleItem.setUnstoragequantity(0D);
					saleItem.setPretendingtake(0D);
					saleItem.setTaxprice(Double.valueOf(itemJon.getString("nqtorigtaxprice")));
					saleItem.setUntaxprice(Double.valueOf(itemJon.getString("nqtorigprice")));
					saleItem.setTaxrate(Double.valueOf(itemJon.getString("ntaxrate")));
					saleItem.setRemarks(itemJon.getString("remark"));
					saleItem.setNcId(itemJon.getString("ncId"));
					saleItem.setNcStatus(itemJon.getString("status"));
					itemList.add(saleItem);
				}
			}
		}
		return itemList;
	}

	@Override
	public PaginationVO<AppOrderResp> appToPage(AppOrderReq req) {
		PaginationVO<AppOrderResp> page = null;
		if(req != null){
			page = new PaginationVO<AppOrderResp>();
			long count = salesApplicationMapper.findAppToPageGroupMaterielCount(req);
			if(count > 0){
				req.setStart((req.getPageNo() - 1) * req.getPageSize());
				req.setLimit(req.getPageSize());
				List<AppOrderResp> list = salesApplicationMapper.findAppToPageGroupMateriel(req);
				page.setList(list);
			}
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
			page.setTotal(count);
		}
		return page;
	}

	@Override
	public Result appToDetail(AppOrderReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getId())
				&& StringUtils.isNotBlank(req.getDetailid())){
			SalesApplication application = salesApplicationMapper.selectByPrimaryKey(req.getId());
			SalesApplicationDetail applicationDetail = salesApplicationDetailMapper.selectByPrimaryKey(req.getDetailid());
			if(application != null && applicationDetail != null){
				AppOrderDetailResp resp = new AppOrderDetailResp();
				resp.setId(application.getId());
				resp.setDetailid(applicationDetail.getId());
				resp.setCode(application.getCode());
				resp.setMaterialName(applicationDetail.getMaterielname());
				resp.setOrgName(application.getOrgname());
				resp.setCustomerName(application.getCustomername());
				resp.setBillDateStr(DateUtil.parse(application.getBilltime(), "yyyy-MM-dd HH:mm:ss"));
				resp.setMargin(applicationDetail.getMargin());
				result.setData(resp);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
			}
		}
		return result;
	}
	
	private String getCode(String code, String userId) throws Exception {
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode(code);
		codeReq.setCodeType(true);
		codeReq.setUserid(userId);
		return systemCodeService.getCode(codeReq).getData().toString();
	}
	
	private void updateCode(String code, String userId) throws Exception {
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode(code);
		codeReq.setCodeType(true);
		codeReq.setUserid(userId);
		systemCodeService.updateCodeItem(codeReq);
	}

	@Override
	public Result billNotAuditValid(BillValidReq req) {
		Result result = Result.getParamErrorResult();
		if (req != null && StringUtils.isNotBlank(req.getId())
				&& StringUtils.isNotBlank(req.getDetailId())
				&& StringUtils.isNotBlank(req.getStatus())) {
			SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(req.getId());
			if (StringUtils.equals(req.getStatus(), Constant.ONE_STRING)) {
				sa.setValidStatus(Constant.TWO_STRING);
			} else {
				sa.setValidStatus(Constant.THREE_STRING);
				sa.setValidError(req.getMsg());
			}
			salesApplicationMapper.updateByPrimaryKeySelective(sa);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	@Override
	public Result billYesAuditValid(BillValidReq req) {
		Result result = Result.getParamErrorResult();
		if (req != null && StringUtils.isNotBlank(req.getId())
				&& StringUtils.isNotBlank(req.getDetailId())
				&& StringUtils.isNotBlank(req.getStatus())) {
			SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(req.getId());
			if (StringUtils.equals(req.getStatus(), Constant.ONE_STRING)) {
				sa.setValidStatus(Constant.TWO_STRING);
				sa.setNcStatus(Constant.FOUR_STRING);
				SalesArrive bean = new SalesArrive();
				bean.setBillid(req.getId());
				bean.setBilldetailid(req.getDetailId());
				List<SalesArrive> list = salesArriveMapper.selectSelective(bean);
				if (CollectionUtils.isNotEmpty(list)) {
					bean = list.get(0);
					if (StringUtils.equals(bean.getStatus(), Constant.ZERO_STRING)) {
						bean.setStatus(Constant.THREE_STRING);
						bean.setValidStatus(Constant.TWO_STRING);
						salesArriveMapper.updateByPrimaryKeySelective(bean);
					}
				}
			} else {
				sa.setValidStatus(Constant.THREE_STRING);
				sa.setValidError(req.getMsg());
			}
			salesApplicationMapper.updateByPrimaryKeySelective(sa);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	//审核通过才会回写状态的接口
	@Override
	public synchronized Result billAuditCallBack(BillValidReq req) throws Exception {
		Result result = Result.getParamErrorResult();
		if (req != null && StringUtils.isNotBlank(req.getId())
				&& StringUtils.isNotBlank(req.getDetailId())
				&& StringUtils.isNotBlank(req.getNcId())
				&& StringUtils.isNotBlank(req.getDetailNcId())
				&& StringUtils.isNotBlank(req.getAuditid())
				&& StringUtils.isNotBlank(req.getAuditname())
				&& req.getAudittime() != null) {
			SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(req.getId());
			SalesApplicationDetail sad = salesApplicationDetailMapper.selectByPrimaryKey(req.getDetailId());
			if (StringUtils.equals(sa.getValidStatus(), Constant.ZERO_STRING)) {
				if (!StringUtils.equals(sa.getNcStatus(), Constant.TWO_STRING)) {
					sa.setStatus(Constant.ONE_STRING);
					sa.setNcId(req.getNcId());
					sa.setNcStatus(Constant.TWO_STRING);
					sa.setAuditid(req.getAuditid());
					sa.setAuditname(req.getAuditname());
					sa.setAudittime(req.getAudittime());
					salesApplicationMapper.updateByPrimaryKeySelective(sa);
					sad.setNcId(req.getDetailNcId());
					sad.setPretendingtake(sad.getSalessum());
					salesApplicationDetailMapper.updateByPrimaryKeySelective(sad);
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					//一单一车
					if (StringUtils.equals(sa.getBilltypeid(), Constant.ZERO_STRING)) {
						VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(sa.getVehicleId());
						//校验车辆是否运行创建通知单
						if (validVehicle(vehicle)) {
							if (StringUtils.isNotBlank(sa.getDriverId())) {
								DriverManage driver = driverManageMapper.selectByPrimaryKey(sa.getDriverId());
								if (validDriver(driver)) {
									saveOneBillOneCarNotice(sa, sad, vehicle, driver);
								}
							} else {
								saveOneBillOneCarNotice(sa, sad, vehicle, null);
							}
						}
					}
				} else {
					result.setErrorCode(ErrorCode.APPLICATION_IS_VALID_ERROR);
				}
			} else {
				result.setErrorCode(ErrorCode.APPLICATION_IS_VALID_ERROR);
			}
		}
		return result;
	}

	private void saveOneBillOneCarNotice(SalesApplication sa, SalesApplicationDetail sad, VehicleManage vehicle,
			DriverManage driver) throws Exception {
		SalesArrive bean = new SalesArrive();
		bean.setId(UUIDUtil.getId());
		bean.setCode(getCode("TH", sa.getMakerid()));
		bean.setAuditstatus(Constant.ONE_STRING);
		switch (sa.getBillSource()) {
		case 0:
			//NC
			bean.setSource(Constant.ZERO_STRING);
			break;
		case 1:
			//业务平台
			bean.setSource(Constant.ZERO_STRING);
			break;
		case 2:
			//客商APP
			bean.setSource(Constant.TWO_STRING);
			break;
		default:
			break;
		}
		bean.setStatus(Constant.ZERO_STRING);
		bean.setVehicleid(vehicle.getId());
		bean.setVehicleno(vehicle.getVehicleno());
		bean.setVehiclerfid(vehicle.getRfid());
		if (driver != null) {
			bean.setDriverid(driver.getId());
			bean.setDrivername(driver.getName());
			bean.setDriveridentityno(driver.getIdentityno());
		}
		bean.setBillid(sa.getId());
		bean.setBillcode(sa.getCode());
		bean.setBilldetailid(sad.getId());
		bean.setUnit(sad.getUnit());
		bean.setTakeamount(sad.getSalessum());
		bean.setState(Constant.ONE_STRING);
		bean.setMakerid(sa.getMakerid());
		bean.setMakebillname(sa.getMakebillname());
		bean.setMakebilltime(System.currentTimeMillis());
		bean.setCreator(sa.getMakerid());
		bean.setCreatetime(System.currentTimeMillis());
		salesArriveMapper.insertSelective(bean);
		updateCode("TH", sa.getMakerid());
		SalesApplicationArrive join = new SalesApplicationArrive();
		join.setId(UUIDUtil.getId());
		join.setBillId(sa.getId());
		join.setBillDetailId(sad.getId());
		join.setNoticeId(bean.getId());
		join.setNumber(sad.getSalessum());
		join.setSequence(1);
		salesApplicationArriveMapper.insertSelective(join);
		sa.setNoticeMark(Constant.ONE_STRING);
		salesApplicationMapper.updateByPrimaryKeySelective(sa);
		sad.setMargin(0D);
		sad.setPretendingtake(sad.getSalessum());
		salesApplicationDetailMapper.updateByPrimaryKeySelective(sad);
		salesArriveMapper.emptyForceOutFactoryByVehicle(vehicle.getId());
	}

	private boolean validVehicle(VehicleManage vehicle) {
		boolean flag = false;
		if (vehicle != null && StringUtils.equals(vehicle.getState(), Constant.ONE_STRING)) {
			if (StringUtils.equals(vehicle.getIsvalid(), Constant.ONE_STRING)) {
				if (StringUtils.equals(vehicle.getIsblacklist(), Constant.ZERO_STRING)) {
					PurchaseArrive pa = new PurchaseArrive();
					pa.setVehicleid(vehicle.getId());
					List<PurchaseArrive> paList = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
					if (CollectionUtils.isEmpty(paList)) {
		            	SalesArrive sa = new SalesArrive();
		                sa.setVehicleid(vehicle.getId());
		                List<SalesArrive> saList = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
		                if (CollectionUtils.isEmpty(saList)) {
		                	OtherArrive oa = new OtherArrive();
		                    oa.setVehicleid(vehicle.getId());
		                    List<OtherArrive> oaList = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
		                    if (CollectionUtils.isNotEmpty(oaList)) {
		                        if(!StringUtils.equals(oaList.get(0).getBusinesstype(), Constant.FIVE_STRING) && !StringUtils.equals(oaList.get(0).getBusinesstype(), Constant.SEVEN_STRING)){
		                        	flag = true;
		                        }
		                    } else {
	                        	flag = true;
	                        }
		                }
		            }
				}
			}
		}
		return flag;
	}
	
	private boolean validDriver(DriverManage driver) {
		boolean flag = false;
		if (driver != null && StringUtils.equals(driver.getState(), Constant.ONE_STRING)) {
			if (StringUtils.equals(driver.getIsvalid(), Constant.ONE_STRING)) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * NC自制实时推送到FC
	 * @throws Exception 
	 */
	@Override
	public synchronized Result pushSalesTofc(JSONArray array) throws Exception {
		Result result = Result.getParamErrorResult();
		if (array != null && array.size() == 2) {
			JSONObject jsonItem = array.getJSONObject(0);
			JSONArray jsonItem2 = array.getJSONArray(1);
			if(jsonItem!=null && jsonItem2!=null){
				SalesApplication sa = converSalesJson2Bean(jsonItem);
				List<SalesApplicationDetail> SalesApplicationDetailList = converSalesDetailJson2Bean(jsonItem2);
				//保存主表数据
				salesApplicationMapper.insertSelective(sa);
				for (SalesApplicationDetail sad : SalesApplicationDetailList) {
					salesApplicationDetailMapper.insertSelective(sad);
				}
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}	
		}
		return result;
	}
	
	/**
	 * 推送过来的主表数据对象封装成主表bean对象
	 * @author xcy
	 * @param jsonItem
	 * @return
	 * @throws Exception 
	 */
	private SalesApplication converSalesJson2Bean(JSONObject jsonItem) throws Exception{
		SalesApplication sa  = new SalesApplication();
		sa.setId(jsonItem.getString("ncId"));		
		sa.setCode(jsonItem.getString("code"));
		sa.setStatus(Constant.ONE_STRING);
		sa.setSource(Constant.ZERO_STRING);
		sa.setBilltypeid(jsonItem.getString("billtypeid"));
		sa.setBilltypename(BillTypeEnum.getName(jsonItem.getString("billtypeid")));
		sa.setCustomerid(jsonItem.getString("customerid"));
		sa.setCustomername(jsonItem.getString("customername"));
		sa.setChannelcode(jsonItem.getString("channelcode"));
		sa.setSalesmanid(jsonItem.getString("salesmanid"));
		sa.setSalesmanname(jsonItem.getString("salesmanname"));
		sa.setBilltime(jsonItem.getLong("billtime"));
		sa.setOrgid(jsonItem.getString("orgid"));
		sa.setOrgname(jsonItem.getString("orgname"));
		sa.setTransportcompanyid(jsonItem.getString("transportcompanyid"));
		sa.setTransportcompanyname(jsonItem.getString("transportcompanyname"));
		sa.setDepartmentid(jsonItem.getString("departmentid"));
		sa.setDepartmentname(jsonItem.getString("departmentname"));
		sa.setAuditid(jsonItem.getString("auditid"));
		sa.setAuditname(jsonItem.getString("auditname"));
		sa.setAudittime(jsonItem.getLong("audittime"));
		sa.setState(Constant.ONE_STRING);
		sa.setMakerid(jsonItem.getString("makerid"));
		sa.setMakebillname(jsonItem.getString("makebillname"));
		sa.setMakebilltime(jsonItem.getLong("makebilltime"));
		sa.setRemarks(jsonItem.getString("remarks"));		
		sa.setCreator(jsonItem.getString("creator"));
		sa.setCreatetime(jsonItem.getLong("createtime"));
		sa.setVehicleNo(jsonItem.getString("vehicleNo"));
		sa.setBillSource(Constant.ZERO_NUMBER);
		sa.setNcId(jsonItem.getString("ncId"));
		sa.setValidStatus(Constant.ZERO_STRING);
		sa.setNcStatus(jsonItem.getString("ncStatus"));
		return sa;
	}
	
	/**
	 *  推送过来的子表数据对象封装成子表bean对象
	 * @author xcy
	 * @param jsonItem2
	 * @return
	 * @throws Exception	 
	 */
	private List<SalesApplicationDetail> converSalesDetailJson2Bean(JSONArray array) throws Exception{
		List<SalesApplicationDetail> list = null;
		if (array != null && array.size() > 0) {
			list = new ArrayList<SalesApplicationDetail>();
			for (int i = 0; i < array.size(); i++) {
				JSONObject item = array.getJSONObject(i);
				SalesApplicationDetail sad = new SalesApplicationDetail();
				sad.setId(item.getString("ncId"));
				sad.setSalesid(item.getString("salesid"));
				sad.setMaterielid(item.getString("materielid"));
				sad.setMaterielname(item.getString("materielname"));
				sad.setWarehouseid(item.getString("warehouseid"));
				sad.setWarehousename(item.getString("warehousename"));
				sad.setUnit("吨");
				sad.setSalessum(item.getDouble("salessum"));
				sad.setMargin(item.getDouble("margin"));
				sad.setStoragequantity(0D);
				sad.setUnstoragequantity(0D);
				sad.setPretendingtake(0D);
				sad.setTaxprice(item.getDouble("taxprice"));
				sad.setRemarks(item.getString("remarks"));
				sad.setNcStatus(item.getString("ncStatus"));
				sad.setNcId(item.getString("ncId"));			
				list.add(sad);
			}
		}
		return list;		
	}
}