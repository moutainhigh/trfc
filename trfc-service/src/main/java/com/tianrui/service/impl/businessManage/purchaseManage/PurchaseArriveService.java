package com.tianrui.service.impl.businessManage.purchaseManage;

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
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationDetailService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.app.AppNoticeOrderReq;
import com.tianrui.api.req.businessManage.app.AppOrderReq;
import com.tianrui.api.req.businessManage.app.AppOrderSaveReq;
import com.tianrui.api.req.businessManage.logisticsManage.PurchaseLogisticsQuery;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveQuery;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.api.resp.businessManage.app.AppNoticeOrderResp;
import com.tianrui.api.resp.businessManage.logisticsManage.PurchaseLogisticsResp;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationDetailResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.logisticsManage.AccessRecordMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
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
	private PurchaseApplicationMapper purchaseApplicationMapper;
	@Autowired
	private IPurchaseApplicationDetailService purchaseApplicationDetailService;
	@Autowired
	private PurchaseApplicationDetailMapper purchaseApplicationDetailMapper;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private IVehicleManageService vehicleManageService;
	@Autowired
	private IDriverManageService driverManageService;
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private PoundNoteMapper poundNoteMapper;
	@Autowired
	private AccessRecordMapper accessRecordMapper;
	@Autowired
	private OtherArriveMapper otherArriveMapper;

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
				List<PurchaseArriveResp> listResp = copyBeanList2RespList(list, false);
				listRespSetListApplicationResp(listResp);
				page.setList(listResp);
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
			if(validDriverAndVehicle(save, result, pa)){
				//TODO 采礦點
				
				PropertyUtils.copyProperties(pa, save);
				pa.setId(UUIDUtil.getId());
				setNoticeBody(save, pa);
				pa.setType(Constant.ZERO_STRING);
				GetCodeReq codeReq = new GetCodeReq();
				codeReq.setCode("DH");
				codeReq.setCodeType(true);
				codeReq.setUserid(save.getCurrId());
				pa.setCode(systemCodeService.getCode(codeReq).getData().toString());
				pa.setAuditstatus(Constant.ONE_STRING);
				pa.setStatus(Constant.ZERO_STRING);
				pa.setState(Constant.ONE_STRING);
				pa.setSource(Constant.ZERO_STRING);
				pa.setMakerid(save.getCurrId());
				pa.setMakebillname(systemUserService.getUser(save.getCurrId()).getName());
				pa.setCreator(save.getCurrId());
				pa.setCreatetime(System.currentTimeMillis());
				if(purchaseArriveMapper.insertSelective(pa) > 0 
						&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
					if(StringUtils.isNotBlank(pa.getBilldetailid())){
						PurchaseApplicationDetailResp detailResp = purchaseApplicationDetailService.findOne(pa.getBilldetailid());
						PurchaseApplicationDetail detail = new PurchaseApplicationDetail();
						detail.setId(detailResp.getId());
						detail.setMargin(detailResp.getMargin() - pa.getArrivalamount());
						detail.setPretendingtake(detailResp.getPretendingtake() + pa.getArrivalamount());
						if(purchaseApplicationDetailMapper.updateByPrimaryKeySelective(detail) > 0){
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						}else{
							result.setErrorCode(ErrorCode.OPERATE_ERROR);
						}
					}
					purchaseArriveMapper.emptyForceOutFactoryByVehicle(save.getVehicleid());
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}

	private void setNoticeBody(PurchaseArriveSave save, PurchaseArrive pa) throws Exception {
		VehicleManageResp vehicle = vehicleManageService.findOne(save.getVehicleid());
		if(vehicle != null){
			pa.setVehicleno(vehicle.getVehicleno());
			pa.setVehiclerfid(vehicle.getRfid());
		}
		DriverManageResp driver = driverManageService.findOne(save.getDriverid());
		if(driver != null){
			pa.setDrivername(driver.getName());
			pa.setDriveridentityno(driver.getIdentityno());
		}

	}

	private boolean validDriverAndVehicle(PurchaseArriveSave save, Result result, PurchaseArrive pa) throws Exception {
		boolean flag = true;
		VehicleManageResp vehicle = vehicleManageService.findOne(save.getVehicleid());
        if(vehicle != null){
            pa.setVehicleid(save.getVehicleid());
            List<PurchaseArrive> listVehicle = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
            if(listVehicle != null && listVehicle.size() > 0){
                result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
                result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle.get(0).getCode()+"，如有疑问请与销售处联系！");
                flag = false;
            }
            SalesArrive sa = new SalesArrive();
            sa.setVehicleid(save.getVehicleid());
            List<SalesArrive> listVehicle1 = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
            if(listVehicle1 != null && listVehicle1.size() > 0){
                result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
                result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle1.get(0).getCode()+"，如有疑问请与销售处联系！");
                flag = false;
            }
            //验证其他业务中的通知单
            OtherArrive oa = new OtherArrive();
            oa.setVehicleid(save.getVehicleid());
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
            //ic卡信息
            if(StringUtils.isNotBlank(save.getIcardno())){
                Card card = cardMapper.selectByCardno(save.getIcardno());
                if(card!=null){
                    //ic卡是否占用
                    SalesArrive sales = salesArriveMapper.checkICUse(card.getId());
                    PurchaseArrive purchase = purchaseArriveMapper.checkICUse(card.getId());
                    oa.setVehicleid(null);
                    oa.setIcardid(card.getId());
                    List<OtherArrive> listIcard2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
                    if(sales == null && purchase == null && listIcard2.size()==0) {
                        save.setIcardid(card.getId());
                    }else{
                        result.setErrorCode(ErrorCode.CARD_IN_USE);
                        flag = false;
                    }
                }else{
                    result.setErrorCode(ErrorCode.CARD_NOT_EXIST);
                    flag = false;
                }
            }
        } else {
            result.setErrorCode(ErrorCode.VEHICLE_NOT_EXIST);
            flag = false;
        }
		return flag;
	}

	@Transactional
	@Override
	public Result returnAdd(PurchaseArriveSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			if(StringUtils.isNotBlank(save.getPoundnoteid())){
				PurchaseArrive pa = new PurchaseArrive();
				if(validDriverAndVehicle(save, result, pa)){
					PoundNote poundNote = poundNoteMapper.selectByPrimaryKey(save.getPoundnoteid());
					if(poundNote != null){
						PropertyUtils.copyProperties(pa, save);
						pa.setId(UUIDUtil.getId());
						pa.setType(Constant.ONE_STRING);
						pa.setBillid(poundNote.getBillid());
						pa.setBillcode(poundNote.getBillcode());
						pa.setBilldetailid(poundNote.getBilldetailid());
						pa.setPoundnotecode(poundNote.getCode());
						setNoticeBody(save, pa);
						pa.setCode(getCode("EH", save.getCurrId(), true));
						pa.setAuditstatus(Constant.ONE_STRING);
						pa.setStatus(Constant.ZERO_STRING);
						pa.setState(Constant.ONE_STRING);
						pa.setSource(Constant.ZERO_STRING);
						pa.setMakerid(save.getCurrId());
						pa.setMakebillname(systemUserService.getUser(save.getCurrId()).getName());
						pa.setMakebilltime(System.currentTimeMillis());
						pa.setCreator(save.getCurrId());
						pa.setCreatetime(System.currentTimeMillis());
						purchaseArriveMapper.insertSelective(pa);
						updateCode("EH", save.getCurrId());
						purchaseArriveMapper.emptyForceOutFactoryByVehicle(save.getVehicleid());
						result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					}else{
						result.setErrorCode(ErrorCode.POUNDNOTE_NOT_EXIST);
					}
				}
			}
		}
		return result;
	}

	@Override
	public Result update(PurchaseArriveSave update) throws Exception {
		Result result = Result.getParamErrorResult();
		if(update != null && StringUtils.isNotBlank(update.getId())){
			PurchaseArrive purchaseArrive = purchaseArriveMapper.selectByPrimaryKey(update.getId());
			if(purchaseArrive != null){
				PurchaseArrive pa = new PurchaseArrive();
				pa.setId(update.getId());
				if(validDriverAndVehicle(update, result, pa)){
					PropertyUtils.copyProperties(pa, update);
					setNoticeBody(update, pa);
					pa.setModifier(update.getCurrId());
					pa.setModifytime(System.currentTimeMillis());
					if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) == 1){
						if(StringUtils.isNotBlank(pa.getBilldetailid())){
							PurchaseApplicationDetailResp detailResp = purchaseApplicationDetailService.findOne(pa.getBilldetailid());
							PurchaseApplicationDetail detail = new PurchaseApplicationDetail();
							detail.setId(detailResp.getId());
							detail.setMargin(detailResp.getMargin() + (purchaseArrive.getArrivalamount() - pa.getArrivalamount()));
							detail.setPretendingtake(detailResp.getPretendingtake() - (purchaseArrive.getArrivalamount() - pa.getArrivalamount()));
							if(purchaseApplicationDetailMapper.updateByPrimaryKeySelective(detail) > 0){
								result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
							}else{
								result.setErrorCode(ErrorCode.OPERATE_ERROR);
							}
						}
					}else{
						result.setErrorCode(ErrorCode.OPERATE_ERROR);
					}
				}
			}else{
				result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
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
			pa.setModifier(update.getCurrId());
			pa.setModifytime(System.currentTimeMillis());
			if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) == 1){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}
		return result;
	}
	
	@Override
	public Result invalid(PurchaseArriveSave update) {
		Result result = Result.getParamErrorResult();
		if(update != null && StringUtils.isNotBlank(update.getId())
				&& StringUtils.isNotBlank(update.getCurrId())){
			PurchaseArrive pa = purchaseArriveMapper.selectByPrimaryKey(update.getId());
			if (pa != null) {
				if (!StringUtils.equals(pa.getStatus(), Constant.THREE_STRING)) {
					if (!StringUtils.equals(pa.getStatus(), Constant.FIVE_STRING)) {
						if (StringUtils.equals(pa.getStatus(), Constant.ZERO_STRING)) {
							PurchaseArrive bean = new PurchaseArrive();
							bean.setId(pa.getId());
							bean.setStatus(Constant.THREE_STRING);
							bean.setModifier(update.getCurrId());
							bean.setModifytime(System.currentTimeMillis());
							callBackBillMargin(pa.getId());
							purchaseArriveMapper.updateByPrimaryKeySelective(bean);
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						} else {
							result.setErrorCode(ErrorCode.NOTICE_NOT_ENTER);
						}
					} else {
						result.setErrorCode(ErrorCode.NOTICE_OUT_FACTORY);
					}
				} else {
					result.setErrorCode(ErrorCode.NOTICE_ON_INVALID);
				}
			} else {
				result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
			}
		}
		return result;
	}
	
	private void callBackBillMargin(String noticeId) {
		PurchaseArrive pa = purchaseArriveMapper.selectByPrimaryKey(noticeId);
		if (pa != null) {
			PurchaseApplicationDetail pad = purchaseApplicationDetailMapper.selectByPrimaryKey(pa.getBilldetailid());
			double noticeNumber = pa.getArrivalamount();
			switch (pa.getStatus()) {
			case "0":
				//未入厂
				pad.setMargin(pad.getMargin() + noticeNumber);
				pad.setPretendingtake(pad.getPretendingtake() - noticeNumber);
				break;
			case "6":
				//入厂
				pad.setMargin(pad.getMargin() + noticeNumber);
				pad.setUnstoragequantity(pad.getUnstoragequantity() - noticeNumber);
				break;
			case "2":
				//二次过磅
				PoundNote pn = poundNoteMapper.selectByNoticeId(noticeId);
				pad.setMargin(pad.getMargin() + pn.getNetweight());
				pad.setStoragequantity(pad.getStoragequantity() - pn.getNetweight());
				break;
			default:
				break;
			}
			purchaseApplicationDetailMapper.updateByPrimaryKeySelective(pad);
		}
	}

	@Override
	public PurchaseArriveResp findOne(String id) throws Exception {
		PurchaseArriveResp resp = null;
		if(StringUtils.isNotBlank(id)){
			resp = copyBean2Resp(purchaseArriveMapper.selectByPrimaryKey(id), true);
		}
		return resp;
	}

	@Override
	public List<PurchaseArriveResp> selectByIds(List<String> ids) throws Exception {
		List<PurchaseArriveResp> list = null;
		if(CollectionUtils.isNotEmpty(ids)){
			list = copyBeanList2RespList(purchaseArriveMapper.selectByIds(ids), false);
			listRespSetListApplicationResp(list);
		}
		return list;
	}

	private void listRespSetListApplicationResp(List<PurchaseArriveResp> list) throws Exception {
		if(CollectionUtils.isNotEmpty(list)){
			List<String> ids = new ArrayList<String>();
			List<String> detailIds = new ArrayList<String>();
			for(PurchaseArriveResp resp : list){
				ids.add(resp.getBillid());
				detailIds.add(resp.getBilldetailid());
				PoundNote pound = poundNoteMapper.selectByNoticeId(resp.getId());
				if(pound!=null){
					PoundNoteResp poundResp = new PoundNoteResp();
					PropertyUtils.copyProperties(poundResp, pound);
					resp.setPoundNoteResp(poundResp);
				}
				//获取出入厂时间
				AccessRecord access = accessRecordMapper.selectByNoticeId(resp.getId());
				if(access!=null){
					resp.setEnterTime(access.getEntertime());
					resp.setOutTime(access.getOuttime());
				}
			}
			List<PurchaseApplicationResp> listApplication = purchaseApplicationService.selectByIds(ids, false);
			if(CollectionUtils.isNotEmpty(listApplication)){
				for(PurchaseArriveResp resp : list){
					for(PurchaseApplicationResp applicationResp : listApplication){
						if(StringUtils.equals(resp.getBillid(), applicationResp.getId())){
							resp.setPurchaseApplicationResp(applicationResp);
						}
					}
				}
			}
			List<PurchaseApplicationDetailResp> listApplicationDetail = purchaseApplicationDetailService.selectByIds(detailIds);
			if(CollectionUtils.isNotEmpty(listApplicationDetail)){
				for(PurchaseArriveResp resp : list){
					for(PurchaseApplicationDetailResp applicationDetailResp : listApplicationDetail){
						if(StringUtils.equals(resp.getBilldetailid(), applicationDetailResp.getId())){
							resp.setPurchaseApplicationDetailResp(applicationDetailResp);
						}
					}
				}
			}
		}
	}

	private List<PurchaseArriveResp> copyBeanList2RespList(List<PurchaseArrive> list, boolean setApplication) throws Exception {
		List<PurchaseArriveResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<PurchaseArriveResp>();
			for(PurchaseArrive mater : list){
				listResp.add(copyBean2Resp(mater, setApplication));
			}
		}
		return listResp;
	}

	private PurchaseArriveResp copyBean2Resp(PurchaseArrive bean, boolean setApplication) throws Exception {
		PurchaseArriveResp resp = null;
		if(bean != null){
			resp = new PurchaseArriveResp();
			PropertyUtils.copyProperties(resp, bean);
			if(setApplication){
				if(StringUtils.isNotBlank(bean.getBillid())){
					resp.setPurchaseApplicationResp(purchaseApplicationService.findOne(bean.getBillid()));
				}
				if(StringUtils.isNotBlank(bean.getBilldetailid())){
					resp.setPurchaseApplicationDetailResp(purchaseApplicationDetailService.findOne(bean.getBilldetailid()));
				}
				if(StringUtils.equals(resp.getType(), "0")){
					//获取磅单信息
					PoundNote pound = poundNoteMapper.selectByNoticeId(resp.getId());
					if(pound!=null){
						PoundNoteResp poundResp = new PoundNoteResp();
						PropertyUtils.copyProperties(poundResp, pound);
						resp.setPoundNoteResp(poundResp);
					}
				}else{
					//获取磅单信息
					PoundNote pound = poundNoteMapper.selectByPrimaryKey(resp.getPoundnoteid());
					if(pound!=null){
						PoundNoteResp poundResp = new PoundNoteResp();
						PropertyUtils.copyProperties(poundResp, pound);
						resp.setPoundNoteResp(poundResp);
					}
				}
			}
		}
		return resp;
	}

	@Override
	public Result delete(PurchaseArriveQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null
				&& StringUtils.isNotBlank(query.getId())){
			PurchaseArrive pa = new PurchaseArrive();
			pa.setId(query.getId());
			pa.setState("0");
			pa.setModifier(query.getCurrid());
			pa.setModifytime(System.currentTimeMillis());
			if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}
		return result;
	}

	@Override
	public PaginationVO<AppNoticeOrderResp> appToPage(AppNoticeOrderReq req) {
		PaginationVO<AppNoticeOrderResp> page = null;
		if(req != null){
			page = new PaginationVO<AppNoticeOrderResp>();
			long count = purchaseArriveMapper.findAppNoticePageCount(req);
			if(count > 0){
				req.setStart((req.getPageNo() - 1) * req.getPageSize());
				req.setLimit(req.getPageSize());
				List<AppNoticeOrderResp> list = purchaseArriveMapper.findAppNoticePage(req);
				page.setList(list);
			}
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
			page.setTotal(count);
		}
		return page;
	}

	@Override
	public Result appToDetail(AppNoticeOrderReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getId())){
			AppNoticeOrderResp resp = new AppNoticeOrderResp();
			PurchaseArrive pa = purchaseArriveMapper.selectByPrimaryKey(req.getId());
			if(pa != null){
				resp.setId(pa.getId());
				resp.setCode(pa.getCode());
				resp.setNoticetime(DateUtil.parse(pa.getMakebilltime(), "yyyy-MM-dd HH:mm:ss"));
				resp.setVehicleno(pa.getVehicleno());
				resp.setNumber(pa.getArrivalamount());
				resp.setSource(pa.getSource());
				resp.setStatus(pa.getStatus());
				PurchaseApplication application = purchaseApplicationMapper.selectByPrimaryKey(pa.getBillid());
				if(application != null){
					resp.setBillCode(application.getCode());
					resp.setBilltime(DateUtil.parse(application.getBilltime(), "yyyy-MM-dd HH:mm:ss"));
				}
				PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(pa.getBilldetailid());
				if(applicationDetail != null){
					resp.setMaterialName(applicationDetail.getMaterielname());
				}
				resp.setOrgName(Constant.ORG_NAME);
				result.setData(resp);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
			}
		}
		return result;
	}

	@Override
	public Result appInfoFactoryVehicleAndMaterial(AppOrderReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getUserId())){
			result.setData(purchaseArriveMapper.appInfoFactoryVehicleAndMaterial(req));
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	@Override
	public Result appToAddNotice(AppOrderSaveReq req) throws Exception {
		Result result = Result.getParamErrorResult();
		if(req != null
				&& StringUtils.isNotBlank(req.getUserId())
				&& StringUtils.isNotBlank(req.getBillId())
				&& StringUtils.isNotBlank(req.getBillDetailId())
				&& StringUtils.isNotBlank(req.getVehicleId())
				&& StringUtils.isNotBlank(req.getDriverId())
				&& StringUtils.isNotBlank(req.getNumber())){
			PurchaseArriveSave save = new PurchaseArriveSave();
			save.setVehicleid(req.getVehicleId());
			save.setDriverid(req.getDriverId());
			PurchaseArrive bean = new PurchaseArrive();
			if(validDriverAndVehicle(save, result, bean)){
				bean.setId(UUIDUtil.getId());
				bean.setBillid(req.getBillId());
				bean.setBilldetailid(req.getBillDetailId());
				bean.setVehicleid(req.getVehicleId());
				bean.setDriverid(req.getDriverId());
				if(StringUtils.isNotBlank(req.getNumber())){
					bean.setArrivalamount(Double.parseDouble(req.getNumber()));
				}
				setNoticeBody(save, bean);
				PurchaseApplication application = purchaseApplicationMapper.selectByPrimaryKey(req.getBillId());
				if(application != null){
					bean.setBillcode(application.getCode());
					bean.setType("0");
					GetCodeReq codeReq = new GetCodeReq();
					codeReq.setCode("DH");
					codeReq.setCodeType(true);
					codeReq.setUserid(req.getUserId());
					bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
					bean.setAuditstatus("1");
					bean.setStatus("0");
					bean.setState("1");
					bean.setSource("2");
					bean.setMakerid(req.getUserId());
					bean.setMakebillname(systemUserService.getUser(req.getUserId()).getName());
					bean.setMakebilltime(System.currentTimeMillis());
					bean.setCreator(req.getUserId());
					bean.setCreatetime(System.currentTimeMillis());
					if(purchaseArriveMapper.insertSelective(bean) > 0 
							&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
						if(StringUtils.isNotBlank(bean.getBilldetailid())){
							PurchaseApplicationDetailResp detailResp = purchaseApplicationDetailService.findOne(bean.getBilldetailid());
							PurchaseApplicationDetail detail = new PurchaseApplicationDetail();
							detail.setId(detailResp.getId());
							detail.setMargin(detailResp.getMargin() - bean.getArrivalamount());
							detail.setPretendingtake(detailResp.getPretendingtake() + bean.getArrivalamount());
							if(purchaseApplicationDetailMapper.updateByPrimaryKeySelective(detail) > 0){
								result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
							}else{
								result.setErrorCode(ErrorCode.OPERATE_ERROR);
							}
						}
						purchaseArriveMapper.emptyForceOutFactoryByVehicle(save.getVehicleid());
					}else{
						result.setErrorCode(ErrorCode.OPERATE_ERROR);
					}
				}
			}
		}
		return result;
	}

	@Override
	public Result appInvalid(AppNoticeOrderReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getId())
				&& StringUtils.isNotBlank(req.getUserId())){
			PurchaseArrive bean = new PurchaseArrive();
			bean.setId(req.getId());
			bean.setStatus("3");
			bean.setModifier(req.getUserId());
			bean.setModifytime(System.currentTimeMillis());
			if(purchaseArriveMapper.updateByPrimaryKeySelective(bean) == 1){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public PaginationVO<PurchaseLogisticsResp> logisticsPage(PurchaseLogisticsQuery query) {
		PaginationVO<PurchaseLogisticsResp> page = null;
		if(query != null){
			page = new PaginationVO<PurchaseLogisticsResp>();
			long count = purchaseArriveMapper.selectLogisticsPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<PurchaseLogisticsResp> list = purchaseArriveMapper.selectLogisticsPage(query);
				page.setList(list);
			}
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
			page.setTotal(count);
		}
		return page;
	}

	@Transactional
    @Override
    public Result outfactory(PurchaseArriveSave update) throws Exception {
        Result result = Result.getParamErrorResult();
        if (update != null && StringUtils.isNotBlank(update.getId())) {
            PurchaseArrive bean = purchaseArriveMapper.selectByPrimaryKey(update.getId());
            if (bean != null) {
                PurchaseArrive pa = new PurchaseArrive();
                pa.setId(bean.getId());
                pa.setStatus(Constant.FIVE_STRING);
                pa.setForceOutFactory(Constant.ONE_NUMBER);
                pa.setForceOutFactoryPerson(update.getCurrId());
                pa.setForceOutFactoryTime(System.currentTimeMillis());
                pa.setAbnormalperson(update.getCurrId());
                pa.setAbnormalpersonname(systemUserService.getUser(update.getCurrId()).getName());
                pa.setAbnormaltime(System.currentTimeMillis());
                pa.setModifier(update.getCurrId());
                pa.setModifytime(System.currentTimeMillis());
                purchaseArriveMapper.updateByPrimaryKeySelective(pa);
                AccessRecord accessRecord = accessRecordMapper.selectByNoticeId(pa.getId());
                AccessRecord ar = new AccessRecord();
                ar.setId(accessRecord.getId());
                ar.setOuttime(System.currentTimeMillis());
                ar.setAccesstype(Constant.TWO_STRING);
                ar.setModifier(update.getCurrId());
                ar.setModifytime(System.currentTimeMillis());
                accessRecordMapper.updateByPrimaryKeySelective(ar);
                result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
            } else {
                result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
            }
        }
        return result;
    }
	
	private String getCode(String code, String userId, boolean flag) throws Exception {
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode(code);
		codeReq.setCodeType(flag);
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

}
