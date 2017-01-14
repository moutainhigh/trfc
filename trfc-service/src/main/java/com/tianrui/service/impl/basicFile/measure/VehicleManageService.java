package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.measure.IBlacklistManageService;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.req.basicFile.measure.BlacklistManageReq;
import com.tianrui.api.req.basicFile.measure.VehicleManageQuery;
import com.tianrui.api.req.basicFile.measure.VehicleManageSave;
import com.tianrui.api.req.basicFile.measure.VehicleManageApi;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.RFIDMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 车辆管理Service
 * 
 * @author zhanggaohao
 * @createtime 2016年12月20日 下午4:23:52
 * @classname VehicleManageService.java
 */
@Service
public class VehicleManageService implements IVehicleManageService {

	@Autowired
	private VehicleManageMapper vehicleManageMapper;

	@Autowired
	private IBlacklistManageService blacklistManageService;

	@Autowired
	private RFIDMapper rfidMapper;
	
	@Autowired
	SalesArriveMapper salesArriveMapper;

	@Override
	public PaginationVO<VehicleManageResp> page(VehicleManageQuery query) throws Exception {
		PaginationVO<VehicleManageResp> page = null;
		if (query != null) {
			page = new PaginationVO<VehicleManageResp>();
			long count = vehicleManageMapper.findVehiclePageCount(query);
			if (count > 0) {
				query.setStart((query.getPageNo() - 1) * query.getPageSize());
				query.setLimit(query.getPageSize());
				List<VehicleManage> list = this.vehicleManageMapper.findVehiclePage(query);
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
	public Result addVehicle(VehicleManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null) {
			VehicleManage vehicle = new VehicleManage();
			vehicle.setVehicleno(save.getVehicleno());
			List<VehicleManage> list = this.vehicleManageMapper.selectSelective(vehicle);
			if (list != null && list.size() > 0) {
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			} else {
				PropertyUtils.copyProperties(vehicle, save);
				vehicle.setId(UUIDUtil.getId());
				vehicle.setIsblacklist("0");
				// vehicle.setCreator("");
				vehicle.setCreatetime(System.currentTimeMillis());
				// vehicle.setModifier("");
				vehicle.setModifytime(System.currentTimeMillis());
				if (this.vehicleManageMapper.insert(vehicle) > 0) {
					result.setData(vehicle);
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				} else {
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}

	@Transactional
	@Override
	public Result editVehicle(VehicleManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null) {
			VehicleManage vehicle = new VehicleManage();
			PropertyUtils.copyProperties(vehicle, save);
			// vehicle.setModifier("");
			vehicle.setModifytime(System.currentTimeMillis());
			if (this.vehicleManageMapper.updateByPrimaryKeySelective(vehicle) > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result deleteVehicle(VehicleManageQuery query) {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			VehicleManage vehicle = new VehicleManage();
			vehicle.setId(query.getId());
			vehicle.setState("0");
			vehicle.setModifier("");
			vehicle.setModifytime(System.currentTimeMillis());
			if (this.vehicleManageMapper.updateByPrimaryKeySelective(vehicle) > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result delblacklist(VehicleManageQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			VehicleManage vehicle = new VehicleManage();
			vehicle.setId(query.getId());
			vehicle.setIsblacklist("0");
			vehicle.setModifier("");
			vehicle.setModifytime(System.currentTimeMillis());
			if (this.vehicleManageMapper.updateByPrimaryKeySelective(vehicle) > 0) {
				if (this.blacklistManageService.deleteBlacklist(query.getId()) > 0) {
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				} else {
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Transactional
	@Override
	public Result addblacklist(VehicleManageQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			VehicleManage vehicle = new VehicleManage();
			vehicle.setId(query.getId());
			vehicle.setIsblacklist("1");
			vehicle.setModifier("");
			vehicle.setModifytime(System.currentTimeMillis());
			if (this.vehicleManageMapper.updateByPrimaryKeySelective(vehicle) > 0) {
				BlacklistManageReq breq = new BlacklistManageReq();
				breq.setId(UUIDUtil.getId());
				breq.setVehicleid(query.getId());
				breq.setVehicleno(query.getBlackVno());
				breq.setRemarks(query.getBlackRemarks());
				breq.setCreator(query.getBlackCreator());
				breq.setCreatetime(System.currentTimeMillis());
				breq.setModifier(query.getBlackCreator());
				breq.setModifytime(System.currentTimeMillis());
				if (this.blacklistManageService.addBlacklist(breq) > 0) {
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				} else {
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result findListByParmas(VehicleManageQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		VehicleManage vehicle = new VehicleManage();
		if (query != null) {
			PropertyUtils.copyProperties(vehicle, query);
		}
		List<VehicleManage> list = vehicleManageMapper.selectSelective(vehicle);
		result.setData(copyBeanList2RespList(list));
		return result;
	}

	@Override
	public VehicleManageResp findOne(VehicleManageQuery query) throws Exception {
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			return copyBean2Resp(vehicleManageMapper.selectByPrimaryKey(query.getId()));
		}
		return null;
	}

	private List<VehicleManageResp> copyBeanList2RespList(List<VehicleManage> list) throws Exception {
		List<VehicleManageResp> listResp = null;
		if (list != null && list.size() > 0) {
			listResp = new ArrayList<VehicleManageResp>();
			for (VehicleManage vehicle : list) {
				listResp.add(copyBean2Resp(vehicle));
			}
		}
		return listResp;
	}

	private VehicleManageResp copyBean2Resp(VehicleManage bean) throws Exception {
		VehicleManageResp resp = null;
		if (bean != null) {
			resp = new VehicleManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

	@Override
	public Result addVehicleApi(VehicleManageApi vehicleManageApi) {
		Result result = Result.getParamErrorResult();
		if (vehicleManageApi != null) {
			if (StringUtils.isNotBlank(vehicleManageApi.getRfid())) {
				RFID rfid = new RFID();
				rfid.setRfid(vehicleManageApi.getRfid());
				rfid.setState(true);
				long count = rfidMapper.selectSelectiveCount(rfid);
				if (count == 0) {
					result.setErrorCode(ErrorCode.RFID_NOT_EXIST);
					return result;
				}
			} else if (StringUtils.isNotBlank(vehicleManageApi.getVehicleNo())) {
				VehicleManage vehicle = new VehicleManage();
				vehicle.setVehicleno(vehicleManageApi.getVehicleNo());
				vehicle.setState("1");
				List<VehicleManage> list = vehicleManageMapper.selectSelective(vehicle);
				if (list != null && list.size() > 0) {
					VehicleManage v = list.get(0);
					if (StringUtils.equals(v.getRfid(), vehicleManageApi.getRfid())) {
						// 已绑定rfid
						result.setErrorCode(ErrorCode.RFID_VEHICLE_EXIST);
						return result;
					} else {
						// 绑定rfid
						vehicle.setId(v.getId());
						vehicle.setModifier("");
						vehicle.setModifytime(System.currentTimeMillis());
						if (vehicleManageMapper.updateByPrimaryKeySelective(vehicle) > 0) {
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						} else {
							result.setErrorCode(ErrorCode.OPERATE_ERROR);
						}
					}
				} else {
					// 新增
					vehicle.setId(UUIDUtil.getId());
					vehicle.setRfid(vehicleManageApi.getRfid());
					vehicle.setCode("CL" + (int) (Math.random() * 1000000));
					vehicle.setCreator("");
					vehicle.setCreatetime(System.currentTimeMillis());
					vehicle.setModifier("");
					vehicle.setModifytime(System.currentTimeMillis());
					if (vehicleManageMapper.insertSelective(vehicle) > 0) {
						result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					} else {
						result.setErrorCode(ErrorCode.OPERATE_ERROR);
					}
				}
			}
		}
		return result;
	}

	@Override
	public Result vehicleCheck(VehicleManageApi vehicleManageApi) {
		Result result = Result.getParamErrorResult();
		if (vehicleManageApi != null) {
			if (StringUtils.isNotBlank(vehicleManageApi.getVehicleNo())) {
				VehicleManage vehicle = new VehicleManage();
				vehicle.setState("1");
				vehicle.setVehicleno(vehicleManageApi.getVehicleNo());
				List<VehicleManage> list = vehicleManageMapper.selectSelective(vehicle);
				if (list == null || list.size() == 0) {
					result.setErrorCode(ErrorCode.VEHICLE_NOT_EXIST);
					return result;
				}
				if (StringUtils.isNotBlank(vehicleManageApi.getRfid())) {
					RFID rfid = new RFID();
					rfid.setRfid(vehicleManageApi.getRfid());
					rfid.setState(true);
					long count = rfidMapper.selectSelectiveCount(rfid);
					if(count == 0){
						result.setErrorCode(ErrorCode.RFID_NOT_EXIST);
						return result;
					}
				}
				vehicle.setRfid(vehicleManageApi.getRfid());
				List<VehicleManage> list2 = vehicleManageMapper.selectSelective(vehicle);
				if(list2 == null || list2.size() == 0){
					result.setErrorCode(ErrorCode.RFID_VEHICLE_NOT_EXIST);
					return result;
				}
				SalesArrive sa = new SalesArrive();
				sa.setVehicleid(list2.get(0).getId());
				List<SalesArrive> listSales = salesArriveMapper.selectSelective(sa);
				if(listSales == null || listSales.size() == 0){
					result.setErrorCode(ErrorCode.RFID_VEHICLE_NOT_EXIST);
					return result;
				}else if(listSales.size() > 1){
					result.setErrorCode(ErrorCode.VEHICLE_ARRIVE_NOT_ONLY);
					String code = ",";
					for(SalesArrive s : listSales){
						code += s.getCode();
					}
					result.setData(code.substring(1, code.length()));
					return result;
				}else{
					if(!StringUtils.equals(listSales.get(0).getStatus(), "0")){
						result.setErrorCode(ErrorCode.VEHICLE_ARRIVE_ALREADY_ENTER);
						result.setData(listSales.get(0).getCode());
						return result;
					}
				}
			}
		}
		return result;
	}

}