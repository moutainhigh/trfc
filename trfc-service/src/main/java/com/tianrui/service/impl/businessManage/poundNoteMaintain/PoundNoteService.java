package com.tianrui.service.impl.businessManage.poundNoteMaintain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.businessManage.poundNoteMaintain.IPoundNoteService;
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
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.ApiSignDetailResp;
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
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageList;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageListItem;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationJoinNatice;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationJoinPoundNote;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesOutboundOrder;
import com.tianrui.service.bean.businessManage.salesManage.SalesOutboundOrderItem;
import com.tianrui.service.bean.common.UploadImage;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.MinemouthManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.measure.YardManageMapper;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.service.mapper.businessManage.logisticsManage.AccessRecordMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseStorageListItemMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseStorageListMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationJoinNaticeMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationJoinPoundNoteMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesOutboundOrderItemMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesOutboundOrderMapper;
import com.tianrui.service.mapper.common.UploadImageMapper;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.common.ApiParamUtils;
import com.tianrui.smartfactory.common.common.HttpUtils;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
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
	private SalesApplicationJoinNaticeMapper salesApplicationJoinNaticeMapper;
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
	
	private String getCode(String code, String userId) throws Exception {
        GetCodeReq codeReq1 = new GetCodeReq();
        codeReq1.setCode(code);
        codeReq1.setCodeType(true);
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
			bean.setCode(getCode("RK", save.getMakerid()));
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
			storage.setCode(getCode("RKD", save.getMakerid()));
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
				result.setErrorCode(returnPurchaseStorage(storage, storageItem));
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
				bean.setCode(getCode("RK", save.getMakerid()));
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
				storage.setCode(getCode("RKD", bean.getMakerid()));
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
					result.setErrorCode(returnPurchaseStorage(storage, storageItem));
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
			PoundNote poundNote = poundNoteMapper.selectByPrimaryKey(query.getId());
			if(poundNote != null){
				PoundNote bean = new PoundNote();
				bean.setId(query.getId());
				bean.setRedcollide(Constant.ONE_STRING);
				bean.setModifier(query.getCurrId());
				bean.setModifytime(System.currentTimeMillis());
				//增加红冲入库单
				PurchaseStorageList storage = new PurchaseStorageList();
				storage.setId(UUIDUtil.getId());
				storage.setCode(getCode("RKD", query.getCurrId()));
				storage.setNcId(poundNote.getBillid());
				storage.setPoundId(poundNote.getId());
				storage.setType("3");
				PurchaseApplication purchaseApplication = null;
				PurchaseApplicationDetail purchaseApplicationDetail = null;
				if (StringUtils.isNotBlank(poundNote.getBillid()) && StringUtils.isNotBlank(poundNote.getBilldetailid())) {
					purchaseApplication = purchaseApplicationMapper.selectByPrimaryKey(poundNote.getBillid());
					purchaseApplicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(poundNote.getBilldetailid());
				}
				if (purchaseApplication != null) {
					storage.setPkOrg(purchaseApplication.getOrgid());
					storage.setCdptid(purchaseApplication.getDepartmentid());
					storage.setCvendorid(purchaseApplication.getSupplierid());
				}
				if(StringUtils.isNotBlank(poundNote.getPutinwarehouseid())){
					PurchaseStorageList storageOld = purchaseStorageListMapper.selectByPrimaryKey(poundNote.getPutinwarehouseid());
					if(storageOld != null){
						storage.setReturnRkdNcId(storageOld.getRkdNcId());
					}
				}
				storage.setNtotalnum("-" + poundNote.getNetweight());
				storage.setDbilldate(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
				storage.setBillmaker(poundNote.getMakerid());
				storage.setCreationtime(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
				storage.setTs(storage.getCreationtime());
				storage.setStatus("0");
				PurchaseStorageListItem storageItem = setPurchaseStorageItem(purchaseApplicationDetail, poundNote, storage);
				if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0
						&& purchaseStorageListMapper.insertSelective(storage) > 0
						&& purchaseStorageListItemMapper.insertSelective(storageItem) > 0 
						&& updateCode("RKD", query.getCurrId())) {
					pushDcRedcollide(result, storage, storageItem);
				} else {
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.POUNDNOTE_NOT_EXIST);
			}
		}
		return result;
	}
	//红冲推单到dc
    private void pushDcRedcollide(Result result, PurchaseStorageList storage, PurchaseStorageListItem storageItem) {
        List<PurchaseStorageList> list = new ArrayList<PurchaseStorageList>();
        list.add(storage);
        List<PurchaseStorageListItem> itemList = new ArrayList<PurchaseStorageListItem>();
        itemList.add(storageItem);
        storage.setList(itemList);
        ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(list),
        		Constant.URL_RETURN_PURCHASESTORAGEATION);
        // 调用dc 接口成功 则推单状态为推单中 榜单展示为推单中
        if (apiResult != null && StringUtils.equals(apiResult.getCode(), Constant.SUCCESS)) {
        	PurchaseStorageList storageUpdate = new PurchaseStorageList();
        	storageUpdate.setId(storage.getId());
        	storageUpdate.setStatus(Constant.PUSH_STATUS_ING);
        	if (purchaseStorageListMapper.updateByPrimaryKeySelective(storageUpdate) > 0) {
        		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
        	}else{
        		result.setErrorCode(ErrorCode.OPERATE_ERROR);
        	}
        }else{
        	result.setErrorCode(ErrorCode.OPERATE_ERROR);
        }
    }

	@Override
	public Result invalid(PoundNoteQuery query) {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			PoundNote bean = new PoundNote();
			bean.setId(query.getId());
			bean.setStatus("3");
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

	@Override
	public PoundNoteResp findOne(String id) throws Exception {
		PoundNoteResp resp = null;
		if (StringUtils.isNotBlank(id)) {
			resp = poundNoteMapper.findOne(id);
			if (resp != null && StringUtils.equals(resp.getBilltype(), "0")) {

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
			bean.setCode(getCode("CK", save.getMakerid()));
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
			        parseBeanList(bean, array, list, orderList, orderItemList, getCode("A6XC", save.getMakerid()));
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
			double netWeight = bean.getNetweight();
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
				if (application != null && applicationDetail != null && netWeight > 0) {
					SalesApplicationDetail detail = new SalesApplicationDetail();
					SalesOutboundOrder order = new SalesOutboundOrder();
					order.setId(UUIDUtil.getId());
					order.setCode(code);
					order.setNcId(billid);
					order.setPoundId(bean.getId());
					order.setBilldate(DateUtil.parse(application.getBilltime(), "yyyy-MM-dd HH:mm:ss"));
					order.setPkOrg(application.getOrgid());
					order.setCdptid(application.getDepartmentid());
					order.setCbizid(application.getSalesmanid());
					order.setCdilivertypeid(application.getTransportcompanyid());
					order.setBillmaker(bean.getMakerid());
					order.setDmakedate(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
					order.setTs(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
					order.setStatus("0");
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
					orderItemList.add(orderItem);
					join.setBillsum(applicationDetail.getSalessum());
					if (StringUtils.equals(billid, bean.getBillid())) {
						bean.setPutinwarehouseid(order.getId());
						bean.setPutinwarehousecode(order.getCode());
					}
					applicationDetailList.add(detail);
				}
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
				result = saveOtherCKPoundNote(query);
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
						ErrorCode ec = returnPurchaseStorage(storage, storageItem);
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
				pa.setStatus("2");
				// 生成入库单
				PurchaseStorageList storage = null;
				PurchaseStorageListItem storageItem = null;
				PurchaseApplicationDetail detail = null;
				if(bean.getNetweight() >= Constant.NOT_RETURN_NUM || arrive.getSignStatus() == Constant.ONE_NUMBER) {
					storage = setPurchaseStorage(query.getCurrid(), application, bean);
					storageItem = setPurchaseStorageItem(applicationDetail, bean, storage);
					bean.setPutinwarehouseid(storage.getId());
					bean.setPutinwarehousecode(storage.getCode());
					detail = new PurchaseApplicationDetail();
					detail.setId(applicationDetail.getId());
					detail.setStoragequantity(applicationDetail.getStoragequantity() + bean.getNetweight());
					detail.setUnstoragequantity(applicationDetail.getUnstoragequantity() - arrive.getArrivalamount());
					detail.setMargin(applicationDetail.getMargin() + (arrive.getArrivalamount() - bean.getNetweight()));
				}
				if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0
						&& purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0) {
				    result.setData(bean.getCode());
					if(detail != null && storage != null && storageItem != null){
						if(purchaseApplicationDetailMapper.updateByPrimaryKeySelective(detail) > 0 
								&& purchaseStorageListMapper.insertSelective(storage) > 0
								&& purchaseStorageListItemMapper.insertSelective(storageItem) > 0
								&& updateCode("RKD", query.getCurrid())){
							result.setErrorCode(returnPurchaseStorage(storage, storageItem));
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
			if (poundNoteMapper.insertSelective(bean) > 0
					&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(),
							ErrorCode.SYSTEM_SUCCESS.getCode())
					&& purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0) {
				result.setData(bean.getCode());
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	// 推送采购入库单
	private ErrorCode returnPurchaseStorage(PurchaseStorageList storage, PurchaseStorageListItem storageItem) {
		ErrorCode ec = ErrorCode.OPERATE_ERROR;
		List<PurchaseStorageList> list = new ArrayList<PurchaseStorageList>();
		list.add(storage);
		List<PurchaseStorageListItem> itemList = new ArrayList<PurchaseStorageListItem>();
		itemList.add(storageItem);
		storage.setList(itemList);
		ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(list),
				Constant.URL_RETURN_PURCHASESTORAGEATION);
		// 调用dc 接口成功 则推单状态为推单中 榜单展示为推单中
		if (apiResult != null && StringUtils.equals(apiResult.getCode(), Constant.SUCCESS)) {
			PurchaseStorageList storageUpdate = new PurchaseStorageList();
			storageUpdate.setId(storage.getId());
			storageUpdate.setStatus(Constant.PUSH_STATUS_ING);
			if (purchaseStorageListMapper.updateByPrimaryKeySelective(storageUpdate) > 0) {
				PoundNote pn = new PoundNote();
				pn.setId(storage.getPoundId());
				pn.setReturnstatus(Constant.POUND_PUSH_STATUS_ING);
				if (poundNoteMapper.updateByPrimaryKeySelective(pn) == 1) {
					ec = ErrorCode.SYSTEM_SUCCESS;
				}
			}
		} else {
			PurchaseStorageList ps = new PurchaseStorageList();
			ps.setId(storage.getId());
			ps.setStatus(Constant.PUSH_STATUS_ING);
			if (purchaseStorageListMapper.updateByPrimaryKeySelective(ps) > 0) {
				ec = ErrorCode.SYSTEM_SUCCESS;
			}
		}
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
		}
		storageItem.setNshouldnum("" + bean.getOriginalnetweight());
		storageItem.setPkCreqwareid(bean.getWarehouseid());
		storageItem.setTs(storage.getTs());
		storageItem.setCreateTime(System.currentTimeMillis());
		return storageItem;
	}

	// 初始化采购入库单
	private PurchaseStorageList setPurchaseStorage(String currid, PurchaseApplication application, PoundNote bean)
			throws Exception {
		PurchaseStorageList storage = new PurchaseStorageList();
		storage.setId(UUIDUtil.getId());
		storage.setCode(getCode("RKD", currid));
		storage.setNcId("");
		storage.setPoundId(bean.getId());
		storage.setType("1");
		if (application != null) {
			storage.setPkOrg(application.getOrgid());
			storage.setCdptid(application.getDepartmentid());
			storage.setCvendorid(application.getSupplierid());
		}
		storage.setNtotalnum("" + bean.getNetweight());
		storage.setDbilldate(DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		storage.setBillmaker(bean.getMakerid());
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
		bean.setOriginalnetweight(arrive.getArrivalamount());
		bean.setPickupquantity(arrive.getArrivalamount());
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

	private Result saveSalesPoundNote(ApiPoundNoteQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		SalesArrive arrive = salesArriveMapper.selectByCode(query.getNotionformcode());
		SalesApplication application = salesApplicationMapper.selectByPrimaryKey(arrive.getBillid());
		SalesApplicationDetail applicationDetail = salesApplicationDetailMapper
				.selectByPrimaryKey(arrive.getBilldetailid());
		PoundNote bean = new PoundNote();
		// 皮重
		if (StringUtils.equals(query.getType(), "1")) {
			GetCodeReq codeReq = setSalesBeanBody(query, arrive, application, applicationDetail, bean);
			// 更新通知单状态
			SalesArrive sa = new SalesArrive();
			sa.setId(arrive.getId());
			sa.setStatus("1");
			if (poundNoteMapper.insertSelective(bean) > 0
					&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(),
							ErrorCode.SYSTEM_SUCCESS.getCode())
					&& salesArriveMapper.updateByPrimaryKeySelective(sa) > 0) {
				result.setData(bean.getCode());
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
			// 毛重
		} else {
			bean.setVehicleno(query.getVehicleno());
			bean.setNoticecode(query.getNotionformcode());
			bean.setReturnstatus("0");
			bean.setState("1");
			bean.setBilltype("2");
			List<PoundNote> list = poundNoteMapper.selectSelective(bean);
			if (CollectionUtils.isNotEmpty(list)) {
				bean = list.get(0);
				bean.setGrossweight(Double.parseDouble(query.getNumber()));
				bean.setNetweight(bean.getGrossweight() - bean.getTareweight());
				bean.setWeighttime(DateUtil.parse(query.getTime(), "yyyy-MM-dd HH:mm:ss"));
				bean.setModifier(query.getCurrid());
				bean.setModifytime(System.currentTimeMillis());
				// 更新通知单状态
				SalesArrive sa = new SalesArrive();
				sa.setId(arrive.getId());
				sa.setStatus("2");
				List<SalesApplicationJoinNatice> listPoundNote = salesApplicationJoinNaticeMapper
						.selectByNaticeId(bean.getNoticeid());
				List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
				if (CollectionUtils.isNotEmpty(listPoundNote)) {
					for (SalesApplicationJoinNatice join : listPoundNote) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("billid", join.getBillid());
						map.put("billdetailid", join.getBilldetailid());
						listMap.add(map);
					}
				}
				JSONArray array = JSONArray.parseArray(JSONObject.toJSONString(listMap));
				List<SalesApplicationJoinPoundNote> listJoin = new ArrayList<SalesApplicationJoinPoundNote>();
				List<SalesOutboundOrder> orderList = new ArrayList<SalesOutboundOrder>();
				List<SalesOutboundOrderItem> orderItemList = new ArrayList<SalesOutboundOrderItem>();
				List<SalesApplicationDetail> applicationDetailList = 
				        parseBeanList(bean, array, listJoin, orderList, orderItemList, getCode("A6XC", query.getCurrid()));
				if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0
						&& salesApplicationJoinPoundNoteMapper.insertBatch(listJoin) > 0
						&& salesOutboundOrderMapper.insertBatch(orderList) > 0
						&& salesOutboundOrderItemMapper.insertBatch(orderItemList) > 0
						&& updateCode("A6XC", query.getCurrid())
						&& salesArriveMapper.updateByPrimaryKeySelective(sa) > 0) {
					if (CollectionUtils.isNotEmpty(applicationDetailList)) {
						for (SalesApplicationDetail salesApplicationDetail : applicationDetailList) {
							salesApplicationDetailMapper.updateByPrimaryKeySelective(salesApplicationDetail);
						}
					}
					ErrorCode ec = returnSalesStorage(orderList, orderItemList);
					result.setErrorCode(ec);
					result.setData(bean.getCode());
				} else {
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}

	// 推送采购入库单
	private ErrorCode returnSalesStorage(List<SalesOutboundOrder> orderList,
			List<SalesOutboundOrderItem> orderItemList) {
		ErrorCode ec = ErrorCode.OPERATE_ERROR;
		List<SalesOutboundOrder> list = new ArrayList<SalesOutboundOrder>();
		for (SalesOutboundOrder order : orderList) {
			for (SalesOutboundOrderItem orderItem : orderItemList) {
				if (StringUtils.equals(order.getId(), orderItem.getSaleOutboundOrderId())) {
					List<SalesOutboundOrderItem> item = new ArrayList<SalesOutboundOrderItem>();
					item.add(orderItem);
					order.setList(item);
				}
			}
			list.clear();
			list.add(order);
			ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(list),
					Constant.URL_RETURN_SALESOUTBOUNDCATION);
			if (apiResult != null && StringUtils.equals(apiResult.getCode(), Constant.SUCCESS)) {
				SalesOutboundOrder orderUpdate = new SalesOutboundOrder();
				orderUpdate.setId(order.getId());
				orderUpdate.setStatus("1");
				if (salesOutboundOrderMapper.updateByPrimaryKeySelective(orderUpdate) > 0) {
					PoundNote pn = new PoundNote();
					pn.setId(order.getPoundId());
					pn.setReturnstatus("2");
					if (poundNoteMapper.updateByPrimaryKeySelective(pn) == 1) {
						ec = ErrorCode.SYSTEM_SUCCESS;
					}
				}
			} else {
				SalesOutboundOrder so = new SalesOutboundOrder();
				so.setId(order.getId());
				so.setStatus("0");
				if (salesOutboundOrderMapper.updateByPrimaryKeySelective(so) > 0) {
					ec = ErrorCode.SYSTEM_SUCCESS;
				}
			}
		}
		return ec;
	}

	private GetCodeReq setSalesBeanBody(ApiPoundNoteQuery query, SalesArrive arrive, SalesApplication application,
			SalesApplicationDetail applicationDetail, PoundNote bean) throws Exception {
		bean.setId(UUIDUtil.getId());
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode("CK");
		codeReq.setCodeType(true);
		codeReq.setUserid(query.getCurrid());
		bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
		bean.setReturnstatus("0");
		bean.setRedcollide("0");
		bean.setStatus("0");
		bean.setBilltype("2");
		bean.setBillid(application.getId());
		bean.setBillcode(application.getCode());
		bean.setBilldetailid(applicationDetail.getId());
		bean.setMaindeduction("0");
		bean.setNoticeid(arrive.getId());
		bean.setNoticecode(arrive.getCode());
		bean.setSenddepartmentid(Constant.ORG_ID);
		bean.setSenddepartmentname(Constant.ORG_NAME);
		bean.setDriverid(arrive.getDriverid());
		bean.setDrivername(arrive.getDrivername());
		bean.setDriveridentityno(arrive.getDriveridentityno());
		bean.setVehicleid(arrive.getVehicleid());
		bean.setVehicleno(arrive.getVehicleno());
		bean.setVehiclerfid(arrive.getVehiclerfid());
		bean.setPickupquantity(arrive.getTakeamount());
		bean.setTareweight(Double.parseDouble(query.getNumber()));
		bean.setLighttime(DateUtil.parse(query.getTime(), "yyyy-MM-dd HH:mm:ss"));
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

	@Override
	public Result validation(ApiPoundNoteValidation valid) {
		Result result = Result.getParamErrorResult();
		if (valid != null && StringUtils.isNotBlank(valid.getVehicleno()) && StringUtils.isNotBlank(valid.getRfid())
				&& StringUtils.isNotBlank(valid.getType()) && StringUtils.isNotBlank(valid.getCurrid())) {
			if (StringUtils.equals(valid.getType(), "1")) {
				result = validOneWeight(valid);
			} else {
				result = validTwoWeight(valid);
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
		List<SalesArrive> listSales = salesArriveMapper.validNoticeByVehicle(valid.getVehicleno(), valid.getRfid());
		if (CollectionUtils.isEmpty(listSales)) {
			List<PurchaseArrive> listPurchase = purchaseArriveMapper.validNoticeByVehicle(valid.getVehicleno(),
					valid.getRfid());
			if (CollectionUtils.isEmpty(listPurchase)) {

				// 通过车号 获取车辆id和rfid 进行检测
				VehicleManage vehicle = vehicleManageMapper.selectByVehicleno(valid.getVehicleno());
				if (vehicle != null && StringUtils.equals(vehicle.getRfid(), valid.getRfid())) {
					// 检测其他业务中时候有通知单
					OtherArrive otherArrive = otherArriveMapper.hasOtherArrive(vehicle.getId());
					if (otherArrive == null) {
						// 该车辆没有通知单
						result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
					} else {
						if (StringUtils.equals(otherArrive.getStatus(), "6")) {
							validNoticeInfoAccessRecord(result, otherArrive.getId());
						} else {
							result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ENTER);
						}
					}
				} else {
					result.setErrorCode(ErrorCode.VEHICLE_NOT_EXIST);
				}
			} else {
				if (listPurchase.size() == 1) {
					if (StringUtils.equals(listPurchase.get(0).getStatus(), "6")) {
						validNoticeInfoAccessRecord(result, listPurchase.get(0).getId());
					} else {
						result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ENTER);
					}
				} else {
					result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONLY);
				}
			}
		} else {
			if (listSales.size() == 1) {
				if (StringUtils.equals(listSales.get(0).getStatus(), "6")) {
					validNoticeInfoAccessRecord(result, listSales.get(0).getId());
				} else {
					result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ENTER);
				}
			} else {
				result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONLY);
			}
		}
		return result;
	}

	// 检验通知单是否有入厂门禁
	private void validNoticeInfoAccessRecord(Result result, String noticeId) {
		// 验证是否有入场门禁记录
		AccessRecord access = accessRecordMapper.selectByNoticeId(noticeId);
		if (access != null) {
			if (StringUtils.equals(access.getAccesstype(), "1")) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.NOTICE_OUT_FACTORY);
			}
		} else {
			result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ACCESSRECORD);
		}
	}

	/**
	 * @Description 二次过磅验证
	 * @author zhanggaohao
	 * @version 2017年3月27日 上午10:41:36
	 * @param valid
	 */
	private Result validTwoWeight(ApiPoundNoteValidation valid) {
		Result result = Result.getErrorResult();
		List<SalesArrive> listSales = salesArriveMapper.validNoticeByVehicle(valid.getVehicleno(), valid.getRfid());
		if (CollectionUtils.isEmpty(listSales)) {
			List<PurchaseArrive> listPurchase = purchaseArriveMapper.validNoticeByVehicle(valid.getVehicleno(),
					valid.getRfid());
			if (CollectionUtils.isEmpty(listPurchase)) {

				// 通过车号 获取车辆id和rfid 进行检测
				VehicleManage vehicle = vehicleManageMapper.selectByVehicleno(valid.getVehicleno());
				if (vehicle != null && StringUtils.equals(vehicle.getRfid(), valid.getRfid())) {
					// 检测其他业务中时候有通知单
					OtherArrive otherArrive = otherArriveMapper.hasOtherArrive(vehicle.getId());
					if (otherArrive == null) {
						// 该车辆没有通知单
						result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
					} else {
						if (StringUtils.equals(otherArrive.getStatus(), "1")) {
							isTwoWeight(result, otherArrive.getId(), otherArrive.getBusinesstype());
						} else {
							result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ENTER);
						}
					}
				} else {
					// 该车辆没有通知单
					result.setErrorCode(ErrorCode.VEHICLE_NOT_EXIST);
				}
			} else {
				// 判断通知单是否唯一
				if (listPurchase.size() == 1) {
			        //二次过磅校验变更为是否签收或退货  2017-09-06
					if (StringUtils.equals(listPurchase.get(0).getStatus(), Constant.SEVEN_STRING)) {
						isTwoWeight(result, listPurchase.get(0).getId(), listPurchase.get(0).getType());
					} else {
						result.setErrorCode(ErrorCode.NOTICE_NOT_SIGN);
					}
				} else {
					result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONLY);
				}
			}
		} else {
			// 判断通知单是否唯一
			if (listSales.size() == 1) {
				if (StringUtils.equals(listSales.get(0).getStatus(), "7")) {
					isTwoWeight(result, listSales.get(0).getId(), "2");
				} else {
					result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_LOAD);
				}
			} else {
				result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONLY);
			}
		}
		return result;
	}

	private void isTwoWeight(Result result, String noticeid, String type) {
		PoundNote poundNote = new PoundNote();
		poundNote.setNoticeid(noticeid);
		List<PoundNote> listPoundNote = poundNoteMapper.selectSelective(poundNote);
		// 判断是否一次过磅
		if (StringUtils.equals(type, "0")) {
			if (CollectionUtils.isNotEmpty(listPoundNote) && listPoundNote.get(0).getGrossweight() != null
					&& listPoundNote.get(0).getGrossweight() > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONE_WEIGHT);
			}
		} else if (StringUtils.equals(type, "1")) {
			if (CollectionUtils.isNotEmpty(listPoundNote) && listPoundNote.get(0).getTareweight() != null
					&& listPoundNote.get(0).getTareweight() > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONE_WEIGHT);
			}
		} else if (StringUtils.equals(type, "2")) {
			if (CollectionUtils.isNotEmpty(listPoundNote) && listPoundNote.get(0).getTareweight() != null
					&& listPoundNote.get(0).getTareweight() > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONE_WEIGHT);
			}
		} else if (StringUtils.equals(type, "5")) {
			if (CollectionUtils.isNotEmpty(listPoundNote) && listPoundNote.get(0).getGrossweight() != null
					&& listPoundNote.get(0).getGrossweight() > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONE_WEIGHT);
			}
		} else if (StringUtils.equals(type, "7")) {
			if (CollectionUtils.isNotEmpty(listPoundNote) && listPoundNote.get(0).getTareweight() != null
					&& listPoundNote.get(0).getTareweight() > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONE_WEIGHT);
			}
		} else if (StringUtils.equals(type, "4")) {
			if (CollectionUtils.isNotEmpty(listPoundNote) && listPoundNote.get(0).getTareweight() != null
					&& listPoundNote.get(0).getTareweight() > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONE_WEIGHT);
			}
		} else if (StringUtils.equals(type, "9")) {
			if (CollectionUtils.isNotEmpty(listPoundNote) && listPoundNote.get(0).getTareweight() != null
					&& listPoundNote.get(0).getTareweight() > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONE_WEIGHT);
			}
		} else {
			result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
		}
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
			List<PoundNote> rs = poundNoteMapper.selectSelective(query);
			if (CollectionUtils.isNotEmpty(rs)) {
				PoundNote poundNote = rs.get(0);
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
        if (copy != null 
                && StringUtils.isNotBlank(copy.getPoundNoteId())
                && StringUtils.isNotBlank(copy.getBillId())
                && StringUtils.isNotBlank(copy.getBillDetailId())) {
            PoundNote oldPn = poundNoteMapper.selectByPrimaryKey(copy.getPoundNoteId());
            if (oldPn != null) {
                PurchaseApplication newBill = purchaseApplicationMapper.selectByPrimaryKey(copy.getBillId());
                PurchaseApplicationDetail newBillDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(copy.getBillDetailId());
                if (newBill != null && newBillDetail != null) {
                    // TODO 判断订单的余量大于等于预提量
                    if (validateWeight(oldPn, newBillDetail, result)) {
                        PurchaseArrive oldPa = purchaseArriveMapper.selectByPrimaryKey(oldPn.getNoticeid());
                        if (StringUtils.equals(oldPa.getStatus(), Constant.FIVE_STRING)) {
                            AccessRecord oldAr = accessRecordMapper.selectByNoticeId(oldPa.getId());
                            PurchaseArrive newPa = copyNotice(oldPa, copy.getCurrId());
                            copyAccessRecord(oldAr, newPa, copy.getCurrId());
                            copyPoundNote(oldPn, newBill, newBillDetail, newPa, copy.getCurrId());
                            result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                        } else {
                            result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_OUT_FACTORY);
                        }
                    }
                } else {
                    result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
                }
            } else {
                result.setErrorCode(ErrorCode.POUNDNOTE_NOT_EXIST);
            }
        }
        return result;
    }
	/**
	 * @annotation 复制榜单并生成新的入库单
	 * @param oldPn
	 * @param newBill
	 * @param newBillDetail
	 * @param newPa
	 * @param userId
	 * @throws Exception
	 */
	private void copyPoundNote(PoundNote oldPn, PurchaseApplication newBill, PurchaseApplicationDetail newBillDetail,
            PurchaseArrive newPa, String userId) throws Exception {
	    PoundNote newPn = new PoundNote();
	    PropertyUtils.copyProperties(newPn, oldPn);
	    newPn.setId(UUIDUtil.getId());
	    newPn.setCode(getCode("RK", userId));
	    //TODO 推单状态
	    newPn.setReturnstatus(Constant.ZERO_STRING);
	    newPn.setRedcollide(Constant.ZERO_STRING);
	    newPn.setBillid(newBill.getId());
	    newPn.setBillcode(newBill.getCode());
	    newPn.setBilldetailid(newBillDetail.getId());
	    newPn.setNoticeid(newPa.getId());
	    newPn.setNoticecode(newPa.getCode());
	    newPn.setMinemouthid(newBill.getMinemouthid());
	    newPn.setMinemouthname(newBill.getMinemouthname());
	    
	    //TODO 入库单
        PurchaseStorageList storage = new PurchaseStorageList();
        storage.setId(UUIDUtil.getId());
        storage.setCode(getCode("RKD", userId));
        storage.setPoundId(newPn.getId());
        storage.setType(Constant.ONE_STRING);
        storage.setPkOrg(newBill.getOrgid());
        storage.setNcId(newBill.getId());
        storage.setCdptid(newBill.getDepartmentid());
        storage.setCvendorid(newBill.getSupplierid());
        storage.setNtotalnum("" + newPn.getNetweight());
        storage.setDbilldate(DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S));
        storage.setBillmaker(newPn.getMakerid());
        storage.setCreationtime(DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S));
        storage.setTs(storage.getCreationtime());
        storage.setStatus(Constant.ZERO_STRING);
        PurchaseStorageListItem storageItem = setPurchaseStorageItem(newBillDetail, newPn, storage);
        newPn.setPutinwarehouseid(storage.getId());
        newPn.setPutinwarehousecode(storage.getCode());
        poundNoteMapper.insertSelective(newPn);
        updateCode("RK", userId);
        purchaseStorageListMapper.insertSelective(storage);
        purchaseStorageListItemMapper.insertSelective(storageItem);
        updateCode("RKD", userId);
        PurchaseApplicationDetail billDetail = new PurchaseApplicationDetail();
        billDetail.setId(newBillDetail.getId());
        billDetail.setMargin(newBillDetail.getMargin() - newPn.getNetweight());
        billDetail.setStoragequantity(newBillDetail.getStoragequantity() + newPn.getNetweight());
        purchaseApplicationDetailMapper.updateByPrimaryKeySelective(billDetail);
    }

    /**
	 * @annotation 复制门禁记录
	 * @param oldAr
	 * @param newPa
	 * @param userId
	 * @throws Exception
	 */
	private void copyAccessRecord(AccessRecord oldAr, PurchaseArrive newPa, String userId) throws Exception {
	    AccessRecord newAr = new AccessRecord();
	    PropertyUtils.copyProperties(newPa, oldAr);
	    newAr.setId(UUIDUtil.getId());
	    newAr.setCode(getCode("ZW", userId));
	    accessRecordMapper.insertSelective(newAr);
	    updateCode("ZW", userId);
    }
	/**
	 * @annotation 复制通知单
	 * @param oldPa
	 * @param userId
	 * @return
	 * @throws Exception
	 */
    private PurchaseArrive copyNotice(PurchaseArrive oldPa, String userId) throws Exception {
	    PurchaseArrive newPa = new PurchaseArrive();
	    PropertyUtils.copyProperties(newPa, oldPa);
	    newPa.setId(UUIDUtil.getId());
	    newPa.setCode(getCode("DH", userId));
        purchaseArriveMapper.insertSelective(newPa);
        updateCode("DH", userId);
	    return newPa;
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
	    if (oldPn.getOriginalnetweight() > billDetail.getMargin()) {
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

}
