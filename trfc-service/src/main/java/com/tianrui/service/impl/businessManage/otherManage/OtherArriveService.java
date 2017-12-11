package com.tianrui.service.impl.businessManage.otherManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.otherManage.IOtherArriveService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.otherManage.AppOtherArriveReq;
import com.tianrui.api.req.businessManage.otherManage.OtherArriveReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.otherManage.AppOtherArriveResp;
import com.tianrui.api.resp.businessManage.otherManage.OtherArriveResp;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.measure.YardManage;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.system.auth.Organization;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.measure.YardManageMapper;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.logisticsManage.AccessRecordMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.system.auth.OrganizationMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class OtherArriveService implements IOtherArriveService {

	@Autowired
	private OtherArriveMapper otherArriveMapper;
	@Autowired
	private MaterielManageMapper materielManageMapper;
	@Autowired
	private SupplierManageMapper supplierManageMapper;
	@Autowired
	private WarehouseManageMapper warehouseManageMapper;
	@Autowired
	private SystemUserMapper systemUserMapper;
	@Autowired
	private DriverManageMapper driverManageMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	@Autowired
	private CustomerManageMapper customerManageMapper;
	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private PoundNoteMapper poundNoteMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private YardManageMapper yardManageMapper;
	@Autowired
	private AccessRecordMapper accessRecordMapper;



	@Override
	public Result update(OtherArriveReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		
		OtherArrive arrive = otherArriveMapper.selectByPrimaryKey(req.getId());
		//校验通知单是否审核
		if(arrive != null && !StringUtils.equals(arrive.getAuditstatus(), Constant.ZERO_STRING)){
			rs.setErrorCode(ErrorCode.NOTICE_DONT_UPDATE_ERROR);
			return rs;
		}		
		OtherArrive oa = new OtherArrive();
		oa.setId(req.getId());
		if(req!=null && StringUtils.isNotBlank(req.getId()) && validDriverAndVehicle(req,rs,oa)){
			//转换类型
			PropertyUtils.copyProperties(oa, req);
			oa.setModifier(req.getUserid());
			oa.setModifytime(System.currentTimeMillis());
			oa.setUtc(System.currentTimeMillis());
			//更新数据到数据库
			int index = otherArriveMapper.updateByPrimaryKeySelective(oa);
			//判断操作是否成功
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result updateOperation(OtherArriveReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		OtherArrive oa = new OtherArrive();
		oa.setId(req.getId());
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//转换类型
			PropertyUtils.copyProperties(oa, req);
			oa.setModifier(req.getUserid());
			oa.setModifytime(System.currentTimeMillis());
			oa.setUtc(System.currentTimeMillis());
			//更新数据到数据库
			int index = otherArriveMapper.updateByPrimaryKeySelective(oa);
			//判断操作是否成功
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result add(OtherArriveReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		OtherArrive oa = new OtherArrive();
		if(req!=null && validDriverAndVehicle(req,rs,oa)){
			PropertyUtils.copyProperties(oa, req);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode(req.getCodekey());
			codeReq.setCodeType(true);
			codeReq.setUserid(req.getUserid());
			oa.setCode(String.valueOf(systemCodeService.getCode(codeReq).getData()));
			//获取id
			oa.setId(UUIDUtil.getId());
			oa.setAuditstatus(Constant.ONE_STRING);
			oa.setStatus(Constant.ZERO_STRING);
			oa.setReceivedepartmentid(Constant.ORG_ID);
			oa.setCreator(req.getUserid());
			oa.setCreatetime(System.currentTimeMillis());
			oa.setModifier(req.getUserid());
			oa.setModifytime(System.currentTimeMillis());
			oa.setUtc(System.currentTimeMillis());
			//更新数据到数据库
			int index = otherArriveMapper.insertSelective(oa);
			//判断操作是否成功
			if(index>0){
				systemCodeService.updateCodeItem(codeReq);
				otherArriveMapper.emptyForceOutFactoryByVehicle(req.getVehicleid());
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result page(OtherArriveReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
		    if (StringUtils.isBlank(req.getVehicleid()) && StringUtils.isNotBlank(req.getVehicleno())) {
		        VehicleManage vehicle = vehicleManageMapper.getVehicleByNo(req.getVehicleno());
		        req.setVehicleid(vehicle.getId());
		    }
			PaginationVO<OtherArriveResp> pagevo = new PaginationVO<OtherArriveResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize*pageNo);
			//获取数据总数
			long total = otherArriveMapper.count(req);
			pagevo.setPageNo(pageNo);
			pagevo.setPageSize(pageSize);
			pagevo.setTotal(total);
			List<OtherArriveResp> resps = new ArrayList<OtherArriveResp>();
			//判断是否有数据可以查询
			if(total>0){
				List<OtherArrive> list = otherArriveMapper.page(req);
				for(OtherArrive oa : list){
					resps.add(bean2Resp(oa));
				}
			}
			pagevo.setList(resps);
			rs = Result.getSuccessResult();
			rs.setData(pagevo);
		}
		return rs;
	}

	/**
	 * 转换类型
	 * @param OtherArrive
	 * @return OtherArriveResp
	 * @throws Exception
	 */
	private OtherArriveResp bean2Resp(OtherArrive oa) throws Exception{
		OtherArriveResp resp = new OtherArriveResp();
		PropertyUtils.copyProperties(resp, oa);
		if (!StringUtils.equals(oa.getBusinesstype(), Constant.FOUR_STRING)) {
		    //获取磅单
	        PoundNote pound = poundNoteMapper.selectByNoticeId(oa.getId());
	        if(pound!=null){
	            PoundNoteResp poundResp = new PoundNoteResp();
	            poundResp.setCode(pound.getCode());
	            poundResp.setVehicleno(pound.getVehicleno());
	            poundResp.setGrossweight(pound.getGrossweight());
	            poundResp.setTareweight(pound.getTareweight());
	            poundResp.setNetweight(pound.getNetweight());
	            poundResp.setWeighttime(pound.getWeighttime());
	            poundResp.setLighttime(pound.getLighttime());
	            resp.setPoundDetail(poundResp);
	        }
		}
		//获取物料名称
		if(StringUtils.isNotBlank(oa.getMaterielid())){
			MaterielManage materiel = materielManageMapper.selectByPrimaryKey(oa.getMaterielid());
			if(materiel!=null){
				resp.setMaterielname(materiel.getName());
			}
		}
		//获取客户名称
		if(StringUtils.isNotBlank(oa.getCustomerid())){
			CustomerManage customer = customerManageMapper.selectByPrimaryKey(oa.getCustomerid());
			if(customer!=null){
				resp.setCustomername(customer.getName());
			}
		}
		//获取堆场名称
		if(StringUtils.isNotBlank(oa.getEnteryard())){
			YardManage yard = yardManageMapper.selectByPrimaryKey(oa.getEnteryard());
			if(yard!=null){
				resp.setEnteryerdname(yard.getName());
			}
		}
		if(StringUtils.isNotBlank(oa.getLeaveyard())){
			YardManage yard = yardManageMapper.selectByPrimaryKey(oa.getLeaveyard());
			if(yard!=null){
				resp.setLeaveyerdname(yard.getName());
			}
		}

		//获取供应商名称
		if(StringUtils.isNotBlank(oa.getSupplierid())){
			SupplierManage supplier = supplierManageMapper.selectByPrimaryKey(oa.getSupplierid());
			if(supplier!=null){
				resp.setSuppliername(supplier.getName());
			}
		}
		//获取组织名称
		if(StringUtils.isNotBlank(oa.getReceivedepartmentid())){
			Organization org = organizationMapper.selectByPrimaryKey(oa.getReceivedepartmentid());
			if(org!=null){
				resp.setReceivedepartmentname(org.getName());
			}
		}
		//获取组织名称
		if(StringUtils.isNotBlank(oa.getSenddepartmentid())){
			Organization org = organizationMapper.selectByPrimaryKey(oa.getSenddepartmentid());
			if(org!=null){
				resp.setSenddepartmentname(org.getName());
			}
		}
		//获取司机信息
		if(StringUtils.isNotBlank(oa.getDriverid())){
			DriverManage driver = driverManageMapper.selectByPrimaryKey(oa.getDriverid());
			if(driver!=null){
				resp.setDrivername(driver.getName());
				resp.setDriveridentityno(driver.getIdentityno());
			}
		}
		//获取车辆信息
		if(StringUtils.isNotBlank(oa.getVehicleid())){
			VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(oa.getVehicleid());
			if(vehicle!=null){
				resp.setVehicleno(vehicle.getVehicleno());
				resp.setRfid(vehicle.getRfid());
			}
		}
		//获取仓库名称
		if(StringUtils.isNotBlank(oa.getWarehouseid())){
			WarehouseManage warehouse = warehouseManageMapper.selectByPrimaryKey(oa.getWarehouseid());
			if(warehouse!=null){
				resp.setWarehousename(warehouse.getName());
			}
		}
		//获取用户名称
		if(StringUtils.isNotBlank(oa.getCreator())){
			SystemUser user = systemUserMapper.selectByPrimaryKey(oa.getCreator());
			if(user!=null){
				resp.setCreatorname(user.getName()); 
			}
		}
		return resp;
	}

	@Override
	public Result findOne(String id) throws Exception{
		Result rs = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(id)){
			OtherArrive oa = otherArriveMapper.selectByPrimaryKey(id);
			OtherArriveResp resp = new OtherArriveResp();
			if(oa!=null){
				resp = bean2Resp(oa);
			}
			rs = Result.getSuccessResult();
			rs.setData(resp);
		}
		return rs;
	}

	private boolean validDriverAndVehicle(OtherArriveReq req, Result result,OtherArrive oa) {
		boolean flag = true;
		if(StringUtils.equals(req.getBusinesstype(), "4")){
			if(StringUtils.isNotBlank(req.getVehicleid())){
				oa.setVehicleid(req.getVehicleid());
				List<OtherArrive> listVehicle2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
				if(listVehicle2!=null && listVehicle2.size()>0 && StringUtils.equals(listVehicle2.get(0).getBusinesstype(), "4")){
					result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
					result.setError("此车辆己有厂内倒运通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle2.get(0).getCode()+"，如有疑问请与销售处联系！");
					flag = false;
				}
			}
//			if(StringUtils.isNotBlank(req.getDriverid())){
//				oa.setVehicleid(null);
//				oa.setDriverid(req.getDriverid());
//				List<OtherArrive> listDriver2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
//				if(listDriver2!=null && listDriver2.size()>0 && StringUtils.equals(listDriver2.get(0).getBusinesstype(), "4")){
//					result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
//					result.setError("此司机己有厂内倒运通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver2.get(0).getCode()+"，如有疑问请与销售处联系！");
//					flag = false;
//				}
//			}
		}else{
			PurchaseArrive pa = new PurchaseArrive();
			SalesArrive sa = new SalesArrive();
			if(StringUtils.isNotBlank(req.getVehicleid())){
				pa.setVehicleid(req.getVehicleid());
				List<PurchaseArrive> listVehicle = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
				if(listVehicle != null && listVehicle.size() > 0){
					result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
					result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle.get(0).getCode()+"，如有疑问请与销售处联系！");
					flag = false;
				}
				sa.setVehicleid(req.getVehicleid());
				List<SalesArrive> listVehicle1 = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
				if(listVehicle1 != null && listVehicle1.size() > 0){
					result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
					result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle1.get(0).getCode()+"，如有疑问请与销售处联系！");
					flag = false;
				}
				oa.setVehicleid(req.getVehicleid());
				List<OtherArrive> listVehicle2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
				if(listVehicle2!=null && listVehicle2.size()>0){
					if(StringUtils.equals(listVehicle2.get(0).getBusinesstype(), "5")){
						result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
						result.setError("此车辆己有其他入库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle2.get(0).getCode()+"，如有疑问请与销售处联系！");
						flag = false;
					}else if(StringUtils.equals(listVehicle2.get(0).getBusinesstype(), "7")){
						result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
						result.setError("此车辆己有其他出库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle2.get(0).getCode()+"，如有疑问请与销售处联系！");
						flag = false;
					}
				}
			}
//			if(StringUtils.isNotBlank(req.getDriverid())){
//				pa.setVehicleid(null);
//				pa.setDriverid(req.getDriverid());
//				List<PurchaseArrive> listDriver = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
//				if(listDriver != null && listDriver.size() > 0){
//					result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
//					result.setError("此司机己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver.get(0).getCode()+"，如有疑问请与销售处联系！");
//					flag = false;
//				}
//				sa.setVehicleid(null);
//				sa.setDriverid(req.getDriverid());
//				List<SalesArrive> listDriver1 = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
//				if(listDriver1 != null && listDriver1.size() > 0){
//					result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
//					result.setError("此司机己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver1.get(0).getCode()+"，如有疑问请与销售处联系！");
//					flag = false;
//				}
//				oa.setVehicleid(null);
//				oa.setDriverid(req.getDriverid());
//				List<OtherArrive> listDriver2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
//				if(listDriver2!=null && listDriver2.size()>0){
//					if(StringUtils.equals(listDriver2.get(0).getBusinesstype(), "5")){
//						result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
//						result.setError("此司机己有其他入库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver2.get(0).getCode()+"，如有疑问请与销售处联系！");
//						flag = false;
//					}else if(StringUtils.equals(listDriver2.get(0).getBusinesstype(), "7")){
//						result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
//						result.setError("此司机己有其他出库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver2.get(0).getCode()+"，如有疑问请与销售处联系！");
//						flag = false;
//					}
//				}
//			}
		}
		//ic卡信息
		if(StringUtils.isNotBlank(req.getIcardno())){
			Card card = cardMapper.selectByCardno(req.getIcardno());
			if(card!=null){
				//ic卡是否占用
				SalesArrive sales = salesArriveMapper.checkICUse(card.getId());
				PurchaseArrive purchase = purchaseArriveMapper.checkICUse(card.getId());
				oa.setVehicleid(null);
				oa.setIcardid(card.getId());
				List<OtherArrive> listIcard2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
				if(sales == null && purchase == null && listIcard2.size()==0) {
					//获取icard id
					req.setIcardid(card.getId());
				}else{
					result.setErrorCode(ErrorCode.CARD_IN_USE);
					flag = false;
				}
			}else{
				result.setErrorCode(ErrorCode.CARD_NOT_EXIST);
				flag = false;
			}
		}
		return flag;
	}

	@Override
    public PaginationVO<AppOtherArriveResp> appPage(AppOtherArriveReq req) throws Exception {
	    PaginationVO<AppOtherArriveResp> page = null;
        if (req != null) {
            page = new PaginationVO<AppOtherArriveResp>();
            long count = otherArriveMapper.appPageCount(req);
            if (count > 0) {
                req.setStart((req.getPageNo() - 1) * req.getPageSize());
                req.setLimit(req.getPageSize());
                List<AppOtherArriveResp> list = otherArriveMapper.appPage(req);
                page.setList(list);
            }
            page.setTotal(count);
            page.setPageNo(req.getPageNo());
            page.setPageSize(req.getPageSize());
        }
        return page;
    }

    @Override
    public Result forceOutFactory(OtherArriveReq req) {
        Result result = Result.getParamErrorResult();
        if (req != null && StringUtils.isNotBlank(req.getId())) {
            OtherArrive bean = otherArriveMapper.selectByPrimaryKey(req.getId());
            if (bean != null) {
                OtherArrive oa = new OtherArrive();
                oa.setId(bean.getId());
                oa.setStatus(Constant.FIVE_STRING);
                oa.setForceOutFactory(Constant.ONE_NUMBER);
                oa.setForceOutFactoryPerson(req.getUserid());
                oa.setForceOutFactoryTime(System.currentTimeMillis());
                otherArriveMapper.updateByPrimaryKeySelective(oa);
                AccessRecord accessRecord = accessRecordMapper.selectByNoticeId(oa.getId());
                AccessRecord ar = new AccessRecord();
                ar.setId(accessRecord.getId());
                ar.setOuttime(System.currentTimeMillis());
                ar.setAccesstype(Constant.TWO_STRING);
                ar.setModifier(req.getUserid());
                ar.setModifytime(System.currentTimeMillis());
                accessRecordMapper.updateByPrimaryKeySelective(ar);
                result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
            } else {
                result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
            }
        }
        return result;
    }

	@Override
	public Result goodsConfirm(OtherArriveReq req) throws Exception {
		Result result = Result.getParamErrorResult();
		//验证参数
        if (req != null && StringUtils.isNotBlank(req.getId())) {
        	//查询通知单
            OtherArrive bean = otherArriveMapper.selectByPrimaryKey(req.getId());
            if (bean != null ) {
               //类型为入库通知单
               if (StringUtils.equals(bean.getBusinesstype(), Constant.NOTICE_OF_QTRK)){
            	   //状态为一次磅
            	   if(StringUtils.equals(bean.getStatus(), Constant.ONE_STRING) ){
            		   OtherArrive oa = new OtherArrive();
                       oa.setId(bean.getId());
                       oa.setStatus(Constant.SEVEN_STRING);
                       oa.setModifier(req.getUserid());
                       oa.setModifytime(System.currentTimeMillis());
                       otherArriveMapper.updateByPrimaryKeySelective(oa);
                       result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
            	   }else{
            		   result.setErrorCode(ErrorCode.NOTICE_STATUS_ERROR);
            	   }
               }else{
            	   result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST2);
               }
            } else {
                result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
            }
        }
        return result;
	}
    
    

}
