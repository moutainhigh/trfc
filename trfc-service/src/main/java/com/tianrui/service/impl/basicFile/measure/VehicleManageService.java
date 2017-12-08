package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.measure.IBlacklistManageService;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.measure.BlacklistManageSave;
import com.tianrui.api.req.basicFile.measure.VehicleManageQuery;
import com.tianrui.api.req.basicFile.measure.VehicleManageSave;
import com.tianrui.api.req.businessManage.app.AppQueryReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.req.basicFile.measure.VehicleManageApi;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.api.resp.businessManage.app.AppVehicleResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.bean.basicFile.measure.TransportunitManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.mapper.basicFile.measure.TransportunitManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.RFIDMapper;
import com.tianrui.smartfactory.common.constants.Constant;
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
	private ISystemCodeService systemCodeService;
	@Autowired
	private TransportunitManageMapper transportunitManageMapper;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;

	@Override
	public PaginationVO<VehicleManageResp> page(VehicleManageQuery query) throws Exception {
		PaginationVO<VehicleManageResp> page = null;
		if (query != null) {
			page = new PaginationVO<VehicleManageResp>();
			query.setState(Constant.ONE_STRING);
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
	public Result add(VehicleManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null) {
			VehicleManage vehicle = new VehicleManage();
			vehicle.setState(Constant.ONE_STRING);
			vehicle.setVehicleno(save.getVehicleno());
			List<VehicleManage> list = this.vehicleManageMapper.selectSelective(vehicle);
			if(list == null || list.size() == 0){
				PropertyUtils.copyProperties(vehicle, save);
				vehicle.setId(UUIDUtil.getId());
				vehicle.setCode(getCode(save.getCurrUId()));
				vehicle.setInternalcode(getInternalCode(save.getCurrUId()));
				vehicle.setIsvalid(Constant.ONE_STRING);
				vehicle.setIsblacklist(Constant.ZERO_STRING);
	            vehicle.setOrgid(Constant.ORG_ID);
	            vehicle.setOrgname(Constant.ORG_NAME);
				vehicle.setState(Constant.ONE_STRING);
				vehicle.setCreator(save.getCurrUId());
				vehicle.setCreatetime(System.currentTimeMillis());
				vehicle.setModifier(save.getCurrUId());
				vehicle.setModifytime(System.currentTimeMillis());
				if (this.vehicleManageMapper.insert(vehicle) == 1
				        && updateCode(save.getCurrUId())) {
					result.setData(vehicle);
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				} else {
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}
		}
		return result;
	}
	
    private boolean updateCode(String userId) throws Exception {
        boolean flag = false;
        GetCodeReq codeReq = new GetCodeReq();
        codeReq.setCode("CL");
        codeReq.setCodeType(true);
        codeReq.setUserid(userId);
        if (StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
            codeReq.setCodeType(false);
            if (StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
                flag = true; 
            }
        }
        return flag;
    }
    
    private String getCode(String userId) throws Exception {
        GetCodeReq codeReq = new GetCodeReq();
        codeReq.setCode("CL");
        codeReq.setCodeType(true);
        codeReq.setUserid(userId);
        return systemCodeService.getCode(codeReq).getData().toString();
    }
    
    private String getInternalCode(String userId) throws Exception {
        GetCodeReq codeReq = new GetCodeReq();
        codeReq.setCode("CL");
        codeReq.setCodeType(false);
        codeReq.setUserid(userId);
        return systemCodeService.getCode(codeReq).getData().toString();
    }

	@Transactional
	@Override
	public Result update(VehicleManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null) {
			VehicleManage vehicle = new VehicleManage();
			PropertyUtils.copyProperties(vehicle, save);
			vehicle.setModifier(save.getCurrUId());
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
	public Result delete(VehicleManageQuery query) {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			VehicleManage vehicle = new VehicleManage();
			vehicle.setId(query.getId());
			vehicle.setState(Constant.ZERO_STRING);
			vehicle.setModifier(query.getCurrUId());
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
	public Result white(VehicleManageQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			VehicleManage vehicle = new VehicleManage();
			vehicle.setId(query.getId());
			vehicle.setIsblacklist(Constant.ZERO_STRING);
			vehicle.setModifier(query.getCurrUId());
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
	public Result black(VehicleManageQuery query) throws Exception {
	    BlacklistManageSave save = new BlacklistManageSave();
	    save.setVehicleid(query.getId());
	    save.setRemarks(query.getBlackRemarks());
	    save.setCurrId(query.getCurrUId());
		return blacklistManageService.add(save);
	}

	@Override
	public Result findListByParmas(VehicleManageQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		VehicleManage vehicle = new VehicleManage();
		if (query != null) {
			PropertyUtils.copyProperties(vehicle, query);
		}
		vehicle.setState(Constant.ONE_STRING);
		List<VehicleManage> list = vehicleManageMapper.selectSelective(vehicle);
		result.setData(copyBeanList2RespList(list));
		return result;
	}

	@Override
	public VehicleManageResp findOne(String id) throws Exception {
		if ( StringUtils.isNotBlank(id) ) {
			return copyBean2Resp(vehicleManageMapper.selectByPrimaryKey(id));
		}
		return null;
	}

	@Override
	public List<VehicleManageResp> autoCompleteSearch(String isCutover, String likeName, String isBlack) throws Exception {
		List<VehicleManage> list = vehicleManageMapper.autoCompleteSearch(isCutover, likeName, isBlack);
		if (CollectionUtils.isNotEmpty(list)) {
			List<VehicleManageResp> respList = new ArrayList<VehicleManageResp>();
			for (VehicleManage vehicle : list) {
				VehicleManageResp resp = new VehicleManageResp();
				PropertyUtils.copyProperties(resp, vehicle);
				respList.add(resp);
			}
			return respList;
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
			if (StringUtils.isNotBlank(bean.getTransportunit())) {
			    TransportunitManage transportunit = transportunitManageMapper.selectByPrimaryKey(bean.getTransportunit());
			    if (transportunit != null) {
			        resp.setTransportunitName(transportunit.getName());
			    }
			}
			if (StringUtils.isNotBlank(bean.getCreator())) {
			    SystemUserResp user = systemUserService.get(bean.getCreator());
			    if (user != null) {
			        resp.setCreator(user.getName());
			    }
			}
		}
		return resp;
	}
	/**
	 * @annotation 校验RFID是否绑定过车辆
	 * @param rfid
	 * @param vehicleNo
	 * @param result
	 * @return
	 */
	private boolean validateRfidAndVehicleNo(String rfid, String vehicleNo, Result result) {
	    boolean flag = false;
	    VehicleManage bean = new VehicleManage();
        bean.setRfid(rfid);
        bean.setState(Constant.ONE_STRING);
        List<VehicleManage> list = vehicleManageMapper.selectSelective(bean);
        if (CollectionUtils.isNotEmpty(list)) {
            if (StringUtils.equals(list.get(0).getVehicleno(), vehicleNo)) {
                //这个RFID与该车辆已经绑定，无需重复绑定！
                result.setErrorCode(ErrorCode.RFID_VEHICLE_EXIST1);
            } else {
                //这个RFID已经绑定过其他车辆了！
                result.setErrorCode(ErrorCode.RFID_VEHICLE_EXIST2);
            }
        } else {
			bean.setVehicleno(vehicleNo);
			bean.setState(Constant.ONE_STRING);
			List<VehicleManage> vehicleList = vehicleManageMapper.selectSelective(bean);
			if (CollectionUtils.isNotEmpty(vehicleList)) {
				bean = vehicleList.get(0);
				if (StringUtils.equals(bean.getIsvalid(), Constant.ZERO_STRING)) {
					result.setErrorCode(ErrorCode.VEHICLE_IS_WX);
				} else if (StringUtils.equals(bean.getIsblacklist(), Constant.ONE_STRING)) {
					result.setErrorCode(ErrorCode.VEHICLE_IS_BLACK);
				} else if (StringUtils.isNotBlank(bean.getRfid())) {
					result.setErrorCode(ErrorCode.VEHICLE_DONT_BIND_RFID);
				} else {
					flag = true;
				}
			} else {
				flag = true;
			}
        }
        return flag;
	}
	
	private void bindRfid(VehicleManageApi apiParams, Result result) throws Exception {
	    VehicleManage vehicle = new VehicleManage();
	    vehicle.setVehicleno(apiParams.getVehicleNo());
        vehicle.setState(Constant.ONE_STRING);
        List<VehicleManage> list = vehicleManageMapper.selectSelective(vehicle);
        if (CollectionUtils.isNotEmpty(list)) {
            //车辆存在  直接绑定RFID
        	vehicle = list.get(0);
            vehicle.setRfid(apiParams.getRfid());
            vehicleManageMapper.updateByPrimaryKeySelective(vehicle);
            //修改未出厂的通知单和磅单的车辆信息
            updateNoticeAndPoundNoteDetail(vehicle);
        } else {
            //车辆不存在  新增并绑定RFID
            vehicle.setId(UUIDUtil.getId());
            vehicle.setRfid(apiParams.getRfid());
            vehicle.setCode(getCode(apiParams.getCurrUid()));
            vehicle.setInternalcode(getInternalCode(apiParams.getCurrUid()));
            vehicle.setIsvalid(Constant.ONE_STRING);
            vehicle.setIsblacklist(Constant.ZERO_STRING);
            vehicle.setOrgid(Constant.ORG_ID);
            vehicle.setOrgname(Constant.ORG_NAME);
            vehicle.setState(Constant.ONE_STRING);
            vehicle.setCreator(apiParams.getCurrUid());
            vehicle.setCreatetime(System.currentTimeMillis());
            vehicle.setModifier(apiParams.getCurrUid());
            vehicle.setModifytime(System.currentTimeMillis());
            vehicleManageMapper.insertSelective(vehicle);
            updateCode(apiParams.getCurrUid());
        }
        result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
	}
	
	private void updateNoticeAndPoundNoteDetail(VehicleManage vehicle) {
		SalesArrive sa = new SalesArrive();
		sa.setVehicleno(vehicle.getVehicleno());
		sa.setStatus(Constant.ZERO_STRING);
		sa.setState(Constant.ONE_STRING);
		sa.setValidStatus(Constant.ZERO_STRING);
		List<SalesArrive> salesList = salesArriveMapper.selectSelective(sa);
		if (CollectionUtils.isNotEmpty(salesList)) {
			sa = salesList.get(0);
			sa.setVehicleid(vehicle.getId());
			sa.setVehicleno(vehicle.getVehicleno());
			sa.setVehiclerfid(vehicle.getRfid());
			salesArriveMapper.updateByPrimaryKeySelective(sa);
		} else {
			PurchaseArrive pa = new PurchaseArrive();
			pa.setVehicleno(vehicle.getVehicleno());
			pa.setStatus(Constant.ZERO_STRING);
			pa.setState(Constant.ONE_STRING);
			List<PurchaseArrive> purList = purchaseArriveMapper.selectSelective(pa);
			if (CollectionUtils.isNotEmpty(purList)) {
				pa = purList.get(0);
				pa.setVehicleid(vehicle.getId());
				pa.setVehicleno(vehicle.getVehicleno());
				pa.setVehiclerfid(vehicle.getRfid());
				purchaseArriveMapper.updateByPrimaryKeySelective(pa);
			}
		}
		
	}

	@Override
	public Result addVehicleApi(VehicleManageApi apiParams) throws Exception {
	    Result result = Result.getParamErrorResult();
	    if (apiParams != null && StringUtils.isNotBlank(apiParams.getVehicleNo())
	            && StringUtils.isNotBlank(apiParams.getRfid())) {
	        RFID rfid = rfidMapper.selectByPrimaryKey(apiParams.getRfid());
	        if (rfid != null) {
	            if (validateRfidAndVehicleNo(apiParams.getRfid(), apiParams.getVehicleNo(), result)) {
                    bindRfid(apiParams, result);
                }
            } else {
                //RFID未注册
                result.setErrorCode(ErrorCode.RFID_NOT_EXIST);
            }
        }
	    return result;
	}

	@Override
	public Result vehicleCheck(VehicleManageApi vehicleManageApi) {
		Result result = Result.getParamErrorResult();
		if (vehicleManageApi != null && StringUtils.isNotBlank(vehicleManageApi.getVehicleNo()) && StringUtils.isNotBlank(vehicleManageApi.getRfid())) {
			VehicleManage vehicle = new VehicleManage();
			vehicle.setState(Constant.ONE_STRING);
			vehicle.setVehicleno(vehicleManageApi.getVehicleNo());
			List<VehicleManage> list = vehicleManageMapper.selectSelective(vehicle);
			//判断车牌号是否存在且唯一
			if (list != null && list.size() == Constant.ONE_NUMBER) {
				RFID rfid = new RFID();
				rfid.setRfid(vehicleManageApi.getRfid());
				rfid.setState(true);
				long count = rfidMapper.selectSelectiveCount(rfid);
				//判断RFID是否已注册且唯一
				if(count == Constant.ONE_NUMBER){
					if(StringUtils.equals(vehicleManageApi.getRfid(), list.get(0).getRfid())){
						result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
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
	public PaginationVO<AppVehicleResp> appToPage(AppQueryReq req) {
		PaginationVO<AppVehicleResp> page = null;
		if (req != null) {
			page = new PaginationVO<AppVehicleResp>();
			long count = vehicleManageMapper.appQueryVehiclePageCount(req);
			if (count > 0) {
				req.setStart((req.getPageNo() - 1) * req.getPageSize());
				req.setLimit(req.getPageSize());
				List<AppVehicleResp> list = this.vehicleManageMapper.appQueryVehiclePage(req);
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		return page;
	}

	@Override
	public Result selectByVehicleNo(VehicleManageApi vehicleManageApi) throws Exception {
		Result result = Result.getParamErrorResult();
		if (vehicleManageApi != null && StringUtils.isNotBlank(vehicleManageApi.getVehicleNo()) ) {
			VehicleManage vehicle = new VehicleManage();
			vehicle.setVehicleno(vehicleManageApi.getVehicleNo());
			List<VehicleManage> list = vehicleManageMapper.selectSelective(vehicle);
			if( CollectionUtils.isNotEmpty(list) ){
				VehicleManage db=list.get(0);
				if( StringUtils.isNotBlank(db.getRfid()) ){
					//成功返回rfid
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					result.setData(db);
				//没有rfid信息	
				}else{
					result.setErrorCode(ErrorCode.RFID_ERROR4);
				}
			//车辆不存在	
			}else{
				result.setErrorCode(ErrorCode.VEHICLE_NOT_EXIST);
			}
		}	
		return result;
	}

	

}