package com.tianrui.service.impl.businessManage.poundNoteMaintain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.businessManage.poundNoteMaintain.IPoundNoteService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPushSingleService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationDetailService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.app.AppPoundOrderReq;
import com.tianrui.api.req.businessManage.app.AppPoundOrderResp;
import com.tianrui.api.req.businessManage.poundNoteMaintain.ApiPoundNoteQuery;
import com.tianrui.api.req.businessManage.poundNoteMaintain.ApiPoundNoteValidation;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteCopyDTO;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteQuery;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteSave;
import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.ApiSignDetailResp;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNotePrintVo;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.api.resp.common.UploadImageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.bean.basicFile.measure.MinemouthManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.measure.YardManage;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
import com.tianrui.service.bean.businessManage.examine.ExceptionAudit;
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageList;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageListItem;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationJoinPoundNote;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesOutboundOrder;
import com.tianrui.service.bean.businessManage.salesManage.SalesOutboundOrderItem;
import com.tianrui.service.bean.common.UploadImage;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.MinemouthManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.measure.YardManageMapper;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.service.mapper.businessManage.examine.ExceptionAuditMapper;
import com.tianrui.service.mapper.businessManage.logisticsManage.AccessRecordMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseStorageListItemMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseStorageListMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationJoinPoundNoteMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesOutboundOrderItemMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesOutboundOrderMapper;
import com.tianrui.service.mapper.common.UploadImageMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.common.ApiParamUtils;
import com.tianrui.smartfactory.common.common.HttpUtils;
import com.tianrui.smartfactory.common.constants.BusinessConstants;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.enums.ExceptionAuditEnum;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.NumberUtils;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class PoundNoteService implements IPoundNoteService {

	@Autowired
	private PoundNoteMapper poundNoteMapper;
	@Autowired
	private PurchaseApplicationMapper purchaseApplicationMapper;
	@Autowired
	private PurchaseApplicationDetailMapper purchaseApplicationDetailMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	@Autowired
	private DriverManageMapper driverManageMapper;
	@Autowired
	private WarehouseManageMapper warehouseManageMapper;
	@Autowired
	private MinemouthManageMapper minemouthManageMapper;
	@Autowired
	private YardManageMapper yardManageMapper;
	@Autowired
	private SalesApplicationMapper salesApplicationMapper;
	@Autowired
	private SalesApplicationDetailMapper salesApplicationDetailMapper;
	@Autowired
	private SalesApplicationJoinPoundNoteMapper salesApplicationJoinPoundNoteMapper;
	@Autowired
	private ISalesApplicationService salesApplicationService;
	@Autowired
	private ISalesApplicationDetailService salesApplicationDetailService;
	@Autowired
	private SalesOutboundOrderMapper salesOutboundOrderMapper;
	@Autowired
	private SalesOutboundOrderItemMapper salesOutboundOrderItemMapper;
	@Autowired
	private PurchaseStorageListMapper purchaseStorageListMapper;
	@Autowired
	private PurchaseStorageListItemMapper purchaseStorageListItemMapper;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private AccessRecordMapper accessRecordMapper;
	@Autowired
	private OtherArriveMapper otherArriveMapper;
	@Autowired
	private UploadImageMapper uploadImageMapper;
	@Autowired
	private CustomerManageMapper customerManageMapper;
	@Autowired
	private SupplierManageMapper supplierManageMapper;
	@Autowired
	private MaterielManageMapper materielManageMapper;
	@Autowired
	private IPushSingleService pushSingleService;
	@Autowired
	private SystemUserMapper systemUserMapper;
	@Autowired
	private SalesApplicationArriveMapper salesApplicationArriveMapper;
	@Autowired
	private ExceptionAuditMapper exceptionAuditMapper;

	@Override
	public PaginationVO<PoundNoteResp> purchasePage(PoundNoteQuery query) throws Exception {
		PaginationVO<PoundNoteResp> page = null;
		if (query != null) {
			page = new PaginationVO<PoundNoteResp>();
			long count = poundNoteMapper.purchasePageCount(query);
			if (count > 0) {
				query.setStart((query.getPageNo() - 1) * query.getPageSize());
				query.setLimit(query.getPageSize());
				List<PoundNoteResp> list = poundNoteMapper.purchasePage(query);
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	private String getCode(String code, String userId, boolean flag) throws Exception {
        GetCodeReq codeReq1 = new GetCodeReq();
        codeReq1.setCode(code);
        codeReq1.setCodeType(flag);
        codeReq1.setUserid(userId);
        return systemCodeService.getCode(codeReq1).getData().toString();
    }
	private boolean updateCode(String code, String userId) throws Exception {
        boolean flag = false;
        GetCodeReq codeReq = new GetCodeReq();
        codeReq.setCode(code);
        codeReq.setCodeType(true);
        codeReq.setUserid(userId);
        if (StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
            flag = true; 
        }
        return flag;
    }
	@Transactional
	@Override
	public Result addPurchasePoundNote(PoundNoteSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null) {
			PoundNote bean = new PoundNote();
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			bean.setCode(getCode("RK", save.getMakerid(), true));
			PurchaseApplication purchaseApplication = null;
			PurchaseApplicationDetail purchaseApplicationDetail = null;
			if (StringUtils.isNotBlank(save.getBillid()) && StringUtils.isNotBlank(save.getBilldetailid())) {
				purchaseApplication = purchaseApplicationMapper.selectByPrimaryKey(save.getBillid());
				purchaseApplicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(save.getBilldetailid());
				if (purchaseApplication != null) {
					bean.setSupplierid(purchaseApplication.getSupplierid());
					bean.setSuppliername(purchaseApplication.getSuppliername());
					bean.setMaterialid(purchaseApplicationDetail.getMaterielid());
					bean.setMaterialname(purchaseApplicationDetail.getMaterielname());
					bean.setBillcode(purchaseApplication.getCode());
					bean.setReceivedepartmentid(purchaseApplication.getOrgid());
					bean.setReceivedepartmentname(purchaseApplication.getOrgname());
				}
			}
			setDetails(save, bean);
			bean.setReturnstatus("0");
			bean.setRedcollide("0");
			bean.setStatus("1");
			bean.setBilltype(Constant.NOTICE_OF_CGDH);
			bean.setNetweight(bean.getGrossweight() - bean.getTareweight());
			bean.setDeductionweight(0D);
			bean.setDeductionother(0D);
			bean.setState("1");
			bean.setCreator(save.getMakerid());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getMakerid());
			bean.setModifytime(System.currentTimeMillis());
			//入库单
			PurchaseStorageList storage = new PurchaseStorageList();
			storage.setId(UUIDUtil.getId());
			storage.setCode(getCode("RKD", save.getMakerid(), true));
			storage.setNcId(bean.getBillid());
			storage.setPoundId(bean.getId());
			storage.setType("1");
			if (purchaseApplication != null) {
				storage.setPkOrg(purchaseApplication.getOrgid());
				storage.setCdptid(purchaseApplication.getDepartmentid());
				storage.setCvendorid(purchaseApplication.getSupplierid());
			}
			storage.setNtotalnum("" + bean.getNetweight());
			storage.setDbilldate(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			storage.setBillmaker(bean.getMakerid());
			storage.setCreationtime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			storage.setTs(storage.getCreationtime());
			storage.setStatus("0");
			PurchaseStorageListItem storageItem = setPurchaseStorageItem(purchaseApplicationDetail, bean, storage);
			bean.setPutinwarehouseid(storage.getId());
			bean.setPutinwarehousecode(storage.getCode());
			if (poundNoteMapper.insertSelective(bean) > 0
					&& updateCode("RK", save.getMakerid())
					&& purchaseStorageListMapper.insertSelective(storage) > 0
					&& purchaseStorageListItemMapper.insertSelective(storageItem) > 0
					&& updateCode("RKD", save.getMakerid())) {
				result.setErrorCode(returnPurchaseStorage(storage, storageItem, bean));
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	private void setDetails(PoundNoteSave save, PoundNote bean) throws Exception {
		if (StringUtils.isNotBlank(save.getVehicleid())) {
			VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(save.getVehicleid());
			if (vehicle != null) {
				bean.setVehicleno(vehicle.getVehicleno());
				bean.setVehiclerfid(vehicle.getRfid());
			}
		}
		if (StringUtils.isNotBlank(save.getDriverid())) {
			DriverManage driver = driverManageMapper.selectByPrimaryKey(save.getDriverid());
			if (driver != null) {
				bean.setDrivername(driver.getName());
				bean.setDriveridentityno(driver.getIdentityno());
			}
		}
		if (StringUtils.isNotBlank(save.getWarehouseid())) {
			WarehouseManage warehouse = warehouseManageMapper.selectByPrimaryKey(save.getWarehouseid());
			if (warehouse != null) {
				bean.setWarehousename(warehouse.getName());
			}
		}
		if (StringUtils.isNotBlank(save.getMinemouthid())) {
			MinemouthManage minemouth = minemouthManageMapper.selectByPrimaryKey(save.getMinemouthid());
			if (minemouth != null) {
				bean.setMinemouthname(minemouth.getName());
			}
		}
		if (StringUtils.isNotBlank(save.getYardid())) {
			YardManage yard = yardManageMapper.selectByPrimaryKey(save.getYardid());
			if (yard != null) {
				bean.setYardname(yard.getName());
			}
		}
		if (StringUtils.isNotBlank(save.getEnteryardid())) {
			YardManage yard = yardManageMapper.selectByPrimaryKey(save.getEnteryardid());
			if (yard != null) {
				bean.setEnteryardname(yard.getName());
			}
		}
		if (StringUtils.isNotBlank(save.getLeaveyardid())) {
			YardManage yard = yardManageMapper.selectByPrimaryKey(save.getLeaveyardid());
			if (yard != null) {
				bean.setLeaveyardname(yard.getName());
			}
		}
		if (StringUtils.isNotBlank(save.getCustomerid())) {
			CustomerManage customer = customerManageMapper.selectByPrimaryKey(save.getCustomerid());
			if (customer != null) {
				bean.setCustomername(customer.getName());
			}
		}
		if (StringUtils.isNotBlank(save.getSupplierid())) {
			SupplierManage supplier = supplierManageMapper.selectByPrimaryKey(save.getSupplierid());
			if (supplier != null) {
				bean.setSuppliername(supplier.getName());
			}
		}
		if (StringUtils.isNotBlank(save.getMaterialid())) {
			MaterielManage materiel = materielManageMapper.selectByPrimaryKey(save.getMaterialid());
			if (materiel != null) {
				bean.setMaterialname(materiel.getName());
			}
		}
		// 收料员
		if (StringUtils.isNotBlank(save.getReceiverpersonid())) {
			SystemUserResp user = systemUserService.getUser(save.getReceiverpersonid());
			if (user != null) {
				bean.setReceiverpersonname(user.getName());
			}
		}
	}

	@Transactional
	@Override
	public Result returnAddPoundNote(PoundNoteSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null) {
			if (StringUtils.isNotBlank(save.getId())) {
				PoundNote bean = poundNoteMapper.selectByPrimaryKey(save.getId());
				bean.setId(UUIDUtil.getId());
				bean.setArrivepoundnotecode(bean.getCode());
				bean.setCode(getCode("RK", save.getMakerid(), true));
				bean.setNoticeid(null);
				bean.setNoticecode(null);
				bean.setGrossweight(save.getGrossweight());
				bean.setTareweight(save.getTareweight());
				bean.setNetweight(save.getNetweight());
				setDetails(save, bean);
				bean.setLighttime(save.getLighttime());
				bean.setWeighttime(save.getWeighttime());
				bean.setReturnstatus("0");
				bean.setRedcollide("0");
				bean.setStatus("2");
				bean.setBilltype(Constant.NOTICE_OF_CGTH);
				bean.setDeductionweight(0D);
				bean.setDeductionother(0D);
				bean.setState("1");
				bean.setMakebilltime(System.currentTimeMillis());
				bean.setCreator(save.getMakerid());
				bean.setCreatetime(System.currentTimeMillis());
				bean.setModifier(save.getMakerid());
				bean.setModifytime(System.currentTimeMillis());
				//增加退货入库单
				PurchaseStorageList storage = new PurchaseStorageList();
				storage.setId(UUIDUtil.getId());
				storage.setCode(getCode("RKD", bean.getMakerid(), true));
				storage.setNcId(bean.getBillid());
				storage.setPoundId(bean.getId());
				storage.setType("2");
				PurchaseApplication purchaseApplication = null;
				PurchaseApplicationDetail purchaseApplicationDetail = null;
				if (StringUtils.isNotBlank(bean.getBillid()) && StringUtils.isNotBlank(bean.getBilldetailid())) {
					purchaseApplication = purchaseApplicationMapper.selectByPrimaryKey(bean.getBillid());
					purchaseApplicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(bean.getBilldetailid());
				}
				if (purchaseApplication != null) {
					storage.setPkOrg(purchaseApplication.getOrgid());
					storage.setCdptid(purchaseApplication.getDepartmentid());
					storage.setCvendorid(purchaseApplication.getSupplierid());
				}
				if(StringUtils.isNotBlank(bean.getPutinwarehouseid())){
					PurchaseStorageList storageOld = purchaseStorageListMapper.selectByPrimaryKey(bean.getPutinwarehouseid());
					if(storageOld != null){
						storage.setReturnRkdNcId(storageOld.getRkdNcId());
					}
				}
				storage.setNtotalnum("-" + bean.getNetweight());
				storage.setDbilldate(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
				storage.setBillmaker(bean.getMakerid());
				storage.setCreationtime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
				storage.setTs(storage.getCreationtime());
				storage.setStatus("0");
				PurchaseStorageListItem storageItem = setPurchaseStorageItem(purchaseApplicationDetail, bean, storage);
				bean.setPutinwarehouseid(storage.getId());
				bean.setPutinwarehousecode(storage.getCode());
				if (poundNoteMapper.insertSelective(bean) > 0 
				        && updateCode("RK", bean.getMakerid())
						&& purchaseStorageListMapper.insertSelective(storage) > 0
						&& purchaseStorageListItemMapper.insertSelective(storageItem) > 0
						&& updateCode("RKD", bean.getMakerid())) {
					result.setErrorCode(returnPurchaseStorage(storage, storageItem, bean));
				} else {
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}

	@Transactional
	@Override
	public Result purchaseRedcollide(PoundNoteQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			PoundNote pn = poundNoteMapper.selectByPrimaryKey(query.getId());
			if(pn != null){
				if (StringUtils.equals(pn.getStatus(), Constant.ZERO_STRING) 
						|| StringUtils.equals(pn.getStatus(), Constant.ONE_STRING)) {
					if (StringUtils.equals(pn.getBilltype(), Constant.ZERO_STRING)) {
						if (StringUtils.equals(pn.getReturnstatus(), Constant.TWO_STRING)) {
							pn.setReturnstatus(Constant.ZERO_STRING);
							pn.setRedcollide(Constant.ONE_STRING);
							pn.setRedColStatus(Constant.ONE_STRING);
							pn.setModifier(query.getCurrId());
							pn.setModifytime(System.currentTimeMillis());
							//增加红冲入库单
							PurchaseApplication pa = purchaseApplicationMapper.selectByPrimaryKey(pn.getBillid());
							PurchaseApplicationDetail pad = purchaseApplicationDetailMapper.selectByPrimaryKey(pn.getBilldetailid());
							PurchaseStorageList storage = setPurchaseStorage(query.getCurrId(), pa, pn);
							storage.setType(Constant.THREE_STRING);
							String netWeight = String.valueOf(pn.getNetweight());
							if (pn.getNetweight() != 0) {
								netWeight = "-" + netWeight;
							}
							storage.setNtotalnum(netWeight);
							PurchaseStorageList storageOld = purchaseStorageListMapper.selectByPrimaryKey(pn.getPutinwarehouseid());
							storage.setReturnRkdNcId(storageOld.getRkdNcId());
							PurchaseStorageListItem storageItem = setPurchaseStorageItem(pad, pn, storage);
							pn.setPutinwarehouseid(storage.getId());
							pn.setPutinwarehousecode(storage.getCode());
							//推送红冲并记录日志
							pushDcRedcollide(result, storage, storageItem, pn);
							poundNoteMapper.updateByPrimaryKeySelective(pn);
							purchaseStorageListMapper.insertSelective(storage);
							purchaseStorageListItemMapper.insertSelective(storageItem);
							updateCode("RKD", query.getCurrId());
					        result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						} else {
							//已推单的单据才允许红冲
							result.setErrorCode(ErrorCode.POUNDNOTE_ERROR2);
						}
					} else {
						//退货单据不允许红冲
						result.setErrorCode(ErrorCode.POUNDNOTE_ERROR4);
					}
				} else {
					//作废和退货单据不允许红冲
					result.setErrorCode(ErrorCode.POUNDNOTE_ERROR5);
				}
			}else{
				result.setErrorCode(ErrorCode.POUNDNOTE_NOT_EXIST);
			}
		}
		return result;
	}
	//红冲推单到dc
    private void pushDcRedcollide(Result result, PurchaseStorageList storage, PurchaseStorageListItem storageItem, PoundNote pn) throws Exception {
        List<PurchaseStorageList> list = new ArrayList<PurchaseStorageList>();
        list.add(storage);
        List<PurchaseStorageListItem> itemList = new ArrayList<PurchaseStorageListItem>();
        itemList.add(storageItem);
        storage.setList(itemList);
        //记录推单日志
        PushSingleReq ps = new PushSingleReq();
		ps.setId(UUIDUtil.getId());
		ps.setRequisitionNum(pn.getCode());
		ps.setRequisitionType(Constant.ONE_STRING);
		ps.setLightCarTime(pn.getLighttime());
		ps.setHeavyCarTime(pn.getWeighttime());
		ps.setNetWeight(String.valueOf(pn.getNetweight()));
		ps.setCreatetime(System.currentTimeMillis());
		ps.setModifytime(System.currentTimeMillis());
		ps.setDesc2(Constant.ZERO_STRING);
        // 调用dc 接口成功 则推单状态为推单中 榜单展示为推单中
		ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(list), Constant.URL_DOMAIN + Constant.URL_RETURN_PURCHASESTORAGEATION);
		if (apiResult != null) {
			if (StringUtils.equals(apiResult.getCode(), Constant.SUCCESS)) {
	        	storage.setId(storage.getId());
	        	storage.setStatus(Constant.PUSH_STATUS_ING);
	        	pn.setRedColStatus(Constant.ONE_STRING);;
                ps.setPushStatus(Constant.ONE_STRING);
	        }else{
                ps.setPushStatus(Constant.THREE_STRING);
	        }
            ps.setReasonFailure(apiResult.getError());
            ps.setDesc1(apiResult.getCode());
		} else {
			ps.setPushStatus(Constant.THREE_STRING);
		    ps.setReasonFailure("FC-DC业务平台红冲磅单推单失败，连接超时。");
		    ps.setDesc1("-1");
		}
		pushSingleService.savePushSingle(ps);
    }

    @Transactional
	@Override
	public Result invalid(PoundNoteQuery query) {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
		    PoundNote poundNote = poundNoteMapper.selectByPrimaryKey(query.getId());
		    if (poundNote != null) {
	            PoundNote bean = new PoundNote();
	            bean.setId(query.getId());
	            bean.setStatus(Constant.THREE_STRING);
	            bean.setModifier(query.getCurrId());
	            bean.setModifytime(System.currentTimeMillis());
	            if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0) {
	                if (StringUtils.equals(poundNote.getBilltype(), Constant.ZERO_STRING) 
	                        || StringUtils.equals(poundNote.getBilltype(), Constant.ONE_STRING)) {
	                    PurchaseArrive pa = new PurchaseArrive();
	                    pa.setId(poundNote.getNoticeid());
	                    pa.setStatus(Constant.SIX_STRING);
	                    pa.setModifier(query.getCurrId());
	                    pa.setModifytime(System.currentTimeMillis());
	                    purchaseArriveMapper.updateByPrimaryKeySelective(pa);
	                } else if (StringUtils.equals(poundNote.getBilltype(), Constant.TWO_STRING)) {
	                    SalesArrive sa = new SalesArrive();
	                    sa.setId(poundNote.getNoticeid());
	                    sa.setStatus(Constant.SIX_STRING);
	                    sa.setModifier(query.getCurrId());
	                    sa.setModifytime(System.currentTimeMillis());
	                    salesArriveMapper.updateByPrimaryKeySelective(sa);
	                } else {
//	                if (StringUtils.equals(poundNote.getBilltype(), Constant.FOUR_STRING)) {
	                    OtherArrive oa = new OtherArrive();
	                    oa.setId(poundNote.getNoticeid());
	                    oa.setStatus(Constant.SIX_STRING);
	                    oa.setModifier(query.getCurrId());
	                    oa.setModifytime(System.currentTimeMillis());
	                    otherArriveMapper.updateByPrimaryKeySelective(oa);
	                }
	                result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
	            } else {
	                result.setErrorCode(ErrorCode.OPERATE_ERROR);
	            }
		    } else {
		        result.setErrorCode(ErrorCode.POUNDNOTE_NOT_EXIST);
		    }
		}
		return result;
	}

	@Override
	public PoundNoteResp findOne(String id) throws Exception {
		PoundNoteResp resp = null;
		if (StringUtils.isNotBlank(id)) {
			resp = poundNoteMapper.findOne(id);
			if (resp != null && StringUtils.equals(resp.getBilltype(), "0")) {
				if (StringUtils.isNotBlank(resp.getNoticeid())) {
					PurchaseArrive pa = purchaseArriveMapper.selectByPrimaryKey(resp.getNoticeid());
					if (pa != null) {
						resp.setSignPerson(pa.getSignPerson());
						resp.setSignPersonName(pa.getSignPersonName());
						resp.setSignTime(pa.getSignTime());
					}
				} else {
					resp.setSignPerson(resp.getReceiverpersonid());
					resp.setSignPersonName(resp.getReceiverpersonname());
					resp.setSignTime(resp.getReceivertime());
				}
			}
			if (resp != null && StringUtils.equals(resp.getBilltype(), "2")) {
				resp.setSalesApplicationList(getSalesApplicationListByPoundNoteId(resp.getId()));
			}
		}
		return resp;
	}

	private List<SalesApplicationResp> getSalesApplicationListByPoundNoteId(String poundNoteId) throws Exception {
		List<SalesApplicationResp> listApplication = null;
		if (StringUtils.isNotBlank(poundNoteId)) {
			List<SalesApplicationJoinPoundNote> listPoundNote = salesApplicationJoinPoundNoteMapper
					.selectByPoundNoteId(poundNoteId);
			if (CollectionUtils.isNotEmpty(listPoundNote)) {
				List<String> ids = new ArrayList<String>();
				List<String> detailIds = new ArrayList<String>();
				for (SalesApplicationJoinPoundNote join : listPoundNote) {
					ids.add(join.getBillid());
					detailIds.add(join.getBilldetailid());
				}
				listApplication = salesApplicationService.selectByIds(ids, false);
				List<SalesApplicationDetailResp> listApplicationDetail = salesApplicationDetailService
						.selectByIds(detailIds);
				for (SalesApplicationJoinPoundNote join : listPoundNote) {
					SalesApplicationResp application = null;
					for (SalesApplicationResp applicationResp : listApplication) {
						if (StringUtils.equals(applicationResp.getId(), join.getBillid())) {
							application = applicationResp;
						}
					}
					if (application != null) {
						List<SalesApplicationDetailResp> listDetail = application.getList();
						if (CollectionUtils.isEmpty(listDetail)) {
							listDetail = new ArrayList<SalesApplicationDetailResp>();
						}
						for (SalesApplicationDetailResp detailResp : listApplicationDetail) {
							if (StringUtils.equals(detailResp.getId(), join.getBilldetailid())) {
								listDetail.add(detailResp);
								application.setList(listDetail);
							}
						}
					}
				}
			}
		}
		return listApplication;
	}

	private List<PoundNoteResp> copyBeanList2RespList(List<PoundNote> list, boolean setApplication) throws Exception {
		List<PoundNoteResp> listResp = null;
		if (list != null && list.size() > 0) {
			listResp = new ArrayList<PoundNoteResp>();
			for (PoundNote sa : list) {
				listResp.add(copyBean2Resp(sa, setApplication));
			}
		}
		return listResp;
	}

	private PoundNoteResp copyBean2Resp(PoundNote bean, boolean setApplication) throws Exception {
		PoundNoteResp resp = null;
		if (bean != null) {
			resp = new PoundNoteResp();
			PropertyUtils.copyProperties(resp, bean);
			if (setApplication) {

			}
		}
		return resp;
	}

	@Override
	public PaginationVO<PoundNoteResp> salesPage(PoundNoteQuery query) {
		PaginationVO<PoundNoteResp> page = null;
		if (query != null) {
			page = new PaginationVO<PoundNoteResp>();
			query.setState("1");
			long count = poundNoteMapper.salesPageCount(query);
			if (count > 0) {
				query.setStart((query.getPageNo() - 1) * query.getPageSize());
				query.setLimit(query.getPageSize());
				List<PoundNoteResp> list = poundNoteMapper.salesPage(query);
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}

	@Transactional
	@Override
	public Result addSalesPoundNote(PoundNoteSave save, String bills) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null) {
			PoundNote bean = new PoundNote();
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			bean.setCode(getCode("CK", save.getMakerid(), true));
			if (StringUtils.isNotBlank(save.getBillid()) && StringUtils.isNotBlank(save.getBilldetailid())) {
				SalesApplication salesApplication = salesApplicationMapper.selectByPrimaryKey(save.getBillid());
				SalesApplicationDetail salesApplicationDetail = salesApplicationDetailMapper.selectByPrimaryKey(save.getBilldetailid());
				if (salesApplication != null) {
					bean.setCustomerid(salesApplication.getCustomerid());
					bean.setCustomername(salesApplication.getCustomername());
					bean.setMaterialid(salesApplicationDetail.getMaterielid());
					bean.setMaterialname(salesApplicationDetail.getMaterielname());
					bean.setBillcode(salesApplication.getCode());
					bean.setReceivedepartmentid(salesApplication.getOrgid());
					bean.setReceivedepartmentname(salesApplication.getOrgname());
				}
			}
			setDetails(save, bean);
			bean.setReturnstatus("0");
			bean.setRedcollide("0");
			bean.setStatus("1");
			bean.setBilltype("2");
			bean.setNetweight(bean.getGrossweight() - bean.getTareweight());
			bean.setDeductionweight(0D);
			bean.setDeductionother(0D);
			bean.setState("1");
			bean.setCreator(save.getMakerid());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getMakerid());
			bean.setModifytime(System.currentTimeMillis());
			JSONArray array = JSONArray.parseArray(bills);
			List<SalesApplicationJoinPoundNote> list = new ArrayList<SalesApplicationJoinPoundNote>();
			List<SalesOutboundOrder> orderList = new ArrayList<SalesOutboundOrder>();
			List<SalesOutboundOrderItem> orderItemList = new ArrayList<SalesOutboundOrderItem>();
			List<SalesApplicationDetail> applicationDetailList = 
			        parseBeanList(bean, array, list, orderList, orderItemList, getCode("A6XC", save.getMakerid(), true));
			if (poundNoteMapper.insertSelective(bean) > 0 
			        && salesApplicationJoinPoundNoteMapper.insertBatch(list) > 0
					&& updateCode("CK", save.getMakerid())
					&& salesOutboundOrderMapper.insertBatch(orderList) > 0
					&& salesOutboundOrderItemMapper.insertBatch(orderItemList) > 0
					&& updateCode("A6XC", save.getMakerid())) {
				if (CollectionUtils.isNotEmpty(applicationDetailList)) {
					for (SalesApplicationDetail salesApplicationDetail : applicationDetailList) {
						salesApplicationDetailMapper.updateByPrimaryKeySelective(salesApplicationDetail);
					}
				}
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	// 格式化待存储对象集合
	private List<SalesApplicationDetail> parseBeanList(PoundNote bean, JSONArray array,
			List<SalesApplicationJoinPoundNote> list, List<SalesOutboundOrder> orderList,
			List<SalesOutboundOrderItem> orderItemList, String code) throws Exception {
		List<SalesApplicationDetail> applicationDetailList = null;
		if (array != null && array.size() > 0) {
			applicationDetailList = new ArrayList<SalesApplicationDetail>();
			for (Object object : array) {
				SalesApplicationJoinPoundNote join = new SalesApplicationJoinPoundNote();
				JSONObject jsonObject = (JSONObject) object;
				String billid = jsonObject.getString("billid");
				String billdetailid = jsonObject.getString("billdetailid");
				join.setId(UUIDUtil.getId());
				join.setBillid(billid);
				join.setBilldetailid(billdetailid);
				join.setPoundnoteid(bean.getId());
				SalesApplication application = salesApplicationMapper.selectByPrimaryKey(billid);
				SalesApplicationDetail applicationDetail = salesApplicationDetailMapper
						.selectByPrimaryKey(billdetailid);
//				List<ExceptionAudit> exceptionAuditList = exceptionAuditMapper.listByPnId(Constant.ONE_STRING, bean.getId());
//				if (CollectionUtils.isEmpty(exceptionAuditList)) {
					double netWeight = bean.getNetweight();
					if (application != null && applicationDetail != null && netWeight > 0) {
						SalesApplicationDetail detail = new SalesApplicationDetail();
						SalesOutboundOrder order = new SalesOutboundOrder();
						order.setId(UUIDUtil.getId());
						order.setCode(code);
						order.setNcId(application.getNcId());
						order.setPoundId(bean.getId());
						order.setBilldate(DateUtil.parse(application.getBilltime(), "yyyy-MM-dd HH:mm:ss"));
						order.setPkOrg(application.getOrgid());
						order.setCdptid(application.getDepartmentid());
						order.setCbizid(application.getSalesmanid());
						order.setCdilivertypeid(application.getTransportcompanyid());
						order.setBillmaker(bean.getMakerid());
						order.setDmakedate(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
						order.setTs(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
						order.setStatus(Constant.ZERO_STRING);
						order.setCreateTime(System.currentTimeMillis());
						orderList.add(order);
						SalesOutboundOrderItem orderItem = new SalesOutboundOrderItem();
						orderItem.setId(UUIDUtil.getId());
						orderItem.setSaleOutboundOrderId(order.getId());
						orderItem.setCmaterialoid(applicationDetail.getMaterielid());
						orderItem.setCunitid(applicationDetail.getUnit());
						Double unstoragequantity = applicationDetail.getUnstoragequantity();
						if (unstoragequantity == null) {
							unstoragequantity = 0D;
						}
						detail.setId(applicationDetail.getId());
						if (netWeight > 0) {
							if (netWeight > unstoragequantity) {
								orderItem.setNnum("" + unstoragequantity);
								detail.setStoragequantity(applicationDetail.getStoragequantity() + unstoragequantity);
								detail.setUnstoragequantity(applicationDetail.getUnstoragequantity() - unstoragequantity);
							} else {
								orderItem.setNnum("" + netWeight);
								detail.setStoragequantity(applicationDetail.getStoragequantity() + netWeight);
								detail.setUnstoragequantity(applicationDetail.getUnstoragequantity() - netWeight);
							}
						} else {
							orderItem.setNnum("0");
						}
						netWeight -= unstoragequantity;
						orderItem.setTs(order.getTs());
						orderItem.setCreateTime(System.currentTimeMillis());
						orderItem.setNcId(applicationDetail.getNcId());
						orderItem.setPnCode(bean.getCode());
						orderItem.setGrossWeight(bean.getGrossweight());
						orderItem.setTareWeight(bean.getTareweight());
						orderItem.setGrossTime(DateUtil.parse(bean.getWeighttime(), DateUtil.Y_M_D_H_M_S));
						orderItem.setTareTime(DateUtil.parse(bean.getLighttime(), DateUtil.Y_M_D_H_M_S));
						orderItem.setVehicleNo(bean.getVehicleno());
						orderItemList.add(orderItem);
						join.setBillsum(applicationDetail.getSalessum());
						if (StringUtils.equals(billid, bean.getBillid())) {
							bean.setPutinwarehouseid(order.getId());
							bean.setPutinwarehousecode(order.getCode());
						}
						applicationDetailList.add(detail);
					}
//				} else {
//					//空车出厂
//					//不推单
//					//回写订单余量和未出库占用
//					SalesArrive sa = salesArriveMapper.selectByPrimaryKey(bean.getNoticeid());
//					double number = sa.getTakeamount();
//					if () {
//						
//					}
//					
//				}
				join.setTakeamount(bean.getPickupquantity());
				join.setState("1");
				join.setCreator(bean.getCreator());
				join.setCreatetime(System.currentTimeMillis());
				join.setModifier(bean.getModifier());
				join.setModifytime(System.currentTimeMillis());
				list.add(join);
			}
		}
		return applicationDetailList;
	}

	@Override
	public Result updateSerialNumber(PoundNoteQuery query) {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId()) && StringUtils.isNotBlank(query.getBatchnumberid())
				&& StringUtils.isNotBlank(query.getSerialnumber())) {
			PoundNote bean = new PoundNote();
			bean.setId(query.getId());
			bean.setBatchnumberid(query.getBatchnumberid());
			bean.setSerialnumber(query.getSerialnumber());
			bean.setModifier(query.getCurrId());
			bean.setModifytime(System.currentTimeMillis());
			if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Transactional
	@Override
	public Result salesRedcollide(PoundNoteQuery query) {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			PoundNote bean = new PoundNote();
			bean.setId(query.getId());
			bean.setRedcollide("1");
			bean.setModifier(query.getCurrId());
			bean.setModifytime(System.currentTimeMillis());
			if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Transactional
	@Override
	public Result savePoundNoteWeight(ApiPoundNoteQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getVehicleno()) && StringUtils.isNotBlank(query.getRfid())
				&& StringUtils.isNotBlank(query.getType()) && StringUtils.isNotBlank(query.getServicetype())
				&& StringUtils.isNotBlank(query.getNotionformcode()) && StringUtils.isNotBlank(query.getNumber())
				&& StringUtils.isNotBlank(query.getTime())) {
			switch (query.getServicetype()) {
			case "0":
				result = savePurchasePoundNote(query);
				break;
			case "1":
				result = savePurchaseReturnPoundNote(query);
				break;
			case "2":
				result = saveSalesPoundNote(query);
				break;
			case "4":
				result = saveCNDYPoundNote(query);
				break;
			case "5":
				result = saveOtherRKPoundNote(query);
				break;
			case "7":
				result = saveOtherCKPoundNote(query);
				break;
			case "9":
				result = saveOtherCKPoundNote(query);
				break;
			default:
				break;
			}
		}
		return result;
	}

	private Result savePurchaseReturnPoundNote(ApiPoundNoteQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		PurchaseArrive arrive = purchaseArriveMapper.selectByCode(query.getNotionformcode());
		PurchaseApplication application = purchaseApplicationMapper.selectByPrimaryKey(arrive.getBillid());
		PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper
				.selectByPrimaryKey(arrive.getBilldetailid());
		PoundNote bean = new PoundNote();
		// 皮重
		if (StringUtils.equals(query.getType(), "1")) {
			GetCodeReq codeReq = setPurchaseBeanBody(query, arrive, application, applicationDetail, bean);
			bean.setBilltype(Constant.NOTICE_OF_CGTH);
			// 更新通知单状态
			PurchaseArrive pa = new PurchaseArrive();
			pa.setId(arrive.getId());
			pa.setStatus("1");
			pa.setModifier(query.getCurrid());
			pa.setModifytime(System.currentTimeMillis());
			if (poundNoteMapper.insertSelective(bean) > 0
					&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(),
							ErrorCode.SYSTEM_SUCCESS.getCode())
					&& purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0) {
				result.setData(bean.getCode());
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		// 毛重
		} else {
			bean.setVehicleno(query.getVehicleno());
			bean.setVehiclerfid(arrive.getVehiclerfid());
			bean.setNoticecode(query.getNotionformcode());
			bean.setReturnstatus(Constant.POUND_PUSH_STATUS_NULL);
			bean.setState(Constant.DATA_VAILD);
			bean.setBilltype(Constant.NOTICE_OF_CGTH);
			List<PoundNote> list = poundNoteMapper.selectSelective(bean);
			if (CollectionUtils.isNotEmpty(list)) {
				bean = list.get(0);
				PurchaseArrive pArrive = purchaseArriveMapper.selectByPrimaryKey(bean.getNoticeid());
				PoundNote parentPoundNote = poundNoteMapper.selectByPrimaryKey(pArrive.getPoundnoteid());
				if(Double.parseDouble(query.getNetweight()) <= parentPoundNote.getNetweight()){
					// 毛重
					bean.setGrossweight(Double.parseDouble(query.getNumber()));
					bean.setNetweight(Double.parseDouble(query.getNetweight()));
					// 口杂
					bean.setDeductionother(Double.parseDouble(query.getDeductionother()));
					// 扣重
					bean.setDeductionweight(Double.parseDouble(query.getDeductionweight()));
					// 原发
					if (StringUtils.isNotBlank(query.getOriginalnetweight())) {
						bean.setOriginalnetweight(Double.parseDouble(query.getOriginalnetweight()));
					}
					bean.setWeighttime(DateUtil.parse(query.getTime(), "yyyy-MM-dd HH:mm:ss"));
					bean.setModifier(query.getCurrid());
					bean.setModifytime(System.currentTimeMillis());
					// 更新通知单状态
					PurchaseArrive pa = new PurchaseArrive();
					pa.setId(arrive.getId());
					pa.setStatus("2");
					pa.setModifier(query.getCurrid());
					pa.setModifytime(System.currentTimeMillis());
					// 生成入库单
					PurchaseStorageList storage = setPurchaseStorage(query.getCurrid(), application, bean);
					double item = Math.abs(Double.parseDouble(storage.getNtotalnum()));
					if (item > 0) {
					    storage.setNtotalnum("-" + item);
					}
					PurchaseStorageListItem storageItem = setPurchaseStorageItem(applicationDetail, bean, storage);
					item = Math.abs(Double.parseDouble(storageItem.getNunm()));
                    if (item > 0) {
                        storageItem.setNunm("-" + item);
                    }
                    item = Math.abs(Double.parseDouble(storageItem.getNshouldnum()));
                    if (item > 0) {
                        storageItem.setNshouldnum("-" + item);
                    }
					storage.setType(Constant.ZERO_STRING);
					bean.setPutinwarehouseid(storage.getId());
					bean.setPutinwarehousecode(storage.getCode());
					PurchaseApplicationDetail detail = new PurchaseApplicationDetail();
					detail.setId(applicationDetail.getId());
					detail.setPretendingtake(applicationDetail.getPretendingtake() + bean.getNetweight());
					detail.setStoragequantity(applicationDetail.getStoragequantity() - bean.getNetweight());
					detail.setMargin(applicationDetail.getMargin() + bean.getNetweight());
					if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0
							&& purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0
							&& purchaseApplicationDetailMapper.updateByPrimaryKeySelective(detail) > 0
							&& purchaseStorageListMapper.insertSelective(storage) > 0
							&& purchaseStorageListItemMapper.insertSelective(storageItem) > 0
							&& updateCode("RKD", query.getCurrid())) {
						ErrorCode ec = returnPurchaseStorage(storage, storageItem, bean);
						result.setErrorCode(ec);
						result.setData(bean.getCode());
					} else {
						result.setErrorCode(ErrorCode.OPERATE_ERROR);
					}
				}else{
					result.setErrorCode(ErrorCode.POUNDNOTE_RETURN_ERROR1);
				}
			}else{
				result.setErrorCode(ErrorCode.POUNDNOTE_NOT_EXIST);
			}
		}
		return result;
	}

	// 生成其他业务磅单
	private Result saveOtherRKPoundNote(ApiPoundNoteQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		OtherArrive arrive = otherArriveMapper.selectByCode(query.getNotionformcode());
		PoundNote bean = new PoundNote();
		// 皮重
		if (StringUtils.equals(query.getType(), "1")) {
			bean.setVehicleno(query.getVehicleno());

			// 获取rfid
			bean.setVehiclerfid(query.getRfid());
			bean.setNoticecode(query.getNotionformcode());
			bean.setReturnstatus("0");
			bean.setState("1");
			bean.setBilltype(query.getServicetype());

			List<PoundNote> list = poundNoteMapper.selectSelective(bean);
			if (CollectionUtils.isNotEmpty(list)) {
				bean = list.get(0);
				// 皮重
				bean.setTareweight(Double.parseDouble(query.getNumber()));
				bean.setNetweight(Double.parseDouble(query.getNetweight()));
				// 口杂
				bean.setDeductionother(Double.parseDouble(query.getDeductionother()));
				// 扣重
				bean.setDeductionweight(Double.parseDouble(query.getDeductionweight()));
				// 原发
				if (StringUtils.isNotBlank(query.getOriginalnetweight())) {
					bean.setOriginalnetweight(Double.parseDouble(query.getOriginalnetweight()));
				}
				bean.setLighttime(DateUtil.parse(query.getTime(), "yyyy-MM-dd HH:mm:ss"));
				bean.setModifier(query.getCurrid());
				bean.setModifytime(System.currentTimeMillis());
				// 更新通知单状态
				OtherArrive oa = new OtherArrive();
				oa.setId(arrive.getId());
				oa.setStatus("2");
				if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0
						&& otherArriveMapper.updateByPrimaryKeySelective(oa) > 0) {
					ErrorCode ec = ErrorCode.SYSTEM_SUCCESS;
					result.setErrorCode(ec);
					result.setData(bean.getCode());
				} else {
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
			// 毛重
		} else {
			GetCodeReq codeReq = setOtherBeanBody(query, arrive, bean);
			// 更新通知单状态
			OtherArrive pa = new OtherArrive();
			pa.setId(arrive.getId());
			pa.setStatus("1");
			if (poundNoteMapper.insertSelective(bean) > 0
					&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(),
							ErrorCode.SYSTEM_SUCCESS.getCode())
					&& otherArriveMapper.updateByPrimaryKeySelective(pa) > 0) {
				result.setData(bean.getCode());
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	private Result saveOtherCKPoundNote(ApiPoundNoteQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		OtherArrive arrive = otherArriveMapper.selectByCode(query.getNotionformcode());
		PoundNote bean = new PoundNote();
		// 毛重
		if (StringUtils.equals(query.getType(), "2")) {
			bean.setVehicleno(query.getVehicleno());

			// 获取rfid
			bean.setVehiclerfid(query.getRfid());
			bean.setNoticecode(query.getNotionformcode());
			bean.setReturnstatus("0");
			bean.setState("1");
			bean.setBilltype(query.getServicetype());

			List<PoundNote> list = poundNoteMapper.selectSelective(bean);
			if (CollectionUtils.isNotEmpty(list)) {
				bean = list.get(0);
				// 毛重
				bean.setGrossweight(Double.parseDouble(query.getNumber()));
				bean.setNetweight(Double.parseDouble(query.getNetweight()));
				// 口杂
				bean.setDeductionother(Double.parseDouble(query.getDeductionother()));
				// 扣重
				bean.setDeductionweight(Double.parseDouble(query.getDeductionweight()));
				// 原发
				if (StringUtils.isNotBlank(query.getOriginalnetweight())) {
					bean.setOriginalnetweight(Double.parseDouble(query.getOriginalnetweight()));
				}

				bean.setLighttime(DateUtil.parse(query.getTime(), "yyyy-MM-dd HH:mm:ss"));
				bean.setModifier(query.getCurrid());
				bean.setModifytime(System.currentTimeMillis());
				// 更新通知单状态
				OtherArrive oa = new OtherArrive();
				oa.setId(arrive.getId());
				oa.setStatus("2");
				if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0
						&& otherArriveMapper.updateByPrimaryKeySelective(oa) > 0) {
					ErrorCode ec = ErrorCode.SYSTEM_SUCCESS;
					result.setErrorCode(ec);
					result.setData(bean.getCode());
				} else {
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
			// 皮重
		} else {
			GetCodeReq codeReq = setOtherBeanBody(query, arrive, bean);
			// 更新通知单状态
			OtherArrive pa = new OtherArrive();
			pa.setId(arrive.getId());
			pa.setStatus("1");
			if (poundNoteMapper.insertSelective(bean) > 0
					&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(),
							ErrorCode.SYSTEM_SUCCESS.getCode())
					&& otherArriveMapper.updateByPrimaryKeySelective(pa) > 0) {
				result.setData(bean.getCode());
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	private Result saveCNDYPoundNote(ApiPoundNoteQuery query) throws Exception {
        Result result = Result.getSuccessResult();
        OtherArrive arrive = otherArriveMapper.selectByCode(query.getNotionformcode());
        long nowTime = System.currentTimeMillis();
        if (nowTime >= arrive.getStarttime() && nowTime < arrive.getEndtime()) {
            PoundNote bean = new PoundNote();
            // 皮重
            if (StringUtils.equals(query.getType(), Constant.ONE_STRING)) {
                bean.setVehicleno(query.getVehicleno());
                // 获取rfid
                bean.setVehiclerfid(query.getRfid());
                bean.setNoticecode(query.getNotionformcode());
                bean.setReturnstatus(Constant.ZERO_STRING);
                bean.setState(Constant.ONE_STRING);
                bean.setBilltype(query.getServicetype());
                bean.setNetweight(0D);

                List<PoundNote> list = poundNoteMapper.selectSelective(bean);

                Collections.sort(list,new Comparator<PoundNote>() {

                    @Override
                    public int compare(PoundNote o1, PoundNote o2) {
                        return (int) (o2.getMakebilltime()-o1.getMakebilltime());
                    }
                    
                });
//                Iterator<PoundNote> it = list.iterator();
//                while (it.hasNext()) {
//                    if (StringUtils.equals(it.next().getStatus(), Constant.THREE_STRING)) {
//                        it.remove();
//                    }
//                }
                if (CollectionUtils.isNotEmpty(list)) {
                    bean = list.get(0);
                    // 毛重
                    bean.setTareweight(Double.parseDouble(query.getNumber()));
                    bean.setNetweight(Double.parseDouble(query.getNetweight()));
                    bean.setLighttime(DateUtil.parse(query.getTime(), DateUtil.Y_M_D_H_M_S));
                    bean.setModifier(query.getCurrid());
                    bean.setModifytime(System.currentTimeMillis());
                    // 更新通知单状态
                    OtherArrive oa = new OtherArrive();
                    oa.setId(arrive.getId());
                    oa.setStatus(Constant.SIX_STRING);
                    if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0
                            && otherArriveMapper.updateByPrimaryKeySelective(oa) > 0) {
                        ErrorCode ec = ErrorCode.SYSTEM_SUCCESS;
                        result.setErrorCode(ec);
                        result.setData(bean.getCode());
                    } else {
                        result.setErrorCode(ErrorCode.OPERATE_ERROR);
                    }
                }
            // 毛重
            } else {
                GetCodeReq codeReq = setCNDYBody(query, arrive, bean);
                // 更新通知单状态
                OtherArrive pa = new OtherArrive();
                pa.setId(arrive.getId());
                pa.setStatus(Constant.ONE_STRING);
                if (poundNoteMapper.insertSelective(bean) > 0
                        && StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(),
                                ErrorCode.SYSTEM_SUCCESS.getCode())
                        && otherArriveMapper.updateByPrimaryKeySelective(pa) > 0) {
                    result.setData(bean.getCode());
                } else {
                    result.setErrorCode(ErrorCode.OPERATE_ERROR);
                }
            }
        } else {
            result.setErrorCode(ErrorCode.NOTICE_NOT_VALID_TIME);
        }
        return result;
    }
    
    private GetCodeReq setCNDYBody(ApiPoundNoteQuery query, OtherArrive arrive, PoundNote bean) throws Exception {
        bean.setId(UUIDUtil.getId());
        GetCodeReq codeReq = new GetCodeReq();
        codeReq.setCode("DR");
        codeReq.setCodeType(true);
        codeReq.setUserid(query.getCurrid());
        bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
        bean.setReturnstatus(Constant.ZERO_STRING);
        bean.setRedcollide(Constant.ZERO_STRING);
        bean.setStatus(Constant.ZERO_STRING);
        bean.setBilltype(query.getServicetype());
        bean.setMaindeduction(Constant.ZERO_STRING);
        bean.setNoticeid(arrive.getId());
        bean.setNoticecode(arrive.getCode());
        bean.setCutoverpartmentid(Constant.ORG_ID);
        bean.setCutoverpartmentname(Constant.ORG_NAME);
        if (StringUtils.isNotBlank(arrive.getEnteryard())) {
            YardManage yard = yardManageMapper.selectByPrimaryKey(arrive.getEnteryard());
            if (yard != null) {
                bean.setEnteryardid(yard.getId());
                bean.setEnteryardname(yard.getName());
            }
        }
        if (StringUtils.isNotBlank(arrive.getLeaveyard())) {
            YardManage yard = yardManageMapper.selectByPrimaryKey(arrive.getLeaveyard());
            if (yard != null) {
                bean.setLeaveyardid(yard.getId());
                bean.setLeaveyardname(yard.getName());
            }
        }
        if (StringUtils.isNotBlank(arrive.getMaterielid())) {
            MaterielManage materiel = materielManageMapper.selectByPrimaryKey(arrive.getMaterielid());
            if (materiel != null) {
                bean.setMaterialid(materiel.getId());
                bean.setMaterialname(materiel.getName());
            }
        }
        if (StringUtils.isNotBlank(arrive.getDriverid())) {
            DriverManage driver = driverManageMapper.selectByPrimaryKey(arrive.getDriverid());
            if (driver != null) {
                bean.setDriverid(driver.getId());
                bean.setDrivername(driver.getName());
                bean.setDriveridentityno(driver.getIdentityno());
            }
        }
        bean.setVehicleid(arrive.getVehicleid());
        bean.setVehicleno(query.getVehicleno());
        bean.setVehiclerfid(query.getRfid());
        bean.setGrossweight(Double.parseDouble(query.getNumber()));
        bean.setWeighttime(DateUtil.parse(query.getTime(), DateUtil.Y_M_D_H_M_S));
        bean.setMakerid(query.getCurrid());
        SystemUserResp user = systemUserService.getUser(query.getCurrid());
        if (user != null) {
            bean.setMakebillname(user.getName());
        }
        bean.setMakebilltime(System.currentTimeMillis());
        bean.setState(Constant.ONE_STRING);
        bean.setCreator(query.getCurrid());
        bean.setCreatetime(System.currentTimeMillis());
        bean.setModifier(query.getCurrid());
        bean.setModifytime(System.currentTimeMillis());
        return codeReq;
    }

	private GetCodeReq setOtherBeanBody(ApiPoundNoteQuery query, OtherArrive arrive, PoundNote bean) throws Exception {
		bean.setId(UUIDUtil.getId());
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode("CK");
		codeReq.setCodeType(true);
		codeReq.setUserid(query.getCurrid());
		bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
		bean.setReturnstatus("0");
		bean.setRedcollide("0");
		bean.setStatus("0");
		bean.setBilltype(query.getServicetype());
		bean.setMaindeduction("0");
		bean.setNoticeid(arrive.getId());
		bean.setNoticecode(arrive.getCode());
		if (StringUtils.isNotBlank(arrive.getSupplierid())) {
			SupplierManage supplier = supplierManageMapper.selectByPrimaryKey(arrive.getSupplierid());
			if (supplier != null) {
				bean.setSupplierid(supplier.getId());
				bean.setSuppliername(supplier.getName());
			}
		}
		if (StringUtils.isNotBlank(arrive.getCustomerid())) {
			CustomerManage customer = customerManageMapper.selectByPrimaryKey(arrive.getCustomerid());
			if (customer != null) {
				bean.setCustomerid(customer.getId());
				bean.setCustomername(customer.getName());
			}
		}
		bean.setDepartment(arrive.getDatasource());
		if (StringUtils.isNotBlank(arrive.getWarehouseid())) {
			WarehouseManage warehouse = warehouseManageMapper.selectByPrimaryKey(arrive.getWarehouseid());
			bean.setWarehouseid(warehouse.getId());
			bean.setWarehousename(warehouse.getName());
		}
		if (StringUtils.isNotBlank(arrive.getMaterielid())) {
			MaterielManage materiel = materielManageMapper.selectByPrimaryKey(arrive.getMaterielid());
			if (materiel != null) {
				bean.setMaterialid(materiel.getId());
				bean.setMaterialname(materiel.getName());
			}
		}
		bean.setCargo(arrive.getCargo());
		if (StringUtils.isNotBlank(arrive.getDriverid())) {
			DriverManage driver = driverManageMapper.selectByPrimaryKey(arrive.getDriverid());
			if (driver != null) {
				bean.setDriverid(driver.getId());
				bean.setDrivername(driver.getName());
				bean.setDriveridentityno(driver.getIdentityno());
			}
		}
		bean.setVehicleid(arrive.getVehicleid());
		bean.setVehicleno(query.getVehicleno());
		bean.setVehiclerfid(query.getRfid());
		bean.setReceivedepartmentid(Constant.ORG_ID);
		bean.setReceivedepartmentname(Constant.ORG_NAME);
		bean.setOriginalnetweight(arrive.getCount());
		if (StringUtils.equals(query.getType(), "1")) {
			bean.setTareweight(Double.parseDouble(query.getNumber()));
			bean.setLighttime(DateUtil.parse(query.getTime(), "yyyy-MM-dd HH:mm:ss"));
		} else {
			bean.setGrossweight(Double.parseDouble(query.getNumber()));
			bean.setWeighttime(DateUtil.parse(query.getTime(), "yyyy-MM-dd HH:mm:ss"));
		}
		bean.setMakerid(query.getCurrid());
		SystemUserResp user = systemUserService.getUser(query.getCurrid());
		if (user != null) {
			bean.setMakebillname(user.getName());
		}
		bean.setMakebilltime(System.currentTimeMillis());
		bean.setState("1");
		bean.setCreator(query.getCurrid());
		bean.setCreatetime(System.currentTimeMillis());
		bean.setModifier(query.getCurrid());
		bean.setModifytime(System.currentTimeMillis());
		return codeReq;
	}

	// 生成采购磅单
	private Result savePurchasePoundNote(ApiPoundNoteQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		PurchaseArrive arrive = purchaseArriveMapper.selectByCode(query.getNotionformcode());
		PurchaseApplication application = purchaseApplicationMapper.selectByPrimaryKey(arrive.getBillid());
		PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper
				.selectByPrimaryKey(arrive.getBilldetailid());
		PoundNote bean = new PoundNote();
		// 皮重
		if (StringUtils.equals(query.getType(), "1")) {
			bean.setVehicleno(query.getVehicleno());
			bean.setVehiclerfid(arrive.getVehiclerfid());
			bean.setNoticecode(query.getNotionformcode());
			bean.setReturnstatus("0");
			bean.setState("1");
			bean.setBilltype("0");
			List<PoundNote> list = poundNoteMapper.selectSelective(bean);
			if (CollectionUtils.isNotEmpty(list)) {
				bean = list.get(0);
				// 皮重
				bean.setTareweight(Double.parseDouble(query.getNumber()));
				// 口杂
				bean.setDeductionother(Double.parseDouble(query.getDeductionother()));
				// 扣重
				bean.setDeductionweight(Double.parseDouble(query.getDeductionweight()));
				// 原发
				if (StringUtils.isNotBlank(query.getOriginalnetweight())) {
					bean.setOriginalnetweight(Double.parseDouble(query.getOriginalnetweight()));
				}
				// 净重
				bean.setNetweight(Double.parseDouble(query.getNetweight()));
				bean.setLighttime(DateUtil.parse(query.getTime(), "yyyy-MM-dd HH:mm:ss"));
				bean.setModifier(query.getCurrid());
				bean.setModifytime(System.currentTimeMillis());
				// 更新通知单状态
				PurchaseArrive pa = new PurchaseArrive();
				pa.setId(arrive.getId());
				pa.setStatus(Constant.TWO_STRING);
				pa.setModifier(query.getCurrid());
				pa.setModifytime(System.currentTimeMillis());
				// 生成入库单
				PurchaseStorageList storage = null;
				PurchaseStorageListItem storageItem = null;
				PurchaseApplicationDetail detail = null;
				if(arrive.getSignStatus() == Constant.ONE_NUMBER) {
					storage = setPurchaseStorage(query.getCurrid(), application, bean);
					storageItem = setPurchaseStorageItem(applicationDetail, bean, storage);
					bean.setPutinwarehouseid(storage.getId());
					bean.setPutinwarehousecode(storage.getCode());
					detail = new PurchaseApplicationDetail();
					detail.setId(applicationDetail.getId());
					detail.setStoragequantity(applicationDetail.getStoragequantity() + bean.getNetweight());
					detail.setUnstoragequantity(applicationDetail.getUnstoragequantity() - arrive.getArrivalamount());
					detail.setMargin(applicationDetail.getMargin() + (arrive.getArrivalamount() - bean.getNetweight()));
				} else {
					detail = new PurchaseApplicationDetail();
					detail.setId(applicationDetail.getId());
					detail.setUnstoragequantity(applicationDetail.getUnstoragequantity() - arrive.getArrivalamount());
					detail.setMargin(applicationDetail.getMargin() + arrive.getArrivalamount());
				}
				if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0
						&& purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0) {
				    result.setData(bean.getCode());
				    if (detail != null) {
				    	purchaseApplicationDetailMapper.updateByPrimaryKeySelective(detail);
				    }
					if (storage != null && storageItem != null) {
						if(purchaseStorageListMapper.insertSelective(storage) > 0
								&& purchaseStorageListItemMapper.insertSelective(storageItem) > 0
								&& updateCode("RKD", query.getCurrid())){
							result.setErrorCode(returnPurchaseStorage(storage, storageItem, bean));
				            LoggerFactory.getLogger("poundNote").info("上传皮重榜单:上传成功 {} ", JSON.toJSONString(query.getVehicleno()));
						}else{
							result.setErrorCode(ErrorCode.OPERATE_ERROR);
						}
					}else{
						result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					}
				} else {
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.POUNDNOTE_NOT_EXIST);
			}
			// 毛重
		} else {
			GetCodeReq codeReq = setPurchaseBeanBody(query, arrive, application, applicationDetail, bean);
			// 更新通知单状态
			PurchaseArrive pa = new PurchaseArrive();
			pa.setId(arrive.getId());
			pa.setStatus("1");
			pa.setModifier(query.getCurrid());
			pa.setModifytime(System.currentTimeMillis());
			if (poundNoteMapper.insertSelective(bean) > 0
					&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(),
							ErrorCode.SYSTEM_SUCCESS.getCode())
					&& purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0) {
				result.setData(bean.getCode());
                LoggerFactory.getLogger("poundNote").info("上传毛重榜单:上传成功  {}", JSON.toJSONString(query.getVehicleno()));
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	// 推送采购入库单
	private ErrorCode returnPurchaseStorage(PurchaseStorageList storage, PurchaseStorageListItem storageItem, PoundNote pn) throws Exception {
		ErrorCode ec = ErrorCode.OPERATE_ERROR;
		List<PurchaseStorageList> list = new ArrayList<PurchaseStorageList>();
		list.add(storage);
		List<PurchaseStorageListItem> itemList = new ArrayList<PurchaseStorageListItem>();
		itemList.add(storageItem);
		storage.setList(itemList);
		ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(list),
				Constant.URL_DOMAIN + Constant.URL_RETURN_PURCHASESTORAGEATION);
		// 调用dc 接口成功 则推单状态为推单中 榜单展示为推单中
		PushSingleReq ps = new PushSingleReq();
		ps.setId(UUIDUtil.getId());
		ps.setRequisitionNum(pn.getBillcode());
		ps.setNoticeNum(pn.getNoticecode());
		ps.setRequisitionType(Constant.ONE_STRING);
		ps.setLightCarTime(pn.getLighttime());
		ps.setHeavyCarTime(pn.getWeighttime());
		ps.setNetWeight(pn.getNetweight().toString());
		ps.setCreator(pn.getModifier());
		ps.setCreatetime(System.currentTimeMillis());
		ps.setModifytime(System.currentTimeMillis());
		ps.setDesc2(Constant.ZERO_STRING);
		if (apiResult != null) {
            if (StringUtils.equals(apiResult.getCode(), Constant.SUCCESS)) {
                PurchaseStorageList storageUpdate = new PurchaseStorageList();
                storageUpdate.setId(storage.getId());
                storageUpdate.setStatus(Constant.PUSH_STATUS_ING);
                purchaseStorageListMapper.updateByPrimaryKeySelective(storageUpdate);
                pn.setId(storage.getPoundId());
                pn.setReturnstatus(Constant.POUND_PUSH_STATUS_ING);
                poundNoteMapper.updateByPrimaryKeySelective(pn);
                ec = ErrorCode.SYSTEM_SUCCESS;
                ps.setPushStatus(Constant.ONE_STRING);
            } else {
                ec = ErrorCode.RETURN_ERROR;
                ps.setPushStatus(Constant.THREE_STRING);
            }
            ps.setReasonFailure(apiResult.getError());
            ps.setDesc1(apiResult.getCode());
            LoggerFactory.getLogger("ncPush").info("入库单推送NC: {}", JSON.toJSONString(apiResult));
		} else {
		    LoggerFactory.getLogger("ncPush").info("入库单推送NC: {}", "连接超时。");
		    ec = ErrorCode.CONNECTION_TIMEOUT_ERROR;
		    ps.setPushStatus(Constant.THREE_STRING);
		    ps.setReasonFailure("FC-DC到货单推单失败，连接超时。");
		    ps.setDesc1("-1");
		}
		pushSingleService.savePushSingle(ps);
		return ec;
	}

	// 初始化采购入库单字表
	private PurchaseStorageListItem setPurchaseStorageItem(PurchaseApplicationDetail applicationDetail, PoundNote bean,
			PurchaseStorageList storage) {
		PurchaseStorageListItem storageItem = new PurchaseStorageListItem();
		storageItem.setId(UUIDUtil.getId());
		storageItem.setPurchaseStorageListId(storage.getId());
		storageItem.setPkOrg(storage.getPkOrg());
		if (applicationDetail != null) {
			storageItem.setCmaterialoid(applicationDetail.getMaterielid());
			storageItem.setCastunitid(applicationDetail.getUnit());
			storageItem.setNunm("" + bean.getNetweight());
			storageItem.setNcId(applicationDetail.getId());
		}
		storageItem.setNshouldnum("" + bean.getOriginalnetweight());
		storageItem.setPkCreqwareid(bean.getWarehouseid());
		storageItem.setTs(storage.getTs());
		storageItem.setCreateTime(System.currentTimeMillis());
		storageItem.setPnCode(bean.getCode());
		storageItem.setGrossWeight(bean.getGrossweight());
		storageItem.setTareWeight(bean.getTareweight());
		storageItem.setGrossTime(DateUtil.parse(bean.getWeighttime(), DateUtil.Y_M_D_H_M_S));
		storageItem.setTareTime(DateUtil.parse(bean.getLighttime(), DateUtil.Y_M_D_H_M_S));
		storageItem.setVehicleNo(bean.getVehicleno());
		return storageItem;
	}

	// 初始化采购入库单
	private PurchaseStorageList setPurchaseStorage(String userId, PurchaseApplication pa, PoundNote pn)
			throws Exception {
		PurchaseStorageList storage = new PurchaseStorageList();
		storage.setId(UUIDUtil.getId());
		storage.setCode(getCode("RKD", userId, true));
		storage.setNcId(pa.getId());
		storage.setPoundId(pn.getId());
		storage.setType(Constant.ONE_STRING);
		if (pa != null) {
			storage.setPkOrg(pa.getOrgid());
			storage.setCdptid(pa.getDepartmentid());
			storage.setCvendorid(pa.getSupplierid());
		}
		storage.setNtotalnum(String.valueOf(pn.getNetweight()));
		storage.setDbilldate(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		storage.setBillmaker(pn.getMakerid());
		storage.setCreationtime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		storage.setTs(storage.getCreationtime());
		storage.setStatus(Constant.PUSH_STATUS_NULL);
		return storage;
	}

	private GetCodeReq setPurchaseBeanBody(ApiPoundNoteQuery query, PurchaseArrive arrive,
			PurchaseApplication application, PurchaseApplicationDetail applicationDetail, PoundNote bean)
					throws Exception {
		bean.setId(UUIDUtil.getId());
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode("RK");
		codeReq.setCodeType(true);
		codeReq.setUserid(query.getCurrid());
		bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
		bean.setReturnstatus(Constant.POUND_PUSH_STATUS_NULL);
		bean.setRedcollide(Constant.ZERO_STRING);
		bean.setStatus(Constant.ZERO_STRING);
		bean.setBilltype(Constant.NOTICE_OF_CGDH);
		bean.setBillid(arrive.getBillid());
		bean.setBillcode(arrive.getBillcode());
		bean.setBilldetailid(applicationDetail.getId());
		bean.setMaindeduction(Constant.ZERO_STRING);
		bean.setNoticeid(arrive.getId());
		bean.setNoticecode(arrive.getCode());
		bean.setDriverid(arrive.getDriverid());
		bean.setDrivername(arrive.getDrivername());
		bean.setDriveridentityno(arrive.getDriveridentityno());
		bean.setVehicleid(arrive.getVehicleid());
		bean.setVehicleno(arrive.getVehicleno());
		bean.setVehiclerfid(query.getRfid());
		bean.setReceivedepartmentid(Constant.ORG_ID);
		bean.setReceivedepartmentname(Constant.ORG_NAME);
		bean.setMinemouthid(application.getMinemouthid());
		bean.setMinemouthname(application.getMinemouthname());
		bean.setPickupquantity(arrive.getArrivalamount());
		if (application != null) {
		    bean.setSupplierid(application.getSupplierid());
		    bean.setSuppliername(application.getSuppliername());
		}
		if (StringUtils.equals(query.getType(), "1")) {
			bean.setTareweight(Double.parseDouble(query.getNumber()));
			bean.setLighttime(DateUtil.parse(query.getTime(), "yyyy-MM-dd HH:mm:ss"));
		} else {
			bean.setGrossweight(Double.parseDouble(query.getNumber()));
			bean.setWeighttime(DateUtil.parse(query.getTime(), "yyyy-MM-dd HH:mm:ss"));
		}
		bean.setMakerid(query.getCurrid());
		SystemUserResp user = systemUserService.getUser(query.getCurrid());
		if (user != null) {
			bean.setMakebillname(user.getName());
		}
		bean.setMakebilltime(System.currentTimeMillis());
		bean.setState("1");
		bean.setCreator(query.getCurrid());
		bean.setCreatetime(System.currentTimeMillis());
		bean.setModifier(query.getCurrid());
		bean.setModifytime(System.currentTimeMillis());
		//2017/11/18客商APP测试发现
		bean.setMaterialid(applicationDetail.getMaterielid());
		bean.setMaterialname(applicationDetail.getMaterielname());
		return codeReq;
	}

	private Result saveSalesPoundNote(ApiPoundNoteQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		SystemUser user = systemUserMapper.selectByPrimaryKey(query.getCurrid());
		if (user != null) {
			if (user.getIslock() == BusinessConstants.USER_UNLOCK_BYTE) {
				if (user.getIsvalid() == BusinessConstants.USER_VALID_BYTE) {
					SalesArrive notice = salesArriveMapper.selectByCode(query.getNotionformcode());
					if (notice != null) {
						PoundNote pn = poundNoteMapper.selectByNoticeId(notice.getId());
						// 皮重
						if (StringUtils.equals(query.getType(), Constant.ONE_STRING)) {
							if (pn == null) {
								result = salesTareWeight(query, user, notice);
							} else {
								result.setErrorCode(ErrorCode.NOTICE_YES_POUNDNOTE);
							}
						// 毛重
						} else {
							if (pn != null) {
								pn.setGrossweight(Double.parseDouble(query.getNumber()));
								pn.setWeighttime(DateUtil.parse(query.getTime(), DateUtil.Y_M_D_H_M_S));
								pn.setNetweight(Double.valueOf(query.getNetweight()));
								pn.setDeductionweight(Double.valueOf(query.getDeductionweight()));
								pn.setDeductionother(Double.valueOf(query.getDeductionother()));
								pn.setOriginalnetweight(Double.valueOf(query.getOriginalnetweight()));
								pn.setModifier(user.getId());
								pn.setModifytime(System.currentTimeMillis());
								poundNoteMapper.updateByPrimaryKeySelective(pn);
								notice.setStatus(Constant.TWO_STRING);
								salesArriveMapper.updateByPrimaryKeySelective(notice);
								//回写订单未出库占用、出库占用、余量 并 生成出库单
								callBackBill(user, notice, pn);
								result.setData(pn.getCode());
								result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
							} else {
								result.setErrorCode(ErrorCode.NOTICE_NOT_ONE_POUNDNOTE);
							}
						}
					} else {
						result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
					}
				} else {
					result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR5);
				}
			} else {
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR3);
			}
		} else {
			result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR17);
		}
		return result;
	}
	//保存皮重记录
	private Result salesTareWeight(ApiPoundNoteQuery query, SystemUser user, SalesArrive notice) throws Exception {
		Result result = Result.getSuccessResult();
		SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(notice.getBillid());
		SalesApplicationDetail sad = salesApplicationDetailMapper.selectByPrimaryKey(notice.getBilldetailid());
		PoundNote bean = new PoundNote();
		bean.setId(UUIDUtil.getId());
		bean.setCode(getCode("CK", user.getId(), true));
		bean.setReturnstatus(Constant.ZERO_STRING);
		bean.setRedcollide(Constant.ZERO_STRING);
		bean.setStatus(Constant.ZERO_STRING);
		bean.setBilltype(Constant.NOTICE_OF_XSDH);
		bean.setBillid(sa.getId());
		bean.setBillcode(sa.getCode());
		bean.setBilldetailid(sad.getId());
		bean.setMaindeduction(Constant.ZERO_STRING);
		bean.setNoticeid(notice.getId());
		bean.setNoticecode(notice.getCode());
		bean.setSenddepartmentid(Constant.ORG_ID);
		bean.setSenddepartmentname(Constant.ORG_NAME);
		bean.setDriverid(notice.getDriverid());
		bean.setDrivername(notice.getDrivername());
		bean.setDriveridentityno(notice.getDriveridentityno());
		bean.setVehicleid(notice.getVehicleid());
		bean.setVehicleno(notice.getVehicleno());
		bean.setVehiclerfid(notice.getVehiclerfid());
		bean.setPickupquantity(notice.getTakeamount());
		bean.setTareweight(Double.parseDouble(query.getNumber()));
		bean.setLighttime(DateUtil.parse(query.getTime(), DateUtil.Y_M_D_H_M_S));
		bean.setMakerid(query.getCurrid());
		bean.setMakebillname(user.getName());
		bean.setMakebilltime(System.currentTimeMillis());
		bean.setCustomerid(sa.getCustomerid());
		bean.setCustomername(sa.getCustomername());
		bean.setMaterialid(sad.getMaterielid());
		bean.setMaterialname(sad.getMaterielname());
		bean.setState(Constant.ONE_STRING);
		bean.setCreator(query.getCurrid());
		bean.setCreatetime(System.currentTimeMillis());
		poundNoteMapper.insertSelective(bean);
		updateCode("CK", user.getId());
		notice.setStatus(Constant.ONE_STRING);
		salesArriveMapper.updateByPrimaryKeySelective(notice);
		result.setData(bean.getCode());
		return result;
	}
	//回写订单未出库占用、出库占用、余量
	private void callBackBill(SystemUser user, SalesArrive notice, PoundNote pn) throws Exception {
		boolean flag = false;
		List<ExceptionAudit> exceptionAuditList = exceptionAuditMapper.listByPnId(Constant.ONE_STRING, pn.getId());
		if (CollectionUtils.isNotEmpty(exceptionAuditList)) {
			flag = true;
		}
		//回写订单未出库占用、出库占用、余量 并 生成出库单
		List<SalesApplicationArrive> listJoin = salesApplicationArriveMapper.listByNoticeId(notice.getId());
		double number = pn.getNetweight();
		for (SalesApplicationArrive join : listJoin) {
			SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(join.getBillId());
			SalesApplicationDetail sad = salesApplicationDetailMapper.selectByPrimaryKey(join.getBillDetailId());
			//空车出厂不生成出库单，并走 else 回写未出库占用和余量
			if (number > 0 && !flag) {
				double nnum = 0D;
				if (number > join.getNumber()) {
					nnum = join.getNumber();
				} else {
					nnum = number;
				}
				//回写未出库占用、出库占用、余量
				sad.setUnstoragequantity(sad.getUnstoragequantity() - join.getNumber());
				sad.setStoragequantity(sad.getStoragequantity() + nnum);
				sad.setMargin(sad.getMargin() + join.getNumber() - nnum);
				//生成出库单
				addSalesOutBound(user, notice, pn, sa, sad, nnum);
			} else {
				//回写未出库占用、出库占用、余量
				sad.setUnstoragequantity(sad.getUnstoragequantity() - join.getNumber());
				sad.setMargin(sad.getMargin() + join.getNumber());
			}
			salesApplicationDetailMapper.updateByPrimaryKeySelective(sad);
			number -= join.getNumber();
		}
	}
	
	//生成出库单
	private void addSalesOutBound(SystemUser user, SalesArrive notice, PoundNote pn, SalesApplication sa,
			SalesApplicationDetail sad, double nnum) throws Exception {
		SalesOutboundOrder order = new SalesOutboundOrder();
		order.setId(UUIDUtil.getId());
		order.setCode(getCode("A6XC", user.getId(), true));
		order.setNcId(sa.getNcId());
		order.setPoundId(pn.getId());
		order.setBilldate(DateUtil.parse(sa.getBilltime(), DateUtil.Y_M_D_H_M_S));
		order.setPkOrg(sa.getOrgid());
		order.setCdptid(sa.getDepartmentid());
		order.setCbizid(sa.getSalesmanid());
		order.setCdilivertypeid(sa.getTransportcompanyid());
		order.setBillmaker(user.getId());
		order.setDmakedate(DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S));
		order.setTs(DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S));
		order.setStatus(Constant.ZERO_STRING);
		order.setCreateTime(System.currentTimeMillis());
		salesOutboundOrderMapper.insertSelective(order);
		updateCode("A6XC", user.getId());
		
		SalesOutboundOrderItem orderItem = new SalesOutboundOrderItem();
		orderItem.setId(UUIDUtil.getId());
		orderItem.setSaleOutboundOrderId(order.getId());
		orderItem.setCmaterialoid(sad.getMaterielid());
		orderItem.setCunitid(sad.getUnit());
		orderItem.setNnum(String.valueOf(nnum));
		orderItem.setTs(order.getTs());
		orderItem.setCreateTime(System.currentTimeMillis());
		orderItem.setNcId(sad.getNcId());
		orderItem.setPnCode(pn.getCode());
		orderItem.setGrossWeight(pn.getGrossweight());
		orderItem.setTareWeight(pn.getTareweight());
		orderItem.setGrossTime(DateUtil.parse(pn.getWeighttime(), DateUtil.Y_M_D_H_M_S));
		orderItem.setTareTime(DateUtil.parse(pn.getLighttime(), DateUtil.Y_M_D_H_M_S));
		orderItem.setVehicleNo(notice.getVehicleno());
		salesOutboundOrderItemMapper.insertSelective(orderItem);
		pushPnToDc(pn, order, orderItem);
	}

	private void pushPnToDc(PoundNote pn, SalesOutboundOrder order, SalesOutboundOrderItem orderItem) throws Exception {
		List<SalesOutboundOrder> orderList = new ArrayList<SalesOutboundOrder>();
		List<SalesOutboundOrderItem> orderItemList = new ArrayList<SalesOutboundOrderItem>();
		orderItemList.add(orderItem);
		order.setList(orderItemList);
		orderList.add(order);
		ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(orderList), Constant.URL_DOMAIN + Constant.URL_RETURN_SALESOUTBOUNDCATION);
		PushSingleReq ps = new PushSingleReq();
		ps.setId(UUIDUtil.getId());
		ps.setRequisitionNum(pn.getBillcode());
		ps.setNoticeNum(pn.getNoticecode());
		ps.setRequisitionType(Constant.ONE_STRING);
		ps.setLightCarTime(pn.getLighttime());
		ps.setHeavyCarTime(pn.getWeighttime());
		ps.setNetWeight(pn.getNetweight().toString());
		ps.setCreatetime(System.currentTimeMillis());
		ps.setModifytime(System.currentTimeMillis());
		ps.setDesc2(Constant.ONE_STRING);
		if (apiResult != null) {
			if (StringUtils.equals(apiResult.getCode(), Constant.SUCCESS)) {
				SalesOutboundOrder orderUpdate = new SalesOutboundOrder();
		        orderUpdate.setId(order.getId());
		        orderUpdate.setStatus(Constant.ONE_STRING);
		        if (salesOutboundOrderMapper.updateByPrimaryKeySelective(orderUpdate) > 0) {
		            pn.setId(order.getPoundId());
		            pn.setReturnstatus(Constant.ONE_STRING);
		            poundNoteMapper.updateByPrimaryKeySelective(pn);
		        }
			} else {
				ps.setPushStatus(Constant.THREE_STRING);
			}
			ps.setReasonFailure(apiResult.getError());
		} else {
			ps.setPushStatus(Constant.THREE_STRING);
			ps.setReasonFailure("FC-DC出库单推单失败，连接超时。");
			ps.setDesc1("-1");
		}
		pushSingleService.savePushSingle(ps);
	}

	@Override
	public Result validation(ApiPoundNoteValidation valid) {
		Result result = Result.getParamErrorResult();
		if (valid != null && StringUtils.isNotBlank(valid.getVehicleno()) && StringUtils.isNotBlank(valid.getRfid())
				&& StringUtils.isNotBlank(valid.getType()) && StringUtils.isNotBlank(valid.getCurrid())) {
			VehicleManage vehicle = vehicleManageMapper.getByVehicleNoAndRfid(valid.getVehicleno(), valid.getRfid());
			if (vehicle != null) {
				valid.setVehicleId(vehicle.getId());
				if (StringUtils.equals(valid.getType(), "1")) {
					result = validOneWeight(valid);
				} else {
					result = validTwoWeight(valid);
				}
			} else {
				result.setErrorCode(ErrorCode.RFID_VEHICLE_NOT_EXIST);
			}
		}
		return result;
	}

	/**
	 * @Description 一次过磅验证
	 * @author zhanggaohao
	 * @version 2017年3月27日 上午10:41:25
	 * @param valid
	 */
	private Result validOneWeight(ApiPoundNoteValidation valid) {
		Result result = Result.getErrorResult();
		SalesArrive sales = salesArriveMapper.getByVehicleId(valid.getVehicleId());
		if (sales == null) {
			PurchaseArrive purchase = purchaseArriveMapper.getByVehicleId(valid.getVehicleId());
			if (purchase == null) {
				// 检测其他业务中时候有通知单
				OtherArrive otherArrive = otherArriveMapper.getByVehicleId(valid.getVehicleId());
				if (otherArrive == null) {
					// 该车辆没有通知单
					result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
				} else {
				    if (StringUtils.equals(otherArrive.getStatus(), "6")) {
                        if (!StringUtils.equals(otherArrive.getBusinesstype(), Constant.FOUR_STRING)) {
                        	result = validNoticeInfoAccessRecord(otherArrive.getId());
                        } else {
                            //厂内倒运
                            if (StringUtils.equals(otherArrive.getAuditstatus(), Constant.ONE_STRING)) {
                                result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                            } else {
                                result.setErrorCode(ErrorCode.NOTICE_NOT_AUDIT);
                            }
                        }
				    } else {
				        result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ENTER);
				    }
				}
			} else {
				if (StringUtils.equals(purchase.getStatus(), Constant.SIX_STRING)) {
					result = validNoticeInfoAccessRecord(purchase.getId());
				} else {
					result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ENTER);
				}
			}
		} else {
			if (StringUtils.equals(sales.getStatus(), Constant.SIX_STRING)) {
				result = validNoticeInfoAccessRecord(sales.getId());
			} else {
				result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ENTER);
			}
		}
		return result;
	}

	// 检验通知单是否有入厂门禁
	private Result validNoticeInfoAccessRecord(String noticeId) {
		Result result = Result.getSuccessResult();
		AccessRecord access = accessRecordMapper.selectByNoticeId(noticeId);
		if (access != null) {
			if (StringUtils.equals(access.getAccesstype(), Constant.ONE_STRING)) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.NOTICE_OUT_FACTORY);
			}
		} else {
			result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ACCESSRECORD);
		}
		return result;
	}

	/**
	 * @Description 二次过磅验证
	 * @author zhanggaohao
	 * @version 2017年3月27日 上午10:41:36
	 * @param valid
	 */
	private Result validTwoWeight(ApiPoundNoteValidation valid) {
		Result result = Result.getErrorResult();
		SalesArrive sales = salesArriveMapper.getByVehicleId(valid.getVehicleId());
		if (sales == null) {
			PurchaseArrive purchase = purchaseArriveMapper.getByVehicleId(valid.getVehicleId());
			if (purchase == null) {
				// 检测其他业务中时候有通知单
				OtherArrive otherArrive = otherArriveMapper.getByVehicleId(valid.getVehicleId());
				if (otherArrive == null) {
					// 该车辆没有通知单
					result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
				} else {
					//如果是其他入库 需要判断状态为收货确认 
					if(StringUtils.equals(otherArrive.getBusinesstype(), Constant.NOTICE_OF_QTRK) ){
						//判断是否为收货状态
						if (StringUtils.equals(otherArrive.getStatus(), Constant.SEVEN_STRING)) {
							result = isOneWeight(otherArrive.getId(), otherArrive.getBusinesstype());
						} else {
							result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_LOAD2);
						}
					//工程车辆 其他出 内倒
					}else{
						//判断是否为一次磅
						if (StringUtils.equals(otherArrive.getStatus(), Constant.ONE_STRING)) {
							result = isOneWeight(otherArrive.getId(), otherArrive.getBusinesstype());
						} else {
							result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_LOAD2);
						}
					}
				}
			} else {
		        //二次过磅校验变更为是否签收或退货  2017-09-06
				if (StringUtils.equals(purchase.getStatus(), Constant.SEVEN_STRING)) {
					result = isOneWeight(purchase.getId(), purchase.getType());
				} else {
					result.setErrorCode(ErrorCode.NOTICE_NOT_SIGN);
				}
			}
		} else {
			result = SalesTwoValidation(sales);
		}
		return result;
	}
	
	private Result SalesTwoValidation(SalesArrive sales) {
		Result result = Result.getSuccessResult();
		PoundNote poundNote = poundNoteMapper.getNewByNoticeId(sales.getId());
		if (poundNote != null ) {
			List<ExceptionAudit> list = exceptionAuditMapper.listByPnId(null, poundNote.getId());
			if (CollectionUtils.isNotEmpty(list)) {
				boolean flag = false;
				Integer type = null;
				for (ExceptionAudit ea : list) {
					//跳过垃圾数据 状态为2,3的
					if( ea.getType()==2 || ea.getType()==3 ){
						continue;
					}
					if (!ea.getAuditStatus()) {
						flag = true;
						type = ea.getType();
						break;
					}
				}
				if (flag) {
					result.setCode("-1");
					result.setError("异常单据：" + ExceptionAuditEnum.getName(type) + "，未审批。");
				}
			} else {
				if (!StringUtils.equals(sales.getStatus(), Constant.SEVEN_STRING)) {
					result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_LOAD);
				}
			}
		} else {
			result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONE_WEIGHT);
		}
		return result;
	}

	//判断是否有榜单
	private Result isOneWeight(String noticeid, String type) {
		Result result = Result.getSuccessResult();
		PoundNote poundNote = poundNoteMapper.getNewByNoticeId(noticeid);
		if (poundNote != null ) {
            result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONE_WEIGHT);
		}
		return result;
	}

	@Override
	public Result tareWeight(ApiPoundNoteQuery valid) {
		Result result = Result.getParamErrorResult();
		if (valid != null && StringUtils.isNotBlank(valid.getVehicleno()) && StringUtils.isNotBlank(valid.getRfid())
				&& valid.getLimit() != null && valid.getLimit() > 0) {
			List<Double> list = poundNoteMapper.historyTareWeight(valid);
			result.setData(list);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	@Override
	public PaginationVO<AppPoundOrderResp> appToPage(AppPoundOrderReq req) {
		PaginationVO<AppPoundOrderResp> page = null;
		if (req != null) {
			if (StringUtils.equals(req.getIdentityTypes(), Constant.USER_SUPPLIER)) {
				page = appPurchasePage(req);
			}
			if (StringUtils.equals(req.getIdentityTypes(), Constant.USER_CUSTOMER)) {
				page = appSalesPage(req);
			}
		}
		return page;
	}

	private PaginationVO<AppPoundOrderResp> appPurchasePage(AppPoundOrderReq req) {
		PaginationVO<AppPoundOrderResp> page = null;
		if (req != null) {
			page = new PaginationVO<AppPoundOrderResp>();
			long count = poundNoteMapper.appPurchasePageCount(req);
			if (count > 0) {
				req.setStart((req.getPageNo() - 1) * req.getPageSize());
				req.setLimit(req.getPageSize());
				List<AppPoundOrderResp> list = poundNoteMapper.appPurchasePage(req);
				page.setList(list);
			}
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
			page.setTotal(count);
		}
		return page;
	}

	private PaginationVO<AppPoundOrderResp> appSalesPage(AppPoundOrderReq req) {
		PaginationVO<AppPoundOrderResp> page = null;
		if (req != null) {
			page = new PaginationVO<AppPoundOrderResp>();
			long count = poundNoteMapper.appSalesPageCount(req);
			if (count > 0) {
				req.setStart((req.getPageNo() - 1) * req.getPageSize());
				req.setLimit(req.getPageSize());
				List<AppPoundOrderResp> list = poundNoteMapper.appSalesPage(req);
				page.setList(list);
			}
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
			page.setTotal(count);
		}
		return page;
	}

	@Override
	public Result appToDetail(AppPoundOrderReq req) {
		Result result = Result.getParamErrorResult();
		if (req != null && StringUtils.isNotBlank(req.getId())) {
			PoundNote poundNote = poundNoteMapper.selectByPrimaryKey(req.getId());
			AppPoundOrderResp resp = new AppPoundOrderResp();
			resp.setId(poundNote.getId());
			resp.setCode(poundNote.getCode());
			resp.setBillcode(poundNote.getBillcode());
			resp.setNoticecode(poundNote.getNoticecode());
			resp.setVehicleno(poundNote.getVehicleno());
			resp.setLighttime(DateUtil.parse(poundNote.getLighttime(), "yyyy-MM-dd HH:mm:ss"));
			resp.setWeighttime(DateUtil.parse(poundNote.getWeighttime(), "yyyy-MM-dd HH:mm:ss"));
			resp.setPickupquantity(poundNote.getPickupquantity());
			resp.setGrossweight(poundNote.getGrossweight());
			resp.setTareweight(poundNote.getTareweight());
			resp.setNetweight(poundNote.getNetweight());
			resp.setSerialnumber(poundNote.getSerialnumber());
			if (StringUtils.equals(req.getIdentityTypes(), Constant.USER_SUPPLIER)) {
				PurchaseApplication purchaseApplication = purchaseApplicationMapper
						.selectByPrimaryKey(poundNote.getBillid());
				PurchaseApplicationDetail purchaseApplicationDetail = purchaseApplicationDetailMapper
						.selectByPrimaryKey(poundNote.getBilldetailid());
				resp.setCustomerName(purchaseApplication.getSuppliername());
				resp.setMaterielname(purchaseApplicationDetail.getMaterielname());
			}
			if (StringUtils.equals(req.getIdentityTypes(), Constant.USER_CUSTOMER)) {
				SalesApplication salesApplication = salesApplicationMapper.selectByPrimaryKey(poundNote.getBillid());
				SalesApplicationDetail salesApplicationDetail = salesApplicationDetailMapper
						.selectByPrimaryKey(poundNote.getBilldetailid());
				resp.setCustomerName(salesApplication.getCustomername());
				resp.setMaterielname(salesApplicationDetail.getMaterielname());
			}
			resp.setOrgname(Constant.ORG_NAME);
			result.setData(resp);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	@Override
	public Result detail(ApiPoundNoteQuery req) {
		Result result = Result.getParamErrorResult();
		if (req != null && StringUtils.isNotBlank(req.getNotionformcode())
				&& StringUtils.isNotBlank(req.getServicetype())) {
			PoundNote query = new PoundNote();
			query.setNoticecode(req.getNotionformcode());
			List<PoundNote> list = poundNoteMapper.selectSelective(query);

            Collections.sort(list,new Comparator<PoundNote>() {

                @Override
                public int compare(PoundNote o1, PoundNote o2) {
                    return (int) (o2.getMakebilltime()-o1.getMakebilltime());
                }
                
            });
            
			if (CollectionUtils.isNotEmpty(list)) {
				PoundNote poundNote = list.get(0);
				AppPoundOrderResp resp = new AppPoundOrderResp();
				resp.setId(poundNote.getId());
				resp.setCode(poundNote.getCode());
				resp.setBillcode(poundNote.getBillcode());
				resp.setNoticecode(poundNote.getNoticecode());
				resp.setVehicleno(poundNote.getVehicleno());
				resp.setLighttime(DateUtil.parse(poundNote.getLighttime(), "yyyy-MM-dd HH:mm:ss"));
				resp.setWeighttime(DateUtil.parse(poundNote.getWeighttime(), "yyyy-MM-dd HH:mm:ss"));
				resp.setPickupquantity(poundNote.getPickupquantity());
				resp.setGrossweight(poundNote.getGrossweight());
				resp.setTareweight(poundNote.getTareweight());
				resp.setNetweight(poundNote.getNetweight());
				resp.setSerialnumber(poundNote.getSerialnumber());
				result.setData(resp);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}
		return result;
	}
	
	@Override
	public List<UploadImageResp> getPoundImages(String billcode) throws Exception {
		List<UploadImageResp> resps = new ArrayList<UploadImageResp>();
		if (StringUtils.isNotBlank(billcode)) {
			List<UploadImage> list = uploadImageMapper.selectByBillcode(billcode);
			for (UploadImage image : list) {
				UploadImageResp resp = new UploadImageResp();
				PropertyUtils.copyProperties(resp, image);
				resps.add(resp);
			}
		}
		return resps;
	}

	@Override
	public PaginationVO<PoundNoteResp> otherPoundNotePage(PoundNoteQuery query) throws Exception {
		PaginationVO<PoundNoteResp> page = null;
		if (query != null) {
			page = new PaginationVO<PoundNoteResp>();
			long count = poundNoteMapper.queryOtherPoundNotePageCount(query);
			if (count > 0) {
				query.setStart((query.getPageNo() - 1) * query.getPageSize());
				query.setLimit(query.getPageSize());
				List<PoundNote> list = poundNoteMapper.queryOtherPoundNotePage(query);
				page.setList(copyBeanList2RespList(list, false));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}

	@Override
	public Result cutoverAdd(PoundNoteSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null && StringUtils.isNotBlank(save.getLeaveyardid())
				&& StringUtils.isNotBlank(save.getEnteryardid()) && StringUtils.isNotBlank(save.getMaterialid())
				&& StringUtils.isNotBlank(save.getVehicleid()) && StringUtils.isNotBlank(save.getDriverid())
				&& save.getGrossweight() != null && save.getTareweight() != null && save.getMakebilltime() != null
				&& save.getWeighttime() != null && save.getLighttime() != null) {
			PoundNote bean = new PoundNote();
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("DY");
			codeReq.setCodeType(true);
			codeReq.setUserid(save.getMakerid());
			bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
			setDetails(save, bean);
			bean.setStatus("1");
			bean.setBilltype("4");
			bean.setState("1");
			bean.setCutoverpartmentid(Constant.ORG_ID);
			bean.setCutoverpartmentname(Constant.ORG_NAME);
			bean.setNetweight(save.getGrossweight() - save.getTareweight());
			bean.setCreator(save.getMakerid());
			bean.setCreatetime(System.currentTimeMillis());
			if (poundNoteMapper.insertSelective(bean) == 1 && StringUtils
					.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result otherIntoAdd(PoundNoteSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null && StringUtils.isNotBlank(save.getSupplierid()) && StringUtils.isNotBlank(save.getMaterialid())
				&& StringUtils.isNotBlank(save.getVehicleid()) && StringUtils.isNotBlank(save.getDriverid())
				&& save.getGrossweight() != null && save.getTareweight() != null && save.getMakebilltime() != null
				&& save.getWeighttime() != null && save.getLighttime() != null) {
			PoundNote bean = new PoundNote();
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("QR");
			codeReq.setCodeType(true);
			codeReq.setUserid(save.getMakerid());
			bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
			setDetails(save, bean);
			bean.setStatus("1");
			bean.setBilltype("5");
			bean.setState("1");
			bean.setReceivedepartmentid(Constant.ORG_ID);
			bean.setReceivedepartmentname(Constant.ORG_NAME);
			bean.setNetweight(save.getGrossweight() - save.getTareweight());
			bean.setCreator(save.getMakerid());
			bean.setCreatetime(System.currentTimeMillis());
			if (poundNoteMapper.insertSelective(bean) == 1 && StringUtils
					.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result otherOutAdd(PoundNoteSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null && StringUtils.isNotBlank(save.getCustomerid()) && StringUtils.isNotBlank(save.getMaterialid())
				&& StringUtils.isNotBlank(save.getVehicleid()) && StringUtils.isNotBlank(save.getDriverid())
				&& save.getGrossweight() != null && save.getTareweight() != null && save.getMakebilltime() != null
				&& save.getWeighttime() != null && save.getLighttime() != null) {
			PoundNote bean = new PoundNote();
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("QR");
			codeReq.setCodeType(true);
			codeReq.setUserid(save.getMakerid());
			bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
			setDetails(save, bean);
			bean.setStatus("1");
			bean.setBilltype("7");
			bean.setState("1");
			bean.setSenddepartmentid(Constant.ORG_ID);
			bean.setSenddepartmentname(Constant.ORG_NAME);
			bean.setNetweight(save.getGrossweight() - save.getTareweight());
			bean.setCreator(save.getMakerid());
			bean.setCreatetime(System.currentTimeMillis());
			if (poundNoteMapper.insertSelective(bean) == 1 && StringUtils
					.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Transactional
    @Override
    public Result copy(PoundNoteCopyDTO copy) throws Exception {
        Result result = Result.getParamErrorResult();
        if (copy != null && StringUtils.isNotBlank(copy.getPoundNoteId())
                && StringUtils.isNotBlank(copy.getBillId())
                && StringUtils.isNotBlank(copy.getBillDetailId())) {
            PoundNote pn = poundNoteMapper.selectByPrimaryKey(copy.getPoundNoteId());
            if (pn != null) {
                if (StringUtils.equals(pn.getRedcollide(), Constant.ONE_STRING)
                		&& StringUtils.equals(pn.getRedColStatus(), Constant.TWO_STRING)) {
					PurchaseApplication newBill = purchaseApplicationMapper.selectByPrimaryKey(copy.getBillId());
	                PurchaseApplicationDetail newBillDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(copy.getBillDetailId());
	                if (newBill != null && newBillDetail != null) {
	                    // 判断订单的余量大于等于预提量
	                    if (validateWeight(pn, newBillDetail, result)) {
	                    	if (StringUtils.isNotBlank(pn.getNoticeid())) {
	                    		PurchaseArrive notice = purchaseArriveMapper.selectByPrimaryKey(pn.getNoticeid());
	                    		notice.setBillid(newBill.getId());
	                    		notice.setBillcode(newBill.getCode());
	                    		notice.setBilldetailid(newBillDetail.getId());
	                    		purchaseArriveMapper.updateByPrimaryKeySelective(notice);
							}
	                    	pn.setRedColStatus(Constant.ZERO_STRING);
	                    	pn.setBillid(newBill.getId());
	                    	pn.setBillcode(newBill.getCode());
	                    	pn.setBilldetailid(newBillDetail.getId());
	                    	pn.setMinemouthid(newBill.getMinemouthid());
	                    	pn.setMinemouthname(newBill.getMinemouthname());
	                    	pn.setSupplierid(newBill.getSupplierid());
	                    	pn.setSuppliername(newBill.getSuppliername());
	                    	pn.setMaterialid(newBillDetail.getMaterielid());
	                    	pn.setMaterialname(newBillDetail.getMaterielname());
	                    	pn.setModifier(copy.getCurrId());
	                    	pn.setModifytime(System.currentTimeMillis());
	                    	PurchaseStorageList storage = setPurchaseStorage(copy.getCurrId(), newBill, pn);
	                    	pn.setPutinwarehouseid(storage.getId());
	                        pn.setPutinwarehousecode(storage.getCode());
	                        PurchaseStorageListItem storageItem = setPurchaseStorageItem(newBillDetail, pn, storage);
	                        purchaseStorageListMapper.insertSelective(storage);
	                        purchaseStorageListItemMapper.insertSelective(storageItem);
	                        updateCode("RKD", copy.getCurrId());
	                    	poundNoteMapper.updateByPrimaryKey(pn);
	                    	newBillDetail.setMargin(newBillDetail.getMargin() - pn.getNetweight());
	                    	newBillDetail.setStoragequantity(newBillDetail.getStoragequantity() + pn.getNetweight());
	                    	purchaseApplicationDetailMapper.updateByPrimaryKeySelective(newBillDetail);
	                    	result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
	                    }
	                } else {
	                    result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
	                }
				} else {
					result.setErrorCode(ErrorCode.POUNDNOTE_ERROR1);
				}
            } else {
                result.setErrorCode(ErrorCode.POUNDNOTE_NOT_EXIST);
            }
        }
        return result;
    }
    /**
     * 
     * @annotation 校验新的订单余量大于等于原通知单预提量
     * @param oldPn
     * @param billDetail
     * @param result
     * @return
     */
    private boolean validateWeight(PoundNote oldPn, PurchaseApplicationDetail billDetail, Result result) {
	    boolean flag = true;
	    if (oldPn.getNetweight() > billDetail.getMargin()) {
	       flag = false; 
	       result.setErrorCode(ErrorCode.POUNDNOTE_RETURN_ERROR2);
	    }
	    return flag;
	}

    @Override
    public Result querySignDetail(ApiPoundNoteQuery query) {
        // TODO 查询签收详情
        Result result = Result.getParamErrorResult();
        if (StringUtils.isNotBlank(query.getVehicleno())
                && StringUtils.isNotBlank(query.getRfid())) {
            List<PurchaseArrive> list = purchaseArriveMapper.validNoticeByVehicle(query.getVehicleno(), query.getRfid());
            if (CollectionUtils.isNotEmpty(list)) {
                PurchaseArrive pa = list.get(0);
                PurchaseApplication application = purchaseApplicationMapper.selectByPrimaryKey(pa.getBillid());
                PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(pa.getBilldetailid());
                PoundNote poundNote = poundNoteMapper.selectByNoticeId(pa.getId());
                if (poundNote != null) {
                    ApiSignDetailResp resp = new ApiSignDetailResp();
                    resp.setPoundNoteCode(poundNote.getCode());
                    resp.setNoticeCode(pa.getCode());
                    if (application != null) {
                        resp.setSupplier(application.getSuppliername());
                    }
                    if (applicationDetail != null) {
                        resp.setMaterial(applicationDetail.getMaterielname());
                    }
                    resp.setGrossweight(poundNote.getGrossweight());
                    resp.setTareweight(poundNote.getTareweight());
                    resp.setNetweight(poundNote.getNetweight());
                    resp.setOriginalnetweight(poundNote.getOriginalnetweight());
                    resp.setDeductionweight(poundNote.getDeductionweight());
                    resp.setDeductionother(poundNote.getDeductionother());
                    resp.setSignPersonName(pa.getSignPersonName());
                    resp.setSignID(pa.getSignID());
                    resp.setSignTime(DateUtil.parse(pa.getSignTime(), DateUtil.Y_M_D_H_M_S));
                    resp.setWarehouse(poundNote.getWarehousename());
                    resp.setYard(poundNote.getYardname());
                    result.setData(resp);
                    result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                } else {
                    result.setErrorCode(ErrorCode.NOTICE_NOT_ONE_POUNDNOTE);
                }
            } else {
                result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
            }
        }
        return result;
    }

    @Override
    public Result print(PoundNoteQuery query) {
        Result result = Result.getParamErrorResult();
        if (query != null && StringUtils.isNotBlank(query.getId())) {
            PoundNote pn = poundNoteMapper.selectByPrimaryKey(query.getId());
            if (pn != null) {
                PoundNotePrintVo vo = new PoundNotePrintVo();
                vo.setBillCode(pn.getBillcode());
                vo.setNoticeCode(pn.getNoticecode());
                vo.setPoundNoteCode(pn.getCode());
                vo.setVehicle(pn.getVehicleno());
                vo.setTareTime(DateUtil.parse(pn.getLighttime(), DateUtil.Y_M_D_H_M_S));
                vo.setTareWeight(pn.getTareweight().toString());
                vo.setGrossTime(DateUtil.parse(pn.getWeighttime(), DateUtil.Y_M_D_H_M_S));
                vo.setGrossWeight(pn.getGrossweight().toString());
                vo.setNetWeight(pn.getNetweight().toString());
                vo.setNetWeightBig(NumberUtils.numberUpper(vo.getNetWeight()));
                vo.setType(pn.getBilltype());
                //采购（到货/退货）
                if (StringUtils.equals(pn.getBilltype(), Constant.ZERO_STRING) 
                        || StringUtils.equals(pn.getBilltype(), Constant.ONE_STRING)) {
                    if (StringUtils.isNotBlank(pn.getBillid())) {
                        PurchaseApplication pa = purchaseApplicationMapper.selectByPrimaryKey(pn.getBillid());
                        vo.setCustomer(Constant.ORG_NAME);
                        vo.setSendDept(pa.getSuppliername());
                        vo.setBillType(pa.getBilltypename());
                    }
                    if (StringUtils.isNotBlank(pn.getBilldetailid())) {
                        PurchaseApplicationDetail paDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(pn.getBilldetailid());
                        vo.setUnit(paDetail.getUnit());
                        vo.setMaterial(paDetail.getMaterielname());
                    }
                }
                //销售Z
                if (StringUtils.equals(pn.getBilltype(), Constant.TWO_STRING)) {
                    if (StringUtils.isNotBlank(pn.getBillid())) {
                        SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(pn.getBillid());
                        vo.setCustomer(sa.getCustomername());
                        vo.setSendDept(Constant.ORG_NAME);
                        vo.setBillType(sa.getBilltypename());
                    }
                    if (StringUtils.isNotBlank(pn.getBilldetailid())) {
                        SalesApplicationDetail saDetail = salesApplicationDetailMapper.selectByPrimaryKey(pn.getBilldetailid());
                        vo.setUnit(saDetail.getUnit());
                        vo.setMaterial(saDetail.getMaterielname());
                    }
                }
                //其他入库
                if (StringUtils.equals(pn.getBilltype(), Constant.FIVE_STRING)) {
                    vo.setCustomer(Constant.ORG_NAME);
                    vo.setSendDept(pn.getSuppliername());
                    vo.setMaterial(pn.getMaterialname());
                }
                //其他出库
                if (StringUtils.equals(pn.getBilltype(), Constant.SEVEN_STRING)) {
                    vo.setSendDept(Constant.ORG_NAME);
                    vo.setCustomer(pn.getSuppliername());
                    vo.setMaterial(pn.getMaterialname());
                }
                    
                result.setData(vo);
                result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
            } else {
                result.setErrorCode(ErrorCode.POUNDNOTE_NOT_EXIST);
            }
        }
        return result;
    }
    
    private boolean validateYard(YardManage yard, Result result) {
    	boolean flag = false;
    	if (yard != null && StringUtils.equals(yard.getState(), Constant.ONE_STRING)) {
			if (StringUtils.equals(yard.getIsvalid(), Constant.ONE_STRING)) {
				flag = true;
			} else {
				result.setErrorCode(ErrorCode.YARD_NOT_VALID);
			}
		} else {
			result.setErrorCode(ErrorCode.YARD_NOT_EXIST);
		}
    	return flag;
    }

	@Override
	public Result purchaseUpdatePn(PoundNoteSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null && StringUtils.isNotBlank(save.getId())
				&& StringUtils.isNotBlank(save.getYardid())
				&& StringUtils.isNotBlank(save.getSignPerson())) {
			PoundNote pn = poundNoteMapper.selectByPrimaryKey(save.getId());
			if (pn != null) {
				YardManage yard = yardManageMapper.selectByPrimaryKey(save.getYardid());
				if (validateYard(yard, result)) {
					SystemUserResp user = systemUserService.get(save.getSignPerson());
					if (user != null) {
						pn.setYardid(yard.getId());
						pn.setYardname(yard.getName());
						pn.setReceiverpersonid(user.getId());
						pn.setReceiverpersonname(user.getName());
						pn.setModifier(save.getUserId());
						pn.setModifytime(System.currentTimeMillis());
						if (StringUtils.isNotBlank(pn.getNoticeid())) {
							PurchaseArrive pa = purchaseArriveMapper.selectByPrimaryKey(pn.getNoticeid());
							pa.setSignPerson(user.getId());
							pa.setSignPersonName(user.getName());
							pa.setModifier(save.getUserId());
							pa.setModifytime(System.currentTimeMillis());
							purchaseArriveMapper.updateByPrimaryKeySelective(pa);
						}
						poundNoteMapper.updateByPrimaryKeySelective(pn);
						result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					} else {
						result.setErrorCode(ErrorCode.POUNDNOTE_ERROR6);
					}
				}
			} else {
				result.setErrorCode(ErrorCode.POUNDNOTE_NOT_EXIST);
			}
		}
		return result;
	}

}
